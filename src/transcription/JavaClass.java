package transcription;

import java.util.ArrayList;

public class JavaClass extends JavaIdentifiable {
	private JavaClass superClass;
	private ArrayList<JavaMethod> methods;
	private ArrayList<JavaClass> typeVariables;

	public JavaClass(String name) {
		super("class", name);
		methods = new ArrayList<JavaMethod>();
	}
		
	public JavaClass getSuperClass() {
		return superClass;
	}

	public void setSuperClass(JavaClass superClass) {
		this.superClass = superClass;
	}
	
	public ArrayList<JavaMethod> getMethods() {
		return methods;
	}
	
	public void add(JavaMethod method) {
		methods.add(method);
	}
	
	public void add(int position, JavaMethod method) {
		methods.add(position, method);
	}
	
	public void remove(JavaMethod method) {
		methods.remove(method);
	}
	
	public ArrayList<JavaClass> getTypeVariables() {
		return typeVariables;
	}
	
	public void addTypeVariable(JavaClass tv) {
		typeVariables.add(tv);
	}
	
	public void add(int position, JavaClass tv) {
		typeVariables.add(position, tv);
	}
	
	public void remove(JavaClass tv) {
		typeVariables.remove(tv);
	}
	
	public String getIdentifier() {
		String str = super.getName();
		if(typeVariables.size()>=1)
		{
			str = str + "<";
			str = str + typeVariables.get(0);
			for (int i = 1; i<typeVariables.size(); i++)
			{
				str = str + "," + typeVariables.get(i).getIdentifier();
			}
			str = str + ">";
		}
		return str;
	}

	public String toString() {
		String str = super.toString() + " ";
		if(superClass!=null) {
			str = str + "extends " + superClass.getName() + " ";
		}
		str = str + "{\n";
		for(JavaMethod m : methods) {
			str = str + m + "\n";
		}
		str = str + "}\n";
		return str;
	}
}
