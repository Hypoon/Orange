package utranscription;

public class Structure extends CodeElement {
	private String beginBlock;
	private String endBlock;
	
	public Structure(String type) {
		super(type);
	}

	public String getBeginBlock() {
		return beginBlock;
	}

	public void setBeginBlock(String beginBlock) {
		this.beginBlock = beginBlock;
	}

	public String getEndBlock() {
		return endBlock;
	}

	public void setEndBlock(String endBlock) {
		this.endBlock = endBlock;
	}
}
