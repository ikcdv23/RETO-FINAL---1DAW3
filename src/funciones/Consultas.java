package funciones;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import funciones.Menus;

public class Consultas {
	 public static void insertarAlumnos(Alumno alumno) throws SQLException {
		    String query = "INSERT INTO alumnos (nombre, apellido1, apellido2, fecha_nacimiento, curso, clase) VALUES (?, ?, ?, ?, ?, ?)";

		    // Comprobar si la película ya existe
		        String queryCheck = "SELECT COUNT(*) FROM alumnos WHERE nombre = ?";
		    try (PreparedStatement checkStmt = conexion.prepareStatement(queryCheck)) {

		        checkStmt.setString(1, alumno.getNombre());
		 
		    
		        ResultSet resultSet = checkStmt.executeQuery();
		        resultSet.next();
		        int count = resultSet.getInt(1);
		    
		        if (count > 0) {
		            System.out.println("El alumno \"" + alumno.getNombre() + "\" ya existe en la base de datos y no se insertará nuevamente.");
		            return; // Al devolver un return no se ejecutará el código restante y lo retoma desde el método que lo llamó
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }



		    try (PreparedStatement preparedStatement = conexion.prepareStatement(query)) {
		        preparedStatement.setString(1, alumno.getNombre());
		        preparedStatement.setString(2, alumno.getApellido());
		        preparedStatement.setString(3, alumno.getApellido2());
		        preparedStatement.setString(4, alumno.getFechadenacimiento());
		        preparedStatement.setInt(5, alumno.getClase());
		        preparedStatement.setInt(6, alumno.getCurso());

		        preparedStatement.executeUpdate();
		    }
		}
}
