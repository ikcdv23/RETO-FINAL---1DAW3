package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import Repositorios.Pelicula;
import Repositorios.RepositorioBusqueda;
import View.VistaLogin;
import View.VistaPelicula;
import View.VistaPelicula;
import View.DetallesPelicula;
import View.VistaBienvenida;

public class ControlerBienvenida {

    private VistaBienvenida vista;

    public ControlerBienvenida(VistaBienvenida vista) {
        this.vista = vista;

        // Verificar que la instancia es correcta
        System.out.println("Controlador creado con vista: " + this.vista);

        this.vista.getBtnSalir().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Botón Salir presionado"); // Depuración
                salir();
            }
        });
        this.vista.getJlist().addListSelectionListener(e->{
    		if(!e.getValueIsAdjusting()) {
    			
    			Pelicula productoSeleccionado= this.vista.getJlist().getSelectedValue();
    			
    			vista.dispose();
    			DetallesPelicula det =new DetallesPelicula(productoSeleccionado,vista.getUsuario());
    			ControlerDetalles con =new ControlerDetalles(det);
    			con.iniciar();

    			
    		}
    		});
        
    
	this.vista.getBotonAgregar().addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			agregaRopa();
		}
	 });
    
    
	
	
    this.vista.getBotonBuscar().addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				
			String TextoBusqueda=ControlerBienvenida.this.vista.getField().getText();
				ControlerBienvenida.this.vista.getLista().clear();
				
				ArrayList<Pelicula> Peliculas=RepositorioBusqueda.busca(TextoBusqueda);
				
				DefaultListModel<Pelicula> listamodelo = ControlerBienvenida.this.vista.getLista();
				for(Pelicula i: Peliculas) {
					listamodelo.addElement(i);
				}
		}
	 });
	}



	 public void agregaRopa() {
		 vista.dispose();
		 
		 VistaPelicula vistaRopa= new VistaPelicula();
		 ControlerPelicula cont =new ControlerPelicula(vistaRopa);
		 cont.iniciar();
	 }

    public void iniciar() {
        vista.setVisible(true);
    }

    public void salir() {
        JOptionPane.showMessageDialog(vista, "¡Hasta la próxima, " + vista.getUsuario() + "!");

        // Cerrar la ventana actual
        vista.dispose();

        // Abrir la ventana de login
        VistaLogin vistaLogin = new VistaLogin();
        ControlerLogin controladorLogin = new ControlerLogin(vistaLogin);
        controladorLogin.iniciar();
    }
}
