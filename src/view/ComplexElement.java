package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class ComplexElement extends Element {
	private List<Element> contents;
		
	public ComplexElement(String name) {
		this(name, Color.black);
	}
	
	public ComplexElement(String name, Color c) {
		super(name,c);
		this.contents = new ArrayList<Element>();
	}
		
	public void add(Element el) {
		add(el, -1);
	}
	
	public void add(Element el, int pos) {
		if(pos == -1) {
			contents.add(el);
		}
		else {
			contents.add(pos,el);
		}
		el.setEditor(getEditor());
		el.setParent(this);
		getEditor().repaint();
	}
	
	public void remove(Element el) {
		contents.remove(el);
		el.setEditor(null);
		el.setParent(null);
	}
	
	public void paint(Graphics2D g, int x, int y, int width) {
		setWidth(width);
		rr.setRoundRect(x, y, getWidth(), getHeight(), 20, 20);
		g.setPaint(getFill());
		g.fill(rr);
		g.setPaint(getBorder());
		g.setStroke(new BasicStroke(4f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		g.draw(rr);
		
		if(getEditor() != null) {
			g.drawLine(x + getWidth() - 20, y+10, x + getWidth() - 10, y+20);
			g.drawLine(x + getWidth() - 10, y+10, x + getWidth() - 20, y+20);
			closeBox.setRect(x + getWidth() - 22, y+8, 14, 14);
		}
		
		g.setFont(new Font("Lucida Grande",Font.BOLD,16));
		FontMetrics metrics = g.getFontMetrics();
		if(drawName) {
			g.drawString(getName(), x+10, y+metrics.getAscent()+10);
		}
		labelBox.setRect(x+10, y+10, metrics.stringWidth(getName()), metrics.getHeight());

		g.setStroke(new BasicStroke(1f));
		/*g.draw(labelBox);
		if(editor != null) {
			g.draw(closeBox);
		}*/
		
		int offset=20 + Editor.margin;
		for(Element el : contents) {
			el.paint(g, x + Editor.margin, y + offset, getWidth()-2*Editor.margin);
			offset += el.getHeight()+Editor.margin;
		}
	}
	
	public void setWidth(int width) {
		super.setWidth(width);
		for (Element el : contents) {
			el.setWidth(width-2*Editor.margin);
		}
	}
	public int getWidth() {
		int tempWidth = Element.defaultWidth;
		for (Element el : contents) {
			tempWidth = Math.max(tempWidth, el.getWidth()+2*Editor.margin);
		}
		return Math.max(super.getWidth(), tempWidth);
	}
	
	public int getHeight() {
		int height = 60;
		for(Element el : contents) {
			height += el.getHeight();
		}
		height += Editor.margin*(contents.size()-1);
		return height;
	}
			
	public void handleClick(Point p, int clicks) {
		for(Element el : contents) {
			if(el.contains(p)) {
				el.handleClick(p, clicks);
				return;
			}
		}
		super.handleClick(p, clicks);
	}
	
	public boolean handleDrop(Point p, Element liftedEl) {
		for(Element el : contents) {
			if(el instanceof ComplexElement && el.contains(p)) {
				return ((ComplexElement) el).handleDrop(p,liftedEl);
			}
		}
		for(int index = 0; index < contents.size(); index++) {
			if(p.y <= contents.get(index).getLocation().y) {
				if(index == 0) {
					Element transitioningEl = liftedEl;
					getEditor().dropEl();
					add(transitioningEl,0);
					return true;
				}
				else if (p.y >= contents.get(index-1).getLocation().y + contents.get(index-1).getHeight()) {
					Element transitioningEl = liftedEl;
					getEditor().dropEl();
					add(transitioningEl,index);
					return true;
				}
			}
		}
		if(contents.size()>0) {
			if(p.y >= contents.get(contents.size()-1).getLocation().y + contents.get(contents.size()-1).getHeight()) {
				Element transitioningEl = liftedEl;
				getEditor().dropEl();
				add(transitioningEl);
				return true;
			}
		} else {
			Element transitioningEl = liftedEl;
			getEditor().dropEl();
			add(transitioningEl);
			return true;
		}
		return false;
	}

	public Rectangle2D handleLine(Point mousePoint) {
		for(Element el : contents) {
			if(el instanceof ComplexElement && el.contains(mousePoint)) {
				Rectangle2D l = ((ComplexElement) el).handleLine(mousePoint);
				if(l!=null)
					return l;
			}
		}
		for(int index = 0; index < contents.size(); index++) {
			if(mousePoint.y <= contents.get(index).getLocation().y) {
				if(index == 0) {
					return new Rectangle2D.Double(getLocation().getX()+Editor.margin,contents.get(0).getLocation().y-Editor.margin/2,rr.getWidth()-2*Editor.margin,0);
				}
				else if (mousePoint.y >= contents.get(index-1).getLocation().y + contents.get(index-1).getHeight()) {
					return new Rectangle2D.Double(getLocation().getX()+Editor.margin, (contents.get(index-1).getLocation().y + contents.get(index-1).getHeight() + contents.get(index).getLocation().y)/2, rr.getWidth()-2*Editor.margin, 0);
				}
			}
		}
		if(contents.size()>0) {
			if(mousePoint.y >= contents.get(contents.size()-1).getLocation().y + contents.get(contents.size()-1).getHeight()) {
				return new Rectangle2D.Double(getLocation().getX()+Editor.margin, contents.get(contents.size()-1).getLocation().y + contents.get(contents.size()-1).getHeight() + Editor.margin/2, rr.getWidth()-2*Editor.margin, 0);
			}
		} else {
			return new Rectangle2D.Double(getLocation().getX()+Editor.margin, getLocation().getY()+getHeight()-Editor.margin/2, rr.getWidth()-2*Editor.margin, 0);
		}
		return null;
	}
	
	public ComplexElement clone() {
		ComplexElement el = new ComplexElement(getName(), this.getBorder());
		el.setLocation(getLocation());
		return el;
	}
	
	public void handlePress(Point p, Editor ed) {
		for(Element el : contents) {
			if(el.contains(p)) {
				el.handlePress(p, ed);
				return;
			}
		}
		super.handlePress(p, ed);
	}
	
	public String toString() {
		String str = "";
		for(int i = 0; i<getEditor().getTabLevel(); i++) {
			str = str.concat("\t");
		}
		str = str.concat(getName() + " {\n");
		getEditor().incrementTabLevel();
		for(Element el : contents){
			str = str.concat(el.toString());
		}
		getEditor().decrementTabLevel();
		for(int i = 0; i<getEditor().getTabLevel(); i++) {
			str = str.concat("\t");
		}
		str = str.concat("}\n");
		return str;
	}

}
