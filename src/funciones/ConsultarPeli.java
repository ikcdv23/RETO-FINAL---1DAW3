package funciones;

import java.util.Calendar;
import java.util.Scanner;
import Clases.Pelicula;
import Clases.Usuario;
import Clases.Videoclub;

import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import funciones.Menus;
import conectorBD.conectorBD;
import java.sql.Statement;




public class ConsultarPeli {
	public  static Scanner scanner= new Scanner(System.in);
	public static  void MostarPelisPorGenero(String genero,Videoclub videoclub) throws SQLException {
			 System.out.println("\n--- Mostrar Peliculas por genero ---");
		

			 //Hace la consulta para filtrar las pelis por genero
			 String query = "SELECT p.nombre, v.localidad, p.codigo, p.autor, p.precio, p.genero from pelicula p join guardar g on p.codigo=g.codPeli join videoclub v on g.nif=v.nif where genero= ? and localidad = ?";
			 try (PreparedStatement preparedStatement = conectorBD.conexion.prepareStatement(query)) {
			     preparedStatement.setString(1, genero);
			     preparedStatement.setString(2, videoclub.getLocalidad());
			     ResultSet resultSet = preparedStatement.executeQuery();
			     //Si no se  se encuentra el genero muestra el mensaje
			     if (!resultSet.isBeforeFirst()) {
			         System.out.println("No se encontraron peliculas con ese genero: " + genero);
			     } else {
			    	 //Si el genero concuerda muestra las peliculas
			         while (resultSet.next()) {
			        	 System.out.println("Nombre: " + resultSet.getString("nombre") +
			                     ", Codigo: " + resultSet.getInt("codigo") +
			                     ", Autor: " + resultSet.getString("autor") +
			                     ", Precio: " + resultSet.getInt("precio") +
			                     ", Genero: " + resultSet.getString("genero"));
			        	 
			                     
			         	}
			         }
			     }
			 
			 
			 System.out.println("1. volver al menu principal");
			 int opcion=scanner.nextInt();
			 switch (opcion) {

				case 1:
					Menus.menuSecundario( videoclub, null);
					break;
			 	}
			 }
	
	public static  void MostarPelisPorPrecio(double precio,Videoclub videoclub) throws SQLException {
			 System.out.println("\n--- Mostrar peliculas por precio inferior al introducido ---");
			
			 


			 String query = "SELECT p.nombre, v.localidad, p.codigo, p.autor, p.precio, p.genero from pelicula p join guardar g on p.codigo=g.codPeli join videoclub v on g.nif=v.nif where precio < ? and localidad = ?";;
			 try (PreparedStatement preparedStatement = conectorBD.conexion.prepareStatement(query)) {
			     preparedStatement.setDouble(1, precio);
			     preparedStatement.setString(2, videoclub.getLocalidad());
			     ResultSet resultSet = preparedStatement.executeQuery();

			     if (!resultSet.isBeforeFirst()) {
			         System.out.println("No se encontraron peliculas con precio inferior: " + precio);
			     } else {
			         while (resultSet.next()) {
			        	 System.out.println("Nombre: " + resultSet.getString("nombre") +
			                     ", Codigo: " + resultSet.getInt("codigo") +
			                     ", Autor: " + resultSet.getString("autor") +
			                     ", Precio: " + resultSet.getInt("precio") +
			                     ", Genero: " + resultSet.getString("genero"));
			                     
			         	}
			         }
			     }
			 
			 System.out.println("1. volver al menu principal");
			 int opcion=scanner.nextInt();
			 switch (opcion) {

				case 1:
					Menus.menuSecundario( videoclub, null);
					break;
			 	}
			 }
			 
	
	
	public static void realizarReserva(int codigo, Usuario usuario,Videoclub videoclub) throws SQLException {
	    // Consulta para verificar si la película existe
	    String queryCheck = "SELECT COUNT(*) FROM pelicula WHERE codigo = ? ";
	    String queryInsert = "INSERT INTO reserva (codigo, fechaReserva, fechaEntrega, dni, codigoPelicula) VALUES (DEFAULT, ?, ?, ?, ? )";

	    try (PreparedStatement checkStmt = conectorBD.conexion.prepareStatement(queryCheck)) {
	        checkStmt.setInt(1, codigo);
	        
	        try (ResultSet resultSet = checkStmt.executeQuery()) {
	        	 if (!resultSet.isBeforeFirst()) {
	    	         System.out.println("No se encontraron películas para el codigo: " + codigo);
	        	 }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw e; // Re-lanzamos la excepción para que el llamador pueda manejarla
	    }

	    // Insertar la reserva si la película existe
	    try (PreparedStatement preparedStatement = conectorBD.conexion.prepareStatement(queryInsert)) {
	    	Timestamp timestampActual = new Timestamp(System.currentTimeMillis());
	        
	    	Calendar calendar = Calendar.getInstance();
	        calendar.setTimeInMillis(timestampActual.getTime());
	        calendar.add(Calendar.DAY_OF_MONTH, 15);
	        
	        Timestamp timestampFuturo = new Timestamp(calendar.getTimeInMillis());
	        
	        
	        preparedStatement.setTimestamp(1, timestampActual);
	        preparedStatement.setTimestamp(2, timestampFuturo);
	        preparedStatement.setString(3, usuario.getDNI());
	        preparedStatement.setInt(4, codigo);
	        
	        
	        int filasAfectadas = preparedStatement.executeUpdate();
	        if (filasAfectadas > 0) {
	            System.out.println("Reserva realizada con éxito.");
	        } else {
	            System.out.println("No se pudo realizar la reserva.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw e;
	    }
	}
	
	
	
	
	

	
	public static  void MostrarPeliculaPorNombre(String nombre,Videoclub videoclub) throws SQLException {
		 System.out.println("\n--- Mostrar Peliculas por nombre ---");
		 System.out.print("Ingresa el genero: ");


		 String query = "SELECT p.nombre, v.localidad, p.codigo, p.autor, p.precio, p.genero from pelicula p join guardar g on p.codigo=g.codPeli join videoclub v on g.nif=v.nif where nombre = ? and localidad = ?";
		 try (PreparedStatement preparedStatement =conectorBD.conexion.prepareStatement(query)) {
		     preparedStatement.setString(1, nombre);
		     preparedStatement.setString(2, videoclub.getLocalidad());
		     ResultSet resultSet = preparedStatement.executeQuery();

		     if (!resultSet.isBeforeFirst()) {
		         System.out.println("No se encontro la pelicula con ese nombre: " + nombre);
		     } else {
		         while (resultSet.next()) {
		        	 System.out.println("Nombre: " + resultSet.getString("nombre") +
		                     ", Codigo: " + resultSet.getInt("codigo") +
		                     ", Autor: " + resultSet.getString("autor") +
		                     ", Precio: " + resultSet.getInt("precio") +
		                     ", Genero: " + resultSet.getString("genero"));
		                     
		         	}
		         }
		     }
		 System.out.println("1. volver al menu principal");
		 int opcion=scanner.nextInt();
		 switch (opcion) {

			case 1:
				Menus.menuSecundario( videoclub, null);
				break;
		 	}
		 }
		 
	
	public static  void MostrarTodasPeliculas(Object object,Videoclub videoclub) throws SQLException {
		//Introduce el genero por el que quieres que te clasifique
			 System.out.println("\n--- Mostrar Todas Las Peliculas ---");
			 System.out.print("Ingresa el genero: ");

			 //HAce la consulta para filtrar
			 String query = "SELECT p.nombre, v.localidad, p.codigo, p.autor, p.precio, p.genero from pelicula p join guardar g on p.codigo=g.codPeli join videoclub v on g.nif=v.nif where localidad = ?";;
			 try (PreparedStatement preparedStatement =conectorBD.conexion.prepareStatement(query)) {
				 preparedStatement.setString(1, videoclub.getLocalidad());
			     ResultSet resultSet = preparedStatement.executeQuery();

			     if (!resultSet.isBeforeFirst()) {
			         System.out.println("Hubo un error");
			     } else {
			         while (resultSet.next()) {
			        	 //Muestra las peliculas con el genero pedido
			        	 System.out.println("Todas las peliculas");
			        	 System.out.println("Nombre: " + resultSet.getString("nombre") +
			                     ", Codigo: " + resultSet.getInt("codigo") +
			                     ", Autor: " + resultSet.getString("autor") +
			                     ", Precio: " + resultSet.getInt("precio") +
			                     ", Genero: " + resultSet.getString("genero"));
			                     
			         	}
			         }
			     }
			 System.out.println("1. volver al menu principal");
			 int opcion=scanner.nextInt();
			 switch (opcion) {

				case 1:
					Menus.menuSecundario( videoclub, null);
					break;
			 	}
			 }
	
	
	
	
	
	
	
	
	
	
	
	public  static void  volverMenu() {
		System.out.println("1. volver al menu principal");
		 int opcion=scanner.nextInt();
		 switch (opcion) {

			case 1:
			try {
				Menus.menuSecundario(null, null);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				break;
		 	}
		 }


	public static void MostrarReservas(Usuario usuario, Videoclub videoclub) throws SQLException {
	    System.out.println("\n--- Mostrar Todas Las Reservas ---");
	    
	    // Consulta para filtrar las reservas por DNI
	    String query = "SELECT * FROM reserva ";
	    
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
	            Menus.menuSecundario( videoclub, null);
	        }
	    } else {
	        System.out.println("Opción inválida. Regresando al menú principal.");
	        scanner.nextLine(); // Limpiar la entrada
	        Menus.menuSecundario( videoclub, null);
	    }
	}
}
