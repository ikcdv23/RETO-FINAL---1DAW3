package Repositorio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import funciones.Menus;

public class Registrar {
	private  static Scanner sc= new Scanner(System.in);

    public static void Registro() {
        // Información de conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/videoclub";
        String usuarioBD = "root";
        String contrasenaBD = "1DAW3_BBDD";

        try (Scanner scanner = new Scanner(System.in)) {
            // Solicitar los datos del nuevo usuario
            System.out.print("Ingrese un nombre de usuario: ");
            String nombre = scanner.nextLine();

            String dni;
            while (true) {
                System.out.print("Ingrese tu DNI: ");
                dni = scanner.nextLine();

                // Verificar si el DNI ya existe
                if (verificarDni(dni, url, usuarioBD, contrasenaBD)) {
                    System.out.println("El DNI ya esta registrado. Por favor, introduce otro.");
                } else {
                    break; // Salir del bucle si el DNI no existe
                }
            }

            System.out.print("Ingrese tu email: ");
            String email = scanner.nextLine();

            System.out.print("Ingrese una contraseña: ");
            String contra = scanner.nextLine();
            
            System.out.print("Ingrese su rol: (administrador/cliente) ");
            String rol = scanner.nextLine();

            // Conexión a la base de datos
            Connection conexion = DriverManager.getConnection(url, usuarioBD, contrasenaBD);

            // Consulta SQL para insertar el nuevo usuario
            String consulta = "INSERT INTO usuario (nombre, dni, email, contraseña, rol) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, nombre);
            sentencia.setString(2, dni);
            sentencia.setString(3, email);
            sentencia.setString(4, contra);
            sentencia.setString(5, rol);

            // Ejecutar la inserción
            int filasAfectadas = sentencia.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("¡Usuario registrado con exito!");
            } else {
                System.out.println("Hubo un error al registrar el usuario.");
            }
            
            try {
                Menus.menuInicial(sc);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Ocurrió un error al regresar al menú inicial.");
            }
        

            // Cerrar la conexión
            sentencia.close();
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    // Método para verificar si el DNI ya existe en la base de datos
    public static boolean verificarDni(String dni, String url, String usuarioBD, String contrasenaBD) {
        boolean existe = false;
        try {
            // Conexión a la base de datos
            Connection conexion = DriverManager.getConnection(url, usuarioBD, contrasenaBD);

            // Consulta SQL para verificar el DNI
            String consulta = "SELECT COUNT(*) FROM usuario WHERE dni = ?";
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, dni);

            // Ejecutar la consulta
            ResultSet resultado = sentencia.executeQuery();
            if (resultado.next()) {
                existe = resultado.getInt(1) > 0; // Si el conteo es mayor a 0, el DNI ya existe
            }

            // Cerrar la conexión
            resultado.close();
            sentencia.close();
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return existe;
    }
}


