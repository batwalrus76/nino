package edu.jhuapl.nino.model.credentials;

import lombok.Data;

@Data
public class NinoUsernamePasswordCredentials extends NinoCredentials {

	private String username;
	private String password;

}
