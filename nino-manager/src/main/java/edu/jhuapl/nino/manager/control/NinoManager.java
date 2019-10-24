package edu.jhuapl.nino.manager.control;

import edu.jhuapl.nino.exporter.control.NinoExporter;
import edu.jhuapl.nino.generator.control.NinoGeneratorController;
import edu.jhuapl.nino.model.NinoState;
import edu.jhuapl.nino.ui.view.NinoUiManager;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@Log
public class NinoManager {

	@Autowired
	private NinoUiManager ninoUiManager;

	@Autowired
	private NinoExporter[] ninoExporters;

	@Autowired
	private NinoGeneratorController ninoGeneratorController;

	private NinoState ninoState;


	public static void main(String[] args) {
		NinoManager ninoManager = new NinoManager();
		boolean stillProcessing = ninoManager.getNinoUiManager() == null;
		while (stillProcessing) {
			try {
				if (ninoManager.getNinoUiManager().getState() != null) {
					ninoManager.processNinoState();
				} else {
					Thread.sleep(10000l);
				}
			} catch (InterruptedException e) {
				log.warning("The following InterruptedException encountered while trying to get the NinoState from the NinoUiManager: " + e);
			}
		}
		ninoManager.generate();
		System.exit(0);
	}

	public void processNinoState() {
		if (!ninoUiManager.getState().isProcessing()) {
			this.ninoState = ninoUiManager.getState();
		}
	}

	public void generate(){
		this.ninoGeneratorController.generate(this.ninoState);
	}
}
