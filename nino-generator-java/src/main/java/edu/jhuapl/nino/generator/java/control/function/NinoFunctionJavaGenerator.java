package edu.jhuapl.nino.generator.java.control.function;

import edu.jhuapl.nino.generator.control.interfaces.function.comms.NinoFunctionInputCommsGenerator;
import edu.jhuapl.nino.generator.control.interfaces.function.comms.NinoFunctionOutputCommsGenerator;
import edu.jhuapl.nino.generator.java.control.CSVReaderJP_Updated;
import edu.jhuapl.nino.model.NinoFunction;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class NinoFunctionJavaGenerator {
	
	@Autowired
	private NinoFunctionInputCommsGenerator ninoFunctionInputCommsGenerator;
	
	@Autowired
	private NinoFunctionOutputCommsGenerator ninoFunctionOutputCommsGenerator;

	public List<Path> generateCode(NinoFunction ninoFunction){
		List<Path> generatedFiles = new ArrayList<>();
		generatedFiles.addAll(this.generateInputCommsCode(ninoFunction));
		generatedFiles.addAll(this.generateOutputCommsCode(ninoFunction));
		return generatedFiles;
	}

	private List<Path> generateInputCommsCode(NinoFunction ninoFunction) {
		List<Path> generatedFiles = new ArrayList<>();
		switch (ninoFunction.getInputComms()){
			case File:
				generatedFiles.addAll(generateInputFileCode(ninoFunction));
				break;
		}
		return generatedFiles;
	}

	private List<Path> generateInputFileCode(NinoFunction ninoFunction){
		List<Path> generatedFiles = new ArrayList<>();
		switch (ninoFunction.getInputDataType()){ //You should stub out the other DataType cases
			case CSV:
				generatedFiles.addAll(generateInputCSVFileCode(ninoFunction));
				break;
		}
		return generatedFiles;
	}

	private List<Path> generateInputCSVFileCode(NinoFunction ninoFunction){
		List<Path> generatedFiles = new ArrayList<>();
		// a. This is where you will initially put your own code to read in arbitrary CSV files
		// b. After that works then you will write JavaPoet code to generate this same code

		//replace with calling CSVFileReaderJP
		CSVReaderJP_Updated.main(null);

			//run write to & pass to directory (research if makes full path or not)--> not a directory in this project/class path
				//Path p1 = Paths.get(URI.create("/ricemh1"));

		return generatedFiles;
	}

	private List<Path> generateOutputCommsCode(NinoFunction ninoFunction) {
		List<Path> generatedFiles = new ArrayList<>();

		return generatedFiles;
	}
}
