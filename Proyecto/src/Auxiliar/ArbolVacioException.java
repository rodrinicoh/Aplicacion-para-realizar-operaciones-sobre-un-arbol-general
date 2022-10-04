package Auxiliar;

/**
 * Clase ArbolVacioException
 * @author Enriquez Merino Manuel y Herlein Rodrigo Nicolas
 * Contempla la situacion en la que el Arbol usado por la logica este vacio
 */
@SuppressWarnings("serial")
public class ArbolVacioException extends Exception {

	/**
	 * Crea la excepcion con un mensaje dado 
	 * @param msg mensaje que mostrara la excepcion
	 */
	public ArbolVacioException(String msg) {
		super(msg);
	}
}
