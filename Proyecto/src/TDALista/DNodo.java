package TDALista;

import Auxiliar.Position;

/**
*	Clase DNodo
*	@author Enriquez Merino Manuel y Herlein Rodrigo Nicolas
*	Representa un Nodo que conoce su rotulo, el nodo previo y su siguiente
*/
public class DNodo<E> implements Position<E>{

	private DNodo<E> previo;
	private DNodo<E> siguiente;
	private E elemento;
	
	/**
 	* 	Inicializa un nuevo Objeto de la clase DNodo el cual representa un Nodo que conoce su rotulo, el nodo previo y su siguiente
	* 	@param prev	DNodo previo al nuevo DNodo 
	* 	@param sig	DNodo siguiente al nuevo DNodo
	* 	@param elem	elemento del nuevo DNodo
	*/
	public DNodo(DNodo<E> prev, DNodo<E> sig, E elem){
		previo=prev;
		siguiente=sig;
		elemento=elem;
	}
	
	/**
	 * Devuelve el Elemento del nodo
	 */
	public E element(){
		return elemento;
	}
	
	/**
	 * Devuelve el nodo previo a un nodo
	 * @return el DNodo previo a un DNodo
	 */
	public DNodo<E> getPrevio(){
		return previo;
	}
	
	/**
	 * Devuelve el nodo siguiente a un nodo
	 * @return el DNodo siguiente a un DNodo
	 */
	public DNodo<E> getSiguiente(){
		return siguiente;
	}
	
	/**
	 * Establece el elemento del nodo
	 * @param elem el nuevo elemento del nodo
	 */
	public void setElemento(E elem){
		elemento=elem;
	}
	
	/**
	 * Establece el nodo previo al nodo
	 * @param prev el nuevo nodo previo al nodo
	 */
	public void setPrevio(DNodo<E> prev){
		previo=prev;
	}
	
	/**
	 * Establece el nodo siguiente al nodo
	 * @param sig el nuevo nodo siguiente al nodo
	 */
	public void setSiguiente(DNodo<E> sig){
		siguiente=sig;
	}

}



