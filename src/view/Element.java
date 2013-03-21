package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.RoundRectangle2D;

public abstract class Element {
	private Color fill;
	private Color border;
	private String name = "";
	protected RoundRectangle2D rr;
	protected Rectangle labelBox;
	protected Rectangle closeBox;
	private Editor editor;
	private ComplexElement parent;
	protected boolean drawName;
	public static final int defaultWidth = 160;
	private int width;
		
	public Element(String name) {
		this(name, Color.black);
	}
	
	public Element(String name, Color c) {
		this.name = name;
		border = c;
		fill = c.darker();
		if(fill.equals(border)) {
			Color iborder = new Color(255-border.getRed(),255-border.getGreen(),255-border.getBlue());
			fill = new Color(255-iborder.darker().getRed(),255-iborder.darker().getGreen(),255-iborder.darker().getBlue());
		}
		rr = new RoundRectangle2D.Double(0, 0, 1, 1, 20, 20);
		labelBox = new Rectangle(0, 0, 1, 1);
		closeBox = new Rectangle(0, 0, 1, 1);
		editor = null;
		drawName = true;
	}
	
	public Color getFill() {
		return fill;
	}
	
	public Color getBorder() {
		return border;
	}
			
	public abstract void paint(Graphics2D g, int x, int y, int width);
	
	public abstract int getHeight();
	
	public int getWidth() {
		return Math.max(width, Element.defaultWidth);
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setName(String str) {
		name = str;
		editor.repaint();
	}
	
	public String getName() {
		return name;
	}
	
	public boolean contains(Point p) {
		return rr.contains(p);
	}
	
	public void handleClick(Point p, int clicks) {
		if (labelBox.contains(p) && clicks == 2) {
			editor.rename(labelBox, this);
		}
		if (editor != null && closeBox.contains(p)) {
			Editor tempeditor = editor;
			if(parent == null) {
				editor.remove(this);
			} else {
				parent.remove(this);
			}
			tempeditor.repaint();
		}
	}
	
	public Editor getEditor() {
		return editor;
	}

	public void setEditor(Editor editor) {
		this.editor = editor;
	}
	
	public Point getLocation() {
		return rr.getBounds().getLocation();
	}
	
	public void setLocation(Point p) {
		rr.setRoundRect(p.x, p.y, rr.getWidth(), getHeight(), 20, 20);
	}

	public ComplexElement getParent() {
		return parent;
	}
	
	public void setParent(ComplexElement parent) {
		this.parent = parent;
	}
	
	public void enableDrawName() {
		drawName = true;
	}
	
	public void disableDrawName() {
		drawName = false;
	}
		
	public abstract Element clone();

	public void handlePress(Point p, Editor ed) {
		ed.liftEl(this,p);
		if(parent == null) {
			if(editor != null) {
				editor.remove(this);}
		} else {
			parent.remove(this);
		}
	}
}
