package Repositorios;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Repositorios.conectorBD;


public class RepositorioUsuario {

	public static boolean verificarUsuario(String usuario, String password) {
		String query = "SELECT * FROM absolute_cinema.cliente where usuario = ? and password = ?";
		try (PreparedStatement preparedStatement = conectorBD.conexion.prepareStatement(query)) {

			preparedStatement.setString(1, usuario);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();

			// Si no se se encuentra el genero muestra el mensaje
			if (resultSet.next()) {
				return true;
			}

			else {
				return false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

public static void insertar(String usuario,String contraseña)  {

	 String query="insert into usuarios (usuario,password) values  (?,?)";
	 try(PreparedStatement preparedStatement = conectorBD.conexion.prepareStatement(query)){
		{
			 
			 preparedStatement.setString(1,usuario);
			 preparedStatement.setString(2,contraseña);
			 preparedStatement.executeUpdate();
		 }
	 } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
		public static void insertarProducto(String nombre,String marca,String categoria,int talla,double precio)  {

			 String query="insert into productos (nombre, marca,categoria, talla, precio) values  (?,?,?,?,?)";
			 try(PreparedStatement preparedStatement = conectorBD.conexion.prepareStatement(query)){
				{
					 
					 preparedStatement.setString(1,nombre);
					 preparedStatement.setString(2,marca);
					 preparedStatement.setString(3,categoria);
					 preparedStatement.setInt(4,talla);
					 preparedStatement.setDouble(5,precio);
					 preparedStatement.executeUpdate();
				 }
			 } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}