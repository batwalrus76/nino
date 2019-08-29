package edu.jhuapl.nino.model.input;

public enum OutputComms {

	REST("REST"),
	MessageQueue ("Message Queue"),
	Events ("Events"),
	DB ("DB"),
	File ("File");

	private final String commsName;

	OutputComms(String text) {
		commsName = text;
	}
}
