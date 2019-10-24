package edu.jhuapl.nino.generator.java.control;

import edu.jhuapl.nino.generator.control.interfaces.NinoStateGenerator;
import edu.jhuapl.nino.generator.control.interfaces.external.NinoExternalGenerator;
import edu.jhuapl.nino.generator.control.interfaces.security.NinoSecurityGenerator;
import edu.jhuapl.nino.generator.control.interfaces.variables.NinoVariablesGenerator;
import edu.jhuapl.nino.generator.java.control.function.NinoFunctionJavaGenerator;
import edu.jhuapl.nino.model.NinoFunction;
import edu.jhuapl.nino.model.NinoState;
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
}
