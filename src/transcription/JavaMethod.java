package transcription;

import java.util.ArrayList;

public class JavaMethod extends JavaIdentifiable {

	private JavaClass returnType = null;
	private ArrayList<JavaIdentifiable> parameters;
	private ArrayList<JavaStatement> contents;

	public JavaMethod(String name) {
		super("void", name);
		parameters = new ArrayList<JavaIdentifiable>();
		contents = new ArrayList<JavaStatement>();
	}

	public JavaClass getReturnType() {
		return returnType;
	}

	public void setReturnType(JavaClass returnType) {
		this.returnType = returnType;
		if(returnType == null)
			super.setType("void");
		else
			super.setType(returnType.getName());
	}

	public ArrayList<JavaStatement> getContents() {
		return contents;
	}

	public void add(JavaStatement statement) {
		contents.add(statement);
	}
	
	public void add(int position, JavaStatement statement) {
		contents.add(position, statement);
	}
	
	public void remove(JavaStatement statement) {
		contents.remove(statement);
	}
	
	public String toString() {
		String str = super.toString();
		str = str + "(";
		for(int i=0; i<parameters.size(); i++) {
			JavaIdentifiable jt = parameters.get(i);
			str = str + jt.getType() + " " + jt.getName();
			if(i!=parameters.size()-1)
				str = str + ", ";
		}
		str = str + ") {\n";
		for(JavaStatement s : contents) {
			str = str + s + "\n";
		}
		str = str + "}\n";
		return str;
	}
}
