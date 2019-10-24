package edu.jhuapl.nino.generator.java.control.function;

import edu.jhuapl.nino.generator.control.interfaces.function.comms.NinoFunctionInputCommsGenerator;
import edu.jhuapl.nino.generator.control.interfaces.function.comms.NinoFunctionOutputCommsGenerator;
import edu.jhuapl.nino.model.NinoFunction;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class NinoFunctionJavaGenerator {
	
	@Autowired
	private NinoFunctionInputCommsGenerator ninoFunctionInputCommsGenerator;
	
	@Autowired
	private NinoFunctionOutputCommsGenerator ninoFunctionOutputCommsGenerator;

	public List<Path> generateCode(NinoFunction ninoFunction){
		List<Path> generatedFiles = new ArrayList<>();
		generatedFiles.addAll(this.generateCommsCode(ninoFunction));
		return generatedFiles;
	}

	private List<Path> generateCommsCode(NinoFunction ninoFunction) {
		List<Path> generatedFiles = new ArrayList<>();

		return generatedFiles;
	}
}
