package edu.jhuapl.nino.exporter.control;

import edu.jhuapl.nino.model.NinoState;

public interface NinoExporter {

	void export(NinoState ninoState);
}
