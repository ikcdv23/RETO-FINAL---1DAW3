package Repositorio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Clases.Usuario;
import conectorBD.conectorBD;
import funciones.Menus;

public class Registrar {
	private static Scanner sc = new Scanner(System.in);

	public static void Registro() {

		try (Scanner scanner = new Scanner(System.in)) {
			// Solicitar los datos del nuevo usuario
			System.out.print("Ingrese un nombre de usuario: ");
			String nombre = scanner.nextLine();

			String dni;
			while (true) {

				// Uso en el código
				System.out.println("Introduce tu DNI:");
				dni = scanner.nextLine().trim();
				while (!validarDNI(dni)) {
					System.out.println(
							"Error: El DNI debe tener 9 caracteres y el formato debe ser correcto (8 dígitos seguidos de una letra). Vuelve a introducirlo:");
					dni = scanner.nextLine().trim();
				}

				// Verificar si el DNI ya existe
				if (verificarDni(dni)) {
					System.out.println("El DNI ya esta registrado. Por favor, introduce otro.");
				} else {
					break; // Salir del bucle si el DNI no existe
				}
			}

			System.out.print("Ingrese tu email: ");
			String email = verificarCorreo();
			String contra = verificarContra();

			String rol1 = elegirRol();

			Usuario usuario = new Usuario(nombre, dni, email, contra, rol1);
			// Conexión a la base de datos

			// Consulta SQL para insertar el nuevo usuario
			String consulta = "INSERT INTO usuario (nombre, dni, email, contraseña, rol) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement sentencia = conectorBD.conexion.prepareStatement(consulta);
			sentencia.setString(1, nombre);
			sentencia.setString(2, dni);
			sentencia.setString(3, email);
			sentencia.setString(4, contra);
			sentencia.setString(5, rol1);

			// Ejecutar la inserción
			int filasAfectadas = sentencia.executeUpdate();
			if (filasAfectadas > 0) {
				System.out.println("¡Usuario registrado con exito!");
			} else {
				System.out.println("Hubo un error al registrar el usuario.");
			}

			try {
				Menus.menuInicial();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Ocurrió un error al regresar al menú inicial.");
			}

			// Cerrar la conexión
			sentencia.close();
			conectorBD.cerrarConexion();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Método para verificar si el DNI ya existe en la base de datos

	public static String elegirRol() {
		String rol;
		do {
			System.out.println("Elige tu rol (Administrador / Cliente): ");
			rol = sc.nextLine().trim();
			if (!rol.equalsIgnoreCase("Administrador") && !rol.equalsIgnoreCase("Cliente")) {
				System.out.println("Error: El rol debe ser 'Administrador' o 'Cliente'. Vuelve a intentarlo.");
			}
		} while (!rol.equalsIgnoreCase("Administrador") && !rol.equalsIgnoreCase("Cliente"));

		return rol;
	}

	public static boolean validarDNI(String dni) {
		// Verifica si el DNI tiene exactamente 9 caracteres y es válido
		return dni.length() == 9 && dni.matches("[0-9]{8}[A-Za-z]");
	}

	// Método para verificar si el DNI ya existe en la base de datos
	public static boolean verificarDni(String dni) {
		boolean existe = false;
		try {
			// Conexión a la base de datos

			// Consulta SQL para verificar el DNI
			String consulta = "SELECT COUNT(*) FROM usuario WHERE dni = ?";

			PreparedStatement sentencia = conectorBD.conexion.prepareStatement(consulta);
			sentencia.setString(1, dni);
			// Ejecutar la consulta
			ResultSet resultado = sentencia.executeQuery();
			if (resultado.next()) {
				existe = resultado.getInt(1) > 0; // Si el conteo es mayor a 0, el DNI ya existe
			}
			// Cerrar la conexión
			resultado.close();
			sentencia.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return existe;
	}

	public static String verificarContra() {
		String contraseña;
		boolean tieneMayuscula, tieneNumero, longitudValida, esValida;

		do {
			System.out.println("Ingrese una contraseña: ");
			contraseña = sc.nextLine();

			// Inicializamos las variables
			tieneMayuscula = false;
			tieneNumero = false;
			longitudValida = contraseña.length() >= 8;

			// Recorremos la contraseña para verificar mayúsculas y números
			for (char c : contraseña.toCharArray()) {
				if (Character.isUpperCase(c)) {
					tieneMayuscula = true;
				}
				if (Character.isDigit(c)) {
					tieneNumero = true;
				}
			}

			// Verificamos si todas las condiciones se cumplen
			esValida = longitudValida && tieneMayuscula && tieneNumero;

			if (!esValida) {
				System.out.println("La contraseña no es válida. Debe cumplir con los siguientes requisitos:");
				if (!longitudValida) {
					System.out.println("- Tener al menos 8 caracteres.");
				}
				if (!tieneMayuscula) {
					System.out.println("- Contener al menos una letra mayúscula.");
				}
				if (!tieneNumero) {
					System.out.println("- Contener al menos un número.");
				}
				System.out.println("Por favor, intente de nuevo.\n");
			}

		} while (!esValida);

		System.out.println("La contraseña es válida :)");
		return contraseña;
	}

	public static String verificarCorreo() {
		String email;
		boolean esValido;

		do {
			System.out.println("Ingrese un correo electrónico: ");
			email = sc.nextLine().trim();

			// Expresión regular para validar correos electrónicos
			String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
			esValido = email.matches(emailRegex);

			if (!esValido) {
				System.out
						.println("El correo electrónico no es válido. Debe tener un formato como usuario@dominio.com.");
				System.out.println("Por favor, intente de nuevo.\n");
			}
		} while (!esValido);

		System.out.println("El correo electrónico es válido.");
		return email;
	}
}