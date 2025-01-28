package funciones;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import conectorBD.conectorBD;

public class HacerReserva {
	private  static Scanner scanner= new Scanner(System.in);
	public static  void MostarPelisPorPrecio(int nombre) throws SQLException {
			 System.out.println("\n--- Elige la pelicula que quieres reservar por el nombre ---");
			 System.out.print("Ingresa el nombre de la pelicula");


			 String query = "SELECT * FROM peliculas WHERE nombre = ?";
			 try (PreparedStatement preparedStatement = conectorBD.conexion.prepareStatement(query)) {
			     preparedStatement.setInt(1, nombre);
			     ResultSet resultSet = preparedStatement.executeQuery();

			     if (!resultSet.isBeforeFirst()) {
			         System.out.println("No se encontraron peliculas el nombre: " + nombre);
			     } else {
			    	query = "INSERT INTO reservas (codigo,DNI,fecha_reserva,codigo_reserva) values(?,?,?,?,?,?)";
			         while (resultSet.next()) {
			        	 System.out.println("Se ha reservado correctamente la pelicula");
			        	 System.out.println("Nombre: " + resultSet.getString("nombre"));
			         	}
			         }
			     }
			 }

	}
	

