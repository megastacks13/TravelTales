package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.viaje.TViaje;
import presentacion.controlador.Controlador;
import presentacion.controlador.Evento;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

public class AnadirViajeFuturo extends JFrame implements IGUI {

	private static final long serialVersionUID = 1L;
	private static final String title = "AÑADIR VIAJE FUTURO";
	private JPanel contentPane;
	private JPanel panelFormulario;
	private JLabel lblInfoBasica;
	private JLabel lblNombre;
	private JTextField textFieldNombre;
	private JLabel lblUbicacion;
	private JTextField textFieldUbicacion;
	private JSpinner spinnerNumPersonas;
	private JLabel lblNumeroPersonas;
	private JLabel lblFechaInicio;
	private JFormattedTextField inputFechaInicio;
	private JButton btnAnadir;
	private JLabel lblFechaFin;
	private JFormattedTextField inputFechaFin;
	private DateFormat df = new SimpleDateFormat("dd/mm/yyyy");


	/**
	 * Create the frame.
	 */
	public AnadirViajeFuturo() {
		setTitle("Añadir viaje futuro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 496, 431);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getPanelFormulario());
		setVisible(true);
	}
	private JPanel getPanelFormulario() {
		if (panelFormulario == null) {
			panelFormulario = new JPanel();
			panelFormulario.setBounds(125, 30, 249, 335);
			panelFormulario.setLayout(null);
			panelFormulario.add(getLblInfoBasica());
			panelFormulario.add(getLblNombre());
			panelFormulario.add(getTextFieldNombre());
			panelFormulario.add(getLblUbicacion());
			panelFormulario.add(getTextFieldUbicacion());
			panelFormulario.add(getSpinnerNumPersonas());
			panelFormulario.add(getLblNumeroPersonas());
			panelFormulario.add(getLblFechaInicio());
			panelFormulario.add(getInputFechaInicio());
			panelFormulario.add(getBtnAnadir());
			panelFormulario.add(getLblFechaFin());
			panelFormulario.add(getInputFechaFin());
		}
		return panelFormulario;
	}
	private JLabel getLblInfoBasica() {
		if (lblInfoBasica == null) {
			lblInfoBasica = new JLabel("Información Básica");
			lblInfoBasica.setHorizontalAlignment(SwingConstants.CENTER);
			lblInfoBasica.setBounds(0, 11, 240, 41);
		}
		return lblInfoBasica;
	}
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre de Viaje:");
			lblNombre.setBounds(10, 73, 116, 20);
		}
		return lblNombre;
	}
	private JTextField getTextFieldNombre() {
		if (textFieldNombre == null) {
			textFieldNombre = new JTextField();
			textFieldNombre.setBounds(144, 73, 96, 20);
			textFieldNombre.setColumns(10);
		}
		return textFieldNombre;
	}
	private JLabel getLblUbicacion() {
		if (lblUbicacion == null) {
			lblUbicacion = new JLabel("Ubicación:");
			lblUbicacion.setBounds(10, 114, 77, 14);
		}
		return lblUbicacion;
	}
	private JTextField getTextFieldUbicacion() {
		if (textFieldUbicacion == null) {
			textFieldUbicacion = new JTextField();
			textFieldUbicacion.setBounds(144, 111, 96, 20);
			textFieldUbicacion.setColumns(10);
		}
		return textFieldUbicacion;
	}
	private JSpinner getSpinnerNumPersonas() {
		if (spinnerNumPersonas == null) {
			SpinnerModel model = new SpinnerNumberModel(1, 1, 1000, 1);
			spinnerNumPersonas = new JSpinner(model);
			spinnerNumPersonas.setBounds(210, 149, 30, 20);
		}
		return spinnerNumPersonas;
	}
	private JLabel getLblNumeroPersonas() {
		if (lblNumeroPersonas == null) {
			lblNumeroPersonas = new JLabel("Número de personas:");
			lblNumeroPersonas.setBounds(10, 152, 148, 14);
		}
		return lblNumeroPersonas;
	}
	private JLabel getLblFechaInicio() {
		if (lblFechaInicio == null) {
			lblFechaInicio = new JLabel("Fecha de inicio:");
			lblFechaInicio.setBounds(10, 190, 115, 14);
		}
		return lblFechaInicio;
	}
	private JTextField getInputFechaInicio() {
		if (inputFechaInicio == null) {
			inputFechaInicio = new JFormattedTextField(df);
			inputFechaInicio.setToolTipText("dd/mm/yyyy");
			inputFechaInicio.setBounds(144, 187, 96, 20);
			inputFechaInicio.setColumns(10);
		}
		return inputFechaInicio;
	}
	private JButton getBtnAnadir() {
		if (btnAnadir == null) {
			btnAnadir = new JButton("Añadir");
			btnAnadir.setBounds(85, 249, 89, 23);
			
			btnAnadir.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String nombre = getTextFieldNombre().getText();
					String destino = getTextFieldUbicacion().getText();
					int numPersonas = (Integer) getSpinnerNumPersonas().getValue();
					String fechaInicio = getInputFechaInicio().getText();
					String fechaFin = getInputFechaFin().getText();
					TViaje viaje = new TViaje(nombre, destino, numPersonas, fechaInicio, fechaFin);
					
					/*
					DAOViaje daoViaje = new DAOViajeImp();
					boolean created = daoViaje.crearViaje(viaje);*/
					try {
						Controlador.getInstancia().accion(Evento.ANADIR_VIAJE_FUTURO, viaje);
					}
					catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Error al crear el viaje. Revise que toda la información es correcta "
								+ "y si no contacte con soporte.", "Alerta de viaje",JOptionPane.ERROR_MESSAGE);
					}
					dispose();

					/*
					if (created) {
						JOptionPane.showMessageDialog(null, "Viaje creado correctamente.", "Alerta de viaje",JOptionPane.INFORMATION_MESSAGE);
						AnadirViajeFuturo frameViajeFuturo = new AnadirViajeFuturo();
						dispose();
						frameViajeFuturo.setVisible(true);
					}
					*/
				}
				
			});
		}
		return btnAnadir;
	}
	private JLabel getLblFechaFin() {
		if (lblFechaFin == null) {
			lblFechaFin = new JLabel("Fecha de fin:");
			inputFechaInicio.setToolTipText("dd/mm/yyyy");
			lblFechaFin.setBounds(10, 216, 115, 14);
		}
		return lblFechaFin;
	}
	private JTextField getInputFechaFin() {
		if (inputFechaFin == null) {
			inputFechaFin = new JFormattedTextField(df);
			inputFechaFin.setColumns(10);
			inputFechaFin.setBounds(144, 216, 96, 20);
		}
		return inputFechaFin;
	}
	@Override
	public void actualizar(int evento, Object datos) {
		switch (evento) {
		case Evento.ANADIR_VIAJE_FUTURO:
			if ((boolean) datos) {
				JOptionPane.showMessageDialog(this, "Se ha añadido el viaje futuro", title,
						JOptionPane.INFORMATION_MESSAGE);
			}
			break;
		default:
			JOptionPane.showMessageDialog(this, "Evento desconocido", "ERROR INESPERADO",
					JOptionPane.ERROR_MESSAGE);
			break;
		}
	}
	
}
