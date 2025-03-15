package persistencia.dao.factoriadao;

import persistencia.dao.viaje.DAOViaje;
import persistencia.dao.viaje.DAOViajeImp;

public class FactoriaDAOImp extends FactoriaDAO {

	@Override
	public DAOViaje crearDAOViaje() {
		return new DAOViajeImp();
	}

}
