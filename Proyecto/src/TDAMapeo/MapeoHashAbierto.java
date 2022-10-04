package TDAMapeo;

import java.util.Iterator;

import Auxiliar.InvalidKeyException;
import Auxiliar.InvalidPositionException;
import Auxiliar.Position;
import TDALista.*;

/**
*	Clase MapeoHashAbierto
*	@author Enriquez Merino Manuel y Herlein Rodrigo Nicolas
*/

public class MapeoHashAbierto<K,V> implements Map<K,V>{

	private PositionList<Entrada<K,V>> [] map;
	private int cant;  // cantidad de entradas
	private int tam;   // tamaño del arreglo
	private final float factor = 5;
	
	/**
 	* 	Inicializa un nuevo Objeto de la clase Mapeo Hash Abierto el cual representa un Mapeo Con Hash Abierto
	*/
	@SuppressWarnings("unchecked")
	public MapeoHashAbierto() {
		tam=13;
		map = (PositionList<Entrada<K,V>> []) new ListaDoblementeEnlazada[tam];
		for (int i = 0 ; i<tam ; i++) 
			map[i] = new ListaDoblementeEnlazada<Entrada<K,V>>();
		cant=0;
	}
	
	public int size() {
		return cant;
	}
	
	public boolean isEmpty() {
		return cant==0;
	}
	
	public V get(K key)throws InvalidKeyException{
		if(key==null)
			throw new InvalidKeyException("Error: Clave nula");
		
		int hash = FuncionHash(key);
		Entrada<K,V> aux = null;
		V retorno=null;
		boolean encontre = false;
		Iterator<Entrada<K, V>> it = map[hash].iterator();
		while (it.hasNext() && !encontre) {
			aux = it.next();
			encontre = aux.getKey().equals(key);
		}
		
		if (encontre)
			retorno = aux.getValue();

		return retorno;
	}
	
	public V put(K key, V value) throws InvalidKeyException{
		if (key==null)
			throw new InvalidKeyException("Error: Clave nula");
		
		if (cant/tam>factor)
			reHashing();
		
		int hash = FuncionHash(key);
		Entrada<K,V> aux = null;
		boolean encontre = false;
		Iterator<Entrada<K,V>> it = map[hash].iterator();
		V retorno=null;
		
		while(it.hasNext() && !encontre) {
			aux = it.next();
			encontre = aux.getKey().equals(key);
		}	
		
		if(encontre) {
			retorno = aux.getValue();
			aux.setValue(value);
		}
		else {
			map[hash].addLast(new Entrada<K, V>(key,value));
			cant++;
		}
		
		return retorno;
	}
	
	public V remove(K key) throws InvalidKeyException{
		if (key==null)
			throw new InvalidKeyException("Error: Clave nula");
		
		int hash = FuncionHash(key);
		Position<Entrada<K,V>> PosAux = null;
		boolean encontre = false;
		Iterator<Position<Entrada<K,V>>> it = map[hash].positions().iterator();
		V retorno=null;
		
		while(it.hasNext() && !encontre) {
			PosAux = it.next();
			encontre = PosAux.element().getKey().equals(key);
		}	
		
		if(encontre) {
			try {
				retorno = PosAux.element().getValue();
				map[hash].remove(PosAux);
				cant--;
			} catch (InvalidPositionException e) {
				throw new InvalidKeyException("Error al eliminar");
			}
		}
		
		return retorno;
	}
	
	public Iterable<K> keys(){
		PositionList<K> aux = new ListaDoblementeEnlazada<K>();
		for (int i=0; i<tam ; i++) {
			for (Entrada<K, V> e : map[i]) {
				aux.addLast(e.getKey());
			}
		}
		return aux;
	}
	
	public Iterable<V> values(){
		PositionList<V> aux = new ListaDoblementeEnlazada<V>();
		for (int i=0; i<tam ; i++) {
			for (Entrada<K, V> e : map[i]) {
				aux.addLast(e.getValue());
			}
		}
		return aux;
	}
	
	public Iterable<Entry<K,V>> entries(){
		PositionList<Entry<K,V>> aux = new ListaDoblementeEnlazada<Entry<K,V>>();
		for (int i=0; i<tam ; i++) {
			for (Entrada<K, V> e : map[i]) {
				aux.addLast(e);
			}
		}
		return aux;
	}
	
	/**
	 * Agranda el tamaño del arreglo que contiene el mapeo en caso de pasar el factor de carga
	 */
	@SuppressWarnings("unchecked")
	private void reHashing() {
		int tamPrevio=tam;
		int h;
		tam=2*primoSiguienteA(tam);
		PositionList<Entrada<K,V>> [] mapAux = (PositionList<Entrada<K,V>> []) new ListaDoblementeEnlazada[tam];
		for (int i = 0 ; i<tam ; i++) 
			mapAux[i] = new ListaDoblementeEnlazada<Entrada<K,V>>();
		
		for (int i=0; i<tamPrevio ; i++) {
			for (Entrada<K, V> e : map[i]) {
				h=FuncionHash(e.getKey());
				mapAux[h].addLast(e);
			}
		}
		
		map = mapAux;
	}
	
	/**
	 * Obtiene el primo siguiente al entero pasado por parametro
	 * @param p entero del cual se desea obtener un primo mayor
	 * @return primo siguiente a p
	 */
	private int primoSiguienteA(int p) {
		int aux = p+1;
		while (!esPrimo(aux))
			aux++;
		return aux;
	}
	
	/**
	 * Verifica si el entero pasado por parametro es primo
	 * @param k entero que se verifica si es primo
	 * @return verdadero si k es primo, caso contrario falso
	 */
	private boolean esPrimo(int k) {
		int aux = 2;
		boolean esPrimo = true;
		
		while (esPrimo && aux<k/2) {
			esPrimo=k%aux!=0;
			aux++;
		}
		
		return esPrimo;
	}
	
	/**
	 * Calcula la funcion Hash
	 * @param key valor del cual se desea obtener la funcion hash
	 * @return valor de la funcion hash de key
	 */
	private int FuncionHash(K key) {
		int resultado;
		int x = key.hashCode();
		x= Math.abs(x);
		resultado = x % tam;
		return resultado;
	}
	
	
}
