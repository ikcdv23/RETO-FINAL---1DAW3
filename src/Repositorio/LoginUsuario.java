package Repositorio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Clases.Usuario;
import Clases.Videoclub;
import funciones.Menus;

public class LoginUsuario {

    private static final String URL = "jdbc:mysql://localhost:3306/videoclub";
    private static final String USUARIO_BD = "root";
    private static final String CONTRASENA_BD = "1DAW3_BBDD";

    public static Usuario iniciarSesion(Scanner scanner, Videoclub videoclub) throws SQLException {
        System.out.print("Ingrese su email: ");
        String email = scanner.nextLine().trim();

        System.out.print("Ingrese su contraseña: ");
        String contra = scanner.nextLine().trim();

        if (email.isEmpty() || contra.isEmpty()) {
            System.out.println("Email o contraseña no pueden estar vacíos.");
            return null;
        }

        Usuario usuario = null;
        String consulta = "SELECT nombre, dni, email, contraseña, rol FROM usuario WHERE email = ? AND contraseña = ?";

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO_BD, CONTRASENA_BD);
             PreparedStatement sentencia = conexion.prepareStatement(consulta)) {

            sentencia.setString(1, email);
            sentencia.setString(2, contra);

            try (ResultSet resultado = sentencia.executeQuery()) {
                if (resultado.next()) {
                    System.out.println("¡Inicio de sesión exitoso!");
                    String nombre = resultado.getString("nombre");
                    String dni = resultado.getString("dni");
                    String contraseña = resultado.getString("contraseña");
                    String rol = resultado.getString("rol");

                     usuario = new Usuario(nombre, dni, email, contraseña, rol);

                    if (verificarAdmin(email, contra)) {
                        System.out.println("Has iniciado sesión como admin");
                        Menus.menuAdministrador(usuario, videoclub);
                    } else {
                        videoclub = Menus.elegirOficina(usuario);
                    }
                } else {
                    System.out.println("Usuario o contraseña incorrectos.");
                    Menus.menuInicial();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error en la conexión con la base de datos.");
        }

        return usuario;
    }

    public static boolean verificarAdmin(String email, String contrasena) {
        String sql = "SELECT rol FROM usuario WHERE email = ? AND contraseña = ?";

        try (Connection conn = DriverManager.getConnection(URL, USUARIO_BD, CONTRASENA_BD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, contrasena);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return "Administrador".equalsIgnoreCase(rs.getString("rol"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
