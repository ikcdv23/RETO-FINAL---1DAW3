package funciones;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Clases.Usuario;
import Clases.Videoclub;
import conectorBD.conectorBD;

public class AdminPeli {
	public  static Scanner scanner= new Scanner(System.in);
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
	                System.out.println(", Código: " + resultSet.getInt("count(codigo)") 
	                        
	                        
	                    );
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al obtener las reservas: " + e.getMessage());
	        e.printStackTrace();
	    }
	    
	    System.out.println("1. Volver al menú principal");
	    System.out.print("Seleccione una opción: ");
	    
	    if (scanner.hasNextInt()) {
	        int opcion = scanner.nextInt();
	        scanner.nextLine(); // Consumir la nueva línea
	        if (opcion == 1) {
	            Menus.menuAdministrador(null, videoclub);
	        }
	    } else {
	        System.out.println("Opción inválida. Regresando al menú principal.");
	        scanner.nextLine(); // Limpiar la entrada
	        Menus.menuAdministrador( null, videoclub);
	    }
	}



	public static void ContarReservasLoc(Usuario usuario, String loc) throws SQLException {
	    System.out.println("\n--- Contar Todas Las Reservas Por Localidad ---");
	    
	    // Consulta para filtrar las reservas por DNI
	    String query = "SELECT p.nombre, v.localidad, p.codigo, p.autor, p.precio, p.genero from pelicula p join guardar g on p.codigo=g.codPeli join videoclub v on g.nif=v.nif  localidad = ?";;;
	    
	    try (PreparedStatement preparedStatement = conectorBD.conexion.prepareStatement(query)) {
	        ResultSet resultSet = preparedStatement.executeQuery();
	        
	        preparedStatement.setString(1, loc);
	        
	        // Verifica si hay resultados
	        if (!resultSet.isBeforeFirst()) {
	            System.out.println("No se encontró ninguna reserva para la localidad: " + loc);
	        } else {
	            System.out.println("Todas las reservas:");
	            while (resultSet.next()) { // Iterar sobre los resultados
	            	System.out.println(", Código: " + resultSet.getInt("count(codigo)"));
	               
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al obtener las reservas: " + e.getMessage());
	        e.printStackTrace();
	    }
	    
	    System.out.println("1. Volver al menú principal");
	    System.out.print("Seleccione una opción: ");
	    
	    if (scanner.hasNextInt()) {
	        int opcion = scanner.nextInt();
	        scanner.nextLine(); // Consumir la nueva línea
	        if (opcion == 1) {
	            Menus.menuAdministrador( null, null);
	        }
	    } else {
	        System.out.println("Opción inválida. Regresando al menú principal.");
	        scanner.nextLine(); // Limpiar la entrada
	        Menus.menuAdministrador( null, null);
	    }
	}

	
public static void ContarUsuarios(Usuario usuario) throws SQLException {
    System.out.println("\n--- Contar Todos Los Usuarios ---");
    
    // Consulta para filtrar las reservas por DNI
    String query = "SELECT count(dni) FROM reserva";
    
    try (PreparedStatement preparedStatement = conectorBD.conexion.prepareStatement(query)) {
        ResultSet resultSet = preparedStatement.executeQuery();
        
        // Verifica si hay resultados
        if (!resultSet.isBeforeFirst()) {
            System.out.println("No se encontró ninguna reserva para el DNI: " + usuario);
        } else {
            System.out.println("Todas las reservas:");
            while (resultSet.next()) { // Iterar sobre los resultados
                System.out.println("Fecha Reserva: " + resultSet.getString("fechaReserva") +
                        ", Código: " + resultSet.getInt("codigo") +
                        ", DNI: " + resultSet.getString("dni") +
                        ", Código Película: " + resultSet.getInt("codigoPelicula"));
            }
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener las reservas: " + e.getMessage());
        e.printStackTrace();
    }
    
    System.out.println("1. Volver al menú principal");
    System.out.print("Seleccione una opción: ");
    
    if (scanner.hasNextInt()) {
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea
        if (opcion == 1) {
            Menus.menuAdministrador(null, null);
        }
    } else {
        System.out.println("Opción inválida. Regresando al menú principal.");
        scanner.nextLine(); // Limpiar la entrada
        Menus.menuAdministrador( null, null);
    }
}

public static void PrecioTotal(Usuario usuario) throws SQLException {
    System.out.println("\n--- Contar Todos Los Usuarios ---");
    
    // Consulta para filtrar las reservas por DNI
    String query = "SELECT sum(precio) FROM pelicula p join reserva r on p.codigo=r.codigoPelicula where p.codigo=codigoPelicula";;
    
    try (PreparedStatement preparedStatement = conectorBD.conexion.prepareStatement(query)) {
        ResultSet resultSet = preparedStatement.executeQuery();
        
        // Verifica si hay resultados
        if (!resultSet.isBeforeFirst()) {
            System.out.println("No se encontró ninguna reserva para el DNI: " + usuario);
        } else {
            System.out.println("Todas las reservas:");
            while (resultSet.next()) { // Iterar sobre los resultados
                System.out.println("Fecha Reserva: " + resultSet.getString("fechaReserva") +
                        ", Código: " + resultSet.getInt("codigo") +
                        ", DNI: " + resultSet.getString("dni") +
                        ", Código Película: " + resultSet.getInt("codigoPelicula"));
            }
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener las reservas: " + e.getMessage());
        e.printStackTrace();
    }
    
    System.out.println("1. Volver al menú principal");
    System.out.print("Seleccione una opción: ");
    
    if (scanner.hasNextInt()) {
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea
        if (opcion == 1) {
            Menus.menuAdministrador (null, null);
        }
    } else {
        System.out.println("Opción inválida. Regresando al menú principal.");
        scanner.nextLine(); // Limpiar la entrada
        Menus.menuAdministrador( null, null);
    }
}
}





