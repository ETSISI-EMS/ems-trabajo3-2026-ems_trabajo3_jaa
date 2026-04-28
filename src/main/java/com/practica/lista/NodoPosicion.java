package com.practica.lista;

import com.practica.genericas.Coordenada;

/**
 * Nodo para las coordenadas. En el guardamos cuántas personas están
 * en una coordenada en un momento temporal. 
 * Se almacena en un HashSet dentro de NodoTemporal.
 */
public class NodoPosicion {
	private Coordenada coordenada;	
	private int numPersonas;
	
	public NodoPosicion() {
		super();
	}
	
	public NodoPosicion(Coordenada coordenada, int numPersonas) {
		super();
		this.coordenada = coordenada;		
		this.numPersonas = numPersonas;
	}

	public Coordenada getCoordenada() {
		return coordenada;
	}

	public void setCoordenada(Coordenada coordenada) {
		this.coordenada = coordenada;
	}

	public int getNumPersonas() {
		return numPersonas;
	}

	public void setNumPersonas(int numPersonas) {
		this.numPersonas = numPersonas;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NodoPosicion other = (NodoPosicion) obj;
		if (coordenada == null) {
			if (other.coordenada != null)
				return false;
		} else if (!coordenada.equals(other.coordenada))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coordenada == null) ? 0 : coordenada.hashCode());
		return result;
	}
	
}
