package edu.jhuapl.nino.model.input;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;

@Data
public class CredentialSystemConfiguration {

	private HashMap<String, String> apiKeyMap;
	private HashMap<String, String> UsernamePasswordMap;
	private ArrayList<String> externalFileRefs;
	private HashMap<String, String> environmentVariables;
	private HashMap<String, String> templateVariables;

}
