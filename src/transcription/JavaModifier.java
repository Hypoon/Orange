package transcription;

public class JavaModifier {
	private static final int abstractm = 0;
	private static final int staticm = 1;
	private static final int finalm = 2;
	private static final int strictfpm = 3;
	public static final JavaModifier Abstract_Mod = new JavaModifier(abstractm);
	public static final JavaModifier Static_Mod = new JavaModifier(staticm);
	public static final JavaModifier Final_Mod = new JavaModifier(finalm);
	public static final JavaModifier Strictfp_Mod = new JavaModifier(strictfpm);

	private int mod;
	
	public JavaModifier(int mod){
		this.mod = mod;
	}
	
	public String toString() {
		switch(mod) {
		case abstractm:
			return "abstract";
		case staticm:
			return "static";
		case finalm:
			return "final";
		case strictfpm:
			return "strictfp";
		default:
			return "";
		}
	}
}
