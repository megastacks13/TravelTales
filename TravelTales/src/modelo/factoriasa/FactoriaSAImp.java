package modelo.factoriasa;

import modelo.viaje.SAViaje;
import modelo.viaje.SAViajeImp;

public class FactoriaSAImp extends FactoriaSA {

	@Override
	public SAViaje crearSAViaje() {
		return new SAViajeImp();
	}

}
