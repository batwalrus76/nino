package edu.jhuapl.nino.ui.view;

import edu.jhuapl.nino.model.NinoState;
import lombok.Data;

@Data
public abstract class NinoUiManager {

	private NinoState state;

	public NinoUiManager() {
		state = new NinoState();
	}
}
