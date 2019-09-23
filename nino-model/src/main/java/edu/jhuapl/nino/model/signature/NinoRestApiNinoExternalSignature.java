package edu.jhuapl.nino.model.signature;

import lombok.Data;

import javax.ws.rs.HttpMethod;

@Data
public class NinoRestApiNinoExternalSignature extends NinoExternalSignature {

	private HttpMethod method;
	private int port;
	private String path;

}
