package Clases;

public class Pelicula {
	
	protected  String nombre;
	protected int codigo;
	protected String autor;
	protected double precio;
	protected String genero;
	
	public Pelicula(String nombre, int codigo, String autor, double precio, String genero) {
		this.nombre = nombre;
		this.codigo = codigo;
		this.autor = autor;
		this.precio = precio;
		
		this.genero = genero;
	}
	
	

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String  genero) {
		this.genero = genero;
	}
	
	
	
}
