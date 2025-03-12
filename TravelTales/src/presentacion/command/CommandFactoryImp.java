package presentacion.command;

public class CommandFactoryImp extends CommandFactory {

	@Override
	public Command getCommand(Integer commandId) {
		Command comando = null;
		comando = new ComandoAnadirViajeFuturo();
		return comando;
	}

}
