package funciones;

import java.util.ArrayList;

import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import Clases.Pelicula;
import Clases.Usuario;
import Clases.Videoclub;

import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import funciones.Menus;
import conectorBD.conectorBD;
import java.sql.Statement;

public class ConsultarPeli implements InterfazFunciones<Videoclub> {
	public static Scanner scanner = new Scanner(System.in);
	private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	// Muestra las pelis por genero
	public static void MostarPelisPorGenero(String genero, Videoclub videoclub) throws SQLException {
		System.out.println("\n--- Mostrar Peliculas por genero ---");

		// Hace la consulta para filtrar las pelis por genero
		
		
		String query = "SELECT p.nombre, v.localidad, p.codigo, p.autor, p.precio, p.genero from pelicula p join guardar g on p.codigo=g.codPeli join videoclub v on g.nif=v.nif where genero= ? and localidad = ?";
		try (PreparedStatement preparedStatement = conectorBD.conexion.prepareStatement(query)) {
			
			preparedStatement.setString(1, genero);
			preparedStatement.setString(2, videoclub.getLocalidad());
			ResultSet resultSet = preparedStatement.executeQuery();
			
			// Si no se se encuentra el genero muestra el mensaje
			if (!resultSet.isBeforeFirst()) {
				System.out.println("No se encontraron peliculas con ese genero: " + genero);				
			} 
			
			else {
				// Si el genero concuerda muestra las peliculas
				while (resultSet.next()) {
					System.out.println("Nombre: " + resultSet.getString("nombre") + ", Codigo: "
							+ resultSet.getInt("codigo") + ", Autor: " + resultSet.getString("autor") + ", Precio: "
							+ resultSet.getInt("precio") + ", Genero: " + resultSet.getString("genero"));

				}
			}
		}

	}

	// Muestra las pelis por precio inferior al introducido
	public static void MostarPelisPorPrecio(double precio, Videoclub videoclub) throws SQLException {
		System.out.println("\n--- Mostrar peliculas por precio inferior al introducido ---");
		// HAce la consulta para filtrar por el precio inferior al introducido
		String query = "SELECT p.nombre, v.localidad, p.codigo, p.autor, p.precio, p.genero from pelicula p join guardar g on p.codigo=g.codPeli join videoclub v on g.nif=v.nif where precio < ? and localidad = ?";
		;
		try (PreparedStatement preparedStatement = conectorBD.conexion.prepareStatement(query)) {
			preparedStatement.setDouble(1, precio);
			preparedStatement.setString(2, videoclub.getLocalidad());
			ResultSet resultSet = preparedStatement.executeQuery();

			if (!resultSet.isBeforeFirst()) {
				System.out.println("No se encontraron peliculas con precio inferior: " + precio);
			} else {
				// Mostrara las pelis que esten por debajo del precio indicado
				while (resultSet.next()) {
					System.out.println("Nombre: " + resultSet.getString("nombre") + ", Codigo: "
							+ resultSet.getInt("codigo") + ", Autor: " + resultSet.getString("autor") + ", Precio: "
							+ resultSet.getInt("precio") + ", Genero: " + resultSet.getString("genero"));

				}
			}
		}

	}


	// Se crea un array list con el objeto pelicula donde se guardaran las pelis que
	// esten disponibles en las fechas introducidas
	public static List<Pelicula> obtenerPeliculasDisponibles(LocalDate fechaEntrada, LocalDate fechaSalida,
			Videoclub videoclub) {
		List<Pelicula> peliculasDisponibles = new ArrayList<>();
		String consulta = "SELECT p.* FROM pelicula p " + "JOIN guardar g ON p.codigo = g.codPeli "
				+ "JOIN videoclub v ON g.nif = v.nif " + "WHERE v.localidad = ? " + "AND p.codigo NOT IN ("
				+ "SELECT codigoPelicula FROM reserva " + "WHERE (? BETWEEN fechaReserva AND fechaEntrega) "
				+ "OR (? BETWEEN fechaReserva AND fechaEntrega))";

		try (PreparedStatement sentencia = conectorBD.conexion.prepareStatement(consulta)) {

			sentencia.setString(1, videoclub.getLocalidad()); // Localidad del videoclub
			sentencia.setDate(2, Date.valueOf(fechaEntrada));
			sentencia.setDate(3, Date.valueOf(fechaSalida));

			try (ResultSet resultado = sentencia.executeQuery()) {
				while (resultado.next()) {
					// Crea un objeto con los alores de la base de datos y los pasa a un objeto
					Pelicula pelicula = new Pelicula(resultado.getString("nombre"), resultado.getInt("codigo"),
							resultado.getString("autor"), resultado.getDouble("precio"), resultado.getString("genero"));
					// En el array de peliculas disponibles se agregan las peliculas que cumplan la
					// condicion de la consulta
					peliculasDisponibles.add(pelicula);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return peliculasDisponibles;
	}

	public static void realizarReserva(Usuario usuario, Videoclub videoclub, Pelicula pelicula) {
		Scanner scanner = new Scanner(System.in);
		// SE crean las fechas null para luego rellenarlas
		String fechaEntradaBD = null;
		String fechaSalidaBD = null;
		LocalDate fechaEntrada = null;
		LocalDate fechaSalida = null;

		if (videoclub == null) {
			System.out.println("Error: No se ha seleccionado una localidad.");
			return;
		}

		while (fechaEntrada == null) {
			try {
				System.out.print("Ingrese la fecha de entrada (aaaa-mm-dd): ");
				fechaEntrada = LocalDate.parse(scanner.nextLine());
				// FUncion que sirve para guardar la fecha actual
				LocalDate hoy = LocalDate.now();
				if (fechaEntrada.isBefore(hoy)) {
					System.out.println("Error, la fecha de entrada tiene que ser posterior al dia de hoy");
					realizarReserva(usuario, videoclub, pelicula);
				}

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				fechaEntradaBD = fechaEntrada.format(formatter);

			} catch (DateTimeParseException e) {
				System.out.println("Formato incorrecto. Intente de nuevo.");
			}
		}

		while (fechaSalida == null) {
			try {
				System.out.print("Ingrese la fecha de salida (aaaa-mm-dd): ");
				fechaSalida = LocalDate.parse(scanner.nextLine());

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				fechaSalidaBD = fechaSalida.format(formatter);

				if (fechaSalida.isBefore(fechaEntrada)) {
					System.out.println("Error, la fecha de salida tiene que ser inferior ala fecha de entrada");
					return;
				}

			} catch (DateTimeParseException e) {
				System.out.println("X Error: Formato de fecha inválido. Usa 'yyyy-MM-dd'. Intenta de nuevo.");
			}
		}
		// El array de peliculasDisponibles se llena mediante los valores que te pasen
		// el metodo obtenerPeliculasDisponibles
		List<Pelicula> peliculasDisponibles = obtenerPeliculasDisponibles(fechaEntrada, fechaSalida, videoclub);

		if (peliculasDisponibles.isEmpty()) {
			System.out.println("No hay películas disponibles en el rango de fechas seleccionado.");
			return;
		}
		// MEdiante un for each se muestran todas las peliculas disponibles
		System.out.println("Películas disponibles:");
		for (Pelicula pelicula1 : peliculasDisponibles) {
			System.out.println(pelicula1.getCodigo() + ". " + pelicula1.getNombre());
		}

		System.out.print("Ingrese el código de la película que desea reservar: ");
		int codigoPelicula = scanner.nextInt();
		scanner.nextLine();
		// Se crea un boolean para saber si esta disponible o no mediante any match
		boolean disponible = peliculasDisponibles.stream().anyMatch(p -> p.getCodigo() == codigoPelicula);
		// Si el boolean es true se hace la insercion en la bqse de datos
		if (disponible) {
			String consulta = "INSERT INTO reserva (fechaReserva, fechaEntrega, dni, codigoPelicula) VALUES (?, ?, ?, ?)";
			try (PreparedStatement sentencia = conectorBD.conexion.prepareStatement(consulta)) {
					

				sentencia.setDate(1, Date.valueOf(fechaEntrada));
				sentencia.setDate(2, Date.valueOf(fechaSalida));
				sentencia.setString(3, usuario.getDNI());
				sentencia.setInt(4, codigoPelicula);

				int filasAfectadas = sentencia.executeUpdate();
				if (filasAfectadas > 0) {
					System.out.println("Reserva realizada con éxito.");
				} else {
					System.out.println("No se pudo realizar la reserva.");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("La película seleccionada no está disponible en el rango de fechas indicado.");
		}
	}

	// Funcion para verificar el formato de la fecha
	public static String solicitarFecha(String mensaje, Scanner scanner) {
		String fecha;
		while (true) {
			System.out.print(mensaje);
			fecha = scanner.nextLine();

			if (esFechaValida(fecha)) {
				return fecha;
			} else {
				System.out.println("X Error: Formato de fecha inválido. Usa 'yyyy-MM-dd'. Intenta de nuevo.");
			}
		}
	}

	// Metodo que verifica que la fecha sea valida
	private static boolean esFechaValida(String fecha) {
		try {
			LocalDate.parse(fecha, dateFormatter);
			return true;
		} catch (DateTimeParseException e) {
			return false; // Fecha inválida
		}
	}

	public static void MostrarPeliculaPorNombre(String nombre, Videoclub videoclub) throws SQLException {
		System.out.println("\n--- Mostrar Peliculas por nombre ---");

		// HAce una consulta para verificar que el nombre introducido exista
		String query = "SELECT p.nombre, v.localidad, p.codigo, p.autor, p.precio, p.genero from pelicula p join guardar g on p.codigo=g.codPeli join videoclub v on g.nif=v.nif where nombre = ? and localidad = ?";
		try (PreparedStatement preparedStatement = conectorBD.conexion.prepareStatement(query)) {
			preparedStatement.setString(1, nombre);
			preparedStatement.setString(2, videoclub.getLocalidad());
			ResultSet resultSet = preparedStatement.executeQuery();

			if (!resultSet.isBeforeFirst()) {
				System.out.println("No se encontro la pelicula con ese nombre: " + nombre);
			} else {
				while (resultSet.next()) {
					System.out.println("Nombre: " + resultSet.getString("nombre") + ", Codigo: "
							+ resultSet.getInt("codigo") + ", Autor: " + resultSet.getString("autor") + ", Precio: "
							+ resultSet.getInt("precio") + ", Genero: " + resultSet.getString("genero"));

				}
			}
		}
	}

	public static void MostrarTodasPeliculas(Videoclub videoclub){
		// Introduce el genero por el que quieres que te clasifique
		System.out.println("\n--- Mostrar Todas Las Peliculas ---");
		System.out.print("Ingresa el genero: ");

		// HAce la consulta para mostrar todas las peliculas por localidad
		String query = "SELECT p.nombre, v.localidad, p.codigo, p.autor, p.precio, p.genero from pelicula p join guardar g on p.codigo=g.codPeli join videoclub v on g.nif=v.nif where localidad = ?";
		;
		try (PreparedStatement preparedStatement = conectorBD.conexion.prepareStatement(query)) {
			preparedStatement.setString(1, videoclub.getLocalidad());
			ResultSet resultSet = preparedStatement.executeQuery();

			if (!resultSet.isBeforeFirst()) {
				System.out.println("Hubo un error");
			} else {
				while (resultSet.next()) {
					// Muestra las peliculas con el genero pedido
					System.out.println("Todas las peliculas");
					System.out.println("Nombre: " + resultSet.getString("nombre") + ", Codigo: "
							+ resultSet.getInt("codigo") + ", Autor: " + resultSet.getString("autor") + ", Precio: "
							+ resultSet.getInt("precio") + ", Genero: " + resultSet.getString("genero"));

				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void mostrar(Videoclub videoclub) {
		
		System.out.println("\n--- Mostrar Todas Las Peliculas ---");
		System.out.print("Ingresa el genero: ");

		// HAce la consulta para mostrar todas las peliculas por localidad
		String query = "SELECT p.nombre, v.localidad, p.codigo, p.autor, p.precio, p.genero from pelicula p join guardar g on p.codigo=g.codPeli join videoclub v on g.nif=v.nif where localidad = ?";
		;
		try (PreparedStatement preparedStatement = conectorBD.conexion.prepareStatement(query)) {
			preparedStatement.setString(1, videoclub.getLocalidad());
			ResultSet resultSet = preparedStatement.executeQuery();

			if (!resultSet.isBeforeFirst()) {
				System.out.println("Hubo un error");
			} else {
				while (resultSet.next()) {
					// Muestra las peliculas con el genero pedido
					System.out.println("Todas las peliculas");
					System.out.println("Nombre: " + resultSet.getString("nombre") + ", Codigo: "
							+ resultSet.getInt("codigo") + ", Autor: " + resultSet.getString("autor") + ", Precio: "
							+ resultSet.getInt("precio") + ", Genero: " + resultSet.getString("genero"));

				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
}