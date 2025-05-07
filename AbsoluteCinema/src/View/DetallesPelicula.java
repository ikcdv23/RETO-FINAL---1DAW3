package View;

import java.awt.GridLayout;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import Repositorios.Pelicula;

public class DetallesPelicula extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String usuario;
	private JLabel id_pelicula;
	private JLabel titulo;
	private JLabel director;
	private JLabel duracion;
	private JLabel genero;
	private JLabel sinopsis;
	private JLabel pais_origen;
	private JLabel anio_lanzamiento;
	private JButton botonSalir;
	private JButton botonFichero;
	

	public DetallesPelicula(Pelicula p, String usuario){
		
			JPanel panel=new JPanel();
		      setSize(300, 200);
		      
			id_pelicula=new JLabel("Nombre:" +p.getId_pelicula());
			titulo =new JLabel("Marca:" +p.getTitulo());
			director =new JLabel("Categoria:"+p.getDirector());
			duracion =new JLabel("Talla:" +p.getDuracion());
			genero =new JLabel("Precio:" +p.getGenero());
			sinopsis =new JLabel("Precio:" +p.getSinopsis());
			pais_origen =new JLabel("Precio:" +p.getPais_origen());
			anio_lanzamiento =new JLabel("Precio:" +p.getAnio_lanzamiento());
			
			botonSalir =new JButton("Atras");
			botonFichero=new JButton("Generar fichero");
			
			setLayout(new GridLayout(7,2,1,1));
			
			add(id_pelicula);
			add(titulo);
			add(director);
			add(duracion);
			add(genero);
			add(pais_origen);
			add(anio_lanzamiento);
			add(botonSalir);
			add(botonFichero);
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		
	}
    public JButton getBtnSalir() {
        return botonSalir;
    }
    
    public void setBtnSalir(JButton botonSalir) {
        this.botonSalir=botonSalir;
    }
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public JButton getBotonFichero() {
		return botonFichero;
	}
	public void setBotonFichero(JButton botonFichero) {
		this.botonFichero = botonFichero;
	}
	public JLabel getId_pelicula() {
		return id_pelicula;
	}
	public void setId_pelicula(JLabel id_pelicula) {
		this.id_pelicula = id_pelicula;
	}
	public JLabel getTitulo() {
		return titulo;
	}
	public void setTitulo(JLabel titulo) {
		this.titulo = titulo;
	}
	public JLabel getDirector() {
		return director;
	}
	public void setDirector(JLabel director) {
		this.director = director;
	}
	public JLabel getDuracion() {
		return duracion;
	}
	public void setDuracion(JLabel duracion) {
		this.duracion = duracion;
	}
	public JLabel getGenero() {
		return genero;
	}
	public void setGenero(JLabel genero) {
		this.genero = genero;
	}
	public JLabel getSinopsis() {
		return sinopsis;
	}
	public void setSinopsis(JLabel sinopsis) {
		this.sinopsis = sinopsis;
	}
	public JLabel getPais_origen() {
		return pais_origen;
	}
	public void setPais_origen(JLabel pais_origen) {
		this.pais_origen = pais_origen;
	}
	public JLabel getAnio_lanzamiento() {
		return anio_lanzamiento;
	}
	public void setAnio_lanzamiento(JLabel anio_lanzamiento) {
		this.anio_lanzamiento = anio_lanzamiento;
	}
	public JButton getBotonSalir() {
		return botonSalir;
	}
	public void setBotonSalir(JButton botonSalir) {
		this.botonSalir = botonSalir;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

    
}