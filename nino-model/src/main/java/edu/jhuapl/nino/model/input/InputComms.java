package edu.jhuapl.nino.model.input;

public enum InputComms {

	REST("REST"),
	MessageQueue ("Message Queue"),
	Events ("Events"),
	DBOneShot ("DB one shot"),
	DBPolling ("DB polling"),
	FileOneShot ("File one shot"),
	FilePolling ("File polling");

	private String commsName;

	InputComms(String text) {
		commsName = text;
	}
}
