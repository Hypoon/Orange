package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Editor extends JPanel {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Element> contents;
	private List<Element> toolbox;
	private Element focusedEl;
	private Element liftedEl;
	//private Point liftedElLocation;
	//private Point liftPoint;
	private Point mousePoint;
	public static final int margin = 20;
	private int tabLevel;
	
	public Editor() {
		super();
		this.contents = new ArrayList<Element>();
		this.toolbox = new ArrayList<Element>();
		this.setPreferredSize(new Dimension(800, 600));
		this.setLayout(null);
		this.setVisible(true);
		MouseAdapter ma = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Point p = e.getPoint();
				for(Element el : contents) {
					if(el.contains(p)) {
						el.handleClick(p,e.getClickCount());
						return;
					}
				}
			}
			public void mousePressed(MouseEvent e) {
				stealFocus();
				Point p = e.getPoint();
				for(Element el : toolbox) {
					if(el.contains(p)) {
						el.clone().handlePress(p, (Editor) e.getSource());
						return;
					}
				}
				for(Element el : contents) {
					if(el.contains(p)) {
						el.handlePress(p, (Editor) e.getSource());
						return;
					}
				}
			}
			public void mouseReleased(MouseEvent e) {
				if(isLiftValid()) {
					Point p = e.getPoint();
					if(p.x > Element.defaultWidth+2*Editor.margin && ((Component) e.getSource()).contains(p)) {
						for(Element el : contents) {
							if(el instanceof ComplexElement && el.contains(p)) {
								if(((ComplexElement) el).handleDrop(p,liftedEl))
									return;
							}
						}
						for(int index = 0; index < contents.size(); index++) {
							if(p.y <= contents.get(index).getLocation().y) {
								if(index == 0) {
									Element transitioningEl = liftedEl;
									dropEl();
									add(transitioningEl,0);
									repaint();
									return;
								}
								else if (p.y >= contents.get(index-1).getLocation().y + contents.get(index-1).getHeight()) {
									Element transitioningEl = liftedEl;
									dropEl();
									add(transitioningEl,index);
									repaint();
									return;
								}
							}
						}
						if(contents.size()>0) {
							if(p.y >= contents.get(contents.size()-1).getLocation().y + contents.get(contents.size()-1).getHeight()) {
								Element transitioningEl = liftedEl;
								dropEl();
								add(transitioningEl);
								repaint();
								return;
							}
						} else {
							Element transitioningEl = liftedEl;
							dropEl();
							add(transitioningEl);
							repaint();
							return;
						}
					}
					dropEl();
					repaint();
					return;
				}
			}
			public void mouseDragged(MouseEvent e) {
				Point p = e.getPoint();
				if(isLiftValid()) {
					mousePoint = p;
					repaint();
				}
			}
		};
		this.addMouseListener(ma);
		this.addMouseMotionListener(ma);
		tabLevel = 0;
	}
	
	public void paint(Graphics g1) {
		Graphics2D g = (Graphics2D) g1.create();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setBackground(Color.darkGray);
		g.clearRect(0, 0, getWidth(), getHeight());
		
		int offset=Editor.margin;
		for(Element el : toolbox) {
			el.paint(g, Editor.margin, offset, Element.defaultWidth);
			offset+=el.getHeight()+Editor.margin;
		}
		
		g.setColor(Color.gray);
		g.setStroke(new BasicStroke(4f));
		g.drawLine(Element.defaultWidth+2*Editor.margin, 0, Element.defaultWidth+2*Editor.margin, getHeight());
		
		offset=Editor.margin;
		for(Element el : contents) {
			el.paint(g, Element.defaultWidth+3*Editor.margin, offset, this.getWidth()-(Element.defaultWidth+4*Editor.margin));
			offset+=el.getHeight()+Editor.margin;
		}
		
		if(isLiftValid()) {
			if(mousePoint.x > Element.defaultWidth+2*Editor.margin && this.contains(mousePoint)) {
				float[] dash = {8f,12f};
				g.setStroke(new BasicStroke(4f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1f, dash, 15f));
				g.setColor(Color.lightGray);
				boolean lineFlag=false;
				for(Element el : contents) {
					if(el instanceof ComplexElement && el.contains(mousePoint)) {
						Rectangle2D l = ((ComplexElement) el).handleLine(mousePoint);
						if(l!=null) {
							g.drawLine((int) l.getX(), (int) l.getY(), (int) (l.getX()+l.getWidth()), (int) (l.getY()+l.getHeight()));
							lineFlag=true;
							break;
						}
					}
				}
				if(!lineFlag){
					for(int index = 0; index < contents.size(); index++) {
						if(mousePoint.y <= contents.get(index).getLocation().y) {
							if(index == 0) {
								g.drawLine(Element.defaultWidth+3*Editor.margin, contents.get(0).getLocation().y/2, this.getWidth()-Editor.margin, contents.get(0).getLocation().y/2);
								lineFlag=true;
								break;
							}
							else if (mousePoint.y >= contents.get(index-1).getLocation().y + contents.get(index-1).getHeight()) {
								g.drawLine(Element.defaultWidth+3*Editor.margin, (contents.get(index-1).getLocation().y + contents.get(index-1).getHeight() + contents.get(index).getLocation().y)/2, this.getWidth()-Editor.margin, (contents.get(index-1).getLocation().y + contents.get(index-1).getHeight() + contents.get(index).getLocation().y)/2);
								lineFlag=true;
								break;
							}
						}
					}
				}
				if(!lineFlag){
					if(contents.size()>0) {
						if(mousePoint.y >= contents.get(contents.size()-1).getLocation().y + contents.get(contents.size()-1).getHeight()) {
							g.drawLine(Element.defaultWidth+3*Editor.margin, contents.get(contents.size()-1).getLocation().y + contents.get(contents.size()-1).getHeight() + Editor.margin/2, this.getWidth()-Editor.margin, contents.get(contents.size()-1).getLocation().y + contents.get(contents.size()-1).getHeight() + Editor.margin/2);
							lineFlag=true;
						}
					} else {
						g.drawLine(Element.defaultWidth+3*Editor.margin, Editor.margin/2, this.getWidth()-Editor.margin, Editor.margin/2);
						lineFlag=true;
					}
				}
			}
			//liftedEl.paint(g, liftedElLocation.x + mousePoint.x - liftPoint.x , liftedElLocation.y + mousePoint.y - liftPoint.y, Element.defaultWidth);
			liftedEl.paint(g, mousePoint.x - liftedEl.getWidth()/2 , mousePoint.y - liftedEl.getHeight()/2, Element.defaultWidth);
		}
		
		g.dispose();
		
		super.paintChildren(g1);
	}
	
	public void add(Element el) {
		add(el,-1);
	}
	
	public void add(Element el,int pos) {
		if(pos == -1) {
			contents.add(el);
		}
		else {
			contents.add(pos, el);
		}
		el.setEditor(this);
	}
	
	public void remove(Element el) {
		contents.remove(el);
		el.setEditor(null);
	}
	
	public void rename(Rectangle r, Element el) {
		JTextField tf = new TextBox(el.getName(),el.getBorder());
		focusedEl = el;
		Rectangle bounds = new Rectangle(r.x,r.y,Math.max(r.width+2,150),r.height+1);
		tf.setBounds(bounds);
		el.disableDrawName();
		tf.setVisible(true);
		this.add(tf);
		tf.requestFocusInWindow();
		tf.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				JTextField tf = (JTextField) e.getComponent();
				focusedEl.enableDrawName();
				tf.setVisible(false);
				tf.getParent().remove(tf);
				tf.setText(null);
			}
		});
		tf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField tf = (JTextField) e.getSource();
				if(tf.getText().length()>0)
					((Editor) tf.getParent()).focusedEl.setName(tf.getText());
				focusedEl.enableDrawName();
				tf.setVisible(false);
			}
		});
	}
	
	public void stealFocus() {
		for(Component c : this.getComponents()){
			focusedEl.enableDrawName();
			c.setVisible(false);
		}
	}
	
	public void setToolBox(Element[] tools) {
		for(Element el : tools) {
			toolbox.add(el);
		}
	}

	public void liftEl(Element el, Point p) {
		liftedEl = el;
		//liftedElLocation = el.getLocation();
		//liftPoint = p;
		mousePoint = p;
	}
	
	public void dropEl() {
		liftedEl = null;
		//liftedElLocation = null;
		//liftPoint = null;
		mousePoint = null;
	}
	
	private boolean isLiftValid() {
		//return (liftedEl != null && liftedElLocation != null && liftPoint != null && mousePoint != null);
		return (liftedEl != null && mousePoint != null);
	}
	
	public String toString() {
		String str = "";
		for(Element el : contents){
			str = str+el.toString();
		}
		return str;
	}
	
	public void incrementTabLevel() {
		tabLevel++;
	}
	public void decrementTabLevel() {
		tabLevel--;
	}
	public int getTabLevel() {
		return tabLevel;
	}
}
