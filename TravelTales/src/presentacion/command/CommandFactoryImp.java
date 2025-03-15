package presentacion.command;

import presentacion.controlador.Evento;

public class CommandFactoryImp extends CommandFactory {

	@Override
	public Command getCommand(Integer commandId) {
		Command comando = null;
		switch (commandId) {
		case Evento.ANADIR_VIAJE_FUTURO:
			comando = new ComandoAnadirViajeFuturo();
			break;
		}
		
		return comando;
	}

}
