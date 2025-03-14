package persistencia.dao.factoriadao;

import persistencia.dao.viaje.DAOViaje;

public abstract class FactoriaDAO {
	
	private static FactoriaDAO instancia;

	public synchronized static FactoriaDAO getInstancia() {
		if (instancia == null) 
			instancia = new FactoriaDAOImp();
		return instancia;
	}
	
	public abstract DAOViaje crearDAOViaje();
	
}
