import javax.swing.JOptionPane;

import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;

public class Main {
	
	public static void main(String[] args) {
		try {
			//Inicio.runUI();
			Controlador.getInstancia().accion(Evento.GUI_INICIO, null);
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ha sucedido un error inesperado: " + e.getMessage(), 
					"ERROR INESPERADO", JOptionPane.ERROR_MESSAGE);
		}
	}
}
