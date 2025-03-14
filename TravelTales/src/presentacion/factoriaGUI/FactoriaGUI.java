package presentacion.factoriaGUI;

import presentacion.IGUI;

public abstract class FactoriaGUI {
	
	public static FactoriaGUI instance;
	
	public static FactoriaGUI getInstance() {
		if (instance == null)
			instance = new FactoriaGUIImp();
		return instance;
	}
	
	public abstract IGUI generarGUI(int evento);

}
