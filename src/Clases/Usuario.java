package Clases;

public class Usuario {
	private String nombre;
	private String contraseña;
	private String email;
	private String Rol;
	private String DNI;
	
	
	public Usuario(String nombre, String contraseña, String email, String rol, String DNI) {
		super();
		this.nombre = nombre;
		this.contraseña = contraseña;
		this.email = email;
		this.DNI=DNI;
		Rol = rol;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRol() {
		return Rol;
	}
	public void setRol(String rol) {
		Rol = rol;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}
	
	
	
	
}
