package Auxiliar;

/**
 * Clase PosicionInvalidaException
 * @author Enriquez Merino Manuel y Herlein Rodrigo Nicolas
 * Contempla la situacion en la posicion del arbol usado por logica no sea valido
 */
@SuppressWarnings("serial")
public class PosicionInvalidaException extends Exception{

	/**
	 * Crea la excepcion con un mensaje dado 
	 * @param msg mensaje que mostrara la excepcion
	 */
	public PosicionInvalidaException(String msg) {
		super(msg);
	}
}
