package edu.jhuapl.nino.generator.java.control;

import edu.jhuapl.nino.generator.control.interfaces.NinoStateGenerator;
import edu.jhuapl.nino.generator.control.interfaces.external.NinoExternalGenerator;
import edu.jhuapl.nino.generator.control.interfaces.security.NinoSecurityGenerator;
import edu.jhuapl.nino.generator.control.interfaces.variables.NinoVariablesGenerator;
import edu.jhuapl.nino.generator.java.control.function.NinoFunctionJavaGenerator;
import edu.jhuapl.nino.model.NinoFunction;
import edu.jhuapl.nino.model.NinoState;
import edu.jhuapl.nino.model.enums.Comms;
import edu.jhuapl.nino.model.enums.ConversionType;
import edu.jhuapl.nino.model.signature.NinoExternalSignature;
import edu.jhuapl.nino.model.signature.NinoFileExternalSignature;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Data
public class NinoStateJavaGenerator implements NinoStateGenerator {

	@Autowired
	private NinoFunctionJavaGenerator ninoFunctionJavaGenerator;

	// The following three collections of generators may be needed in the future

	@Autowired
	private NinoVariablesGenerator[] ninoVariablesGenerators;

	@Autowired
	private NinoSecurityGenerator[] ninoSecurityGenerators;

	@Autowired
	private NinoExternalGenerator[] ninoExternalGenerators;

	public List<Path> generateCode(NinoState ninoState){
		List<Path> generatedFiles = new ArrayList<>();
		for(NinoFunction ninoFunction : ninoState.getFunctions()){
			List<Path> functionGeneratedFiles = this.ninoFunctionJavaGenerator.generateCode(ninoFunction);
			generatedFiles.addAll(functionGeneratedFiles);
		}
		return generatedFiles;
	}

	public static void main(String[] args) {
		NinoStateJavaGenerator ninoStateJavaGenerator = new NinoStateJavaGenerator();
		NinoState ninoState = new NinoState();
		NinoFunction ninoFunction = new NinoFunction();
		ninoFunction.setInputComms(Comms.File);
		ninoFunction.setOutputComms(Comms.File);
		ninoFunction.setMinInstance(1);
		ninoFunction.setMaxInstance(1);
		ninoFunction.setCpuMinInstance(1);
		ninoFunction.setCpuMaxInstance(1);
		ninoFunction.setInputLibraryName("commons-csv");
		ninoFunction.setInputLibraryNamespace("org.apache.commons");
		ninoFunction.setInputLibraryVersion("1.5");
		ninoFunction.setInputNamespace("org.apache.commons.csv");
		ninoFunction.setInputFunction("csvFileReadWrite");
		ninoFunction.setOutputLibraryName("commons-csv");
		ninoFunction.setOutputLibraryNamespace("org.apache.commons");
		ninoFunction.setOutputLibraryVersion("1.5");
		ninoFunction.setOutputNamespace("org.apache.commons.csv");
		ninoFunction.setOutputFunction("writeFile");
		NinoFileExternalSignature ninoFileExternalSignature = new NinoFileExternalSignature();
		ninoFileExternalSignature.setVariable("outputFile");
		ninoFileExternalSignature.setFileName("output.csv");
		ninoFunction.setExternalSignature(ninoFileExternalSignature);
		ninoFunction.setInputArgumentConversionType(ConversionType.Naive);
		List<NinoFunction> ninoFunctions = new ArrayList<>();
		ninoFunctions.add(ninoFunction);
		ninoState.setFunctions(ninoFunctions);
		List<Path> generatedFiles = ninoStateJavaGenerator.generateCode(ninoState);
		System.exit(0);
	}
}
