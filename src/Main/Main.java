package Main;
import java.sql.SQLException;

import conectorBD.conectorBD;
import funciones.Menus;

public class Main {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		conectorBD.conectar();
		
		Menus.menuInicial();
		
		conectorBD.cerrarConexion();
		
	}

}
