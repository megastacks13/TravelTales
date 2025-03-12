package presentacion.command;

import utils.Pair;

public interface Command {
	public Pair<Integer, Object> execute(Object data);
}
