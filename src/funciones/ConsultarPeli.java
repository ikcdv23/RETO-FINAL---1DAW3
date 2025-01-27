package funciones;
import java.util.Scanner;
import Clases.Pelicula;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import funciones.Menus;
import conectorBD.conectorBD;

public class ConsultarPeli {
	private  static Scanner scanner= new Scanner(System.in);
	public static  void MostarPelisPorGenero() throws SQLException {
			 System.out.println("\n--- Mostrar Peliculas por genero ---");
			 System.out.print("Ingresa el genero (thriller o accion): ");


			 String query = "SELECT * FROM peliculas WHERE genero = ?";
			 try (PreparedStatement preparedStatement = conexion.prepareStatement(query)) {
			     preparedStatement.setString(1, genero);
			     ResultSet resultSet = preparedStatement.executeQuery();

			     if (!resultSet.isBeforeFirst()) {
			         System.out.println("No se encontraron peliculas con ese genero: " + genero);
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
	

