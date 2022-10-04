package Auxiliar;

/**
 * Clase YaExisteRaizException
 * @author Enriquez Merino Manuel y Herlein Rodrigo Nicolas
 * Contempla la situacion en la se intente crear el arbol usado por la logica cuando el arbol ya existe
 */
@SuppressWarnings("serial")
public class YaExisteRaizException extends Exception{

	/**
	 * Crea la excepcion con un mensaje dado 
	 * @param msg mensaje que mostrara la excepcion
	 */
	public YaExisteRaizException(String msg) {
		super(msg);
	}
}
