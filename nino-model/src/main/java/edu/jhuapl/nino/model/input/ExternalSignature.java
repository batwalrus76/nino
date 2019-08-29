package edu.jhuapl.nino.model.input;

public enum ExternalSignature {

	MQTopicName ("MQ topic name"),
	RESTUrlPattern ("REST url pattern"),
	DBQuery ("DB query");

	private String name;

	ExternalSignature(String text) {
		this.name = text;
	}
}
