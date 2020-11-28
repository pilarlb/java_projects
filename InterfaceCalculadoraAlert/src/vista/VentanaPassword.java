package vista;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;



public class VentanaPassword extends JDialog{

	private static final long serialVersionUID = 1L;
//
	private JPasswordField cajatex2;
	private JLabel etiq1, etiq2;
	private JButton boton1;
	
	public VentanaPassword() {
		
		//tamaño ventana
		setSize(450,250);
		//ubicacion en la pantalla, centrada
		setLocationRelativeTo(null);
		//añado titulo
		setTitle("Acceso por contraseña");
		//Creo carpeta resources donde guardo img para icon
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources/hojaicon.png"));
		//para colocar manualmente componentes, quitamos layout
		setLayout(null);
		//llamo a metodo para iniciar componentes
		iniciarComponentes();
		setVisible(true);
	}
	
	private void iniciarComponentes() {
		
		etiq1 = new JLabel("Introduzca la contraseña para realizar la operacion");
		etiq1.setBounds(30,10,390,30);
		
		etiq2 = new JLabel("");
		etiq2.setBounds(30,50,390,30);
		
		cajatex2 = new JPasswordField();
		cajatex2.setBounds(30, 90, 370, 30);
		
		boton1 = new JButton("Aceptar");
		boton1.setBounds(30, 130, 200, 40);
		
		add(etiq1);
		add(etiq2);
		add(cajatex2);
		add(boton1);
		
		//manejador de eventos para boton1
		boton1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//CONTROLADOR EVENTOS PARA BOTON PASSWORD
				String contrasena = String.copyValueOf(cajatex2.getPassword());
				
				if(contrasena.equals("12345")) {
					//cerramos la ventana
					dispose();
					
				}else {
					etiq2.setText("---CONTRASEÑA INCORRECTA ----");
				}
			}
		});
		
		
	}

	//GETTERS
	public JPasswordField getCajatex2() {
		return cajatex2;
	}

	public JLabel getEtiq1() {
		return etiq1;
	}

	public JLabel getEtiq2() {
		return etiq2;
	}

	public JButton getBoton1() {
		return boton1;
	}
	
	
	
}
