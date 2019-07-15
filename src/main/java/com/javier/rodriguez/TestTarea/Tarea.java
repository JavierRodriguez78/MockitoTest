package com.javier.rodriguez.TestTarea;
import java.util.Date;

public class Tarea {

	private String nombre;
	private Date fechaCreacion;

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Tarea(String nombre) {
		this.nombre = nombre;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

}
