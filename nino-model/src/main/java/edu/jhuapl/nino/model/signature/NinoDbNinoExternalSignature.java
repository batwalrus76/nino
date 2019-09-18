package edu.jhuapl.nino.model.signature;

import lombok.Data;

@Data
public class NinoDbNinoExternalSignature extends NinoExternalSignature {

	private String db;
	private String table;

}
