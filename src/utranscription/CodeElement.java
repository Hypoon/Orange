package utranscription;

import java.awt.Color;

public class CodeElement {
	private String type;
	private Color color;
	private int parameters;
	private String parameterSeparater;
	
	public CodeElement(String type) {
		this.type = type;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
	
	public String getType() {
		return type;
	}

	public int getParameters() {
		return parameters;
	}

	public void setParameters(int parameters) {
		this.parameters = parameters;
	}

	public String getParameterSeparater() {
		return parameterSeparater;
	}

	public void setParameterSeparater(String parameterSeparater) {
		this.parameterSeparater = parameterSeparater;
	}

}
