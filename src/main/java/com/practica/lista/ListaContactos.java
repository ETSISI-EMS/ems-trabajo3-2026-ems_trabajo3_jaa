package com.practica.lista;

import com.practica.genericas.FechaHora;
import com.practica.genericas.PosicionPersona;

import java.util.SortedSet;
import java.util.StringJoiner;
import java.util.TreeSet;
import java.util.NavigableSet;

public class ListaContactos {
	private TreeSet<NodoTemporal> lista;
	
	public ListaContactos() {
		lista = new TreeSet<>();
	}

	/**
	 * Al estar implementado como un TreeSet, el método add se encargará de ordenar los nodos temporales por fecha,
	 * y de no permitir duplicados (si se intenta insertar un nodo temporal con la misma fecha que otro ya existente, no se insertará).
	 * Las coordenadas se almacenan en un HashSet dentro de cada NodoTemporal.
	 */
	public void insertarNodoTemporal (PosicionPersona p) {
		NodoTemporal aux = new NodoTemporal(p.getFechaPosicion());
		NodoTemporal existing = lista.floor(aux);

		if (existing != null && existing.getFecha().equals(aux.getFecha())) {
			aux = existing;
		} else {
			lista.add(aux);
		}

		// Buscar si la coordenada ya existe en el HashSet
		NodoPosicion npBuscada = aux.getListaCoordenadas().stream()
			.filter(np -> np.getCoordenada().equals(p.getCoordenada()))
			.findFirst()
			.orElse(null);

		if (npBuscada != null) {
			npBuscada.setNumPersonas(npBuscada.getNumPersonas() + 1);
		} else {
			NodoPosicion npNuevo = new NodoPosicion(p.getCoordenada(), 1);
			aux.getListaCoordenadas().add(npNuevo);
		}
	}

	public int personasEnCoordenadas () {
		return lista.stream().mapToInt(nodoTemporal ->
			nodoTemporal.getListaCoordenadas().stream()
				.mapToInt(NodoPosicion::getNumPersonas)
				.sum()
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
		SortedSet<NodoTemporal> rango = ((NavigableSet<NodoTemporal>) lista).subSet(
			new NodoTemporal(inicio), true,
			new NodoTemporal(fin), true
		);
		// Aplicamos map-reduce sobre el stream asociado al conjunto obtenido para realizar el cálculo
		return rango.stream().mapToInt(nodoTemporal ->
			nodoTemporal.getListaCoordenadas().stream()
				.mapToInt(NodoPosicion::getNumPersonas)
				.sum()
		).sum();
	}

	public int numNodosCoordenadaEntreDosInstantes(FechaHora inicio, FechaHora fin) {
		/**
		 * Al trabajar con un TreeSet, podemos obtener un rango de elementos
		 * del conjunto entre dos elementos, como en este caso un NodoTemporal
		 * implementa la interfaz Comparable, podemos crear dos nodos auxiliares
		 * temporales para definir un rango en el TreeSet.
		 */
		SortedSet<NodoTemporal> rango = ((NavigableSet<NodoTemporal>) lista).subSet(
			new NodoTemporal(inicio), true,
			new NodoTemporal(fin), true
		);
		// Aplicamos map-reduce sobre el stream asociado al conjunto obtenido para realizar el cálculo
		return rango.stream().mapToInt(nodoTemporal ->
			nodoTemporal.getListaCoordenadas().size()
		).sum();
	}

	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner(" ");

		for (NodoTemporal nodoTemporal : lista) {
			joiner.add(String.format("%s;%s",
				nodoTemporal.getFecha().getFecha(),
				nodoTemporal.getFecha().getHora())
			);
		}
		return joiner.toString();
	}
}
