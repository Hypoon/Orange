package view;

import java.awt.Color;

import javax.swing.JFrame;

import transcription.JavaAccessModifier;
import transcription.JavaClass;
import transcription.JavaMethod;
import transcription.JavaModifier;
import utranscription.LanguageLoader;

public class Window {
	
	Editor ed;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Window w = new Window();
		ComplexElement el1 = new ComplexElement("Red", Color.red);
		ComplexElement el2 = new ComplexElement("Green", Color.green);
		ComplexElement el3 = new ComplexElement("Blue", Color.blue);
		ComplexElement el4 = new ComplexElement("Magenta", Color.magenta);
		ComplexElement el5 = new ComplexElement("Cyan", Color.cyan);

		w.ed.add(el1);
		w.ed.add(el2);
		el1.add(el3);
		el1.add(el4);
		el3.add(el5);
		
		Element[] toolbox = {new ComplexElement("Pink"		, Color.pink	),
							 new ComplexElement("Red"		, Color.red		),
							 new ComplexElement("Orange"	, Color.orange	),
							 new ComplexElement("Yellow"	, Color.yellow	),
							 new ComplexElement("Green"	, Color.green	),
							 new ComplexElement("Cyan"		, Color.cyan	),
							 new ComplexElement("Blue"		, Color.blue	),
							 new ComplexElement("Magenta"	, Color.magenta	),
							 new SimpleElement("Black", Color.black),
							 new SimpleElement("White", Color.white)};
		w.ed.setToolBox(toolbox);
		
		JavaClass jc = new JavaClass("testClass");
		jc.addMod(JavaModifier.Static_Mod);
		jc.addMod(JavaModifier.Final_Mod);
		jc.setAmod(JavaAccessModifier.Public_AMod);
		JavaMethod jm = new JavaMethod("testMethod");
		jc.add(jm);
		System.out.println(jc);
	}
	
	public Window() {
		JFrame frame = new JFrame("Editor");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ed = new Editor();
		frame.add(ed);
		frame.pack();
		frame.setVisible(true);
	}

}
