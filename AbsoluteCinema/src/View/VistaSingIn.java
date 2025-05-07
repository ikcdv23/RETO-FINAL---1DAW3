package View;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class VistaSingIn extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private JTextField nombre;
	private JTextField apellido;
	private JTextField email;
	private JPasswordField password;
	private JButton Registrate;
	private JButton Volver;
	
	// getters 
	public JPanel getContentPane() {
		return contentPane;
	}
	public JTextField getNombre() {
		return nombre;
	}
	public JTextField getApellido() {
		return apellido;
	}
	public JTextField getEmail() {
		return email;
	}
	public JPasswordField getPassword() {
		return password;
	}
	public JButton getRegistrate() {
		return Registrate;
	}
	public JButton getVolver() {
		return Volver;
	}

	
	
}



















