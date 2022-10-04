package TDAArbol;

import java.util.Iterator;

import Auxiliar.BoundaryViolationException;
import Auxiliar.EmptyTreeException;
import Auxiliar.InvalidOperationException;
import Auxiliar.InvalidPositionException;
import Auxiliar.EmptyListException;
import Auxiliar.Position;
import TDALista.ListaDoblementeEnlazada;
import TDALista.PositionList;

/**
*	Clase ArbolGeneral
*	@author Enriquez Merino Manuel y Herlein Rodrigo Nicolas
*/

public class ArbolGeneral<E> implements Tree<E>{

	private TNodo<E> raiz;
	private int size;
	
	/**
 	* 	Inicializa un nuevo Objeto de la clase Arbol General el cual representa un arbol general
	*/
	public ArbolGeneral() {
		raiz=null;
		size=0;
	}
	
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size==0;
	}

	public Iterator<E> iterator() {
		PositionList<E> aux = new ListaDoblementeEnlazada<E>();
		for(Position<E> p: positions())
			aux.addLast(p.element());
		return aux.iterator();
	}

	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> aux = new ListaDoblementeEnlazada<Position<E>>();
		if(!isEmpty())
			pre(aux,raiz);
		return aux;
	}

	public E replace(Position<E> v, E e) throws InvalidPositionException {
		TNodo<E> pos = checkPosition(v);
		E retorno = pos.element();
		pos.setRotulo(e);
		return retorno;
	}

	public Position<E> root() throws EmptyTreeException {
		if (isEmpty())
			throw new EmptyTreeException("Error: Arbol vacio");
		return raiz;
	}

	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		TNodo<E> pos = checkPosition(v);
		if(pos==raiz)
			throw new BoundaryViolationException("Error: No se puede obtener el padre de la raiz");
		return pos.getPadre();
	}

	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		TNodo<E> pos = checkPosition(v);
		
		PositionList<Position<E>> aux = new ListaDoblementeEnlazada<Position<E>>();
		Iterator<TNodo<E>> it = pos.getHijos().iterator();
		
		while (it.hasNext())
			aux.addLast(it.next());
		
		return aux;
	}

	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		TNodo<E> pos = checkPosition(v);
		return !(pos.getHijos().isEmpty());
	}

	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		//tambien puede ser !isInternal
		TNodo<E> pos = checkPosition(v);
		return pos.getHijos().isEmpty();
	}

	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		TNodo<E> pos = checkPosition(v);
		return pos==raiz;
	}

	public void createRoot(E e) throws InvalidOperationException {
		if (!isEmpty())
			throw new InvalidOperationException("Error: El arbol ya tiene raiz");
		raiz = new TNodo<E>(e,null);
		size++;
	}

	public Position<E> addFirstChild(Position<E> p, E e) throws InvalidPositionException {
		TNodo<E> pos = checkPosition(p);
		TNodo<E> nuevo = new TNodo<E>(e,pos);
		pos.getHijos().addFirst(nuevo);
		size++;
		return nuevo;
	}

	public Position<E> addLastChild(Position<E> p, E e) throws InvalidPositionException {
		TNodo<E> pos = checkPosition(p);
		TNodo<E> nuevo = new TNodo<E>(e,pos);
		pos.getHijos().addLast(nuevo);
		size++;
		return nuevo;
	}

	public Position<E> addBefore(Position<E> p, Position<E> rb, E e) throws InvalidPositionException {
		TNodo<E> pos = checkPosition(p);
		TNodo<E> hermano = checkPosition(rb);
		TNodo<E> nuevo = new TNodo<E>(e,pos);
		PositionList<TNodo<E>> hijos = pos.getHijos();
		boolean encontro=false;
		
		try {
		
			Position<TNodo<E>> indice = hijos.first();
			while(!encontro && indice!=null) {
				encontro = indice.element()==hermano;
					if(!encontro)
						if(indice!=hijos.last())
							indice=hijos.next(indice);
						else
							indice=null;
			}
			if (!encontro)
				throw new InvalidPositionException("Error: Posicion Invalida");
			hijos.addBefore(indice,nuevo);
			
			size++;
		
		} catch (InvalidPositionException | BoundaryViolationException | EmptyListException exc) {
			throw new InvalidPositionException("Error al insertar");
		}
		
		return nuevo;
	}

	public Position<E> addAfter(Position<E> p, Position<E> lb, E e) throws InvalidPositionException {
		TNodo<E> pos = checkPosition(p);
		TNodo<E> hermano = checkPosition(lb);
		TNodo<E> nuevo = new TNodo<E>(e,pos);
		PositionList<TNodo<E>> hijos = pos.getHijos();
		boolean encontre=false;
		
		try {
			Position<TNodo<E>> indice = hijos.first();
			while(!encontre && indice!=null) {
				encontre=indice.element()==hermano;
				if(!encontre)
					if(indice!=hijos.last())
						indice=hijos.next(indice);
					else
						indice=null;
			}
			if (!encontre)
				throw new InvalidPositionException("Error: Posicion Invalida");
			hijos.addAfter(indice, nuevo);
			
			size++;
		
		} catch (InvalidPositionException | BoundaryViolationException | EmptyListException exc) {
			throw new InvalidPositionException("Error al insertar");
		}	
		
		return nuevo;
	}

	public void removeExternalNode(Position<E> p) throws InvalidPositionException {
		TNodo<E> pos = checkPosition(p);
		
		if (!pos.getHijos().isEmpty())
			throw new InvalidPositionException("Error: El Nodo no es externo");
		
		try {
			if(pos!=raiz) {
				boolean encontre = false;
			
				TNodo<E> padre = pos.getPadre();
				PositionList<TNodo<E>> ListaDeHijos = padre.getHijos();
				Position<TNodo<E>> indice = padre.getHijos().first();
			
				while (!encontre && indice!=null) {
					encontre = indice.element()==pos;
					if(!encontre) {
						if(indice==ListaDeHijos.last())
							indice=null;
						else
							indice=ListaDeHijos.next(indice);
					}
				}
			
				if(!encontre)
					throw new InvalidPositionException("Error: Posicion Invalida");
			
				ListaDeHijos.remove(indice);
				size--;
			
			} else
				raiz=null;
			
			
		} catch(InvalidPositionException | EmptyListException | BoundaryViolationException e) {
			throw new InvalidPositionException("Error al eliminar");
		}
	} 

	public void removeInternalNode(Position<E> p) throws InvalidPositionException {
		TNodo<E> pos = checkPosition(p);
		
		if(pos.getHijos().isEmpty())
			throw new InvalidPositionException("Error: El Nodo no es interno");
		
		try {
			if(pos==raiz) {
				if(size>=2 && raiz.getHijos().size()==1) {
					Position<TNodo<E>> raizNueva = raiz.getHijos().first();
					raizNueva.element().setPadre(null);
					raiz=raizNueva.element();
					size--;
				}
				else
					throw new InvalidPositionException("Error: No se puede remover la raiz de un arbol con elementos");
			
			} else {
				TNodo<E> padre = pos.getPadre();
				PositionList<TNodo<E>> HijosDeN = pos.getHijos();
				PositionList<TNodo<E>> HijosPadre = padre.getHijos();
				
				//Buscar N dentro de hijos padre
				
				Position<TNodo<E>> primero = HijosPadre.first();
				
				while (primero.element()!=pos)
					primero = HijosPadre.next(primero);
				
				//Si n tiene hijos los recorro y los inserto en orden
				
				while(!HijosDeN.isEmpty()) {
					Position<TNodo<E>> HijoAInsertar = HijosDeN.first();
					HijosPadre.addBefore(primero,HijoAInsertar.element());
					TNodo<E> insertado = HijosPadre.prev(primero).element();
					insertado.setPadre(padre);
					HijosDeN.remove(HijoAInsertar);
				}
				
				HijosPadre.remove(primero);
				size--;
			}
		
		} catch(InvalidPositionException | EmptyListException | BoundaryViolationException e) {
			throw new InvalidPositionException("Error al eliminar");
		}
	}

	public void removeNode(Position<E> p) throws InvalidPositionException {
		try {
			TNodo<E> n = checkPosition(p);
		
			if(n==raiz) {
				if(size>=2 && raiz.getHijos().size()==1) {
					Position<TNodo<E>> raizNueva = raiz.getHijos().first();
					raizNueva.element().setPadre(null);
					raiz=raizNueva.element();
					size--;
				}
				else
					if (size==1) {
						raiz=null;
						size--;
					}
					else
						throw new InvalidPositionException("Error: No se puede remover la raiz de un arbol con elementos");
			
			} else {
				TNodo<E> padre = n.getPadre();
				PositionList<TNodo<E>> HijosDeN = n.getHijos();
				PositionList<TNodo<E>> HijosPadre = padre.getHijos();
				
				//Buscar N dentro de hijos padre
				
				Position<TNodo<E>> primero = HijosPadre.first();
				
				while (primero.element()!=n)
					primero = HijosPadre.next(primero);
				
				//Si n tiene hijos los recorro y los inserto en orden
				
				if (!HijosDeN.isEmpty())
					while(!HijosDeN.isEmpty()) {
						Position<TNodo<E>> HijoAInsertar = HijosDeN.first();
						HijosPadre.addBefore(primero,HijoAInsertar.element());
						TNodo<E> insertado = HijosPadre.prev(primero).element();
						insertado.setPadre(padre);
						HijosDeN.remove(HijoAInsertar);
					}
				
				HijosPadre.remove(primero);
				size--;
			}
		
		} catch(InvalidPositionException | EmptyListException | BoundaryViolationException e) {
			throw new InvalidPositionException("Error al eliminar");
		}
	}
	

	/**
	 * chequea si una posicion es valida y la devuelve con tipo estatico TNodo de E
	 * @param p una posicion del arbol
	 * @return TNodo que representa la posicion p
	 * @throws InvalidPositionException si la posicion p es nula.
	 */
	private TNodo<E> checkPosition(Position<E> p) throws InvalidPositionException{
		if (isEmpty())
			throw new InvalidPositionException("Error: No se puede operar sobre un arbol vacio");
		if (p==null)
			throw new InvalidPositionException("Error: Posicion Nula");
		
		TNodo<E> aux = null;
		
		try {
			aux=(TNodo<E>) p;
		} catch(ClassCastException e) {
			throw new InvalidPositionException("Error: Posicion Invalida");
		}
		
		return aux;
	}
	
	/**
	*	Se recorre recursivamente el árbol empezando desde la rama izquierda hasta llegar a la raíz devuelta creando un iterable con las posiciones de cada nodo la primera vez que se lo ve a una cadena de caracteres
	*	@param lista Lista donde se guardara el recorrido
	*	@param n Nodo que se usara para recorrer el arbol mientras se crea la lista, en su primer llamado debe ser la raiz del arbol que se desea recorrer
	*/
	private void pre(PositionList<Position<E>> lista, TNodo<E> n) {
		lista.addLast(n);
		Iterator<TNodo<E>> it = n.getHijos().iterator();
		while(it.hasNext())
			pre(lista,it.next());
		
	}
}
