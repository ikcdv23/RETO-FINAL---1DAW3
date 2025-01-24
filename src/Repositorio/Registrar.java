package Repositorio;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Registrar {

    public static void Registro() {
        // Información de conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/videoclub";
        String usuarioBD = "tu_usuario";
        String contrasenaBD = "tu_contraseña";

        try (Scanner scanner = new Scanner(System.in)) {
            // Solicitar los datos del nuevo usuario
            System.out.print("Ingrese un nombre de usuario: ");
            String username = scanner.nextLine();

            System.out.print("Ingrese una contraseña: ");
            String password = scanner.nextLine();

            // Conexión a la base de datos
            Connection conexion = DriverManager.getConnection(url, usuarioBD, contrasenaBD);

            // Consulta SQL para insertar el nuevo usuario
            String consulta = "INSERT INTO usuarios (username, password) VALUES (?, ?)";
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, username);
            sentencia.setString(2, password);

            // Ejecutar la inserción
            int filasAfectadas = sentencia.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("¡Usuario registrado con éxito!");
            } else {
                System.out.println("Hubo un error al registrar el usuario.");
            }

            // Cerrar la conexión
            sentencia.close();
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
