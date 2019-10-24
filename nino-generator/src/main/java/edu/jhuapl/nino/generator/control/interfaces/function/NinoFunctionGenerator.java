package edu.jhuapl.nino.generator.control.interfaces.function;

import edu.jhuapl.nino.model.NinoFunction;

import java.nio.file.Path;
import java.util.List;

public interface NinoFunctionGenerator {

	// TODO might want to have a standard file output strategy, since this might be called multiple times and don't want to overwrite folders/files
	List<Path> generateCode(NinoFunction ninoFunction);

}
