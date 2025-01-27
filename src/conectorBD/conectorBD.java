package conectorBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class conectorBD {	
		public static Connection conexion;

		public static void conectar() {
			//Funcion que carga el driver
			try {

				Class.forName("com.mysql.cj.jdbc.Driver");
				System.out.println("Driver cargado");
				//Obtener conexion con la base de datos
				try {

					conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/videoclub", "root", "1DAW3_BBDD");

					System.out.println("Conexion establecida");

				} catch (Exception e) {
					System.out.println("Error en la conexion");
				}
			} catch (Exception e) {
				System.out.println("Error en el driver");
			}
		}
		//Metodo que cierra la conexion
		public static void cerrarConexion() throws SQLException {
			// TODO Auto-generated method stub
			conexion.close();
		}
	}

