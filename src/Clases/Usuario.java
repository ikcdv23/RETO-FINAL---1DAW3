package Clases;

public class Usuario {
	private String nombre;
	private String contraseña;
	private String email;
	private String Rol;
	private String DNI;
	private String Apellido;
	private String sexo;
	
	
	
	
	public Usuario(String nombre, String dni, String email, String contraseña, String rol) {
		super();
		this.nombre = nombre;
		this.contraseña = contraseña;
		this.email = email;
		this.DNI=DNI;
		this.Apellido=Apellido;
		this.sexo=sexo;
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

	public String getApellido() {
		return Apellido;
	}

	public void setApellido(String apellido) {
		Apellido = apellido;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	
	
	
}
