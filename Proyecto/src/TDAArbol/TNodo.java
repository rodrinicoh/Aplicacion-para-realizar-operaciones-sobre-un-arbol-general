package TDAArbol;

import TDALista.ListaDoblementeEnlazada;
import TDALista.PositionList;
import Auxiliar.Position;

/**
 * clase TNodo
 * @author Enriquez Merino Manuel y Herlein Rodrigo Nicolas
 * Representa un nodo del arbol
 * 
 */
public class TNodo<E> implements Position<E>{

	
	protected E element;
	protected TNodo<E> padre;
	protected PositionList<TNodo<E>> hijos;
	
	/**
	 * Crea un TNodo 
	 * @param elem el elemento del nuevo TNodo
	 * @param p es el padre del nuevo TNodo
	 */
	public TNodo(E elem, TNodo<E> p) {
		element=elem;
		padre=p;
		hijos= new ListaDoblementeEnlazada<TNodo<E>>();
	}
	
	/**
	 * Establece el Rotulo de un TNodo
	 * @param elem nuevo Rotulo del TNodo
	 */
	public void setRotulo(E elem) {
		element=elem;
	}
	
	/**
	 * establece el padre de un TNodo
	 * @param p nuevo padre del TNodo
	 */
	public void setPadre(TNodo<E> p) {
		padre=p;
	}
	
	/**
	 * establece los nuevos hijos de un TNodo
	 * @param h nuevos hijos del TNodo
	 */
	public void setHijos(PositionList<TNodo<E>> h) {
		hijos=h;
	}
	
	public E element() {
		return element;
	}
	
	/**
	 * devuelve el padre de un TNodo
	 * @return padre de un TNodo
	 */
	public TNodo<E> getPadre() {
		return padre;
	}
	
	/**
	 * devuelve los hijos de un TNodo
	 * @return una PositionList formada con los hijos del TNodo
	 */
	public PositionList<TNodo<E>> getHijos() {
		return hijos;
	}
}
