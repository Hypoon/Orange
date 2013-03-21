package transcription;

import java.util.ArrayList;

public class JavaBlock extends JavaStatement {
	
	private ArrayList<JavaStatement> contents;
	
	public JavaBlock() {
		contents = new ArrayList<JavaStatement>();
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
		String str = "{\n";
		for(JavaStatement s : contents) {
			str = str + s + "\n";
		}
		str = str + "}\n";
		return str;
	}
}
