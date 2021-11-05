
package com.alimesa.ordenpedido.controlador.seguridad.modelo;


 import java.io.Serializable;








 public class UsuarioSolicitud implements Serializable
 {
	 private static final long serialVersionUID = 1L;
	 private String usuario;
	 private String password;

	
	 public UsuarioSolicitud() {
	}

	
	 public UsuarioSolicitud(String usuario, String password) {
		/* 21 */ this.usuario = usuario;
		/* 22 */ this.password = password;
		 }

	
	 public String getUsuario() {
		/* 26 */ return this.usuario;
		 }

	
	 public void setUsuario(String usuario) {
		/* 30 */ this.usuario = usuario;
		 }

	
	 public String getPassword() {
		/* 34 */ return this.password;
		 }

	
	 public void setPassword(String password) {
		/* 38 */ this.password = password;
		 }
	 }

/*
 * Location:
 * D:\apps\api-rest-ordenpedido\1.0\api-rest-ordenpedido-1.0.war!\WEB-INF\
 * classes\com\alimesa\ordenpedido\controlador\seguridad\modelo\UsuarioSolicitud
 * .class Java compiler version: 11 (55.0) JD-Core Version: 1.1.3
 */