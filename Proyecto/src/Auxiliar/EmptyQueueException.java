package Auxiliar;

/**
 * Clase EmptyQueueException
 * @author Enriquez Merino Manuel y Herlein Rodrigo Nicolas
 * Contempla la situacion en la que la Cola este vacia
 */
@SuppressWarnings("serial")
public class EmptyQueueException extends Exception {

	/**
	 * Crea la excepcion con un mensaje dado 
	 * @param msg mensaje que mostrara la excepcion
	 */
	public EmptyQueueException(String msg) {
		super(msg);
	}
}
