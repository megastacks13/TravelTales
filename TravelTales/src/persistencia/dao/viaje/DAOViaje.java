package persistencia.dao.viaje;

import modelo.viaje.TViaje;

public interface DAOViaje{
	
	public boolean startConnection();
	public boolean crearViaje(TViaje viaje);
	public boolean comprobarDatos(TViaje viaje);
}
