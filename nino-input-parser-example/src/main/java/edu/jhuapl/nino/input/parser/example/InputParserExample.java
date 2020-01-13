package edu.jhuapl.nino.input.parser.example;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import edu.jhuapl.nino.model.NinoFunction;
import edu.jhuapl.nino.model.NinoState;
import edu.jhuapl.nino.model.credentials.NinoCredentials;
import edu.jhuapl.nino.model.credentials.NinoUsernamePasswordCredentials;
import edu.jhuapl.nino.model.enums.Comms;
import edu.jhuapl.nino.model.enums.ConversionType;
import edu.jhuapl.nino.model.signature.NinoExternalSignature;

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

	public static void main(String[] args) {
		if (args.length > 0) {
			String inputFilename = args[0];
			try {
				JsonReader jsonReader = new JsonReader(new FileReader(inputFilename));
				jsonReader.setLenient(true);
				Gson gson = new Gson();
				NinoState ninoState = generateNinoState(jsonReader); //gson.fromJson(jsonReader, NinoState.class);
				System.out.println("NinoState: " + ninoState);
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

		while (jsonReader.hasNext()) {

			String name = jsonReader.nextName();

			if (name.equals(FUNCTIONS_KEY)) {
				ninoState.setFunctions(generateNinoFunctionArray(jsonReader));
			}
			else if (name.equals(INGRESS_NETWORKING_KEY)) {
				ninoState.setIngressNetworking(generateMap(jsonReader));
			}
			else if (name.equals(NINO_CREDENTIALS_KEY)) {
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
		while (jsonReader.hasNext()) {
			String name = jsonReader.nextName();
			if (name.equals(NINO_USERNAME_CREDENTIALS_KEY)) {
				if (ninoCredentials == null) {
					ninoCredentials = new NinoUsernamePasswordCredentials();
				}
				((NinoUsernamePasswordCredentials) ninoCredentials).setUsername(jsonReader.nextString());
			}
			else if (name.equals(NINO_PASSWORD_CREDENTIALS_KEY)) {
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
		jsonReader.beginArray(); //[
		while (jsonReader.hasNext()) {
			generateNinoFunction(jsonReader);
		}
		jsonReader.endArray(); //]
		return ninoFunctions;
	}

	public static NinoFunction generateNinoFunction(JsonReader jsonReader) throws IOException {
		NinoFunction ninoFunction = new NinoFunction();
		jsonReader.beginObject();
		// TODO: need to handle null values in input, right now not failing but might want to output warnings
		while (jsonReader.hasNext()) {
			String key = jsonReader.nextName();
			if (key.equals("input_comms")) {
				Comms inputComms = Comms.File;
				try {
					String inputCommsStr = jsonReader.nextString();
					inputComms = Comms.valueOf(inputCommsStr);
				} catch (Exception ex) {
					System.out.println("Error at input_comms");
				} finally {
					ninoFunction.setInputComms(inputComms);
				}
			}
			else if (key.equals("output_comms")) {
				Comms outputComms = Comms.File;
				try {
					String outputCommsStr = jsonReader.nextString();
					outputComms = Comms.valueOf(outputCommsStr);
				} catch (Exception ex) {
					System.out.println("Error at output_comms");
				} finally {
					ninoFunction.setOutputComms(outputComms);
				}
			}
			else if (key.equals("processing_code")) {
				String processingCode = null;
				try {
					// Shiva: This is how you handle null values when there is a key, replicate elsewhere
					if(jsonReader.peek().equals(JsonToken.NULL)){
						jsonReader.skipValue();
					} else {
						processingCode = jsonReader.nextString();
					}
				} catch (Exception ex) {
					System.out.println("Error at processing code");
				} finally {
					ninoFunction.setProcessingCode(processingCode);
				}
			}
			else if (key.equals("min_instance")) {
				int minInstance = 1;
				try {
					minInstance = jsonReader.nextInt();
				} catch (Exception ex) {
					System.out.println("Error at max_instance");
				} finally {
					ninoFunction.setMinInstance(minInstance);
				}
			}
			else if (key.equals("max_instance")) {
				int maxInstance = 10;
				try {
					maxInstance = jsonReader.nextInt();
				} catch (Exception ex) {
					System.out.println("Error at min_instance");
				} finally {
					ninoFunction.setMaxInstance(maxInstance);
				}
			}
			else if (key.equals("cpu_min")) {
				int cpuMin = 1;
				try {
					cpuMin = jsonReader.nextInt();
				} catch (Exception ex) {
					System.out.println("Error at cpu_min");
				} finally {
					ninoFunction.setCpuMinInstance(cpuMin);
				}
			}
			else if (key.equals("cpu_max")) {
				int cpuMax = 1;
				try {
					cpuMax = jsonReader.nextInt();
				} catch (Exception ex) {
					System.out.println("Error at cpu_max");
				} finally {
					ninoFunction.setCpuMaxInstance(cpuMax);
				}
			}
			else if (key.equals("input_library_version")) {
				String inputLibraryVersion = "1.7";
				try {
					inputLibraryVersion = jsonReader.nextString();
				} catch (Exception ex) {
					System.out.println("Error at input_library_version");
				} finally {
					ninoFunction.setInputLibraryVersion(inputLibraryVersion);
				}
			}
			else if (key.equals("input_library_name")) {
				String inputLibraryName = "commons-csv";
				try {
					inputLibraryName = jsonReader.nextString();
				} catch (Exception ex) {
					System.out.println("Error at input_library_name");
				} finally {
					ninoFunction.setInputLibraryName(inputLibraryName);
				}
			}
			else if (key.equals("input_namespace")) {
				String inputNamespace = "org.apache.commons.csv";
				try {
					inputNamespace = jsonReader.nextString();
				} catch (Exception ex) {
					System.out.println("Error at input_namespace");
				} finally {
					ninoFunction.setInputNamespace(inputNamespace);
				}
			}
			else if (key.equals("input_function")) {
				String inputFunction = "readFile";
				try {
					inputFunction = jsonReader.nextString();
				} catch (Exception ex) {
					System.out.println("Error at input_function");
				} finally {
					ninoFunction.setInputfunction(inputFunction);
				}
			}
			else if (key.equals("output_library_version")) {
				String outputLibraryVersion = "1.7";
				try {
					outputLibraryVersion = jsonReader.nextString();
				} catch (Exception ex) {
					System.out.println("Error at output_library_version");
				} finally {
					ninoFunction.setOutputLibraryVersion(outputLibraryVersion);
				}
			}
			else if (key.equals("output_library_name")) {
				String outputLibraryName = "commons-csv";
				try {
					outputLibraryName = jsonReader.nextString();
				} catch (Exception ex) {
					System.out.println("Error at output_library_name");
				} finally {
					ninoFunction.setOutputLibraryName(outputLibraryName);
				}
			}
			else if (key.equals("output_namespace")) {
				String outputNamespace = "org.apache.commons.csv";
				try {
					outputNamespace = jsonReader.nextString();
				} catch (Exception ex) {
					System.out.println("Error at output_namespace");
				} finally {
					ninoFunction.setOutputNamespace(outputNamespace);
				}
			}
			else if (key.equals("output_function")) {
				String outputFunction = "writeFile";
				try {
					outputFunction = jsonReader.nextString();
				} catch (Exception ex) {
					System.out.println("Error at output_function");
				} finally {
					ninoFunction.setOutputfunction(outputFunction);
				}
			}
			else if (key.equals("output_signature")) {
				jsonReader.beginArray();
				jsonReader.beginObject();
				String outputSignatureKey = jsonReader.nextName();
				if (outputSignatureKey.equals("outputFile")) {
					String outputFile = "output.csv";
					try {
						outputFile = jsonReader.nextString();
					} catch (Exception ex) {
						System.out.println("Error at output_signature:outputFile");
					} finally {
						ninoFunction.setOutput(outputFile);
					}
					jsonReader.endObject();
					jsonReader.endArray();
				}
			}
			else if (key.equals("external_signature")) {
				jsonReader.beginArray();
				jsonReader.beginObject();
				String externalSignatureKey = jsonReader.nextName();
				if (externalSignatureKey.equals("inputFile")) {
					String inputFile = "input.csv";
					try {
						inputFile = jsonReader.nextString();
					} catch (Exception ex) {
						System.out.println("Error at external_signature:inputFile");
					} finally {
						ninoFunction.setInput(inputFile);
					}
					jsonReader.endObject();
					jsonReader.endArray();
				}
			}
			else if (key.equals("input_to_argument_conversion_type")) {
				// Shiva: I think this fixes your problems with setting the value from a string input to the enum
				// Also, like above, you need to look at how I used jsonReader.peek() and skipValue() to get passed a null value
				ConversionType inputToArgumentConversionType = null;//Don't know what to set this to
				try {
					if(jsonReader.peek().equals(JsonToken.NULL)){
						jsonReader.skipValue();
					} else {
						String inputToArgumentConversionTypeString = jsonReader.nextString();
						inputToArgumentConversionType = ConversionType.valueOf(inputToArgumentConversionTypeString);
					}
				} catch (Exception ex) {
					System.out.println("Error at input_to_argument_conversion_type");
				} finally {
					ninoFunction.setInputArgumentConversionType(inputToArgumentConversionType);
				}
			}
			else if (key.equals("input_to_argument_mapping_code")) {
				ConversionType inputToArgumentMappingCode = null;
				try {
					if(jsonReader.peek().equals(JsonToken.NULL)){
						jsonReader.skipValue();
					} else {
						String inputToArgumentMappingCodeString = jsonReader.nextString();
						inputToArgumentMappingCode = ConversionType.valueOf(inputToArgumentMappingCodeString);
					}
				} catch (Exception ex) {
					System.out.println("Error at input_to_argument_mapping_code");
				} finally {
//					ninoFunction.getInputArgumentMappingCode(inputToArgumentMappingCode);//Why is this an error?
				}
			}
		}
		jsonReader.endObject();
		return ninoFunction;
	}

	public static Map<String, String> generateMap(JsonReader jsonReader) throws IOException {
		Map<String, String> resultMap = new HashMap<String, String>();
		jsonReader.beginObject();
		while (jsonReader.hasNext()) {
			String key = jsonReader.nextName();
			resultMap.put(key, jsonReader.nextString());
		}
		jsonReader.endObject();
		return resultMap;
	}
}