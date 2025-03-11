package persistencia.dao;

import modelo.TViaje;

public abstract class AbstractDaoViaje {
	
	public abstract boolean startConnection();
	public abstract boolean crearViaje(TViaje viaje);
}
