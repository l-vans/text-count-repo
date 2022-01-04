package com.lukeweb.textcount.textcountapp;

public class Text {
	//Attributes 
	private String count;
	private String averageLength;
	private String finalFreqs;
	//No Args Constructor
	public Text() {

	}

	//Args Constructor
	public Text(String count, String averageLength, String finalFreqs) {
		super();
		this.count = count;
		this.averageLength = averageLength;
		this.finalFreqs = finalFreqs;
	}
	
	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getAverageLength() {
		return averageLength;
	}

	public void setAverageLength(String averageLength) {
		this.averageLength = averageLength;
	}

	public String getFinalFreqs() {
		return finalFreqs;
	}

	public void setFinalFreqs(String finalFreqs) {
		this.finalFreqs = finalFreqs;
	}

}
