package View;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class VistaRegistrar extends JFrame {
	 private VistaRegistrar vista;
	 private JTextField usuario;
	 private JTextField password;
	 private JPanel contentPane;
	 private JButton botonEnRegistro;
	 
	 public void VistaRegistrar () {
		 
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 450, 300);
			
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

			setContentPane(contentPane);
			contentPane.setLayout(new FlowLayout(FlowLayout.LEFT));
			
			JPanel panel = new JPanel(new GridLayout(4, 3, 5, 2));
			
			botonEnRegistro=new JButton("Registrar");
		 
		 
		panel.add(new JLabel("Usuario"));
		usuario = new JTextField(10);
		panel.add(usuario);

		panel.add(new JLabel("Contraseña"));
		password = new JTextField(10);
		panel.add(password);
		
		panel.add(botonEnRegistro);
	 }
	 
		public JTextField getUsuario() {
		return usuario;
	}

	public void setUsuario(JTextField usuario) {
		this.usuario = usuario;
	}

	public JTextField getPassword() {
		return password;
	}

	public void setPassword(JTextField password) {
		this.password = password;
	}

		public void iniciar() {
	        vista.setVisible(true);
	    }

		public JButton getBotonEnRegistro() {
			return botonEnRegistro;
		}

		public void setBotonEnRegistro(JButton botonEnRegistro) {
			this.botonEnRegistro = botonEnRegistro;
		}
		
}
