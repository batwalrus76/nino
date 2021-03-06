package edu.jhuapl.nino.model;

import edu.jhuapl.nino.model.enums.Comms;
import edu.jhuapl.nino.model.enums.ConversionType;
import edu.jhuapl.nino.model.signature.NinoExternalSignature;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class NinoFunction {

	private Comms inputComms;
	private Comms outputComms;
	private String processingCode;
	private int minInstance;
	private int maxInstance;
	private int cpuMinInstance;
	private int cpuMaxInstance;
	private String libraryName;
	private String libraryVersion;
	private String namespace;
	private String function;
	private NinoExternalSignature externalSignature;
	private ConversionType inputArgumentConversionType;
	private String inputArgumentMappingCode;

}
