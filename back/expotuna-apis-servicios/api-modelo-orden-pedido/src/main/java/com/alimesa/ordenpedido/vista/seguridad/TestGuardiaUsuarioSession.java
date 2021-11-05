package com.alimesa.ordenpedido.vista.seguridad;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.alimesa.ordenpedido.librerias.generales.UtilEncriptarDataSource;
import com.alimesa.ordenpedido.librerias.vista.anotaciones.ITestGuardiaSession;
import com.alimesa.ordenpedido.librerias.vista.anotaciones.ITestUsuarioSession;
import com.alimesa.ordenpedido.librerias.vista.interfaces.IGuardiaUsuarioSession;
import com.alimesa.ordenpedido.librerias.vista.interfaces.IUsuarioSession;
import com.alimesa.ordenpedido.modelo.seguridad.Usuario;

// TODO: Auto-generated Javadoc
/**
 * The Class TestGuardiaUsuarioSession.
 */
@ITestGuardiaSession
@RequestScoped
public class TestGuardiaUsuarioSession implements IGuardiaUsuarioSession {

	/** The usuario session. */
	@Inject
	@ITestUsuarioSession
	private IUsuarioSession<Usuario> usuarioSession;

	/*
	 * (non-Javadoc)
	 * usuarioEnSession()
	 */
	public boolean usuarioEnSession() {
		return !(usuarioSession == null || usuarioSession.getUsuarioSession() == null);
	}

	/*
	 * (non-Javadoc)
	 * validarSemilla(java.lang.String)
	 */
	public boolean validarSemilla(String semilla) {
		try {
			String semillaReal = UtilEncriptarDataSource
					.encode(usuarioSession.getUsuarioSession().getClave().toUpperCase()
							+ usuarioSession.getUsuarioSession().getUsuario().toUpperCase());
			return semillaReal.equals(semilla);
		} catch (Exception e) {
			return false;
		}
	}
}
