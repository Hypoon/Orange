package utranscription;

import java.util.ArrayList;

public class Language {
	private String name;
	private ArrayList<CodeElement> codeElements;
	
	public Language(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
}
