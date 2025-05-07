package Main;

import Controller.ControlerLogin;
import Repositorios.conectorBD;
import View.VistaLogin;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		conectorBD.conectar();
		        //Cargamos la vista
		        VistaLogin vista = new VistaLogin();
		        //Le pasamos la vista al controlador
		        ControlerLogin controlador = new ControlerLogin(vista);
		        //El controlador manejara el boton cuando le demos a registrar.
		        
		        //Iniciamos la vista.
		        controlador.iniciar();
		       
		      

	}
}
