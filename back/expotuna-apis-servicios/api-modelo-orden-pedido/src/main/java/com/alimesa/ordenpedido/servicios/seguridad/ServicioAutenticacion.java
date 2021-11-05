package com.alimesa.ordenpedido.servicios.seguridad;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.alimesa.ordenpedido.modelo.seguridad.Usuario;
import com.alimesa.ordenpedido.repositorios.seguridad.UsuarioEAO;

// TODO: Auto-generated Javadoc
/**
 * The Class ServicioAutenticacion.
 */
@Stateless
public class ServicioAutenticacion {

	/** The pb usuario. */
	@EJB
	private UsuarioEAO pbUsuario;

	/**
	 * Autenticar.
	 *
	 * @param pUsuario the usuario
	 * @param pClave   the clave
	 * @return the oms usuario
	 */
	public Usuario autenticar(String pUsuario, String pClave) {

		Usuario usuario = pbUsuario.buscarUsuario(pUsuario);

		if (usuario == null) {
			System.err.println("El usuario no existe");
			return null;
		}

		if (usuario.getUsuario() == null || usuario.getUsuario() == "") {
			System.err.println("El usuario no existe");
			return null;
		}

		if (!usuario.getClave().equals(pClave)) {
			System.err.println("La clave no es la correcta");
			return null;
		}
		return usuario;
	}

}
