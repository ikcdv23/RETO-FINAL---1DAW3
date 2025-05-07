package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import Repositorios.Pelicula;
import Repositorios.RepositorioPelicula;
public class VistaBienvenida extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton botonSalir;
    private JButton botonAgregar;
    private String usuario;
    private  JList<Pelicula> jlist;
    private JButton botonBuscar;
    private JTextField field;
 
    private  DefaultListModel<Pelicula> productos;

 


	public VistaBienvenida(String usuario) {
        this.usuario = usuario;
        setTitle("Bienvenido " + usuario);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
       setLayout(new BorderLayout());
       
        JPanel panelNorte = new JPanel(new FlowLayout());
        
      
        
        field =new JTextField(10);
        panelNorte.add(field);
        botonBuscar = new JButton("Buscar");
        panelNorte.add(botonBuscar);
        
        add(panelNorte,BorderLayout.NORTH);

        JPanel panelabajo = new JPanel(new FlowLayout());
        botonSalir = new JButton("Salir");
        
        panelabajo.add(botonSalir);
        botonAgregar = new JButton("Agregar");
        panelabajo.add(botonAgregar);
     
        add(panelabajo, BorderLayout.SOUTH);
      
        productos= new DefaultListModel<Pelicula>();
        ArrayList<Pelicula> lista=RepositorioPelicula.cargar();
        for(Pelicula p : lista) {
        	productos.addElement(p);
        }
        
        jlist = new JList<>(productos);
        JScrollPane scroll=new JScrollPane(jlist);
      
       add(scroll,BorderLayout.CENTER);
       
    }
        


    public String getUsuario() {
        return usuario;
    }

	public JList<Pelicula> getJlist() {
		return jlist;
	}

	public void setJlist(JList<Pelicula> jlist) {
		this.jlist = jlist;
	}



	 public JButton getBtnSalir() {
        return botonSalir;
    }
    
    public void setBtnSalir(JButton botonSalir) {
        this.botonSalir=botonSalir;
    }



	public JButton getBotonAgregar() {
		return botonAgregar;
	}



	public void setBotonAgregar(JButton botonAgregar) {
		this.botonAgregar = botonAgregar;
	}



	public JButton getBotonBuscar() {
		return botonBuscar;
	}



	public void setBotonBuscar(JButton botonBuscar) {
		this.botonBuscar = botonBuscar;
	}



	public JTextField getField() {
		return field;
	}



	public void setField(JTextField field) {
		this.field = field;
	}
	   



		public  DefaultListModel<Pelicula> getLista() {
			return productos;
		}



		

}
