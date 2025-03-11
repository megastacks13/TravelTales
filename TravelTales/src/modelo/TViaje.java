package modelo;

import java.io.Serializable;

public class TViaje implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
    private String nombre;
	private String destino;
	private int numPersonas;
	private String fechaIni;
	private String fechaFin;
	
	
	public TViaje(int id, String nombre, String destino, int numPersonas, String fechaIni, String fechaFin) {
		this.id = id;
		this.nombre = nombre;
		this.destino = destino;
		this.numPersonas = numPersonas;
		this.fechaIni = fechaIni;
		this.fechaFin = fechaFin;
	}
	
	public TViaje(String nombre, String destino, int numPersonas, String fechaIni, String fechaFin) {
		this.nombre = nombre;
		this.destino = destino;
		this.numPersonas = numPersonas;
		this.fechaIni = fechaIni;
		this.fechaFin = fechaFin;
	}
	
	public TViaje() {};
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumPersonas() {
		return id;
	}


	public void setNumPersonas(int numPersonas) {
		this.numPersonas = numPersonas;
	}
	
	public String getDestino() {
		return destino;
	}


	public void setDestino(String destino) {
		this.destino = destino;
	}


	public String getFechaIni() {
		return fechaIni;
	}


	public void setFechaIni(String fechaIni) {
		this.fechaIni = fechaIni;
	}


	public String getFechaFin() {
		return fechaFin;
	}


	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	@Override
	public boolean equals (Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null || this.getClass() != obj.getClass())
			return false;
		
		TViaje viaje = (TViaje) obj; 
		
		return  this.id == viaje.getId() && 
				this.nombre.equals(viaje.getNombre()) && 
				this.destino.equals(viaje.getDestino()) && 
				this.numPersonas == viaje.getNumPersonas() &&
				this.fechaIni.equals(viaje.getFechaIni()) &&
				this.fechaFin.equals(viaje.getFechaFin());
	}


	
}
