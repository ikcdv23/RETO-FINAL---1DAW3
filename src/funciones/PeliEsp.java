package funciones;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PeliEsp {
	private  static Scanner scanner= new Scanner(System.in);
	public static  void MostrarPeliculaPorNombre() throws SQLException {
			 System.out.println("\n--- Mostrar Peliculas por nombre ---");
			 System.out.print("Ingresa el genero: ");


			 String query = "SELECT * FROM peliculas WHERE nombre = ?";
			 try (PreparedStatement preparedStatement = conexion.prepareStatement(query)) {
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
			 }

	}