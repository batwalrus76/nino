package edu.jhuapl.nino.model.input;

import lombok.Data;
import lombok.NonNull;

@Data
public class NINoFunctionConfiguration {

	@NonNull private InputComms inputComms;
	@NonNull private OutputComms outputComms;
	private String processingCode;
	private int minInstance;
	private int maxInstance;
	private int cpuMin;
	private int cpuMax;
	@NonNull private String library;
	@NonNull private String namespace;
	@NonNull private String function;
	@NonNull private ExternalSignature externalSignature;
	@NonNull private InputToArgumentConversionType inputToArgumentConversionType;
	private String inputToArgumentMappingCode;

}
