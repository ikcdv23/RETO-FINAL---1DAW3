package funciones;
import java.util.Scanner;
import Clases.Pelicula;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import funciones.Menus;
import conectorBD.conectorBD;

public class FiltrarPrecio {
	private  static Scanner scanner= new Scanner(System.in);
	public static  void MostarPelisPorPrecio(int precio) throws SQLException {
			 System.out.println("\n--- Mostrar peliculas por precio inferior al introducido ---");
			 System.out.print("Ingresa el precio");
			 


			 String query = "SELECT * FROM pelicula WHERE precio < ?";
			 try (PreparedStatement preparedStatement = conectorBD.conexion.prepareStatement(query)) {
			     preparedStatement.setInt(1, precio);
			     ResultSet resultSet = preparedStatement.executeQuery();

			     if (!resultSet.isBeforeFirst()) {
			         System.out.println("No se encontraron peliculas con precio inferior: " + precio);
			     } else {
			         while (resultSet.next()) {
			        	 System.out.println("Nombre: " + resultSet.getString("nombre") +
			                     ", Apellido1: " + resultSet.getInt("codigo") +
			                     ", Apellido2: " + resultSet.getString("autor") +
			                     ", FechaNacimiento: " + resultSet.getInt("precio") +
			                     ", FechaNacimiento: " + resultSet.getString("genero"));
			                     
			         	}
			         }
			     }
			 }

	}
	

