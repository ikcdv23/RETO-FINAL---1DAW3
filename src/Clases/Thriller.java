package Clases;

public class Thriller extends Pelicula{

	
	private String intensidad;
	
	public Thriller(String nombre, int codigo, String autor, double precio, int codigo_reserva, Enum genero) {
		super(nombre, codigo, autor, precio, codigo_reserva, genero);
		this.intensidad=intensidad;
	}

}
