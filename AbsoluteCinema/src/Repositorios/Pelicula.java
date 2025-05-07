package Repositorios;

public class Pelicula {

	private int id_pelicula;
	private String titulo;
	private String director;
	private String duracion;
	private String genero;
	private String sinopsis;
	private String pais_origen;
	private int anio_lanzamiento;

	public Pelicula(int id_pelicula, String titulo, String director, String duracion, String genero, String sinopsis,
			String pais_origen, int anio_lanzamiento) {
		super();
		this.id_pelicula = id_pelicula;
		this.titulo = titulo;
		this.director = director;
		this.duracion = duracion;
		this.genero = genero;
		this.sinopsis = sinopsis;
		this.pais_origen = pais_origen;
		this.anio_lanzamiento = anio_lanzamiento;
	}

	public int getId_pelicula() {
		return id_pelicula;
	}

	public void setId_pelicula(int id_pelicula) {
		this.id_pelicula = id_pelicula;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public String getPais_origen() {
		return pais_origen;
	}

	public void setPais_origen(String pais_origen) {
		this.pais_origen = pais_origen;
	}

	public int getAnio_lanzamiento() {
		return anio_lanzamiento;
	}

	public void setAnio_lanzamiento(int anio_lanzamiento) {
		this.anio_lanzamiento = anio_lanzamiento;
	}

}
