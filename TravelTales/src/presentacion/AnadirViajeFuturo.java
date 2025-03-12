package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.TViaje;
import persistencia.dao.viaje.DAOViaje;
import persistencia.dao.viaje.DAOViajeImp;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

public class AnadirViajeFuturo extends JFrame {

	private static final long serialVersionUID = 1L;
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
	private DateFormat df = new SimpleDateFormat("dd.mm.yy");


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
	}
	private JPanel getPanelFormulario() {
		if (panelFormulario == null) {
			panelFormulario = new JPanel();
			panelFormulario.setBounds(125, 30, 240, 335);
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
			lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(10, 73, 69, 20);
		}
		return lblNombre;
	}
	private JTextField getTextFieldNombre() {
		if (textFieldNombre == null) {
			textFieldNombre = new JTextField();
			textFieldNombre.setBounds(97, 73, 96, 20);
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
			textFieldUbicacion.setBounds(97, 111, 96, 20);
			textFieldUbicacion.setColumns(10);
		}
		return textFieldUbicacion;
	}
	private JSpinner getSpinnerNumPersonas() {
		if (spinnerNumPersonas == null) {
			spinnerNumPersonas = new JSpinner();
			spinnerNumPersonas.setBounds(135, 149, 30, 20);
		}
		return spinnerNumPersonas;
	}
	private JLabel getLblNumeroPersonas() {
		if (lblNumeroPersonas == null) {
			lblNumeroPersonas = new JLabel("Número de personas:");
			lblNumeroPersonas.setBounds(10, 152, 115, 14);
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
			inputFechaInicio.setBounds(97, 187, 96, 20);
			inputFechaInicio.setColumns(10);
		}
		return inputFechaInicio;
	}
	private JButton getBtnAnadir() {
		if (btnAnadir == null) {
			btnAnadir = new JButton("Añadir");
			btnAnadir.setBounds(69, 248, 89, 23);
			
			btnAnadir.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String nombre = getTextFieldNombre().getText();
					String destino = getTextFieldUbicacion().getText();
					int numPersonas = (Integer) getSpinnerNumPersonas().getValue();
					String fechaInicio = getInputFechaInicio().getText();
					String fechaFin = getInputFechaFin().getText();
					TViaje viaje = new TViaje(nombre, destino, numPersonas, fechaInicio, fechaFin);
					
					DAOViaje daoViaje = new DAOViajeImp();
					daoViaje.crearViaje(viaje);
				}
				
			});
		}
		return btnAnadir;
	}
	private JLabel getLblFechaFin() {
		if (lblFechaFin == null) {
			lblFechaFin = new JLabel("Fecha de fin:");
			lblFechaFin.setBounds(10, 216, 115, 14);
		}
		return lblFechaFin;
	}
	private JTextField getInputFechaFin() {
		if (inputFechaFin == null) {
			inputFechaFin = new JFormattedTextField(df);
			inputFechaFin.setColumns(10);
			inputFechaFin.setBounds(97, 214, 96, 20);
		}
		return inputFechaFin;
	}
	
	
}
