package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JButton;

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
	private JLabel lblFecha;
	private JTextField textField;
	private JButton btnAnadir;


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
			panelFormulario.add(getLblFecha());
			panelFormulario.add(getTextField());
			panelFormulario.add(getBtnAnadir());
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
			lblNombre.setBounds(10, 73, 49, 14);
		}
		return lblNombre;
	}
	private JTextField getTextFieldNombre() {
		if (textFieldNombre == null) {
			textFieldNombre = new JTextField();
			textFieldNombre.setBounds(69, 70, 96, 20);
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
	private JLabel getLblFecha() {
		if (lblFecha == null) {
			lblFecha = new JLabel("Fecha de inicio:");
			lblFecha.setBounds(10, 190, 115, 14);
		}
		return lblFecha;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setBounds(97, 187, 96, 20);
			textField.setColumns(10);
		}
		return textField;
	}
	private JButton getBtnAnadir() {
		if (btnAnadir == null) {
			btnAnadir = new JButton("Añadir");
			btnAnadir.setBounds(69, 248, 89, 23);
		}
		return btnAnadir;
	}
}
