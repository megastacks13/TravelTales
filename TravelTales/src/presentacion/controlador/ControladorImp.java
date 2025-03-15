package presentacion.controlador;

import utils.Pair;
import presentacion.IGUI;
import presentacion.command.Command;
import presentacion.command.CommandFactory;
import presentacion.factoriaGUI.FactoriaGUI;

public class ControladorImp extends Controlador {

	@Override
	public void accion(int evento, Object transfer) {
		Command command = CommandFactory.getInstance().getCommand(evento);
		IGUI gui;
		if (command == null) {
			//Inicio.runUI();			
			gui = FactoriaGUI.getInstancia().generarGUI(evento); // cuando el comando es null es que quiere crear una GUI inicial
			//gui.actualizar(evento, transfer);
		} else {
			Pair<Integer, Object> res = command.execute(transfer);
			gui = FactoriaGUI.getInstancia().generarGUI(res.getKey());
			gui.actualizar(res.getKey(), res.getValue());
			
		}
	}
}
