package Auxiliar;

/**
 * Clase InvalidKeyException
 * @author Enriquez Merino Manuel y Herlein Rodrigo Nicolas
 * Contempla la situacion en la que la key de un mapeo sea nula
 */
@SuppressWarnings("serial")
public class InvalidKeyException extends Exception{

	/**
	 * Crea la excepcion con un mensaje dado 
	 * @param msg mensaje que mostrara la excepcion
	 */
	public InvalidKeyException(String msg) {
		super(msg);
	}
}
