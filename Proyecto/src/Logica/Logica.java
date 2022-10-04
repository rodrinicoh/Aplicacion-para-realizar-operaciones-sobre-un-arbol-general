

package Logica;

import java.util.Iterator;

import Auxiliar.ArbolVacioException;
import Auxiliar.BoundaryViolationException;
import Auxiliar.EmptyListException;
import Auxiliar.EmptyQueueException;
import Auxiliar.EmptyStackException;
import Auxiliar.EmptyTreeException;
import Auxiliar.InvalidKeyException;
import Auxiliar.InvalidOperationException;
import Auxiliar.InvalidPositionException;
import Auxiliar.PosicionInvalidaException;
import Auxiliar.Position;
import Auxiliar.RotuloNoPerteneceAlArbolException;
import Auxiliar.YaExisteRaizException;
import TDAArbol.ArbolGeneral;
import TDAArbol.Tree;
import TDACola.ColaConArregloCircular;
import TDACola.Queue;
import TDALista.ListaDoblementeEnlazada;
import TDALista.PositionList;
import TDAMapeo.Entry;
import TDAMapeo.Map;
import TDAMapeo.MapeoHashAbierto;
import TDAPila.PilaConEnlaces;
import TDAPila.Stack;

/**
*	Clase Logica
*	@author Enriquez Merino Manuel y Herlein Rodrigo Nicolas
*/

public class Logica {
	
	private Tree<Integer> arbol;
	
	/**
 	* 	Inicializa un nuevo Objeto de la clase Lógica el cual usa un arbol de enteros
	*/
	public Logica() {
		arbol = new ArbolGeneral<Integer>();
	}
	
	/**
	*	Establece la raiz del arbol usado por esta clase
	*	@param r El Rótulo de la raíz que será creada
	*	@throws YaExisteRaizException si el árbol ya tiene raíz
	*/
	public void CargarArbol(int r) throws YaExisteRaizException {
		try {
			arbol.createRoot(r);
		} catch (InvalidOperationException e) {
			throw new YaExisteRaizException("El árbol ya tiene raiz");
		}
	}
	
	/**
	*	Agrega un nodo con rótulo r como último hijo de un nodo con rotulo p
	*	@param r el rótulo del nuevo nodo
	*	@param p el rótulo del padre del nuevo nodo
	*	@throws RotuloNoPerteneceAlArbolException si el rotulo del padre pasado por parámetro no pertenece al árbol
	*	@throws ArbolVacioException si el árbol está vacío
	*/
	public void AgregarNodo(int r, int p) throws RotuloNoPerteneceAlArbolException, ArbolVacioException{
		Position<Integer> pos = encontrarRotulo(p);
		try {
			arbol.addLastChild(pos, r);
		} catch (InvalidPositionException e) {
			//En este catch no va a entrar nunca ya que si p no es una posicion valida encontrar rotulo lanza RotuloNoPerteneceAlArbolException
			throw new RotuloNoPerteneceAlArbolException("El rotulo " +p+ " no pertenece al arbol");
		}
	}
	
	/**
	*	Crea una cadena de caracteres del árbol empezando desde la rama izquierda hasta llegar a la raíz concatenando el rótulo de cada nodo la primer vez que se lo ve a una cadena de caracteres
	*	@return Cadena de caracteres con los rótulos del árbol
	*	@throws ArbolVacioException si el árbol está vacío
	*	@throws PosicionInvalidaException si el árbol cuenta con posiciones inválidas
	*/
	public String preOrden() throws ArbolVacioException, PosicionInvalidaException {
		try {
			return pre(arbol.root());
		
		} catch (EmptyTreeException e) {
			throw new ArbolVacioException("No se puede mostrar en preOrden un arbol vacio");
		} 
	}
	
	/**
	*	Se recorre recursivamente el árbol empezando desde la rama izquierda hasta llegar a la raíz devuelta imprimiendo el rótulo de cada nodo la primera vez que se lo ve a una cadena de caracteres
	*	@param	n posicion recursiva del arbol, al llamarlo por primera vez usar la raiz del arbol que se desea obtener el preorden
	*	@return Cadena de caracteres con los rótulos del árbol
	*	@throws PosicionInvalidaException si el árbol cuenta con posiciones inválidas
	*/
	private String pre(Position<Integer> n) throws PosicionInvalidaException {
		try {
			String s="" + n.element();
			Iterator<Position<Integer>> it = arbol.children(n).iterator();
			while(it.hasNext())
				s = s + "  " + pre(it.next());
			return s;
		
		} catch (InvalidPositionException e) {
			throw new PosicionInvalidaException("Error al crear PreOrden");
		}
		
	}
	
	/**
	*	Crea una cadena de caracteres del árbol empezando desde la rama izquierda hasta llegar a la raíz concatenando el rótulo de cada nodo la primer vez que se lo ve a una cadena de caracteres
	*	@return Cadena de caracteres con los rótulos del árbol
	*	@throws ArbolVacioException si el árbol está vacío
	*	@throws PosicionInvalidaException si el árbol cuenta con posiciones inválidas
	*/
	public String posOrden() throws ArbolVacioException, PosicionInvalidaException {
		try {
			String s = "";
			s = pos(s,arbol.root());
			return s.substring(0,s.length()-1);
		
		} catch (EmptyTreeException e) {
			throw new ArbolVacioException("No se puede mostrar en posOrden un arbol vacio");
		} 
	}
	
	/**
	*	Se recorre recursivamente el árbol empezando desde la rama izquierda hasta llegar a la raíz devuelta imprimiendo el rótulo de cada nodo la última vez que se lo ve a una cadena de caracteres
	*	@param s cadena de caracteres que se utiliza para almacenar los rotulos
	*	@param	n posicion recursiva del arbol, al llamarlo por primera vez usar la raiz del arbol que se desea obtener el posorden
	*	@return Cadena de caracteres con los rótulos del árbol
	*	@throws PosicionInvalidaException si el árbol cuenta con posiciones inválidas
	*/
	private String pos(String s,Position<Integer> n) throws PosicionInvalidaException {
		try {
			Iterator<Position<Integer>> it = arbol.children(n).iterator();
			while(it.hasNext())
				 s="" + pos(s,it.next());	
			s= s + n.element() + "  ";
			return s;
		
		} catch (InvalidPositionException e) {
			throw new PosicionInvalidaException("Error al crear PosOrden");
		}
	}
	
	/**
	*	Crea una cadena de caracteres del árbol representando por la clase logica empezando desde el primer nivel y agregando los siguientes niveles en las líneas siguientes
	*	@return Cadena de caracteres con los rótulos del árbol representado por la clase logica
	*	@throws ArbolVacioException si el árbol está vacío
	*	@throws PosicionInvalidaException si el árbol cuenta con posiciones inválidas
	*/
	public String porNiveles() throws PosicionInvalidaException, ArbolVacioException  {
		
		return Niveles(arbol);
	}
	
	/**
	*	Crea una cadena de caracteres del árbol empezando desde el primer nivel y agregando los siguientes niveles en las líneas siguientes
	*	@param t un árbol de enteros
	*	@return Cadena de caracteres con los rótulos del árbol
	*	@throws ArbolVacioException si el árbol está vacío
	*	@throws PosicionInvalidaException si el árbol cuenta con posiciones inválidas
	*/
	private String Niveles(Tree<Integer> t) throws PosicionInvalidaException, ArbolVacioException  {
		try {
			String L="";
			Iterator<Position<Integer>> IT=null;
			Position<Integer> cursor=null;
			Position<Integer> posicion=null;
			Queue<Position<Integer>> Q=new ColaConArregloCircular<Position<Integer>>();
			Q.enqueue(t.root());
			Q.enqueue(null); //Detecta el fin de linea
			while(!(Q.isEmpty())) {
				posicion=Q.dequeue();
				if(posicion!=null) {
					L=L+"  "+posicion.element();//visito v 
					IT=(t.children(posicion)). iterator();
					while(IT.hasNext()) { //para cada hijo (cursor) de v
						cursor=IT.next();
						Q.enqueue(cursor);
					}
				}
				else {
					if(!(Q.isEmpty())) {
						Q.enqueue(null);
						L=L+"\n";
					}
				}
			}
			return L;
		}
		catch(EmptyQueueException e) {
			throw new PosicionInvalidaException("Error al crear el recorrido por niveles");
		}
		catch(EmptyTreeException e) {
			throw new ArbolVacioException("Error al crear el recorrido por niveles");
		}
		catch(InvalidPositionException e) {
			throw new PosicionInvalidaException("Error al crear el recorrido por niveles");
		}
	}
	
	/**
	*	Devuelve el ancestro común entre los nodos con rótulo r1 y r2
	*	@param r1 el rótulo del primer nodo del cual buscaremos el ancestro común mas cercano
	*	@param r2 el rótulo del primer nodo del cual buscaremos el ancestro común mas cercano
	*	@return el ancestro común entre ambos nodos
	*	@throws RotuloNoPerteneceAlArbolException si no hay nodos con rótulo r1 o r2
	*	@throws PosicionInvalidaException si el árbol cuenta con posiciones inválidas
	*	@throws ArbolVacioException si el árbol está vacío
	*/
	public int AncestroComunMasCercano(int r1, int r2) throws RotuloNoPerteneceAlArbolException, PosicionInvalidaException, ArbolVacioException {
		
		try {
			Position<Integer> rotulo1 = encontrarRotulo(r1);
			Position<Integer> rotulo2 = encontrarRotulo(r2);
		
			Stack<Integer> PilaDePadresDeR1 = new PilaConEnlaces<Integer>();
			Stack<Integer> PilaDePadresDeR2 = new PilaConEnlaces<Integer>();
		
			while (rotulo1!=null) {
				PilaDePadresDeR1.push(rotulo1.element());
				if(rotulo1!=arbol.root())
					rotulo1=arbol.parent(rotulo1);
				else
					rotulo1=null;
			}
		
			while (rotulo2!=null) {
				PilaDePadresDeR2.push(rotulo2.element());
				if(rotulo2!=arbol.root())
					rotulo2=arbol.parent(rotulo2);
				else
					rotulo2=null;
			}
			
			//Estos dos whiles terminan cuando se apila la raiz
			
			Integer ancestroComun=PilaDePadresDeR1.top();//ancestroComun=raiz
			boolean encontre=false;
			Integer cursorR1=null;
			Integer cursorR2=null;
			while(!PilaDePadresDeR1.isEmpty() && !PilaDePadresDeR2.isEmpty() && !encontre){
				cursorR1=PilaDePadresDeR1.pop();
			  	cursorR2=PilaDePadresDeR2.pop();
			  	if(cursorR1==cursorR2)
			  		ancestroComun=cursorR1;
			 	else
			  		encontre=true;
			}
			  
			  
		    return ancestroComun;
		
		} catch (InvalidPositionException | BoundaryViolationException | EmptyStackException e) {
			//Ninguna de estas excepciones deberian ser lanzadas
			throw new PosicionInvalidaException("Error al buscar el ancestro mas cercano de " +r1+ " y " +r2);
		} catch (EmptyTreeException e) {
			throw new ArbolVacioException("No se pueden buscar ancestros comunes en un arbol vacio");
		}
		
	}


	/**
	*	Crea un espejo multiplicativo del arbol en forma de String el cual multiplica los rotulos de los nodos por un entero.
	*	@param k entero por el que se multiplicaran los rotulos
	*	@return String que contiene al espejo del arbol multiplicando los rotulos de sus nodos por un entero k
	*	@throws ArbolVacioException si el arbol esta vacio
	*	@throws PosicionInvalidaException si una posicion del arbol es invalida
	*/
	public String CrearEspejo(int k) throws ArbolVacioException, PosicionInvalidaException {
		try {
			Tree<Integer> arbolNuevo = new ArbolGeneral<Integer>();
			arbolNuevo.createRoot(arbol.root().element()*k);
			invertir(k,arbolNuevo,arbol.root(),arbolNuevo.root());
			return (Niveles(arbolNuevo));
		
		} catch (PosicionInvalidaException | InvalidOperationException e) {
			throw new PosicionInvalidaException("Error al crear espejo multiplicativo");
		} catch (EmptyTreeException e) {
			throw new ArbolVacioException("No se puede crear el espejo de un arbol vacio");
		}
		
	}
	
	
	/**
	*	Crea un espejo multiplicativo del arbol en forma de String el cual multiplica los rotulos de los nodos por un entero.
	*	@param k entero por el que se multiplicaran los rótulos
	*	@param arbolNuevo árbol donde se va a crear el espejo multiplicativo
	*	@param posOriginal posicion del arbol usado por la clase logica
	*	@param posNuevo posicion padre de lo insertado en arbol nuevo
	*	@throws PosicionInvalidaException si una posicion del arbol es invalida
	*/
	private void invertir (int k, Tree<Integer> arbolNuevo, Position<Integer> posOriginal, Position<Integer> posNuevo) throws PosicionInvalidaException {	
		try {
			if(arbol.isInternal(posOriginal)) {
				for(Position<Integer> v:arbol.children(posOriginal)) {
					Position<Integer> posAux = arbolNuevo.addFirstChild(posNuevo, v.element()*k);
					invertir(k,arbolNuevo,v,posAux);
				}
			}		
		} catch (InvalidPositionException e) {
		throw new PosicionInvalidaException("Error al crear espejo multiplicativo");
		}
	}
	

	/**
	 * 	Crea un String de las posiciones del arbol indicando en cada una su rotulo altura y profundidad
	 * 	@return un String de las posiciones del arbol indicando en cada una su rotulo altura y profundidad
	 * 	@throws PosicionInvalidaException si una posicion del arbol es invalida
	 */
	public String Mapeo() throws PosicionInvalidaException {
		try {
			Map<Integer,PositionList<Integer>> mapeo = new MapeoHashAbierto<Integer,PositionList<Integer>>();
			
			if (!arbol.isEmpty())
				CrearMapeo(mapeo,arbol.root(),0);
			return MapeoAString(mapeo);
			
		} catch (EmptyTreeException e) {
			//esta excepcion no deberia ser lanzada
			throw new PosicionInvalidaException("Error al crear el mapeo del arbol");
		}
	}
	
	//Metodos Auxiliares




	/**
	*	Encuentra un nodo en el arbol con un rotulo dado
	*	@param r rotulo del nodo que se quiere encontrar
	*	@return Posicion del nodo del arbol con rotulo r
	*	@throws RotuloNoPerteneceAlArbolException si el rotulo no pertenece a un nodo del arbol
	*	@throws ArbolVacioException si arbol esta vacio.
	*/
	private Position<Integer> encontrarRotulo(int r) throws RotuloNoPerteneceAlArbolException, ArbolVacioException{
		if (arbol.isEmpty())
			throw new ArbolVacioException("No se le pueden solicitar cosas a un arbol vacio");
		Iterable<Position<Integer>> ListaAux = arbol.positions();
		Iterator<Position<Integer>> it = ListaAux.iterator();
		Position<Integer> pos=null;
		boolean encontre = false;
		while (it.hasNext() && !encontre) {
			pos = it.next();
			encontre = pos.element()==r;
		}
		if (!encontre)
			throw new RotuloNoPerteneceAlArbolException("El rotulo " +r+ " no pertenece al arbol");
		return pos;
	}



	/**
	*	Crea un mapeo de los nodos del árbol donde cada entrada representa a un nodo y posee su rotulo, su altura y su profundidad.
	*	@param M mapeo a crear cuyas entradas están formadas por un Integer y una lista de Integer
	*	@param pos posición del nodo que se ingresara al mapeo
	*	@param prof es la profundidad de pos en el árbol
	*	@return entero que muestra la altura de la posición+1
	*	@throws PosicionInvalidaException si pos es una posición inválida
	*/
	private Integer CrearMapeo(Map<Integer, PositionList<Integer>> M,Position<Integer> pos, int prof) throws PosicionInvalidaException { 
		//prof inicialmente es 0
	/*
	*	Planteo del problema:
	*	Titulo: Crear Mapeo
	*	Caso Base: Si pos es la posición de una hoja del árbol entonces la altura del arbol es 0 y la profundidad es la profundidad del nodo padre de pos+1
	*	Caso Recursivo: Si pos no es la posicion de una hoja del arbol entonces la altura del arbol sera 1+ la altura de arbolprimo y su profundidad sera la profundidad del padre de pos +1.
	*	donde arbolprimo es igual al arbol formado por el hijo de pos como raiz y sus respectivos hijos.
	*/
		try{
			int aux=0;
			int altura=0;
			PositionList<Integer> L=new ListaDoblementeEnlazada<Integer>();
			M.put(pos.element(), L);
		
		
			if(arbol.isExternal(pos)) {
				L.addFirst(prof);
				L.addFirst(altura);
			}
			else {
				Iterator<Position<Integer>> IT=arbol.children(pos).iterator();
				Position<Integer> cursor=null;
				while(IT.hasNext()) {
					cursor=IT.next();
					aux=altura;
					altura=1+CrearMapeo(M, cursor,prof+1);
					

					if(aux>altura) {
						altura=aux;
					}	
					
				}
				L.addFirst(prof);
				L.addFirst(altura);
			}
			return altura++;
		} catch (InvalidKeyException e){
			//Esta excepcion no deberia ser lanzada si el arbol se creo correctamente
			throw new PosicionInvalidaException("Error al crear el mapeo del arbol");
		} catch (InvalidPositionException e) {
			//Esta excepcion no deberia ser lanzada si el arbol se creo correctamente
			throw new PosicionInvalidaException("Error al crear el mapeo del arbol");
		} 
	}
	



	/**
	*	Pasa un mapeo a tipo String
	*	@param e un mapeo con entradas de formadas por un Integer y una Lista de Integer.
	*	@return un String de cada entrada del mapeo
	*	@throws PosicionInvalidaException si una posicion del arbol es invalida.
	*/
	private String MapeoAString(Map<Integer,PositionList<Integer>> e) throws PosicionInvalidaException {
		try {
			String s="";
			for(Entry<Integer, PositionList<Integer>> p: e.entries()) {
				s= s + "Rotulo: " + p.getKey() + ". Altura: " + p.getValue().first().element() + ". Profundidad: " + p.getValue().last().element() + "\n";
			}
			return s;
		} catch (EmptyListException exc) {
			throw new PosicionInvalidaException("Error al crear el mapeo del arbol");
		}
	}
}

