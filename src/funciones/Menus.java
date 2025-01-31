package funciones;

import java.sql.SQLException;

import java.util.Scanner;

import Clases.Videoclub;
import Repositorio.LoginUsuario;
import Repositorio.Registrar;

import conectorBD.conectorBD;

public class Menus {
	private static Scanner sc = new Scanner(System.in);

	public static void menuInicial(Scanner sc) throws SQLException {
		int opcion;
		// El menu principal que inicia sesion o registra
		System.out.println("1. Registrarse");
		System.out.println("2. Iniciar sesion");

		opcion = sc.nextInt();
		sc.nextLine();

		switch (opcion) {

		case 1:

			Registrar.Registro();
			break;
		case 2:
			LoginUsuario.iniciarSesion(sc);
			break;

		}

	}
	public static void elegirOficina() throws SQLException{
		System.out.println("Elige la oficina (Irunnn  Gasteiz o Donosti)");
		String loc=sc.nextLine();
		 Videoclub videoclub=new Videoclub(loc);
		    Menus.menuSecundario(sc, videoclub);
	}

	public static void menuSecundario(Scanner sc, Videoclub videoclub) throws SQLException {
		int opcion;
		System.out.println("Bienvenido a Global cinesa aqui podra alquilar cualquier pelicula que "
				+ "tengamos diaponible en nuestro caltalogo" + " elija una de las siguientes opciones ");
		System.out.println("1. Mostrar peliculas por genero");
		System.out.println("2. Mostrar peliculas por nombre");
		System.out.println("3. Mostrar peliculas por precio inferior al escrito");
		System.out.println("4. Mostrar todas las peliculas ");
		System.out.println("5. Reserva la pelicula");
		System.out.println("6. Mostrar todas las reservas");
		opcion = sc.nextInt();
		sc.nextLine();

		switch (opcion) {

		case 1:
			 System.out.print("Ingresa el genero (thriller o accion): ");
			 String genero=sc.nextLine();
			ConsultarPeli.MostarPelisPorGenero(genero, videoclub);
			break;
		case 2:
			 System.out.println("Ingresa el nombre de pelicula que quieres buscar");
			 String nombre=sc.nextLine();
			ConsultarPeli.MostrarPeliculaPorNombre(nombre, videoclub);
			break;
		case 3:
			System.out.print("Ingresa el precio");
			 double precio=sc.nextDouble();
			ConsultarPeli.MostarPelisPorPrecio(precio, videoclub);
			break;
		case 4:
			ConsultarPeli.MostrarTodasPeliculas(null, videoclub);
			break;
		case 5:
			System.out.println();
			System.out.println("Elige el codigo de la pelicula que quieras reservar");
			int codigo=sc.nextInt();
			sc.nextLine();
			System.out.println();
			System.out.println("Introduce tu dni");
			String dni=sc.nextLine();
			ConsultarPeli.realizarReserva(codigo,dni,videoclub);
			ConsultarPeli.volverMenu();
			break;
		case 6:
			System.out.print("Ingresa el DNI");
			 String dni2=sc.nextLine();
			ConsultarPeli.MostarReservas(dni2, videoclub);
			break;

		}
	}
}
