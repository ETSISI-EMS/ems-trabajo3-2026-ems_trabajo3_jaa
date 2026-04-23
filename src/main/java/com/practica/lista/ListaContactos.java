package com.practica.lista;

import com.practica.genericas.FechaHora;
import com.practica.genericas.PosicionPersona;

import java.util.SortedSet;
import java.util.TreeSet;

public class ListaContactos {
	private TreeSet<NodoTemporal> lista;
	
	public ListaContactos() {
		lista = new TreeSet<>();
	}

	/**
	 * Al estar implementado como un TreeSet, el método add se encargará de ordenar los nodos temporales por fecha,
	 * y de no permitir duplicados (si se intenta insertar un nodo temporal con la misma fecha que otro ya existente, no se insertará).
	 * Por lo tanto, no es necesario realizar una búsqueda previa para comprobar si el nodo temporal ya existe, ni para encontrar la posición correcta de inserción.
	 * Simplemente se añade el nuevo nodo temporal al TreeSet, y este se encargará de mantener el orden y la unicidad de los nodos temporales.
	 */
	public void insertarNodoTemporal (PosicionPersona p) {
		lista.add(new NodoTemporal(p.getFechaPosicion()));
	}
	
	public int personasEnCoordenadas () {
		return lista.stream().mapToInt(
			nodoTemporal -> nodoTemporal.getListaCoordenadas().getNumPersonas()
		).sum();
	}
	
	public int tamanioLista () {
		return lista.size();
	}

	public String getPrimerNodo() {
		NodoTemporal aux = lista.first();
		String stringFecha = aux.getFecha().getFecha().toString();
		String stringHora = aux.getFecha().getHora().toString();
		return String.format("%s;%s", stringFecha, stringHora);
	}

	/**
	 * Métodos para comprobar que insertamos de manera correcta en las listas de 
	 * coordenadas, no tienen una utilidad en sí misma, más allá de comprobar que
	 * nuestra lista funciona de manera correcta.
	 */
	public int numPersonasEntreDosInstantes(FechaHora inicio, FechaHora fin) {
		/**
		 * Al trabajar con un TreeSet, podemos obtener un rango de elementos
		 * del conjunto entre dos elementos, como en este caso un NodoTemporal
		 * implementa la interfaz Comparable, podemos crear dos nodos auxiliares
		 * temporales para definir un rango en el TreeSet.
		 */
		SortedSet<NodoTemporal> rango = lista.subSet(
			new NodoTemporal(inicio),
			new NodoTemporal(fin)
		);
		// Aplicamos map-reduce sobre el stream asociado al conjunto obtenido para realizar el cálculo
		return rango.stream().mapToInt(
			nodoTemporal -> nodoTemporal.getListaCoordenadas().getNumPersonas()
		).sum();
	}
	
	public int numNodosCoordenadaEntreDosInstantes(FechaHora inicio, FechaHora fin) {
		/**
		 * Al trabajar con un TreeSet, podemos obtener un rango de elementos
		 * del conjunto entre dos elementos, como en este caso un NodoTemporal
		 * implementa la interfaz Comparable, podemos crear dos nodos auxiliares
		 * temporales para definir un rango en el TreeSet.
		 */
		SortedSet<NodoTemporal> rango = lista.subSet(
			new NodoTemporal(inicio),
			new NodoTemporal(fin)
		);
		// Aplicamos map-reduce sobre el stream asociado al conjunto obtenido para realizar el cálculo
		return rango.stream().mapToInt(
			nodoTemporal -> {
				int cont = 0;
				NodoPosicion nodoPosicion = nodoTemporal.getListaCoordenadas();
				while (nodoPosicion != null) {
					cont++;
					nodoPosicion = nodoPosicion.getSiguiente();
				}
				return cont;
			}
		).sum();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (NodoTemporal nodoTemporal : lista) {
			sb.append(String.format("%s;%s ",
				nodoTemporal.getFecha().getFecha(),
				nodoTemporal.getFecha().getHora())
			);
		}
		return sb.toString();
	}
	
	
	
}
