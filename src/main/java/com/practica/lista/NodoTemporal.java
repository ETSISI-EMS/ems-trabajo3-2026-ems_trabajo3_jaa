package com.practica.lista;

import com.practica.genericas.FechaHora;


/**
 * Nodo para guardar un instante de tiempo. Además guardamos una lista con las coordeandas
 * y las personas (solo número) que en ese instante están en una coordeanda en concreto  
 *
 */
public class NodoTemporal implements Comparable<NodoTemporal> {
	private NodoPosicion listaCoordenadas; // TODO: cambiar por diccionario
	private FechaHora fecha;
	
	public NodoTemporal(FechaHora fecha) {
		listaCoordenadas = null;
		this.fecha = fecha;
	}

	public NodoPosicion getListaCoordenadas() {
		return listaCoordenadas;
	}

	public void setListaCoordenadas(NodoPosicion listaCoordenadas) {
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
