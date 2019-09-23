package edu.jhuapl.nino.model;

import edu.jhuapl.nino.model.credentials.NinoCredentials;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class NinoState {

	private boolean processing;
	private List<NinoFunction> functions;
	private Map ingressNetworking;
	private Map egressNetworking;
	private List<String> requiredAvailableServices;
	private List<String> externalFileReferences;
	private Map environmentVariables;
	private Map templateVariables;
	private NinoCredentials credentials;

	public NinoState() {
		processing = false;
	}
}
