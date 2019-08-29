package edu.jhuapl.nino.model.input;

public enum InputToArgumentConversionType {

	Naive ("Naive"),
	IndexMapping ("IndexMapping"),
	NamingMapping ("NamingMapping"),
	Code ("Code");

	private String name;

	InputToArgumentConversionType(String text) {
		this.name = text;
	}
}
