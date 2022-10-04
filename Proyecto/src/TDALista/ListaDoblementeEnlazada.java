package TDALista;

import java.util.Iterator;

import Auxiliar.BoundaryViolationException;
import Auxiliar.EmptyListException;
import Auxiliar.InvalidPositionException;
import Auxiliar.Position;

/**
*	Clase ListaDoblementeEnlazada
*	@author Enriquez Merino Manuel y Herlein Rodrigo Nicolas
*/
public class ListaDoblementeEnlazada<E> implements PositionList<E>{

	private DNodo<E> Cabeza;
	private DNodo<E> Cola;
	private int size;
	
	/**
 	* 	Inicializa un nuevo Objeto de la clase Lista Doblemente Enlazada el cual representa una Lista Doblemente Enlazada
	*/
	public ListaDoblementeEnlazada(){
		Cabeza = null;
		Cola = null;
		size=0;
	}
	
	public int size(){
		return size;
	}
	
	public boolean isEmpty(){
		return size==0;
	}
	
	public Position<E> first() throws EmptyListException{
		if(isEmpty())
			throw new EmptyListException("Error: La lista esta vacia");
		return Cabeza;
	}
	
	public Position<E> last() throws EmptyListException{
		if(isEmpty())
			throw new EmptyListException("Error: La lista esta vacia");
		return Cola;
	}
	
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException{
		if(isEmpty())
			throw new InvalidPositionException("Error: No se puede obtener el elemento previo de una lista vacia");
		if(p==Cabeza)
			throw new BoundaryViolationException("Error: No se puede obtener el elemento previo al primero");
			
		DNodo<E> aux = checkPosition(p);
		
		return aux.getPrevio();
	}
	
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException{
		if(isEmpty())
			throw new InvalidPositionException("Error: No se puede obtener el elemento siguiente de una lista vacia");
		if(p==Cola)
			throw new BoundaryViolationException("Error: No se puede obtener el elemento siguiente al ultimo");
		
		DNodo<E> aux = checkPosition(p);
		
		return aux.getSiguiente();
	}
	
	public void addFirst(E e){
		DNodo<E> Nuevo = new DNodo<E>(null,null,e);
		if (isEmpty()) {
			Cabeza = Nuevo;
			Cola = Nuevo;
		}
		else {
			Nuevo.setSiguiente(Cabeza);
			Cabeza.setPrevio(Nuevo);
			Cabeza=Nuevo;
			}
		size++;
	}
	
	public void addLast(E e){
		DNodo<E> Nuevo = new DNodo<E>(null,null,e);
		if (isEmpty()) {
			Cabeza = Nuevo;
			Cola = Nuevo;
		}
		else {
			Nuevo.setPrevio(Cola);
			Cola.setSiguiente(Nuevo);
			Cola=Nuevo;
		}
		size++;
	}
	
	public void addBefore(Position<E> p, E e) throws InvalidPositionException{
		if(isEmpty())
			throw new InvalidPositionException("Error: No se puede agregar un elemento previo a otro en una lista vacia");
		DNodo<E> pos = checkPosition(p);
		if (pos==Cabeza) {
			addFirst(e);
		} else {
			DNodo<E> Nuevo = new DNodo<E>(pos.getPrevio(),pos,e);
			(pos.getPrevio()).setSiguiente(Nuevo);
			pos.setPrevio(Nuevo);
			size++;
		}
	}
	
	public void addAfter(Position<E> p, E e) throws InvalidPositionException{
		if(isEmpty())
			throw new InvalidPositionException("Error: No se puede agregar un elemento siguiente a otro en una lista vacia");
		DNodo<E> pos = checkPosition(p);
		if(pos==Cola) {
			addLast(e);
		} else {
			DNodo<E> Nuevo = new DNodo<E>(pos,pos.getSiguiente(),e);
			(pos.getSiguiente()).setPrevio(Nuevo);
			pos.setSiguiente(Nuevo);
			size++;
		}
	}
	
	public E set(Position<E> p, E e) throws InvalidPositionException{
		if(isEmpty())
			throw new InvalidPositionException("Error: No se puede modificar un elemento de una lista vacia");
		DNodo<E> pos = checkPosition(p);
		E retorno = pos.element();
		pos.setElemento(e);
		return retorno;
		
	}
	
	public E remove(Position<E> p) throws InvalidPositionException{
		if(isEmpty())
			throw new InvalidPositionException("Error: No se puede eliminar un elemento de una lista vacia");
		DNodo<E> pos = checkPosition(p);
		E retorno = pos.element();
		
		if(p==Cabeza) {
			Cabeza=Cabeza.getSiguiente();
		} else {
			if (p==Cola) {
				Cola=Cola.getPrevio();
			}
			else {
				
				(pos.getPrevio()).setSiguiente(pos.getSiguiente());
				(pos.getSiguiente()).setPrevio(pos.getPrevio());
			}
		}
		
		size--;
		return retorno;
	}
	
	/**
	 * chequea si una posicion es valida y la devuelve con tipo estatico DNodo de E
	 * @param p una posicion del arbol
	 * @return DNodo que representa la posicion p
	 * @throws InvalidPositionException si la posicion p es nula.
	 */
	private DNodo<E> checkPosition(Position<E> p) throws InvalidPositionException{
		if(p==null)
			throw new InvalidPositionException("Error:Posicion Nula");
		
		DNodo<E> aux = null;
		try{
			aux = (DNodo<E>) p;
		} catch (ClassCastException e){
			throw new InvalidPositionException("Error: Posicion Invalida");
		}
		return aux;
	}
	
	public Iterator<E> iterator(){
		return new ElementIterator<E>(this);
	}
	
	public Iterable<Position<E>> positions(){
		
		PositionList<Position<E>> aux = new ListaDoblementeEnlazada<Position<E>>();
		DNodo<E> pos = Cabeza;
			while (pos!=null){
				aux.addLast(pos);
				pos = pos.getSiguiente();
			} 
		return aux;
	}
}

