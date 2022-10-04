package Auxiliar;

/**
 * Clase RotuloNoPerteneceAlArbolException
 * @author Enriquez Merino Manuel y Herlein Rodrigo Nicolas
 * Contempla la situacion en la que se solicite un nodo con un rotulo que no pertenece al arbol usado por la logica
 */
@SuppressWarnings("serial")
public class RotuloNoPerteneceAlArbolException extends Exception {

	/**
	 * Crea la excepcion con un mensaje dado 
	 * @param msg mensaje que mostrara la excepcion
	 */
	public RotuloNoPerteneceAlArbolException(String msg) {
		super(msg);
	}
}
