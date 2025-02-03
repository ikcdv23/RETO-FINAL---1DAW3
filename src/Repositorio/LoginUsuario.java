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
	
	
    public static Usuario iniciarSesion(Scanner scanner,Videoclub videoclub) throws SQLException  {
        // Información de conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/videoclub";
        String usuarioBD = "root";
        String contrasenaBD = "1DAW3_BBDD";

       
            // Solicitar las credenciales al usuario
            System.out.print("Ingrese su email: ");
            String email = scanner.nextLine();

            System.out.print("Ingrese su contraseña: ");
            String contra = scanner.nextLine();
            
            Usuario usuario=new Usuario(null,null,null,null,null);


            // Conexión a la base de datos
            Connection conexion = DriverManager.getConnection(url, usuarioBD, contrasenaBD);

            // Consulta SQL para verificar las credenciales
            String consulta = "SELECT * FROM usuario WHERE email = ? AND contraseña = ?";
            PreparedStatement sentencia;
			try {
				sentencia = conexion.prepareStatement(consulta);
				sentencia.setString(1, email);
	            sentencia.setString(2, contra);

	            ResultSet resultado = sentencia.executeQuery();

	            // Verificar si las credenciales son correctas
	            if (resultado.next()) {
	                System.out.println("¡Inicio de sesión exitoso!");
	                String nombre= resultado.getString("nombre");
	                String dni=resultado.getString("dni");
	                 email=resultado.getString("email");
	                String contraseña =resultado.getString("contraseña");
	                String rol=resultado.getString("rol");
	                
	                usuario=new Usuario(nombre,dni,email,contraseña,rol);
	                try {

	                   

	                videoclub= Menus.elegirOficina(usuario);

	                } catch (SQLException e) {
	                    e.printStackTrace();
	                    System.out.println("Ocurrió un error al regresar al menú inicial.");
	                }
	                resultado.close();
	                sentencia.close();
	                conexion.close();
	            } else {
	                System.out.println("Usuario o contraseña incorrectos.");
	                try {
	                    Menus.menuInicial();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                    System.out.println("Ocurrió un error al regresar al menú inicial.");
	                }
	            }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return usuario;
            

      
            // Cerrar la conexión
            
            
        
    }
}

