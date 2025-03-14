package presentacion;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;

import javax.swing.JButton;

public class Inicio extends JFrame implements IGUI {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnAnadirViajeFuturo;

	/**
	 * Launch the application.
	 */
	public static void runUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame = new Inicio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	

	/**
	 * Create the frame.
	 */
	public Inicio() {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 541, 392);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnAnadirViajeFuturo());
		
		btnAnadirViajeFuturo.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	try {
		    		Controlador.getInstancia().accion(Evento.GUI_ANADIR_VIAJE_FUTURO, null);
		    		//AnadirViajeFuturo frameViajeFuturo = new AnadirViajeFuturo();
			        //frameViajeFuturo.setVisible(true);
		    	} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Ha sucedido un error inesperado: " + ex.getMessage(), 
							"ERROR INESPERADO", JOptionPane.ERROR_MESSAGE);
		    	}
		        dispose();
		        
		    }
		});
	}
	
	private JButton getBtnAnadirViajeFuturo() {
		if (btnAnadirViajeFuturo == null) {
			btnAnadirViajeFuturo = new JButton("Añadir viaje futuro");
			btnAnadirViajeFuturo.setBounds(224, 159, 167, 32);
		}
		return btnAnadirViajeFuturo;
	}


	@Override
	public void actualizar(int evento, Object datos) {
		// Nada
	}
	
}
