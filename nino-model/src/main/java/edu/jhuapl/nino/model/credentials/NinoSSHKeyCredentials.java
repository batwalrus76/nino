package edu.jhuapl.nino.model.credentials;

import lombok.Data;

@Data
public class NinoSSHKeyCredentials extends NinoCredentials {

	private String sshKey;

}
