package TDALista;

import Auxiliar.BoundaryViolationException;
import Auxiliar.EmptyListException;
import Auxiliar.InvalidPositionException;
import Auxiliar.Position;


/**
 * Clase ElementIterator
 * @author Enriquez Merino Manuel y Herlein Rodrigo Nicolas
 *	Representa un iterador de elementos
 * 
 */
public class ElementIterator<E> implements java.util.Iterator<E> {

	protected PositionList<E> lista;
	protected Position<E> cursor; //apunta al elemento que se retornara en el siguiente next

	/**
	 * Crea un nuevo iterador de elementos
	 * @param L lista de elementos a iterar
	 */
	public ElementIterator(PositionList<E> L) {
		try {
			lista=L;
			if(L.isEmpty())
				cursor=null;
			else
				cursor=L.first();
		}
		catch(EmptyListException e) {
			cursor=null;
		}
	}



	/**
	 * Devuelve verdadero o falso si hay un elemento mas para iterar
	 * @return Verdadero si hay un elemento siguiente para iterar
	 */
	public boolean hasNext() {
			
		return cursor!=null;
	}
		
	/**
	 * Itera un elemento
	 * @return elemento siguiente del iterador
	 */
	public E next() {
		
		E element=null;
		try {
			if(cursor!=null) {
				element=cursor.element();
				if(lista.last()==cursor)
					cursor=null;
				else
					cursor=lista.next(cursor);
			}
		}
		catch(BoundaryViolationException e) {
			cursor=null;
		}
		catch(EmptyListException e) {
			cursor=null;
		}
		catch(InvalidPositionException e) {
			cursor=null;
		}
		return element;
		
	}
}
