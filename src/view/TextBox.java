package view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;

public class TextBox extends JTextField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color border;

	public TextBox(String str, Color c) {
		super(str);
		border = c;
		this.setOpaque(false);
		this.setForeground(border);
		this.setFont(new Font("Lucida Grande",Font.BOLD,16));
		this.setBorder(null);
	}
	
}
