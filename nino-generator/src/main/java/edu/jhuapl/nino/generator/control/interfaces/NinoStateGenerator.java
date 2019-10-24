package edu.jhuapl.nino.generator.control.interfaces;

import edu.jhuapl.nino.model.NinoState;

import java.nio.file.Path;
import java.util.List;

public interface NinoStateGenerator {

	List<Path> generateCode(NinoState ninoState);
}
