

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class NeoElement extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Color fill;
	private Color border;
			
	public NeoElement() {
		this(Color.black);
	}
	
	public NeoElement(Color c) {
		super();
		border = c;
		fill = c.darker();
		//this.setLayout(new GridLayout(0,1,0,20));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		this.setVisible(true);
		this.setBackground(fill);
		System.out.println(this.getMaximumSize());
	}	
		
    protected void paintComponent(Graphics g) {
        Graphics scratchGraphics = g.create();
		scratchGraphics.setColor(border);
		scratchGraphics.fillRoundRect(0, 0, this.getWidth()-1,this.getHeight()-1, 20, 20);
		scratchGraphics.drawRoundRect(0, 0, this.getWidth()-1,this.getHeight()-1, 20, 20);
		scratchGraphics.setColor(fill);
		scratchGraphics.fillRoundRect(5, 5, this.getWidth()-11,this.getHeight()-11, 15, 15);
		scratchGraphics.drawRoundRect(5, 5, this.getWidth()-11,this.getHeight()-11, 15, 15);
        scratchGraphics.dispose();
    }
}
