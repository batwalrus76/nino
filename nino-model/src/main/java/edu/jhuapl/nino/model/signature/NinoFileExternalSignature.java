package edu.jhuapl.nino.model.signature;

import lombok.Data;

@Data
public class NinoFileExternalSignature extends NinoExternalSignature {

	private String variable;
	private String fileName;
}
