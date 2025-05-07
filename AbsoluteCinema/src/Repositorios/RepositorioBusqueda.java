package Repositorios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RepositorioBusqueda {
	public static ArrayList<Pelicula> busca(String texto)  {
 ArrayList<Pelicula> peliculas = new ArrayList<Pelicula>();
 String query="select * from productos where nombre like ? ";
 try(PreparedStatement preparedStatement = conectorBD.conexion.prepareStatement(query)){
	
		 String busqueda="%"+texto+"%";
		 preparedStatement.setString(1, busqueda);
		 ResultSet resultSet=preparedStatement.executeQuery();
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
	 } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 
 return peliculas;
}
}
