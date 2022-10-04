package Auxiliar;

/**
 * Clase BoundaryViolationException
 * @author Enriquez Merino Manuel y Herlein Rodrigo Nicolas
 * Contempla la situacion en la posicion pedida supera los limites de la estructura de datos
 */
@SuppressWarnings("serial")
public class BoundaryViolationException extends Exception{

	/**
	 * Crea la excepcion con un mensaje dado 
	 * @param msg mensaje que mostrara la excepcion
	 */
	public BoundaryViolationException(String msg){
		super(msg);
	}
}



