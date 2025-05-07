package Repositorios;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Repositorios.conectorBD;

public class RepositorioPelicula {
	public static ArrayList<Pelicula> cargar(){
 ArrayList<Pelicula> peliculas = new ArrayList<Pelicula>();
 String query="select * from pelicula";
 try(PreparedStatement preparedStatement = conectorBD.conexion.prepareStatement(query)){
	 ResultSet resultSet=preparedStatement.executeQuery();{
		 while(resultSet.next()) {
			 Pelicula p = new Pelicula
					(resultSet.getInt("id_pelicula"), 
					 resultSet.getString("titulo"),
					 resultSet.getString("director"),
					 resultSet.getString("duracion"),
					 resultSet.getString("genero"),
			 		 resultSet.getString("sinopsis"),
			 		 resultSet.getString("pais_origen"),
			 		resultSet.getInt("anio_lanzamiento"));
			 peliculas.add(p);
		 }
	 }
 } catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
 return peliculas;
}
	public static void peli(int id_pelicula, String titulo, String director, String duracion, String genero, String sinopsis,
			String pais_origen, int anio_lanzamiento)  {

		 String query="insert into peliculas (nombre, marca,categoria, talla, precio) values  (?,?,?,?,?)";
		 try(PreparedStatement preparedStatement = conectorBD.conexion.prepareStatement(query)){
			{
				 
				 preparedStatement.setInt(1,id_pelicula);
				 preparedStatement.setString(2,titulo);
				 preparedStatement.setString(3,director);
				 preparedStatement.setString(4,duracion);
				 preparedStatement.setString(5,genero);
				 preparedStatement.setString(6,sinopsis);
				 preparedStatement.setString(7,pais_origen);
				 preparedStatement.setInt(8,anio_lanzamiento);
				 preparedStatement.executeUpdate();
			 }
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
}