package View;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Component;

public class VistaLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnNewButton;
	private JButton registro;
	private JTextField nombre;
	private JPasswordField password;



    public JButton getBtnRegistrar() {
    	return btnNewButton;
    }
   public JTextField getTxtUsuario() {
	   return nombre;
   }
   public JPasswordField getTxtContraseña() {
	   return password;
   }
	public JButton getBtnRegistro() {
	return registro;
	}

	/**
	 * Create the frame.
	 */
	public VistaLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JPanel panel = new JPanel(new GridLayout(4, 3, 5, 2)); // 3 filas, 2 columnas

		panel.add(new JLabel("Usuario (Nombre)"));
		nombre = new JTextField(10);
		panel.add(nombre);

		panel.add(new JLabel("Contraseña"));
		password = new JPasswordField(10);
		panel.add(password);

		panel.add(new JLabel("")); // Celda vacía para mantener alineación
		btnNewButton = new JButton("Iniciar Sesion");
		panel.add(btnNewButton);
		
		panel.add(new JLabel("")); // Celda vacía para mantener alineación
		registro = new JButton("Registrate");
		panel.add(registro);

		contentPane.add(panel);
		
		
	}

}