package com.practica.lista;

import com.practica.genericas.FechaHora;
import java.util.HashSet;


/**
 * Nodo para guardar un instante de tiempo. Además guardamos un HashSet con las coordenadas
 * y las personas (solo número) que en ese instante están en una coordenada en concreto  
 *
 */
public class NodoTemporal implements Comparable<NodoTemporal> {
	private HashSet<NodoPosicion> listaCoordenadas;
	private FechaHora fecha;
	
	public NodoTemporal(FechaHora fecha) {
		listaCoordenadas = new HashSet<>();
		this.fecha = fecha;
	}

	public HashSet<NodoPosicion> getListaCoordenadas() {
		return listaCoordenadas;
	}

	public void setListaCoordenadas(HashSet<NodoPosicion> listaCoordenadas) {
		this.listaCoordenadas = listaCoordenadas;
	}

	public FechaHora getFecha() {
		return fecha;
	}

	@Override
	public int compareTo(NodoTemporal o) {
		return this.fecha.compareTo(o.getFecha());
	}
}
