package presentacion.command;

import modelo.factoriasa.FactoriaSA;
import modelo.viaje.SAViaje;
import modelo.viaje.TViaje;
import persistencia.dao.viaje.DAOViaje;
import persistencia.dao.viaje.DAOViajeImp;
import presentacion.controlador.Evento;
import utils.Pair;

public class ComandoAnadirViajeFuturo implements Command {

	@Override
	public Pair<Integer, Object> execute(Object data) {
		TViaje viaje = (TViaje) data;
		Pair<Integer, Object> pair = new Pair<Integer, Object>(null, null);
		
		try {
			//DAOViaje daoViaje = new DAOViajeImp(); // esto se deberia cambiar llamando a factoriaSA y al metodo en el SA			
			//boolean res = daoViaje.crearViaje(viaje); //Cambiado, borradlo si quereis
			
			SAViaje SAViaje = FactoriaSA.getInstancia().crearSAViaje();
			int res = SAViaje.crearViajeFuturo(viaje);
			
			//cuando se implemete TransactionManager sustituir las dos lineas anteriores por estas dos
			//SAViaje SAViaje = FactoriaSA.getInstancia().crearSAViaje();
			//int res = SAViaje.crearViajeFuturo(viaje);
			
			pair.setKey(Evento.ANADIR_VIAJE_FUTURO);
			pair.setValue(res);
		} catch (Exception e) {
			pair.setKey(Integer.parseInt(e.getMessage()));
		}
		return pair;
	}

}
