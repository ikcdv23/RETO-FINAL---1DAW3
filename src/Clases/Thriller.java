package Clases;

public class Thriller extends Pelicula{

	
	private String intensidad;
	
	public Thriller(String nombre, int codigo, String autor, double precio, String genero) {
		super(nombre, codigo, autor, precio,  genero);
		this.intensidad=intensidad;
	}

}
