package model;

public class TypingSample {
	private String sampleText;
	
	
	public String getSampleText() {
		return sampleText;
	}


	public void setSampleText(String sampleText) {
		this.sampleText = sampleText;
	}


	public int getCharacterCount() {
		return this.sampleText.length();
	}


	public TypingSample(String sampleText) {
		super();
		this.sampleText = sampleText;
	}
	
	
}
