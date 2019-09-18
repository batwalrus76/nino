package edu.jhuapl.nino.model.signature;

import lombok.Data;

@Data
public class NinoMessageQueueNinoExternalSignature extends NinoExternalSignature {

	private String topicName;

}
