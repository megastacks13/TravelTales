package presentacion.factoriaGUI;

import presentacion.IGUI;

public abstract class FactoriaGUI {
	
	public static FactoriaGUI instancia;
	
	public static FactoriaGUI getInstancia() {
		if (instancia == null)
			instancia = new FactoriaGUIImp();
		return instancia;
	}
	
	public abstract IGUI generarGUI(int evento);

}
