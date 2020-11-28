package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controlador.ControladorEvent;

public class Calculadora extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel labelimg, labelUno, labelDos, labelResul, labelimgfin;
	private JTextField textUno, textDos;
	private JButton butSumar, butRestar, butMult, butDivi, butRCuad, butRCub;
	
	public Calculadora() {
		//tamaño ventana JFrame
		setSize(450,730);
		//ubicacion en la pantalla, centrada
		setLocationRelativeTo(null);
		//al pulsar X, salir de la aplicacion
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//añado titulo
		setTitle("Calculadora");
		//Creo carpeta resources donde guardo img para icon
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources/hojaicon.png"));
		//para colocar manualmente componentes, quitamos layout
		setLayout(null);
		//llamo a metodo para iniciar componentes
		iniciarComponentes();
		
		//FUENTE
		
		
		//activo visibilidad del JFrame, siempre lo ultimo de la lista
		setVisible(true);
	}
	
	private void iniciarComponentes() {
		//Elegir el color de fondo
		getContentPane().setBackground(new Color(238, 232, 170));
		
		//instanciamos objeto imagen (PARA IMAGEN DE CALCULADORA)
		Image img = new ImageIcon("resources/hojamarron.png").getImage();
		//En la label, instanciamos ImageIcon y metemos img para escalarlo
		labelimg = new JLabel(new ImageIcon(img.getScaledInstance(210, 210, Image.SCALE_SMOOTH)));
		labelimg.setBounds(90,20,210,210);
		add(labelimg);
		
		
		labelUno = new JLabel("Numero 1:");
		labelUno.setBounds(100,250,70,30);
		
		
		//Caja de texto uno
		textUno = new JTextField();
		textUno.setBounds(240,250,70,30);
		//Quitar borde a la caja de texto
		textUno.setBorder(null);
		add(textUno);
		
		labelDos = new JLabel("Numero 2:");
		labelDos.setBounds(100,300,70,30);
		
		
		//Caja de texto dos
		textDos = new JTextField();
		textDos.setBounds(240,300,70,30);
		textDos.setBorder(null);
		add(textDos);
		
		//Boton sumar
		butSumar = new JButton("Sumar");
		butSumar.setBounds(75,370,130,50);
		butSumar.setBackground(new Color(255, 215, 0));
		butSumar.setBorderPainted(false);
		//add(butSumar);
		
		
		butRestar = new JButton("Restar");
		butRestar.setBounds(240,370,130,50);
		butRestar.setBackground(new Color(255, 215, 0));
		butRestar.setBorderPainted(false);
		//add(butRestar);
		
		butMult = new JButton("Multiplicar");
		butMult.setBounds(75,440,130,50);
		butMult.setBackground(new Color(255, 215, 0));
		butMult.setBorderPainted(false);
		//add(butMult);
		
		butDivi = new JButton("Dividir");
		butDivi.setBounds(240,440,130,50);
		butDivi.setBackground(new Color(255, 215, 0));
		butDivi.setBorderPainted(false);
		//add(butDivi);
		
		butRCuad = new JButton("RCuadrada");
		butRCuad.setBounds(75, 510, 130, 50);
		butRCuad.setBackground(new Color(255, 215, 0));
		butRCuad.setBorderPainted(false);
		
		butRCub = new JButton("RCubica");
		butRCub.setBounds(240, 510, 130, 50);
		butRCub.setBackground(new Color(255, 215, 0));
		butRCub.setBorderPainted(false);
		
		//Label de resultado final del calculo
		labelResul = new JLabel("Resultado:");
		//Que este label ocupe todo el ancho de la app
		labelResul.setBounds(0,565,450,50);
		//para que siempre esté alineado en el centro
		labelResul.setHorizontalAlignment(SwingConstants.CENTER);
		
		//Cambiamos fuente de labels y buttons
		try {
			Font font = Font.createFont(Font.TRUETYPE_FONT,getClass().getResourceAsStream("fuente.ttf"));
								
			
			labelUno.setFont(font.deriveFont(Font.PLAIN, 13f));
			add(labelUno);
			labelDos.setFont(font.deriveFont(Font.PLAIN, 13f));
			add(labelDos);
			
			butSumar.setFont(font.deriveFont(Font.BOLD, 16f));
			add(butSumar);
			
			butRestar.setFont(font.deriveFont(Font.BOLD, 16f));
			add(butRestar);
			
			butMult.setFont(font.deriveFont(Font.BOLD, 16f));
			add(butMult);
			
			butDivi.setFont(font.deriveFont(Font.BOLD, 16f));
			add(butDivi);
			
			butRCuad.setFont(font.deriveFont(Font.BOLD, 16f));
			add(butRCuad);
			
			butRCub.setFont(font.deriveFont(Font.BOLD, 16f));
			add(butRCub);
			
			labelResul.setFont(font.deriveFont(Font.BOLD, 16f));
			labelResul.setForeground(Color.DARK_GRAY);
			add(labelResul);
			
				
			} catch (FontFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		//Imagen final de hojas: imagen a escala para que quepa entera
				Image imgfin = new ImageIcon("resources/muchashojas.png").getImage();
				labelimgfin = new JLabel(new ImageIcon(imgfin.getScaledInstance(440, 140, Image.SCALE_SMOOTH)));
				labelimgfin.setBounds(0, 580, 440, 140);
				add(labelimgfin);
				
		
		
	}
	//METODOS
	
	//Metodo para introducir el int resultado y agregarlo a la label Resultado
	public void setLabelResul(int resultado) {
		
		this.labelResul.setText("Resultado: "+ resultado);
	}
	//para resultados con double
	public void setLabelResul(double resultado) {
		
		this.labelResul.setText("Resultado: "+ resultado);
	}
	
	//metodo para usar un mismo objeto ControladorEvent para todos
	//los botones que tienen el ActionListener
	public void establecerControlador(ControladorEvent controlador) {
		butSumar.addActionListener(controlador);
		butRestar.addActionListener(controlador);
		butMult.addActionListener(controlador);
		butDivi.addActionListener(controlador);
		butRCuad.addActionListener(controlador);
		butRCub.addActionListener(controlador);
	}
		
	//GETTERS Y SETTERS

	public JButton getButSumar() {
		return butSumar;
	}

	public JButton getButRestar() {
		return butRestar;
	}

	public JButton getButMult() {
		return butMult;
	}

	public JButton getButDivi() {
		return butDivi;
	}
	
	public JButton getButRCuad() {
		return butRCuad;
	}

	public JButton getButRCub() {
		return butRCub;
	}

	public JTextField getTextUno() {
		return textUno;
	}

	public JTextField getTextDos() {
		return textDos;
	}

	
}
