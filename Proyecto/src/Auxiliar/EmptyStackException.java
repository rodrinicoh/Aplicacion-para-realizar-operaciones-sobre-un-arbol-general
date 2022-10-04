package Auxiliar;

/**
 * Clase EmptyStackException
 * @author Enriquez Merino Manuel y Herlein Rodrigo Nicolas
 * Contempla la situacion en la que la pila este vacia
 */
@SuppressWarnings("serial")
public class EmptyStackException extends Exception {

	/**
	 * Crea la excepcion con un mensaje dado 
	 * @param msg mensaje que mostrara la excepcion
	 */
	public EmptyStackException(String msg) {
			super(msg);
	}
}
