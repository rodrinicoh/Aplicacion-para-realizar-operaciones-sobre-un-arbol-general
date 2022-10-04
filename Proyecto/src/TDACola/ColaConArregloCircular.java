package TDACola;

import Auxiliar.EmptyQueueException;

/**
*	Clase ColaConArregloCircular
*	@author Enriquez Merino Manuel y Herlein Rodrigo Nicolas
*/

public class ColaConArregloCircular<E> implements Queue<E> {
	
	private E[] arreglo;
	private int i;
	private int f;
	
	/**
 	* 	Inicializa un nuevo Objeto de la clase Cola con Arreglo Circular el cual representa una Cola Con Arreglo Circular
	*/
	@SuppressWarnings({ "unchecked", "unused" })
	public ColaConArregloCircular (){
		arreglo = (E[]) new Object[10];
		int i = 0;
		int f =0;
	}
	
	public int size() {
		return (arreglo.length-i+f)%arreglo.length;
	}
	
	public boolean isEmpty() {
		return i==f;
	}
	
	public void enqueue (E element) {
		if (arreglo.length-1==size()) {
			E[] aux = copiar(i);
			f=size();
			i=0;
			arreglo=aux;
		}
		arreglo[f]=element;
		f=(f+1)%arreglo.length;
	}
	
	public E dequeue() throws EmptyQueueException{
		if (isEmpty())
			throw new EmptyQueueException("Cola Vacia");
		E aux = arreglo[i];
		arreglo[i]=null;
		i=(i+1)%arreglo.length;
		return aux;
	}
	
	public E front() throws EmptyQueueException{
		if (isEmpty()) 
			throw new EmptyQueueException("Cola Vacia");
		return arreglo[i];
	}
	
	/**
	 * Copia los elementos del arreglo que representa la cola, en un arreglo de mayor tamaño
	 * @param m posicion del primer elemento en el arreglo que representa la cola
	 * @return Una copia del arreglo que representa la cola con mayor tamaño
	 */
	@SuppressWarnings("unchecked")
	private E[] copiar(int m) {
		E[] aux = (E[]) new Object[arreglo.length*2];
		
		for (int i=0 ; i<size() ; i++) {
			aux[i]=arreglo[m];
			m=(m+1)%arreglo.length;
			}

		return aux;

	}
}
