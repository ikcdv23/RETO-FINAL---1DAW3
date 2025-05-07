package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import View.VistaPelicula;
import Repositorios.Pelicula;
import Repositorios.RepositorioPelicula;
import Repositorios.RepositorioPelicula;
import View.DetallesPelicula;
import View.VistaBienvenida;
import View.VistaLogin;
import View.VistaPelicula;

public class ControlerPelicula {
	private VistaPelicula vista;
	
	public ControlerPelicula(VistaPelicula vista) {
		this.vista=vista;

	this.vista.getbotonRopa().addActionListener(new ActionListener() {
	
		public void actionPerformed(ActionEvent e) {
			
			//Llamar al Repositorio productos insertar. Le pasaras un Producto como parametro
			//Coger de los JtextField la informacion 
		   añadirProducto();
			VistaBienvenida Vistabienvenida= new VistaBienvenida(vista.getUsuario());
			 ControlerBienvenida cont =new ControlerBienvenida(Vistabienvenida);
			 cont.iniciar();
		}
	 });
	}


	
	public void iniciar() {
        vista.setVisible(true);
    }
	public void añadirProducto() {
		String id_pelicula=this.vista.getId_pelicula().getText();
		String titulo=this.vista.getTitulo().getText();
		String director=this.vista.getDirector().getText();
		String duracion=this.vista.getDuracion().getText();
		String genero=this.vista.getGenero().getText();
		String sinopsis=this.vista.getSinopsis().getText();
		String pais_origen=this.vista.getPais_origen().getText();
		String anio_lanzamiento=this.vista.getAnio_lanzamiento().getText();

     RepositorioPelicula.peli(id_pelicula,titulo,director,duracion,genero,sinopsis,pais_origen,anio_lanzamiento);
	}
}
	