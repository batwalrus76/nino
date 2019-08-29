package edu.jhuapl.nino.model.input;

import lombok.Data;
import lombok.NonNull;

import java.util.HashMap;

@Data
public class NINoInputConfigurationFile {

	@NonNull private NINoFunctionConfiguration ninoFunctionConfiguration;
	@NonNull private NetworkingConfiguration ingressNetworking;
	@NonNull private NetworkingConfiguration egressNetworking;
	private HashMap<String, String> requiredServicesForInstallation;
	private HashMap<String, CredentialSystemConfiguration> credentials;

}
