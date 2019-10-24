package edu.jhuapl.nino.generator.control;

import edu.jhuapl.nino.generator.control.interfaces.NinoStateGenerator;
import edu.jhuapl.nino.generator.control.interfaces.external.NinoExternalGenerator;
import edu.jhuapl.nino.generator.control.interfaces.networking.NinoNetworkingGenerator;
import edu.jhuapl.nino.generator.control.interfaces.security.NinoSecurityGenerator;
import edu.jhuapl.nino.generator.control.interfaces.variables.NinoVariablesGenerator;
import edu.jhuapl.nino.model.NinoState;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class NinoGeneratorController {

	@Autowired
	private NinoStateGenerator ninoStateGenerator;

	@Autowired
	private NinoNetworkingGenerator[] ninoNetworkingGenerators;

	@Autowired
	private NinoExternalGenerator[] ninoExternalGenerators;

	@Autowired
	private NinoSecurityGenerator[] ninoSecurityGenerators;

	@Autowired
	private NinoVariablesGenerator[] ninoVariablesGenerators;


	public void generate(NinoState ninoState){
		/**
		 *  TODO Here is where we will call the various autowired generators from above we should be thinking of how to  do this intelligently
 		 */


	}

}
