package Auxiliar;

/**
 * Clase EmptyTreeException
 * @author Enriquez Merino Manuel y Herlein Rodrigo Nicolas
 * Contempla la situacion en la que el arbol este vacio
 */
@SuppressWarnings("serial")
public class EmptyTreeException extends Exception{

	/**
	 * Crea la excepcion con un mensaje dado 
	 * @param msg mensaje que mostrara la excepcion
	 */
	public EmptyTreeException(String msg) {
		super(msg);
	}
}
