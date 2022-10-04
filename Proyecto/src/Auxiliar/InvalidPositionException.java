package Auxiliar;

/**
 * Clase InvalidPositionException
 * @author Enriquez Merino Manuel y Herlein Rodrigo Nicolas
 * Contempla la situacion en la que Posicion sea invalida
 */
@SuppressWarnings("serial")
public class InvalidPositionException extends Exception{

	/**
	 * Crea la excepcion con un mensaje dado 
	 * @param msg mensaje que mostrara la excepcion
	 */
	public InvalidPositionException(String msg){
		super(msg);
	}
}

