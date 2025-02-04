package funciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import funciones.AdminPeli;
import Clases.Usuario;
import Clases.Videoclub;
import Repositorio.LoginUsuario;
import Repositorio.Registrar;
import conectorBD.conectorBD;

public class Menus {
    private static final Scanner sc = new Scanner(System.in);

    public static Usuario menuInicial() throws SQLException {
        System.out.println("Seleccione una opción:");
        System.out.println("1. Registrarse");
        System.out.println("2. Iniciar sesión");
        
        int opcion = obtenerOpcion(1, 2);

        if (opcion == 1) {
            Registrar.Registro();
            return new Usuario(null,"", null, null, null);
        } else {
            Videoclub videoclub = new Videoclub(null);
            return LoginUsuario.iniciarSesion(sc, videoclub);
        }
    }

  

        public static Videoclub elegirOficina(Usuario usuario) throws SQLException {
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            Videoclub videoclub=null;
            try {
                conn = conectorBD.conexion;
                String query = "SELECT DISTINCT localidad FROM videoclub";
                stmt = conn.prepareStatement(query);
                rs = stmt.executeQuery();
                Set<String> localidadesDisponibles = new HashSet<>();
                while (rs.next()) {
                    localidadesDisponibles.add(rs.getString("localidad").toLowerCase());
                }
                String loc;
                do {
                    System.out.println("Elige la oficina (Irun / Donosti / Gasteiz): ");
                    loc = sc.nextLine().trim().toLowerCase();
                    if (!localidadesDisponibles.contains(loc)) {
                        System.out.println("Ubicación no válida. Por favor, elige entre Irun, Donosti o Gasteiz.");
                    }
                } while (!localidadesDisponibles.contains(loc));
                 videoclub = new Videoclub(loc);
                System.out.println("Has seleccionado la oficina de: " + loc);
                Menus.menuSecundario(sc, videoclub, usuario);  // ✅ Se pasa el usuario ya autenticado
            } finally {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            }
            return videoclub;
        }

    public static void menuSecundario(Scanner sc,Videoclub videoclub, Usuario usuario) throws SQLException {
    	
        while (true) {
            System.out.println("\nBienvenido a Global Cinesa. Elija una opción:");
            System.out.println("1. Mostrar películas por género");
            System.out.println("2. Mostrar películas por nombre");
            System.out.println("3. Mostrar películas por precio");
            System.out.println("4. Mostrar todas las películas");
            System.out.println("5. Reservar película");
            System.out.println("6. Mostrar reservas");
            System.out.println("7. Salir");

            int opcion = obtenerOpcion(1, 7);

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el género (thriller o acción): ");
                    ConsultarPeli.MostarPelisPorGenero(sc.nextLine().trim(), videoclub);
                    break;
                case 2:
                    System.out.print("Ingrese el nombre de la película: ");
                    ConsultarPeli.MostrarPeliculaPorNombre(sc.nextLine(), videoclub);
                    break;
                case 3:
                    System.out.print("Ingrese el precio máximo: ");
                    double precio = sc.nextDouble();
                    sc.nextLine(); 
                    ConsultarPeli.MostarPelisPorPrecio(precio, videoclub);
                    break;
                case 4:
                    ConsultarPeli.MostrarTodasPeliculas(null, videoclub);
                    break;
                case 5:
                    System.out.print("Ingrese el código de la película: ");
                    int codigo = sc.nextInt();
                    System.out.println("Ingrese que dia quieres iniciar el alquiler");
                    String fechain = sc.next();
                    System.out.println("Ingrese la fecha en la que va a acabar el alquiler");
                    String fechaout = sc.next();
                    sc.nextLine();
                    ConsultarPeli.realizarReserva(codigo, usuario, videoclub, fechain, fechaout);
               
                    break;
                case 6:
                    ConsultarPeli.MostrarReservas(usuario, videoclub);
                    break;
                case 7:
                    System.out.println("Saliendo del sistema...");
                    return;
            }
        }
    }
    public static void menuAdministrador(Usuario usuario,Videoclub videoclub) throws SQLException {
    	while (true) {
    	System.out.println("---------Has iniciado sesion como administrador--------");
    	System.out.println("1.Cantidad de reservas");
    	System.out.println("2.Cuantas reservas por localizacion");
    	System.out.println("3.Cantidad de usuarios");
    	System.out.println("4.El dinero ganado en total");
    	
    	int opcion = obtenerOpcion(1, 7);

        switch (opcion) {
            case 1:
                AdminPeli.ContarReservas(usuario,videoclub);
                break;
            case 2:
            	System.out.println("Ingresa una localidad (Irun/Gasteiz/Donosti)");
               String loc=sc.nextLine();
            	AdminPeli.ContarReservasLoc(usuario, loc);
                break;
            case 3:
            	AdminPeli.ContarUsuarios(usuario);
                break;
            case 4:
            	AdminPeli.PrecioTotal(usuario);
                break;
        	}
    	}
    }

    private static int obtenerOpcion(int min, int max) {
        int opcion;
        while (true) {
            System.out.print("Ingrese una opción: ");
            if (sc.hasNextInt()) {
                opcion = sc.nextInt();
                sc.nextLine(); 
                if (opcion >= min && opcion <= max) {
                    return opcion;
                }
            } else {
                sc.nextLine();
            }
            System.out.println("Opción inválida, intente nuevamente.");
        }
    }
}
