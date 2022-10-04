package TDAPila;

import Auxiliar.EmptyStackException;

/**
*	Clase PilaConEnlaces
*	@author Enriquez Merino Manuel y Herlein Rodrigo Nicolas
*/
public class PilaConEnlaces<E> implements Stack<E> {

	private Nodo<E> head;
	private int Size;
	
	/**
 	* 	Inicializa un nuevo Objeto de la clase Pila Con Enlaces el cual representa una Pila Con Enlaces
	*/
	public PilaConEnlaces() {
		head = new Nodo<E>(null,null);
		Size = 0;
	}

	public int size() {
		return Size;
	}

	public boolean isEmpty() {
		return Size==0;
	}

	public E top() throws EmptyStackException {
		if (isEmpty())
			throw new EmptyStackException("Pila Vacia");
		return head.getElemento();
	}

	public void push(E element) {
		Nodo<E> aux = new Nodo<E>(element,head);
		head = aux;
		Size++;
	}

	public E pop() throws EmptyStackException {
		if (isEmpty())
			throw new EmptyStackException("Pila Vacia");
		E aux = head.getElemento();
		head = head.getSiguiente();
		Size--;
		return aux;
	}
	
}
