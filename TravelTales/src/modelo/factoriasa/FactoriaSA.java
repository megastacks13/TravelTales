package modelo.factoriasa;

import modelo.viaje.SAViaje;

public abstract class FactoriaSA {
	
	private static FactoriaSA instancia;

	public synchronized static FactoriaSA getInstancia() {
		if (instancia == null) 
			instancia = new FactoriaSAImp();
		return instancia;
	}
	
	public abstract SAViaje crearSAViaje();
	
}
