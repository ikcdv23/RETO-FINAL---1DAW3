package Main;
import java.sql.SQLException;
import java.util.Scanner;
import conectorBD.conectorBD;
import funciones.Menus;

public class Main {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Scanner sc= new Scanner(System.in);
		conectorBD.conectar();
		
		
		Menus.menuInicial(sc);
		
		conectorBD.cerrarConexion();
		
		sc.close();
		
	}

}
