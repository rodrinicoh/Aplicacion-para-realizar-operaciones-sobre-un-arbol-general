package TDAMapeo;

/**
 * Clase Entry
 * @author Enriquez Merino Manuel y Herlein Rodrigo Nicolas
 */
public interface Entry<K,V> {

	/**
	 * Devuelve la clave de la entrada
	 * @return la clave de la entrada
	 */
	public K getKey();
	
	/**
	 * Devuelve el valor de la entrada
	 * @return el valor de la entrada
	 */
	public V getValue();
}
