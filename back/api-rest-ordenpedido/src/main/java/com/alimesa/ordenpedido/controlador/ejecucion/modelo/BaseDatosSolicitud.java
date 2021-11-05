package com.alimesa.ordenpedido.controlador.ejecucion.modelo;

import java.io.Serializable;

public class BaseDatosSolicitud implements Serializable {
	private static final long serialVersionUID = 1L;
	private String usuario;
	private String fechaSincronizacion;

	public BaseDatosSolicitud(String usuario, String fechaSincronizacion) {
		this.usuario = usuario;
		this.fechaSincronizacion = fechaSincronizacion;
	}

	public BaseDatosSolicitud() {
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getFechaSincronizacion() {
		return this.fechaSincronizacion;
	}

	public void setFechaSincronizacion(String fechaSincronizacion) {
		this.fechaSincronizacion = fechaSincronizacion;
	}
}
