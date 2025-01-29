package funciones;
import java.util.Scanner;
import Clases.Pelicula;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import funciones.Menus;
import conectorBD.conectorBD;
import java.sql.Statement;



public class ConsultarPeli {
	public  static Scanner scanner= new Scanner(System.in);
	public static  void MostarPelisPorGenero(String genero) throws SQLException {
			 System.out.println("\n--- Mostrar Peliculas por genero ---");
		

			 //Hace la consulta para filtrar las pelis por genero
			 String query = "SELECT * FROM pelicula WHERE genero = ?";
			 try (PreparedStatement preparedStatement = conectorBD.conexion.prepareStatement(query)) {
			     preparedStatement.setString(1, genero);
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
					Menus.menuSecundario(scanner);
					break;
			 	}
			 }
	
	public static void MostarPelisPorPrecio(int precio) throws SQLException {
			 System.out.println("\n--- Mostrar peliculas por precio inferior al introducido ---");
			
			 


			 String query = "SELECT * FROM pelicula WHERE precio < ?";
			 try (PreparedStatement preparedStatement = conectorBD.conexion.prepareStatement(query)) {
			     preparedStatement.setInt(1, precio);
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
					Menus.menuSecundario(scanner);
					break;
			 	}
			 }
			 
	
	
	public static void realizarReserva(int codigo) {

		System.out.println("\n--- Elige la pelicula que quieres reservar por el nombre ---");
		

		String query = "SELECT * FROM pelicula WHERE nombre = ?";
		try (PreparedStatement preparedStatement = conectorBD.conexion.prepareStatement(query)) {
			preparedStatement.setInt(1, codigo);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (!resultSet.isBeforeFirst()) {
				System.out.println("No se encontraron peliculas el nombre: " + codigo);
			} else {
				query = "INSERT INTO reserva (codigo,fechaReserva,fechaEntrega,dni,codigoReserva) values(?,?,?,?,?)";
				while (resultSet.next()) {
					System.out.println("Se ha reservado correctamente la pelicula");
					try {
						System.out.println("Nombre: " + resultSet.getString("nombre"));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
		}
		
		
		
	}
	

	
	public static  void MostrarPeliculaPorNombre(String nombre) throws SQLException {
		 System.out.println("\n--- Mostrar Peliculas por nombre ---");
		 System.out.print("Ingresa el genero: ");


		 String query = "SELECT * FROM pelicula WHERE nombre = ?";
		 try (PreparedStatement preparedStatement =conectorBD.conexion.prepareStatement(query)) {
		     preparedStatement.setString(1, nombre);
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
				Menus.menuSecundario(scanner);
				break;
		 	}
		 }
		 
	
	public static  void MostrarTodasPeliculas(Object object) throws SQLException {
		//Introduce el genero por el que quieres que te clasifique
			 System.out.println("\n--- Mostrar Peliculas por nombre ---");
			 System.out.print("Ingresa el genero: ");

			 //HAce la consulta para filtrar
			 String query = "SELECT * FROM pelicula";
			 try (PreparedStatement preparedStatement =conectorBD.conexion.prepareStatement(query)) {
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
					Menus.menuSecundario(scanner);
					break;
			 	}
			 }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}
	

