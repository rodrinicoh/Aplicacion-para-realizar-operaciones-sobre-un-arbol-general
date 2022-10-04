package Auxiliar;

/**
 * Clase EmptyListException
 * @author Enriquez Merino Manuel y Herlein Rodrigo Nicolas
 * Contempla la situacion en la que la Lista este vacia
 */
@SuppressWarnings("serial")
public class EmptyListException extends Exception{
	
	/**
	 * Crea la excepcion con un mensaje dado 
	 * @param msg mensaje que mostrara la excepcion
	 */
	public EmptyListException(String msg){
		super(msg);
	}
}

