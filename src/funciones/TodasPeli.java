package funciones;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import conectorBD.conectorBD;

public class TodasPeli {
	private  static Scanner scanner= new Scanner(System.in);
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
			 }
}