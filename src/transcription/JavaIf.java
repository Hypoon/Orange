package transcription;

public class JavaIf extends JavaStatement {
	
	private JavaStatement condition;
	private JavaStatement payLoad;

	public JavaStatement getCondition() {
		return condition;
	}

	public void setCondition(JavaStatement condition) {
		this.condition = condition;
	}

	public JavaStatement getPayLoad() {
		return payLoad;
	}

	public void setPayLoad(JavaStatement payLoad) {
		this.payLoad = payLoad;
	}
	
	public String toString() {
		return "if (" + condition + ") " + payLoad;
	}
}
