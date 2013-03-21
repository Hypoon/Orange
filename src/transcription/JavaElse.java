package transcription;

public class JavaElse extends JavaStatement {

	private JavaStatement payLoad;
	
	public JavaStatement getPayLoad() {
		return payLoad;
	}

	public void setPayLoad(JavaStatement payLoad) {
		this.payLoad = payLoad;
	}
	
	public String toString() {
		return "else " + payLoad;
	}
	
}
