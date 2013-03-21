package transcription;

public class JavaAccessModifier {
	private static final int privatem = 4;
	private static final int protectedm = 5;
	private static final int publicm = 6;
	public static final JavaAccessModifier Private_AMod = new JavaAccessModifier(privatem);
	public static final JavaAccessModifier Protected_AMod = new JavaAccessModifier(protectedm);
	public static final JavaAccessModifier Public_AMod = new JavaAccessModifier(publicm);

	private int mod;
	
	public JavaAccessModifier(int mod) {
		this.mod = mod;
	}

	public String toString() {
		switch(mod) {
		case privatem:
			return "private";
		case protectedm:
			return "protected";
		case publicm:
			return "public";
		default:
			return "";
		}
	}
}
