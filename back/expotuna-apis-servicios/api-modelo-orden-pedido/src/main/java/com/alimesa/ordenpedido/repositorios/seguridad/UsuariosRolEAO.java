package com.alimesa.ordenpedido.repositorios.seguridad;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import com.alimesa.ordenpedido.configuracion.EAO;
import com.alimesa.ordenpedido.modelo.seguridad.UsuariosRol;

// TODO: Auto-generated Javadoc
/**
 * The Class OmsUsuariosRoleEAO.
 */
@Stateless
@LocalBean
public class UsuariosRolEAO extends EAO<UsuariosRol, Long> {

	/**
	 * Eliminar usuario rol.
	 *
	 * @param idUsuario the id usuario
	 */
	public void eliminarUsuarioRol(Long idUsuario) {
		String lsQuery = "delete from " + "  Usuarios_Roles where id_usuario = :idUsuario ";
		Query query = adminEntidad.createNativeQuery(lsQuery);
		query.setParameter("idUsuario", idUsuario);
		query.executeUpdate();
	}

}
