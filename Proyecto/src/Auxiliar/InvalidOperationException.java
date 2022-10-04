package Auxiliar;

/**
 * Clase InvalidOperationException
 * @author Enriquez Merino Manuel y Herlein Rodrigo Nicolas
 * Contempla la situacion en la que la Operacion sobre el arbol sea invalida
 */
@SuppressWarnings("serial")
public class InvalidOperationException extends Exception{
	
	/**
	 * Crea la excepcion con un mensaje dado 
	 * @param msg mensaje que mostrara la excepcion
	 */
	public InvalidOperationException(String msg) {
		super(msg);
	}
}
