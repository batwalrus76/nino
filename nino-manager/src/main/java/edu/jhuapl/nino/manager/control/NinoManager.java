package edu.jhuapl.nino.manager.control;

import edu.jhuapl.nino.ui.view.UiManager;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@Log
public class NinoManager {

	@Autowired
	private UiManager uiManager;

	public static void main(String[] args) {
		NinoManager ninoManager = new NinoManager();
		boolean stillProcessing = ninoManager.getUiManager() == null;
		while (stillProcessing) {
			try {
				if (ninoManager.getUiManager().getState() != null) {
					ninoManager.processNinoState();
				} else {
					Thread.sleep(10000l);
				}
			} catch (InterruptedException e) {
				log.warning("The following InterruptedException encountered while trying to get the NinoState from the UiManager: " + e);
			}
		}
	}

	public void processNinoState() {
		if (!uiManager.getState().isProcessing()) {
		}
	}
}
