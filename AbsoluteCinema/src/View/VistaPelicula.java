package View;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class VistaPelicula extends JFrame{
		 private VistaRegistrar vista;
		 private JTextField id_pelicula;
		private JTextField titulo;
		private JTextField director;
		private JTextField duracion;
		private JTextField genero;
		private JTextField sinopsis;
		private JTextField pais_origen;
		private JTextField anio_lanzamiento;
		 private JPanel contentPane;
		 private JButton botonRopa;
		 private String usuario;
		 
		 
		 public VistaPelicula() {
			 
			
				
				 setSize(300, 200);
			     setLocationRelativeTo(null);
			    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				
				
				setLayout(new GridLayout(6,1, 5, 5));
				
				
				botonRopa=new JButton("Agregar");
			 
			 
			add(new JLabel("id_pelicula"));
			id_pelicula = new JTextField(10);
			add(id_pelicula);

			add(new JLabel("titulo"));
			titulo = new JTextField(10);
			add(titulo);
			add(new JLabel("director"));
			director = new JTextField(10);
			add(director);
			
			add(new JLabel("duracion"));
			duracion = new JTextField(10);
			add(duracion);
			
			add(new JLabel("genero"));
			genero = new JTextField(10);
			add(genero);
			
			add(new JLabel("sinopsis"));
			sinopsis = new JTextField(10);
			add(sinopsis);
			
			add(new JLabel("pais_origen"));
			pais_origen = new JTextField(10);
			add(pais_origen);
			
			add(new JLabel("anio_lanzamiento"));
			anio_lanzamiento = new JTextField(10);
			add(anio_lanzamiento);
			
			
			
			
			
			
			
			
			
			
			add(botonRopa);
		 }
		 

			

			

			public void setbotonRopa(JButton botonRopa) {
				this.botonRopa = botonRopa;
			}

			public JButton getbotonRopa() {
				return botonRopa;
			}

			public String getUsuario() {
				// TODO Auto-generated method stub
				return usuario;
			}






			public VistaRegistrar getVista() {
				return vista;
			}






			public void setVista(VistaRegistrar vista) {
				this.vista = vista;
			}






			public JTextField getId_pelicula() {
				return id_pelicula;
			}






			public void setId_pelicula(JTextField id_pelicula) {
				this.id_pelicula = id_pelicula;
			}






			public JTextField getTitulo() {
				return titulo;
			}






			public void setTitulo(JTextField titulo) {
				this.titulo = titulo;
			}






			public JTextField getDirector() {
				return director;
			}






			public void setDirector(JTextField director) {
				this.director = director;
			}






			public JTextField getDuracion() {
				return duracion;
			}






			public void setDuracion(JTextField duracion) {
				this.duracion = duracion;
			}






			public JTextField getGenero() {
				return genero;
			}






			public void setGenero(JTextField genero) {
				this.genero = genero;
			}






			public JTextField getSinopsis() {
				return sinopsis;
			}






			public void setSinopsis(JTextField sinopsis) {
				this.sinopsis = sinopsis;
			}






			public JTextField getPais_origen() {
				return pais_origen;
			}






			public void setPais_origen(JTextField pais_origen) {
				this.pais_origen = pais_origen;
			}






			public JTextField getAnio_lanzamiento() {
				return anio_lanzamiento;
			}






			public void setAnio_lanzamiento(JTextField anio_lanzamiento) {
				this.anio_lanzamiento = anio_lanzamiento;
			}






			public JPanel getContentPane() {
				return contentPane;
			}






			public void setContentPane(JPanel contentPane) {
				this.contentPane = contentPane;
			}






			public JButton getBotonRopa() {
				return botonRopa;
			}






			public void setBotonRopa(JButton botonRopa) {
				this.botonRopa = botonRopa;
			}






			public void setUsuario(String usuario) {
				this.usuario = usuario;
			}

		
	}

