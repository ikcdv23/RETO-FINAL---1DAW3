package Clases;

public class Videoclub {
	public  String Localidad;
	public int NIF;

	public Videoclub(String localidad) {
		super();
		Localidad = localidad;
		
	}
	
	public Videoclub(Object object, String loc) {
		// TODO Auto-generated constructor stub
	}

	public String getLocalidad() {
		return Localidad;
	}
	public void setLocalidad(String localidad) {
		Localidad = localidad;
	}
	public int getNIF() {
		return NIF;
	}
	public void setNIF(int nIF) {
		NIF = nIF;
	}
	
	
	
	
	
	
}
