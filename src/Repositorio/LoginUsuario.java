package Repositorio;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import funciones.Menus;

public class LoginUsuario {
	private  static Scanner sc= new Scanner(System.in);
	
    public static void iniciarSesion()  {
        // Información de conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/videoclub";
        String usuarioBD = "root";
        String contrasenaBD = "1DAW3_BBDD";

        try (Scanner scanner = new Scanner(System.in)) {
            // Solicitar las credenciales al usuario
            System.out.print("Ingrese su email: ");
            String email = scanner.nextLine();

            System.out.print("Ingrese su contraseña: ");
            String contra = scanner.nextLine();
            
            

            // Conexión a la base de datos
            Connection conexion = DriverManager.getConnection(url, usuarioBD, contrasenaBD);

            // Consulta SQL para verificar las credenciales
            String consulta = "SELECT * FROM usuario WHERE email = ? AND contraseña = ?";
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, email);
            sentencia.setString(2, contra);

            ResultSet resultado = sentencia.executeQuery();

            // Verificar si las credenciales son correctas
            if (resultado.next()) {
                System.out.println("¡Inicio de sesión exitoso!");
                try {
                    Menus.menuSecundario(sc);

                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Ocurrió un error al regresar al menú inicial.");
                }
            
            } else {
                System.out.println("Usuario o contraseña incorrectos.");
                try {
                    Menus.menuInicial(sc);
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Ocurrió un error al regresar al menú inicial.");
                }
            }

      
            // Cerrar la conexión
            resultado.close();
            sentencia.close();
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

