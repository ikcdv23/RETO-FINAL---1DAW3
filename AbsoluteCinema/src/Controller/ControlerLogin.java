package Controller;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import Repositorios.RepositorioUsuario;
import View.VistaLogin;
import View.VistaRegistrar;
import View.VistaBienvenida;

public class ControlerLogin {
    private VistaLogin vista;
   

    public  ControlerLogin(VistaLogin vista) {
        this.vista = vista;
       

        // Añadir el evento de botón
        this.vista.getBtnRegistrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               verificarUsuario();
            
            }
        });
    
	
        this.vista.getBtnRegistro().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	   String usuario = vista.getTxtUsuario().getText();
                   String pass = vista.getTxtContraseña().getText();
                   RepositorioUsuario.insertar(usuario, pass);
                   JOptionPane.showMessageDialog(vista, "Usuario registrado, identifiquese");
                   
            }
        });
    }

    private void verificarUsuario() {
        String usuario = vista.getTxtUsuario().getText();
        String pass = vista.getTxtContraseña().getText();

        if (RepositorioUsuario.verificarUsuario(usuario, pass)) {
           
            //Aqui deberia el controller cargar otra vista, la siguiente por ejemplo de Bienvenida.
            
         // Cerrar la ventana actual
            vista.dispose();

         // Crear la vista
            VistaBienvenida vistaBienvenida = new VistaBienvenida(usuario);

            // Crear el controlador con la vista correcta
            ControlerBienvenida controlador = new ControlerBienvenida(vistaBienvenida);

            // Iniciar la vista
            controlador.iniciar();
            
        } else {
            JOptionPane.showMessageDialog(vista, "Usuario o contraseña incorrectos");
        }
    }

    public void iniciar() {
        vista.setVisible(true);
    }
   
}

