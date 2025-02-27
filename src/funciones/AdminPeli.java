package funciones;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Clases.Usuario;
import Clases.Videoclub;
import conectorBD.conectorBD;

public class AdminPeli implements InterfazFunciones<Usuario> {
	public static Scanner scanner = new Scanner(System.in);

	// Este metodo cuenta las reservas mediante la consulta de count
	public static void ContarReservas(Usuario usuario, Videoclub videoclub) throws SQLException {
		System.out.println("\n--- Contar Todas Las Reservas ---");

		// Consulta para filtrar las reservas por DNI
		String query = "SELECT count(codigo) FROM reserva";

		try (PreparedStatement preparedStatement = conectorBD.conexion.prepareStatement(query)) {
			ResultSet resultSet = preparedStatement.executeQuery();

			// Verifica si hay resultados
			if (!resultSet.isBeforeFirst()) {
				System.out.println("No se encontró ninguna reserva para el DNI: " + usuario);
			} else {
				System.out.println("Todas las reservas:");
				while (resultSet.next()) { // Iterar sobre los resultados
					System.out.println(" Cantidad de reservas: " + resultSet.getInt("count(codigo)")

					);
				}
			}
		} catch (SQLException e) {
			System.out.println("Error al obtener las reservas: " + e.getMessage());
			e.printStackTrace();
		}

	}

//Este metodo cuenta las reservas por localidad mediante el count
	public static void ContarReservasLoc(Usuario usuario, String loc) throws SQLException {
		System.out.println("\n--- Contar Todas Las Reservas Por Localidad ---");

		// Consulta para filtrar las reservas por DNI
		String query = "SELECT count(codigo), localidad from reserva p join guardar g on p.codigoPelicula=g.codPeli join videoclub v on g.nif=v.nif where localidad= ?";

		try (PreparedStatement preparedStatement = conectorBD.conexion.prepareStatement(query)) {
			preparedStatement.setString(1, loc);

			ResultSet resultSet = preparedStatement.executeQuery();

			// Verifica si hay resultados
			if (!resultSet.isBeforeFirst()) {
				System.out.println("No se encontró ninguna reserva para la localidad: " + loc);
			} else {
				System.out.println("Todas las reservas:");
				while (resultSet.next()) { // Iterar sobre los resultados
					System.out.println("Código: " + resultSet.getInt("count(codigo)"));

				}
			}
		} catch (SQLException e) {
			System.out.println("Error al obtener las reservas: " + e.getMessage());
			e.printStackTrace();
		}

	}

	// Este metodo cuenta los usuarios mediante el count
	public static void ContarUsuarios(Usuario usuario) throws SQLException {
		System.out.println("\n--- Contar Todos Los Usuarios ---");

		// Consulta para filtrar las reservas por DNI
		String query = "SELECT  count(dni) FROM usuario";

		try (PreparedStatement preparedStatement = conectorBD.conexion.prepareStatement(query)) {
			ResultSet resultSet = preparedStatement.executeQuery();

			// Verifica si hay resultados
			if (!resultSet.isBeforeFirst()) {
				System.out.println("No se encontró ninguna reserva para el DNI: " + usuario);
			} else {
				System.out.println("Todas las reservas:");
				while (resultSet.next()) { // Iterar sobre los resultados
					System.out.println("Cantidad de usuarios: " + resultSet.getString("count(dni)"));

				}
			}
		} catch (SQLException e) {
			System.out.println("Error al obtener las reservas: " + e.getMessage());
			e.printStackTrace();
		}

	}

	// Este metodo calcula la suma totoal de dinero con el sum
	public static void PrecioTotal(Usuario usuario) throws SQLException {
		System.out.println("\n--- Contar Todos Los Usuarios ---");

		// Consulta para filtrar las reservas por DNI
		String query = "SELECT sum(precio) FROM pelicula p join reserva r on p.codigo=r.codigoPelicula where p.codigo=codigoPelicula";
		;

		try (PreparedStatement preparedStatement = conectorBD.conexion.prepareStatement(query)) {
			ResultSet resultSet = preparedStatement.executeQuery();

			// Verifica si hay resultados
			if (!resultSet.isBeforeFirst()) {
				System.out.println("No se encontró ninguna reserva para el DNI: " + usuario);
			} else {
				System.out.println("Todas las reservas:");
				while (resultSet.next()) { // Iterar sobre los resultados
					System.out.println("Precio Total: " + resultSet.getString("sum(precio)"));

				}
			}
		} catch (SQLException e) {
			System.out.println("Error al obtener las reservas: " + e.getMessage());
			e.printStackTrace();

		}
	}


public static void MostrarUsuarios(Usuario usuario) throws SQLException {
	System.out.println("\n--- Contar Todos Los Usuarios ---");

	// Consulta para filtrar las reservas por DNI
	String query = "SELECT * FROM usuario";

	try (PreparedStatement preparedStatement = conectorBD.conexion.prepareStatement(query)) {
		ResultSet resultSet = preparedStatement.executeQuery();

		// Verifica si hay resultados
		if (!resultSet.isBeforeFirst()) {
			System.out.println("No se encontró ningun usuario");
		} else {
			System.out.println("Todos los usuarios:");
			while (resultSet.next()) { // Iterar sobre los resultados
				System.out.println("Email: " + resultSet.getString("email") + ", Contraseña: "
						+ resultSet.getString("contraseña") + ", Nombre: " + resultSet.getString("nombre") + ", Rol: "
						+ resultSet.getString("rol") + ", Dni: " + resultSet.getString("dni"));

			}
		}
	} catch (SQLException e) {
		System.out.println("Error al obtener las reservas: " + e.getMessage());
		e.printStackTrace();
	}

}

@Override
public void mostrar(Usuario usuario) {
	System.out.println("\n--- Contar Todos Los Usuarios ---");

	// Consulta para filtrar las reservas por DNI
	String query = "SELECT * FROM usuario";

	try (PreparedStatement preparedStatement = conectorBD.conexion.prepareStatement(query)) {
		ResultSet resultSet = preparedStatement.executeQuery();

		// Verifica si hay resultados
		if (!resultSet.isBeforeFirst()) {
			System.out.println("No se encontró ningun usuario");
		} else {
			System.out.println("Todos los usuarios:");
			while (resultSet.next()) { // Iterar sobre los resultados
				System.out.println("Email: " + resultSet.getString("email") + ", Contraseña: "
						+ resultSet.getString("contraseña") + ", Nombre: " + resultSet.getString("nombre") + ", Rol: "
						+ resultSet.getString("rol") + ", Dni: " + resultSet.getString("dni"));

			}
		}
	} catch (SQLException e) {
		System.out.println("Error al obtener las reservas: " + e.getMessage());
		e.printStackTrace();
	}
	
	
}
}