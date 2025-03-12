package presentacion.factoriaGUI;

import presentacion.AnadirViajeFuturo;
import presentacion.IGUI;
import presentacion.Inicio;
import presentacion.controlador.Evento;

public class FactoriaGUIImp extends FactoriaGUI {

	@Override
	public IGUI generarGUI(int evento) {
		switch (evento) {
		case Evento.GUI_INICIO:
			return new Inicio();
			
		case Evento.GUI_ANADIR_VIAJE_FUTURO:
		case Evento.ANADIR_VIAJE_FUTURO:
			return new AnadirViajeFuturo();
			
		default:
			return null;
		}
	}

}
