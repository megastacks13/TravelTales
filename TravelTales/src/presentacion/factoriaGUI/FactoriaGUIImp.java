package presentacion.factoriaGUI;

import presentacion.AnadirViajeFuturo;
import presentacion.IGUI;
import presentacion.controlador.Evento;

public class FactoriaGUIImp extends FactoriaGUI {

	@Override
	public IGUI generarGUI(int evento) {
		switch (evento) {
		case Evento.ANADIR_VIAJE_FUTURO:
			return new AnadirViajeFuturo();
			
		default:
			return null;
		}
	}

}
