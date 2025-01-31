package Main;
import java.sql.SQLException;
import java.util.Scanner;

import Clases.Usuario;
import Clases.Videoclub;
import conectorBD.conectorBD;
import funciones.Menus;

public class Main {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Scanner sc= new Scanner(System.in);
		conectorBD.conectar();
		
		Usuario usuario = Menus.menuInicial(sc);
		Videoclub videoclub=new Videoclub (null,null);
		Menus.menuSecundario(sc,videoclub,usuario);
		
		conectorBD.cerrarConexion();
		
		sc.close();
		
	}
}
