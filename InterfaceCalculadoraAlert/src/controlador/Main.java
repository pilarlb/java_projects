package controlador;

import vista.Calculadora;

public class Main {

	public static void main(String[] args) {
		//instancio objeto calculadora
		Calculadora calculator = new Calculadora();
		ControladorEvent controlador = new ControladorEvent(calculator);
		//con el metodo de la Calculadora, usamos el mismo objeto controlador
		calculator.establecerControlador(controlador);

	}

}
