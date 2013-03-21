package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

public class SimpleElement extends Element {
	
	public SimpleElement(String name) {
		this(name, Color.black);
	}
	
	public SimpleElement(String name, Color c) {
		super(name,c);
	}

	public int getHeight() {
		return 20;
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
			g.drawLine(x + getWidth() - 20, y+5, x + getWidth() - 10, y+15);
			g.drawLine(x + getWidth() - 10, y+5, x + getWidth() - 20, y+15);
			closeBox.setRect(x + getWidth() - 22, y+8, 14, 14);
		}
		
		g.setFont(new Font("Lucida Grande",Font.BOLD,16));
		FontMetrics metrics = g.getFontMetrics();
		if(drawName) {
			g.drawString(getName(), x+10, y+metrics.getAscent());
		}
		labelBox.setRect(x+10, y, metrics.stringWidth(getName()), metrics.getHeight());

		g.setStroke(new BasicStroke(1f));
		/*g.draw(labelBox);
		if(editor != null) {
			g.draw(closeBox);
		}*/
	}

	
	public SimpleElement clone() {
		SimpleElement el = new SimpleElement(getName(), getBorder());
		el.setLocation(getLocation());
		return el;
	}
	
	public String toString() {
		String str = "";
		for(int i = 0; i<getEditor().getTabLevel(); i++) {
			str = str.concat("\t");
		}
		str = str.concat(getName() + "\n");
		return str;
	}
}
