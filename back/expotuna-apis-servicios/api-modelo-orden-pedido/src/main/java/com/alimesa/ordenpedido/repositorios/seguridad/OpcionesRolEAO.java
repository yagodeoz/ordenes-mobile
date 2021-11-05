package com.alimesa.ordenpedido.repositorios.seguridad;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import com.alimesa.ordenpedido.configuracion.EAO;
import com.alimesa.ordenpedido.modelo.seguridad.Opcion;
import com.alimesa.ordenpedido.modelo.seguridad.OpcionesRol;

// TODO: Auto-generated Javadoc
/**
 * The Class OmsOpcionesRoleEAO.
 */
@Stateless
@LocalBean
public class OpcionesRolEAO extends EAO<OpcionesRol, Long> {

	/** The Constant OPCIONES_PADRE. */
	public static final String OPCIONES_PADRE = "OPCIONES_ROLES.OPCIONES_PADRE";

	/** The Constant OPCIONES_PADRE_ROL. */
	public static final String OPCIONES_PADRE_ROL = "OPCIONES_ROLES.OPCIONES_PADRE_ROL";

	/**
	 * Obtener lista opciones rol.
	 *
	 * @param usuario     the usuario
	 * @param orientacion the orientacion
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<Opcion> obtenerListaOpcionesRol(Long usuario, String orientacion) {
		Query query = adminEntidad.createNamedQuery(OPCIONES_PADRE);
		query.setParameter(1, usuario);
		query.setParameter(2, orientacion);
		return query.getResultList();
	}

	/**
	 * Obtener lista opciones rol usuario.
	 *
	 * @param rol         the rol
	 * @param orientacion the orientacion
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<Opcion> obtenerListaOpcionesRolUsuario(Long rol, String orientacion) {
		Query query = adminEntidad.createNamedQuery(OPCIONES_PADRE_ROL);
		query.setParameter("idRol", rol);
		query.setParameter("orientacion", orientacion);
		return query.getResultList();
	}

	/**
	 * Obtener opcion rol.
	 *
	 * @param idOpcion the id opcion
	 * @param idRol    the id rol
	 * @return the oms opciones role
	 */
	@SuppressWarnings("unchecked")
	public OpcionesRol obtenerOpcionRol(Integer idOpcion, Integer idRol) {
		Query query = adminEntidad.createNativeQuery(
				"select op.* from OPCIONES_ROLES op " + " where op.id_opcion = ? " + " and op.id_rol = ? ",
				OpcionesRol.class);
		query.setParameter(1, idOpcion);
		query.setParameter(2, idRol);
		List<OpcionesRol> resultado = query.getResultList();
		if (!resultado.isEmpty()) {
			return resultado.get(0);
		} else {
			return null;
		}
	}

	/**
	 * Actualizar opciones rol.
	 *
	 * @param estado  the estado
	 * @param idOpRol the id op rol
	 */
	public void actualizarOpcionesRol(String estado, Integer idOpRol) {
		String lsQuery = " update OPCIONES_ROLES b " + " set b.estado = ?, b.fecha_actualizacion=sysdate "
				+ " where b.id = ? ";
		Query query = adminEntidad.createNativeQuery(lsQuery);
		query.setParameter(1, estado);
		query.setParameter(2, idOpRol);
		query.executeUpdate();
	}

	/**
	 * Eliminar opciones rol.
	 *
	 * @param idRol the id rol
	 */
	public void eliminarOpcionesRol(Long idRol) {
		String lsQuery = " delete OPCIONES_ROLES b " + " where b.ID_ROL = :idRol ";
		Query query = adminEntidad.createNativeQuery(lsQuery);
		query.setParameter("idRol", idRol);
		query.executeUpdate();
	}

	/**
	 * Ingresar opcion rol.
	 *
	 * @param opcRol the opc rol
	 */
	public void ingresarOpcionRol(OpcionesRol opcRol) {
		adminEntidad.persist(opcRol);
	}

	/**
	 * Obtener lista opciones ejecutables rol.
	 *
	 * @param lUsuario the l usuario
	 * @param tipo     the tipo
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<Opcion> obtenerListaOpcionesEjecutablesRol(String lUsuario, String tipo) {
		String lsQuery = " select f.* from OPCIONES f, OPCIONES_ROLES s " + "where f.id = s.id_opcion "
				+ "and f.estado = 'A' " + "and f.accion is not null " + "and f.tipo = :tipo " + "and s.id_rol in (  "
				+ "select id_rol from USUARIOS_ROLES f " + "where f.id_usuario in ( "
				+ "select g.id from USUARIOS g " + "where g.usuario = :usuario " + "and g.estado = 'A' " + " ) "
				+ "and estado = 'A' " + ") ";
		Query query = adminEntidad.createNativeQuery(lsQuery, Opcion.class);
		query.setParameter("usuario", lUsuario);
		query.setParameter("tipo", tipo);
		return query.getResultList();
	}

}
