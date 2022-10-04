package TDAMapeo;

/**
 * Clase Entrada
 * @author Enriquez Merino Manuel y Herlein Rodrigo Nicolas
 */
public class Entrada<K,V> implements Entry<K,V>{

	private K key;
	private V value;
	
	/**
	 * Crea una nueva entrada con llave k y valor v
	 * @param k llave de la nueva entrada
	 * @param v valor de la nueva entrada
	 */
	public Entrada(K k,V v) {
		key = k;
		value = v;
	}
	
	public K getKey() {
		return key;
	}
	
	public V getValue() {
		return value;
	}
	
	/**
	 * Establece la llave de la entrada
	 * @param k la nueva llave de la entrada
	 */
	public void setKey(K k) {
		key = k;
	}
	
	/**
	 * Establece el valor de la entrada
	 * @param v el nuevo valor de la entrada
	 */
	public void setValue(V v) {
		value = v;
	}
}
