package funciones;

import java.sql.SQLException;
import java.util.Scanner;

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
            Videoclub videoclub = new Videoclub(null, null);
            return LoginUsuario.iniciarSesion(sc, videoclub);
        }
    }

    public static Videoclub elegirOficina(Usuario usuario) throws SQLException {
        System.out.println("Elige la oficina (Irun, Gasteiz o Donosti):");
        String loc = sc.nextLine().trim();
        
        Videoclub videoclub = new Videoclub(loc);
        menuSecundario(videoclub, usuario);
        return videoclub;
    }

    public static void menuSecundario(Videoclub videoclub, Usuario usuario) throws SQLException {
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
                    sc.nextLine(); 
                    ConsultarPeli.realizarReserva(codigo, usuario, videoclub);
                    ConsultarPeli.volverMenu();
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
