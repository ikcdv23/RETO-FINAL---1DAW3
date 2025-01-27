package funciones;
import java.sql.SQLException;
import java.util.Scanner;

import Repositorio.LoginUsuario;
import Repositorio.Registrar;


import conectorBD.conectorBD;

public class Menus {
	private  static Scanner sc= new Scanner(System.in);



	 public static void menuInicial(Scanner sc) throws SQLException {
		int opcion;
		System.out.println("Bienvenido a GlobalCinesa elige una opcion:");
		System.out.println("1. Registrarse");
		System.out.println("2. Iniciar sesion");
		opcion=sc.nextInt();
		sc.nextLine();
		
		switch (opcion) {
		
		case 1:
			Registrar.Registro();
			break;
		case 2:
			LoginUsuario.iniciarSesion();
			break;
		}
	}
		

	
	public static void menuInicio() {
		System.out.println("Bienvenido a Global cinesa aqui podra alquilar cualquier pelicula que "
				+ "tengamos diaponible en nuestro caltalogo"
				+ " elija una de las siguientes opciones ");
		System.out.println("1.Consulta todas las peliculas disponibles");
		System.out.println("2.Alquilar una pelicula");
		
		
	}
		
		
		
	}
	

