package persistencia.dao.viaje;

import java.util.List;

import modelo.viaje.TViaje;

public interface DAOViaje{
	
	public boolean startConnection();
	public boolean crearViaje(TViaje viaje);
	public boolean comprobarDatos(TViaje viaje);
	public TViaje leerPorNombre(String nombre);
	public List<TViaje> leerTodos();
}
