package com.alimesa.ordenpedido.controlador.ejecucion.modelo;

import java.io.Serializable;

public class RespuestaCarga implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long registrosAfectados;
	private String tipoSegistrosAfectados;
	private String mensaje;

	public Long getRegistrosAfectados() {
		/* 23 */ return this.registrosAfectados;
	}

	public void setRegistrosAfectados(Long registrosAfectados) {
		/* 26 */ this.registrosAfectados = registrosAfectados;
	}

	public String getTipoSegistrosAfectados() {
		/* 29 */ return this.tipoSegistrosAfectados;
	}

	public void setTipoSegistrosAfectados(String tipoSegistrosAfectados) {
		/* 32 */ this.tipoSegistrosAfectados = tipoSegistrosAfectados;
	}

	public String getMensaje() {
		/* 35 */ return this.mensaje;
	}

	public void setMensaje(String mensaje) {
		/* 38 */ this.mensaje = mensaje;
	}
}

/*
 * Location:
 * D:\apps\api-rest-ordenpedido\1.0\api-rest-ordenpedido-1.0.war!\WEB-INF\
 * classes\com\alimesa\ordenpedido\controlador\ejecucion\modelo\RespuestaCarga.
 * class Java compiler version: 11 (55.0) JD-Core Version: 1.1.3
 */