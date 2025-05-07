package Repositorio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Clases.Usuario;
import Clases.Videoclub;
import conectorBD.conectorBD;
import funciones.Menus;

public class LoginUsuario {

	//Le pides al usuario los datos para iniciar sesion 
    public static Usuario iniciarSesion(Scanner scanner, Videoclub videoclub) throws SQLException {
        System.out.print("Ingrese su dni: ");
        String dni = scanner.nextLine().trim();

        System.out.print("Ingrese su contraseña: ");
        String contra = scanner.nextLine().trim();

        if (dni.isEmpty() || contra.isEmpty()) {
            System.out.println("Email o contraseña no pueden estar vacíos.");
            LoginUsuario.iniciarSesion(scanner, videoclub);
            return null;
        }
        //Mediante la consulta se verifica que los datos tengan alguna coincidencia 
        Usuario usuario = null;
        String consulta = "SELECT nombre, dni, email, contraseña, rol FROM usuario WHERE dni = ? AND contraseña = ?";

        try (PreparedStatement sentencia = conectorBD.conexion.prepareStatement(consulta)) {
             
            sentencia.setString(1, dni);
            sentencia.setString(2, contra);

            try (ResultSet resultado = sentencia.executeQuery()) {
            	
                if (resultado.next()) {
                    System.out.println("¡Inicio de sesión exitoso!");
                    String nombre = resultado.getString("nombre");
                     dni = resultado.getString("dni");
                    String email = resultado.getString("email");
                    String contraseña = resultado.getString("contraseña");
                    String rol = resultado.getString("rol");
                    //Se crea un objeto de Usuario para guardar el usuario
                     usuario = new Usuario(nombre, dni, email, contraseña, rol);

                    if (verificarAdmin(dni, contra)) {
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

    public static boolean verificarAdmin(String dni, String contrasena) {
        String sql = "SELECT rol FROM usuario WHERE dni = ? AND contraseña = ?";

        try (PreparedStatement stmt = conectorBD.conexion.prepareStatement(sql)) {;
             

            stmt.setString(1, dni);
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
