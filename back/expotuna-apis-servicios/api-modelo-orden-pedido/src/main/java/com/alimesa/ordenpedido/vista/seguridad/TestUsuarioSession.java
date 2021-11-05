package com.alimesa.ordenpedido.vista.seguridad;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

import com.alimesa.ordenpedido.librerias.vista.anotaciones.ITestUsuarioSession;
import com.alimesa.ordenpedido.librerias.vista.interfaces.IUsuarioSession;
import com.alimesa.ordenpedido.modelo.seguridad.Usuario;

// TODO: Auto-generated Javadoc
/**
 * The Class TestUsuarioSession.
 */
@ITestUsuarioSession
@SessionScoped
public class TestUsuarioSession implements IUsuarioSession<Usuario>, Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The usuario. */
	private Usuario usuario;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.onix.modulo.librerias.vista.interfaces.IUsuarioSession#setUsuarioSession(
	 * java.io.Serializable)
	 */
	public void setUsuarioSession(Usuario usuario) {
		this.usuario = usuario;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.onix.modulo.librerias.vista.interfaces.IUsuarioSession#getUsuarioSession(
	 * )
	 */
	public Usuario getUsuarioSession() {

		return this.usuario;
	}

}
