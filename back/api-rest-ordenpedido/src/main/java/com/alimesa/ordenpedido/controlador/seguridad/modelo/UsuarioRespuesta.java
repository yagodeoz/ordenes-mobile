package com.alimesa.ordenpedido.controlador.seguridad.modelo;

import java.io.Serializable;

public class UsuarioRespuesta implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String estado;
	private String clave;
	private String usuario;
	private String descripcion;
	private String nombres;
	private String apellidos;
	private String fechaactualizacion;
	private String fecharegistro;
	private String correo;

	public UsuarioRespuesta() {
	}

	public UsuarioRespuesta(Long id, String estado, String clave, String usuario, String descripcion, String nombres,
			String apellidos, String fechaactualizacion, String fecharegistro, String correo) {
		/* 33 */ this.id = id;
		/* 34 */ this.estado = estado;
		/* 35 */ this.clave = clave;
		/* 36 */ this.usuario = usuario;
		/* 37 */ this.descripcion = descripcion;
		/* 38 */ this.nombres = nombres;
		/* 39 */ this.apellidos = apellidos;
		/* 40 */ this.fechaactualizacion = fechaactualizacion;
		/* 41 */ this.fecharegistro = fecharegistro;
		/* 42 */ this.correo = correo;
	}

	public Long getId() {
		/* 46 */ return this.id;
	}

	public void setId(Long id) {
		/* 50 */ this.id = id;
	}

	public String getEstado() {
		/* 54 */ return this.estado;
	}

	public void setEstado(String estado) {
		/* 58 */ this.estado = estado;
	}

	public String getClave() {
		/* 62 */ return this.clave;
	}

	public void setClave(String clave) {
		/* 66 */ this.clave = clave;
	}

	public String getUsuario() {
		/* 70 */ return this.usuario;
	}

	public void setUsuario(String usuario) {
		/* 74 */ this.usuario = usuario;
	}

	public String getDescripcion() {
		/* 78 */ return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		/* 82 */ this.descripcion = descripcion;
	}

	public String getNombres() {
		/* 86 */ return this.nombres;
	}

	public void setNombres(String nombres) {
		/* 90 */ this.nombres = nombres;
	}

	public String getApellidos() {
		/* 94 */ return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		/* 98 */ this.apellidos = apellidos;
	}

	public String getFechaactualizacion() {
		/* 102 */ return this.fechaactualizacion;
	}

	public void setFechaactualizacion(String fechaactualizacion) {
		/* 106 */ this.fechaactualizacion = fechaactualizacion;
	}

	public String getFecharegistro() {
		/* 110 */ return this.fecharegistro;
	}

	public void setFecharegistro(String fecharegistro) {
		/* 114 */ this.fecharegistro = fecharegistro;
	}

	public String getCorreo() {
		/* 118 */ return this.correo;
	}

	public void setCorreo(String correo) {
		/* 122 */ this.correo = correo;
	}
}

/*
 * Location:
 * D:\apps\api-rest-ordenpedido\1.0\api-rest-ordenpedido-1.0.war!\WEB-INF\
 * classes\com\alimesa\ordenpedido\controlador\seguridad\modelo\UsuarioRespuesta
 * .class Java compiler version: 11 (55.0) JD-Core Version: 1.1.3
 */