package edu.jhuapl.nino.generator.control.interfaces.function.comms;

import edu.jhuapl.nino.model.enums.Comms;

import java.nio.file.Path;
import java.util.List;

public interface NinoFunctionCommsGenerator {

	List<Path> generateCode(Comms comms);
}
