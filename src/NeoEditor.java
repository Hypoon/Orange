

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class NeoEditor extends JPanel {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public NeoEditor() {
		super();
		this.setPreferredSize(new Dimension(800, 600));
		this.setBackground(Color.darkGray);
		//this.setLayout(new GridLayout(0,1,0,20));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		this.setVisible(true);
	}

}
