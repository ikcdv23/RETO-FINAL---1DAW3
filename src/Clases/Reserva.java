package Clases;

public class Reserva {
	private int codigo;
	private int codigoPelicula;
	private String fecha_reserva;
	private String fecha_entrega;
	private String Dni;
	
	public Reserva(int codigo,int codigoPelicula, String fecha_reserva, String fecha_entrega, String dni) {
		super();
		this.codigo = codigo;
		this.fecha_reserva = fecha_reserva;
		this.fecha_entrega = fecha_entrega;
		this.codigoPelicula=codigoPelicula;
		Dni = dni;
	}
	
	
	public int getCodigoPelicula() {
		return codigoPelicula;
	}

	public void setCodigoPelicula(int codigoPelicula) {
		this.codigoPelicula = codigoPelicula;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getFecha_reserva() {
		return fecha_reserva;
	}
	public void setFecha_reserva(String fecha_reserva) {
		this.fecha_reserva = fecha_reserva;
	}
	public String getFecha_entrega() {
		return fecha_entrega;
	}
	public void setFecha_entrega(String fecha_entrega) {
		this.fecha_entrega = fecha_entrega;
	}
	public String getDni() {
		return Dni;
	}
	public void setDni(String dni) {
		Dni = dni;
	}
	
	
	
	
	
	
	
}
