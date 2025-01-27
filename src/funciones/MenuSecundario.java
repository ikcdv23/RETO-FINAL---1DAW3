package funciones;

import java.sql.SQLException;
import java.util.Scanner;

public class MenuSecundario {
	private  static Scanner sc= new Scanner(System.in);



	 public static void menuInicial(Scanner sc) throws SQLException {
		int opcion;
	
	System.out.println("1. Mostrar peliculas por genero");
	System.out.println("2. Mostrar peliculas por nombre");
	System.out.println("3. Mostrar peliculas por precio inferior al escrito");
		
	opcion=sc.nextInt();
	sc.nextLine();
	
	switch (opcion) {
	
		case 1:
			ConsultarPeli.MostarPelisPorGenero();
			break;
		case 2:
			PeliEsp.MostrarPeliculaPorNombre();
			break;
		case 3:
			FiltrarPrecio.MostarPelisPorPrecio();
	 }
}
}