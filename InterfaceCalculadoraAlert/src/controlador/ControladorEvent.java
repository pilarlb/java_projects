package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


import javax.swing.JOptionPane;

import vista.Calculadora;
import vista.VentanaPassword;

//Clase para manejar eventos de click en botones
public class ControladorEvent implements ActionListener {
	private Calculadora calculadora;
	
	
	//constructor
	public ControladorEvent(Calculadora calculadora) {
		this.calculadora = calculadora;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//variables de los String de las cajas
		String cajaUno = calculadora.getTextUno().getText();
		String cajaDos = calculadora.getTextDos().getText();
		
		//Si se pulsa un boton y esta vacio uno de los campos,
		//por defecto pongo que es un 0
		if(cajaUno.equalsIgnoreCase("")) {
			cajaUno = "0";
		}else if(cajaDos.equalsIgnoreCase("")) {
			cajaDos = "0";
		}
		
		//Convertimos los string en int para operar con ellos
		int numUno = Integer.parseInt(cajaUno);
		int numDos = Integer.parseInt(cajaDos);
		
		int resultado;
		
		//acciones para cada Boton: boton de sumar
		if(e.getSource()==calculadora.getButSumar()){
			
			resultado = numUno + numDos;
			//introducimos el resultado en la etiqueta Resultado
			calculadora.setLabelResul(resultado);

		}
		//Boton de restar
		if(e.getSource()==calculadora.getButRestar()) {
			
			resultado = numUno - numDos;
			//introducimos el resultado en la etiqueta Resultado
			calculadora.setLabelResul(resultado);

		}
		//Boton de multiplicar
		if(e.getSource()==calculadora.getButMult()) {
			
			resultado = numUno*numDos;
			//introducimos el resultado en la etiqueta Resultado
			calculadora.setLabelResul(resultado);

		}
		//Boton de dividir
		if(e.getSource()==calculadora.getButDivi()) {
			
			resultado = numUno/numDos;
			//introducimos el resultado en la etiqueta Resultado
			calculadora.setLabelResul(resultado);

		}
		//Boton Raiz Cuadrada
		if(e.getSource()==calculadora.getButRCuad()) {
			//Salta un Alert
			JOptionPane.showMessageDialog(null, "Funcionalidad no disponible"
					, "", JOptionPane.ERROR_MESSAGE);
			
			
		}
		//Boton Raiz Cubica
		if(e.getSource()==calculadora.getButRCub()) {
			//instanciamos objeto ventanapassword
			VentanaPassword pass = new VentanaPassword();
			
			//Evento para saber si una ventana se ha cerrado
			pass.addWindowListener(new WindowAdapter() {
				
				//metodo windowClosed
				@Override
				public void windowClosed(WindowEvent e) {
	              double raizcubica = 0;
	              
	              //Para hacer la raiz cubica independientemente 
	              //de la casilla rellenada
					if(numUno != 0) {
						raizcubica = Math.cbrt(numUno);
					}else if (numDos != 0) {
						raizcubica = Math.cbrt(numDos);
					}
	                
	    			//introducimos el resultado en la etiqueta Resultado
	    			calculadora.setLabelResul(raizcubica);
	            }
			});
			
			
			
		
		}
		
		
	}
}
