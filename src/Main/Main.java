package Main;

import java.sql.SQLException;
import java.util.Scanner;

import Clases.Usuario;
import Clases.Videoclub;
import conectorBD.conectorBD;
import funciones.Menus;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        try {
            conectorBD.conectar();
            
            // Iniciar sesión o registrarse
			Usuario usuario = Menus.menuInicial();

            // Elegir la oficina antes de acceder al menú secundario
            Videoclub videoclub = Menus.elegirOficina(usuario);

            // Menú de opciones
           
            
            Menus.menuAdministrador(usuario,videoclub);

        } catch (SQLException e) {
            System.err.println("Error en la base de datos: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Cerrar conexión y scanner
            conectorBD.cerrarConexion();
            sc.close();
        }
    }
}
