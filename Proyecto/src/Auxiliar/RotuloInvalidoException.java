package Auxiliar;

/**
 * Clase Rotulo1InvalidoException
 * @author Enriquez Merino Manuel y Herlein Rodrigo Nicolas
 * Contempla la situacion en la que el Rotulo1 no sea un entero valido
 */
@SuppressWarnings("serial")
public class RotuloInvalidoException extends Exception {
	
	/**
	 * Crea la excepcion con un mensaje dado 
	 * @param msg mensaje que mostrara la excepcion
	 */
	public RotuloInvalidoException(String msg) {
		super(msg);
	}
}
