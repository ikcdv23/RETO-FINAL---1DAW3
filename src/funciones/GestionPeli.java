package funciones;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conectorBD.conectorBD;

public class GestionPeli {
	public static  void MostarPelisPorGenero(String genero) throws SQLException {
		 System.out.println("\n--- Mostrar Peliculas por genero ---");
		 System.out.print("Ingresa el genero (thriller o accion): ");
	

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
		                     ", Apellido1: " + resultSet.getInt("codigo") +
		                     ", Apellido2: " + resultSet.getString("autor") +
		                     ", FechaNacimiento: " + resultSet.getInt("precio") +
		                     ", FechaNacimiento: " + resultSet.getString("genero"));
		                     
		         	}
		         }
		     }
		 }

	
	
	
	
	
	
	
	
	
	
	
	
}
