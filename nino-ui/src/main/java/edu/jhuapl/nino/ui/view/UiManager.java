package edu.jhuapl.nino.ui.view;

import edu.jhuapl.nino.model.NinoState;
import lombok.Data;

@Data
public abstract class UiManager {

	private NinoState state;

	public UiManager() {
		state = new NinoState();
	}
}
