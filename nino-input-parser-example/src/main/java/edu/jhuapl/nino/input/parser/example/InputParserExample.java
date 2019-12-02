package edu.jhuapl.nino.input.parser.example;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import edu.jhuapl.nino.model.NinoFunction;
import edu.jhuapl.nino.model.NinoState;
import edu.jhuapl.nino.model.credentials.NinoCredentials;
import edu.jhuapl.nino.model.credentials.NinoUsernamePasswordCredentials;
import edu.jhuapl.nino.model.enums.Comms;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputParserExample {

	public static final String FUNCTIONS_KEY = "functions";
	public static final String INGRESS_NETWORKING_KEY = "ingress_networking";
	public static final String NINO_CREDENTIALS_KEY = "credentials";
	public static final String NINO_USERNAME_CREDENTIALS_KEY = "username";
	public static final String NINO_PASSWORD_CREDENTIALS_KEY = "password";

	public static void main(String[] args){
		if(args.length > 0) {
			String inputFilename = args[0];
			try {
				JsonReader jsonReader = new JsonReader(new FileReader(inputFilename));
				Gson gson = new Gson();
				NinoState ninoState = generateNinoState(jsonReader); //gson.fromJson(jsonReader, NinoState.class);
				System.out.println("NinoState: "  + ninoState);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static NinoState generateNinoState(JsonReader jsonReader) throws IOException {
		NinoState ninoState = new NinoState();
		jsonReader.beginObject();

		while(jsonReader.hasNext()){

			String name = jsonReader.nextName();

			if (name.equals(FUNCTIONS_KEY)) {
				ninoState.setFunctions(generateNinoFunctionArray(jsonReader));
			} else if (name.equals(INGRESS_NETWORKING_KEY)) {
				ninoState.setIngressNetworking(generateMap(jsonReader));
			} else if (name.equals(NINO_CREDENTIALS_KEY)) {
				ninoState.setCredentials(generateCredentials(jsonReader));
			}

		}


		jsonReader.endObject();
		jsonReader.close();
		return ninoState;
	}

	private static NinoCredentials generateCredentials(JsonReader jsonReader) throws IOException {
		NinoCredentials ninoCredentials = null;
		jsonReader.beginObject();
		while(jsonReader.hasNext()) {
			String name = jsonReader.nextName();
			if (name.equals(NINO_USERNAME_CREDENTIALS_KEY)) {
				if (ninoCredentials == null) {
					ninoCredentials = new NinoUsernamePasswordCredentials();
				}
				((NinoUsernamePasswordCredentials) ninoCredentials).setUsername(jsonReader.nextString());
			} else if (name.equals(NINO_PASSWORD_CREDENTIALS_KEY)) {
				if (ninoCredentials == null) {
					ninoCredentials = new NinoUsernamePasswordCredentials();
				}
				((NinoUsernamePasswordCredentials) ninoCredentials).setPassword(jsonReader.nextString());
			}
		}
		jsonReader.endObject();
		return ninoCredentials;
	}

	public static List<NinoFunction> generateNinoFunctionArray(JsonReader jsonReader) throws IOException {
		List<NinoFunction> ninoFunctions = new ArrayList<NinoFunction>();
		jsonReader.beginArray();
		while(jsonReader.hasNext()){
			generateNinoFunction(jsonReader);
		}
		jsonReader.endArray();
		return ninoFunctions;
	}

	public static NinoFunction generateNinoFunction(JsonReader jsonReader) throws IOException {
		NinoFunction ninoFunction = new NinoFunction();
		jsonReader.beginObject();
		jsonReader.beginObject(); //{
		while(jsonReader.hasNext()) {
			String key = jsonReader.nextName();
			if (key == "input_comms") {
				Comms inputComms = Comms.File;
				String inputCommsStr = jsonReader.nextString();
				ninoFunction.setInputComms(inputComms);
			} else if (key == "output_comms") {
				Comms outputComms = Comms.File;
				String outputCommsStr = jsonReader.nextString();
				ninoFunction.setOutputComms(outputComms);
			} else if (key == "min_instance") {
				int minInstance = 1;
				int minInstanceInt = jsonReader.nextInt();
				ninoFunction.setMinInstance(minInstance);
			} else if (key == "max_instance") {
				int maxInstance = 10;
				int maxInstanceInt = jsonReader.nextInt();
				ninoFunction.setMaxInstance(maxInstance);
			} else if (key == "cpu_min") {
				int cpuMin = 1;
				int cpuMinInt = jsonReader.nextInt();
				ninoFunction.setCpuMinInstance(cpuMin);
			} else if (key == "cpu_max") {
				int cpuMax = 1;
				int cpuMaxInt = jsonReader.nextInt();
				ninoFunction.setCpuMaxInstance(cpuMax);
			} else if (key == "input_library_version") {
				String inputLibraryVersion = "1.7";
				String inputLibraryVersionString = jsonReader.nextString();
				ninoFunction.setInputLibraryVersion(inputLibraryVersion);
			} else if (key == "input_library_group") {
				String inputLibraryGroup = "org.apache.commons";
				String inputLibraryGroupString = jsonReader.nextString();
				ninoFunction.setInputLibraryNamespace(inputLibraryGroup);
			} else if (key == "input_library_name") {
				String inputLibraryName = "commons-csv";
				String inputLibraryNameString = jsonReader.nextString();
				ninoFunction.setInputLibraryName(inputLibraryName);
			} else if (key == "input_namespace") {
				String inputNamespace = "org.apache.commons.csv";
				String inputNamespaceString = jsonReader.nextString();
				ninoFunction.setInputLibraryNamespace(inputNamespace);
			} else if (key == "input_function") {
				String inputFunction = "readFile";
				String inputFunctionString = jsonReader.nextString();
				ninoFunction.setInputfunction(inputFunction);
			} else if (key == "output_library_version") {
				String outputLibraryVersion = "1.7";
				String outputLibraryVersionString = jsonReader.nextString();
				ninoFunction.setOutputLibraryVersion(outputLibraryVersion);
			} else if (key == "output_library_group") {
				String outputLibraryGroup = "org.apache.commons";
				String outputLibraryGroupString = jsonReader.nextString();
				ninoFunction.setOutputLibraryNamespace(outputLibraryGroup);
			} else if (key == "output_library_name") {
				String outputLibraryName = "commons-csv";
				String outputLibraryNameString = jsonReader.nextString();
				ninoFunction.setOutputLibraryName(outputLibraryName);
			} else if (key == "output_namespace") {
				String outputNamespace = "org.apache.commons.csv";
				String outputNamespaceString = jsonReader.nextString();
				ninoFunction.setOutputNamespace(outputNamespace);
			} else if (key == "output_function") {
				String outputFunction = "writeFile";
				String outputFunctionString = jsonReader.nextString();
				ninoFunction.setOutputfunction(outputFunction);
			}
		}
		jsonReader.endObject();
		return ninoFunction;
	}

	public static Map<String, String> generateMap(JsonReader jsonReader) throws IOException {
		Map<String, String> resultMap = new HashMap<String, String>();
		jsonReader.beginObject();
		while(jsonReader.hasNext()){
			String key = jsonReader.nextName();
			resultMap.put(key, jsonReader.nextString());
		}
		jsonReader.endObject();
		return resultMap;
	}
}
