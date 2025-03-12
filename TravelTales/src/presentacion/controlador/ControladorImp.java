package presentacion.controlador;

import utils.Pair;
import presentacion.AnadirViajeFuturo;
import presentacion.IGUI;
import presentacion.Inicio;
import presentacion.command.Command;
import presentacion.command.CommandFactory;

public class ControladorImp extends Controlador {

	@Override
	public void accion(int evento, Object transfer) {
		Command command = CommandFactory.getInstance().getCommand(evento);
		IGUI gui;
		if (command == null) {
			Inicio.runUI();
			/*
			gui = FactoriaGUI.getInstance().generateGUI(evento);
			gui.actualizar(evento, transfer);
			*/
		} else {
			Pair<Integer, Object> res = command.execute(transfer);
			/*
			gui = FactoriaGUI.getInstance().generateGUI(res.getKey());
			*/
			gui = new AnadirViajeFuturo();
			gui.actualizar(res.getKey(), res.getValue());
			
		}
	}
}
