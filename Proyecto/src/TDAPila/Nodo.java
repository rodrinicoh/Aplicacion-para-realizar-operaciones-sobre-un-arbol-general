package TDAPila;

/**
 * Clase Nodo
 * @author Enriquez Merino Manuel y Herlein Rodrigo Nicolas
 * Representa un elemento e indica su Nodo siguiente
 * 
 */
public class Nodo<E> {
	
	private E elem;
	private Nodo<E> siguiente;
	
	/**
	 * Crea un nodo con elemento elemento y con Nodo siguiente sig
	 * @param elemento rotulo del nodo
	 * @param sig referencia al nodo siguiente
	 */
	public Nodo(E elemento, Nodo<E> sig) {
		elem=elemento;
		siguiente=sig;
	}
	
	
	/**
	 * devuelve el rotulo de un nodo
	 * @return el rotulo del nodo
	 */
	public E getElemento() {
		return elem;
	}
	
	/**
	 * devuelve el nodo siguiente
	 * @return el nodo siguiente
	 */
	public Nodo<E> getSiguiente() {
		return siguiente;
	}
	
	/**
	 * establece el rotulo del nodo
	 * @param elem el nuevo rotulo del nodo
	 */
	public void setElemento(E elem) {
		this.elem=elem;
	}
	
	/**
	 * establece el siguiente nodo
	 * @param n siguiente nodo
	 */
	public void setSiguiente(Nodo<E> n) {
		siguiente=n;
	}
}
