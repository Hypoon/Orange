package transcription;

import java.util.ArrayList;

public abstract class JavaIdentifiable extends JavaStatement {
	private JavaAccessModifier amod;
	private ArrayList<JavaModifier> mods;
	private String type;
	private String name;
	
	public JavaIdentifiable(String type, String name) {
		this.type = type;
		this.name = name;
		mods = new ArrayList<JavaModifier>();
	}
	
	public JavaAccessModifier getAmod() {
		return amod;
	}

	public void setAmod(JavaAccessModifier amod) {
		this.amod = amod;
	}
	
	public void addMod(JavaModifier mod) {
		mods.add(mod);
	}
	
	public void addMod(int position, JavaModifier mod) {
		mods.add(position, mod);
	}
	
	public void removeMod(JavaModifier mod) {
		mods.remove(mod);
	}

	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		String str = "";
		if(amod!=null)
			str = str + amod.toString() + " ";
		for(JavaModifier mod : mods) {
			str = str + mod.toString() + " ";
		}
		str = str + type + " " + name;
		return str;
	}
}
