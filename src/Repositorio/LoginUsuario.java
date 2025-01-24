package Repositorio;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class LoginUsuario {

    public static void iniciarSesion()  {
        // Información de conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/videoclub";
        String usuarioBD = "root";
        String contrasenaBD = "1DAW3_BBDD";

        try (Scanner scanner = new Scanner(System.in)) {
            // Solicitar las credenciales al usuario
            System.out.print("Ingrese su nombre de usuario: ");
            String username = scanner.nextLine();

            System.out.print("Ingrese su contraseña: ");
            String password = scanner.nextLine();
            
            

            // Conexión a la base de datos
            Connection conexion = DriverManager.getConnection(url, usuarioBD, contrasenaBD);

            // Consulta SQL para verificar las credenciales
            String consulta = "SELECT * FROM usuarios WHERE username = ? AND password = ?";
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, username);
            sentencia.setString(2, password);

            ResultSet resultado = sentencia.executeQuery();

            // Verificar si las credenciales son correctas
            if (resultado.next()) {
                System.out.println("¡Inicio de sesión exitoso!");
            } else {
                System.out.println("Usuario o contraseña incorrectos.");
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
