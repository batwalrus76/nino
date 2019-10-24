package edu.jhuapl.nino.generator.control;

import edu.jhuapl.nino.model.NinoState;

import java.nio.file.Path;
import java.util.List;

public interface NinoStateGenerator {

	List<Path> generaterCode(NinoState ninoState);
}
