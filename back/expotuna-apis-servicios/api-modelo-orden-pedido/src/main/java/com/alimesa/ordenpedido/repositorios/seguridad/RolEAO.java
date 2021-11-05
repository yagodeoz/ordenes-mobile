package com.alimesa.ordenpedido.repositorios.seguridad;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.alimesa.ordenpedido.configuracion.EAO;
import com.alimesa.ordenpedido.modelo.seguridad.OpcionesRol;
import com.alimesa.ordenpedido.modelo.seguridad.Rol;


@Stateless
@LocalBean
public class RolEAO extends EAO<Rol, Long> {

	/**
	 * Lista roles activos.
	 *
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<Rol> listaRolesActivos() {
		Query query = adminEntidad.createNativeQuery("select * from   ROLES a where a.estado = 'A' ", Rol.class);
		return query.getResultList();
	}

	/**
	 * Listar roles disponibles.
	 *
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<Rol> listarRolesDisponibles() {
		Query query = adminEntidad.createNativeQuery("SELECT * FROM sb_roles R", Rol.class);
		return query.getResultList();
	}

	/**
	 * Cod rol asesor.
	 *
	 * @return the integer
	 */
	@SuppressWarnings("unchecked")
	public Integer codRolAsesor() {
		TypedQuery<BigDecimal> qr = (TypedQuery<BigDecimal>) adminEntidad.createNativeQuery(
				" select ro.id from   ROLES ro " + " where ro.estado = 'A' " + " and ro.rol = 'ASESOR' ");
		BigDecimal bd = qr.getSingleResult();
		return bd.intValue();
	}

	/**
	 * Verificar si existe rol opcion.
	 *
	 * @param idRol    the id rol
	 * @param idOpcion the id opcion
	 * @return true, if successful
	 */
	@SuppressWarnings("unchecked")
	public boolean verificarSiExisteRolOpcion(Long idRol, Long idOpcion) {
		String sql = "select * from   OPCIONES_ROLES t1 " + "where t1.id_rol = :rol " + "and t1.id_opcion = :opcion "
				+ "and t1.estado = 'A'";
		Query query = adminEntidad.createNativeQuery(sql, OpcionesRol.class);
		query.setParameter("rol", idRol);
		query.setParameter("opcion", idOpcion);
		List<OpcionesRol> resultado = query.getResultList();
		if (resultado.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Obtener roles.
	 *
	 * @param idRoles the id roles
	 * @return the oms role
	 */
	@SuppressWarnings("unchecked")
	public Rol obtenerRoles(Long idRoles) {
		String sql = "select * from   ROLES r where r.id!=1 and r.id= :idRol";
		Query query = adminEntidad.createNativeQuery(sql, Rol.class);
		query.setParameter("idRol", idRoles);

		List<Rol> resultado = query.getResultList();
		if (resultado.isEmpty()) {
			return null;
		} else {
			return resultado.get(0);
		}
	}
}
