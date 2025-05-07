package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import Repositorios.Pelicula;
import View.DetallesPelicula;
import View.VistaBienvenida;
import View.VistaLogin;

public class ControlerDetalles {
	private DetallesPelicula vista;
	
	public ControlerDetalles(DetallesPelicula vista) {
		this.vista=vista;
		
		
		this.vista.getBtnSalir().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				volver();
			}
		});
	
	

	this.vista.getBotonFichero().addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			
		String[]  id=vista.getId_pelicula().getText().split(":");

    	String[]  tit=vista.getTitulo().getText().split(":");

    	String[]  dir=vista.getDirector().getText().split(":");

    	String[]  dur=vista.getDuracion().getText().split(":");

    	String[]  gen=vista.getGenero().getText().split(":");
    	
    	String[]  sin=vista.getSinopsis().getText().split(":");
    	
    	String[]  pa=vista.getPais_origen().getText().split(":");
    	
    	
    	String i =id[1];

    	String titulo = tit[1];

    	String director = dir[1];

    	String duracion = dur[1];

    	String genero = gen[1];
    	
    	String sinopsis = sin[1];
    	
    	String pais = pa[1];

    	

    	Pelicula p = new Pelicula(i,titulo,director,duracion,genero,sinopsis,pais);

    	generarFactura(p);

    }

});
}
	 public static void generarFactura(Pelicula p) {

			File factura = new File(p.getTitulo()+".txt"); 

			BufferedWriter bw=null;

		

			

			try {

				bw = new BufferedWriter(new FileWriter(factura));

				bw.write("Factura de Venta");

				bw.newLine();

				bw.write("------------------------------");

				bw.newLine();

				bw.write("Nombre              Marca             Categoria        Talla          Precio ");

				bw.newLine();

				

				try {

					bw.write(p.getNombre()+"    "+p.getMarca()+"    "+p.getCategoria()+"    "+p.getTalla()+"     "+String.valueOf(p.getPrecio()));

					bw.newLine();

				} catch (IOException e) {

					e.printStackTrace();

				}

				

				

				bw.close();

			} catch (IOException e) {

				// TODO Auto-generated catch block

				e.printStackTrace();

			};

		}

	
	

	 public void volver() {		 	
	        // Cerrar la ventana actual
	        vista.dispose();

	        // Abrir la ventana de login
	        VistaBienvenida vistaLogin = new VistaBienvenida(vista.getUsuario());
	        ControlerBienvenida  controladorLogin = new ControlerBienvenida(vistaLogin);
	        controladorLogin.iniciar();
	    }
	 public void agregaRopa() {
		 
	 }
	public void iniciar() {
        vista.setVisible(true);
    }
}
	

