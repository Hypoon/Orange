

import java.awt.Color;
import javax.swing.JFrame;

public class NeoWindow extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	NeoEditor ed;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		NeoWindow w = new NeoWindow();
		NeoElement el1 = new NeoElement(Color.red);
		NeoElement el2 = new NeoElement(Color.green);
		NeoElement el3 = new NeoElement(Color.blue);
		NeoElement el4 = new NeoElement(Color.magenta);
		NeoElement el5 = new NeoElement(Color.cyan);

		w.ed.add(el1);
		w.validate();
		w.ed.add(el2);
		w.validate();
		el1.add(el3);
		w.validate();
		el1.add(el4);
		w.validate();
		el3.add(el5);
		w.validate();
	}
	
	public NeoWindow() {
		super("Editor");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ed = new NeoEditor();
		this.setContentPane(ed);
		this.pack();
		this.setVisible(true);
	}

}
