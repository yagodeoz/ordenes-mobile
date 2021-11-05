package com.alimesa.ordenpedido.repositorios.seguridad;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.alimesa.ordenpedido.configuracion.EAO;
import com.alimesa.ordenpedido.modelo.seguridad.AccesosDirecto;
import com.alimesa.ordenpedido.modelo.seguridad.Opcion;
import com.alimesa.ordenpedido.modelo.seguridad.OpcionesRol;
import com.alimesa.ordenpedido.modelo.seguridad.Usuario;

// TODO: Auto-generated Javadoc

@Stateless
@LocalBean
public class OpcionEAO extends EAO<Opcion, Long> {

	/**
	 * Permitir acceso opcion.
	 *
	 * @param opcion  the opcion
	 * @param usuario the usuario
	 * @return true, if successful
	 */
	public boolean permitirAccesoOpcion(Opcion opcion, Usuario usuario) {
		String querySql = "select * " + " " + "from  OPCIONES_ROLES f " + " where f.id_rol in "
				+ "     (select d.id_rol " + "from  USUARIOS_ROLES d where d.id_usuario = ? " + " and d.estado = 'A'"
				+ "      ) " + " and f.id_opcion in ( select id from " + " OPCIONES where id = ? and tipo = 'P' )  "
				+ " and f.estado = 'A'";

		Query query = adminEntidad.createNativeQuery(querySql, OpcionesRol.class);
		query.setParameter(1, usuario.getId());
		query.setParameter(2, opcion.getId());
		return query.getResultList().size() > 0;
	}

	/**
	 * Permitir acceso opcion.
	 *
	 * @param opcion  the opcion
	 * @param usuario the usuario
	 * @return true, if successful
	 */
	public boolean permitirAccesoOpcion(Opcion opcion, Long usuario) {
		String querySql = "select * " + " " + "from  OPCIONES_ROLES f " + " where f.id_rol in "
				+ "     (select d.id_rol " + "from  USUARIOS_ROLES d where d.id_usuario = ? " + " and d.estado = 'A'"
				+ "      ) " + " and f.id_opcion in ( select id from " + " OPCIONES where id = ? and tipo = 'P' )  "
				+ " and f.estado = 'A'";

		Query query = adminEntidad.createNativeQuery(querySql, OpcionesRol.class);
		query.setParameter(1, usuario);
		query.setParameter(2, opcion.getId());
		return query.getResultList().size() > 0;
	}

	/**
	 * Permitir acceso opcion.
	 *
	 * @param opcion the opcion
	 * @param rol    the rol
	 * @return true, if successful
	 */
	public boolean permitirAccesoOpcion(Opcion opcion, String rol) {
		String querySql = "select * " + " from  OPCIONES_ROLES f " + " where f.id_rol in " + "     ( select d.id "
				+ " from  ROLES d where d.rol = ? and d.estado = 'A'" + "      ) "
				+ " and f.id_opcion in ( select id from " + " OPCIONES where id = ? and tipo = 'P' )  "
				+ " and f.estado = 'A'";

		Query query = adminEntidad.createNativeQuery(querySql, OpcionesRol.class);
		query.setParameter(1, rol);
		query.setParameter(2, opcion.getId());
		return query.getResultList().size() > 0;
	}

	/**
	 * Obtiene opciones por usuario.
	 *
	 * @param usuario the usuario
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<Opcion> obtieneOpcionesPorUsuario(String usuario) {
		Query query = adminEntidad.createNativeQuery("SELECT OP.* " + "FROM  USUARIOS        U, "
				+ " USUARIOS_ROLES UR, " + " OPCIONES       OP, " + " OPCIONES_ROLES OPR "
				+ "WHERE U.ID = UR.ID_USUARIO " + "AND UR.ID_ROL = OPR.ID_ROL " + "AND OPR.ID_OPCION = OP.ID "
				+ "AND OPR.ESTADO = 'A' " + "AND OP.ACCION IS NOT NULL " + "AND UPPER(U.USUARIO) = ?", Opcion.class);
		query.setParameter(1, usuario);
		return query.getResultList();
	}

	/**
	 * Lista opciones terminales.
	 *
	 * @param usuario the usuario oms_USUARIOS_ROLES
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<Opcion> listaOpcionesTerminales(String usuario) {
		Query query = adminEntidad
				.createNativeQuery("SELECT OP.* " + "FROM  USUARIOS        U, " + " USUARIOS_ROLES UR, "
						+ " OPCIONES       OP, " + " OPCIONES_ROLES OPR " + "WHERE U.ID = UR.ID_USUARIO "
						+ "AND UR.ID_ROL = OPR.ID_ROL " + "AND OPR.ID_OPCION = OP.ID " + "AND OPR.ESTADO = 'A' "
						+ "AND OP.ACCION IS NOT NULL " + "AND UPPER(U.USUARIO) = ? and OP.ESTADO = 'A'", Opcion.class)
				.setParameter(1, usuario);
		return query.getResultList();
	}

	/**
	 * Lista opciones asignada.
	 *
	 * @param rol the rol
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<Opcion> listaOpcionesAsignada(String rol) {
		Query query = adminEntidad.createNativeQuery(
				" select op.* from  OPCIONES_ROLES opr,  OPCIONES op,  ROLES r "
						+ " where opr.id_opcion = op.id and opr.id_rol = r.id "
						+ " and op.Modulo_Padre is not null and upper(r.rol) = upper(?) and opr.estado='A' ",
				Opcion.class);
		query.setParameter(1, rol);
		return query.getResultList();
	}

	/**
	 * Obtener opcion padre.
	 *
	 * @param opcion the opcion
	 * @return the oms opcione
	 */
	public Opcion obtenerOpcionPadre(Integer opcion) {
		Query query = adminEntidad.createNativeQuery(
				" select b.* from  OPCIONES b where b.id = ? and b.estado = 'A' " + "and b.Modulo_Padre is not null  ",
				Opcion.class);
		query.setParameter(1, opcion);
		return (Opcion) query.getSingleResult();
	}

	/**
	 * Cantidad opciones por padre.
	 *
	 * @param rol    the rol
	 * @param opcion the opcion
	 * @return the integer
	 */
	@SuppressWarnings("unchecked")
	public Integer cantidadOpcionesPorPadre(Integer rol, Integer opcion) {
		TypedQuery<BigDecimal> qr = (TypedQuery<BigDecimal>) adminEntidad
				.createNativeQuery(" select count(*) from  OPCIONES_ROLES ro " + " where ro.id_rol = ? "
						+ " and ro.estado = 'A' " + " and ro.id_opcion in( " + " select o.id from  OPCIONES o "
						+ " where o.modulo_padre = (select o.id " + "     from  OPCIONES o "
						+ "     where o.Modulo_Padre is null " + "      and o.id = ?)) ");
		qr.setParameter(1, rol);
		qr.setParameter(2, opcion);
		BigDecimal bd = qr.getSingleResult();
		return bd.intValue();
	}

	/**
	 * Obtener opcion usuario.
	 *
	 * @param idUsuario the id usuario
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<AccesosDirecto> obtenerOpcionUsuario(Long idUsuario) {
		// TODO Auto-generated method stub
		String sql = " select * from  ACCESOS_DIRECTOS f " + "where f.id_usuario = :idUsuario " + "and f.estado = 'A'";
		Query query = adminEntidad.createNativeQuery(sql, AccesosDirecto.class);
		query.setParameter("idUsuario", idUsuario);
		return query.getResultList();
	}

	/**
	 * Eliminar accesos usuario.
	 *
	 * @param idUsuario the id usuario
	 */
	public void eliminarAccesosUsuario(Long idUsuario) {
		// TODO Auto-generated method stub
		String sql = "delete from  ACCESOS_DIRECTOS where id_usuario = :idUsuario";
		Query query = adminEntidad.createNativeQuery(sql, AccesosDirecto.class);
		query.setParameter("idUsuario", idUsuario);
		query.executeUpdate();
	}

	/**
	 * Obtener opcion no asignada usuario.
	 *
	 * @param idUsuario the id usuario oms_OPCIONES
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<Opcion> obtenerOpcionNoAsignadaUsuario(Long idUsuario) {
		// TODO Auto-generated method stub
		String sql = " select * " + "from  OPCIONES r " + "where r.id not in (select f.id_opcion "
				+ "from  ACCESOS_DIRECTOS f  where f.id_usuario = :idUsuario "
				+ " and f.estado = 'A') and r.estado = 'A' and r.accion is not null " + "and r.id in ( "
				+ " SELECT OP.id  FROM  USUARIOS        U, " + " USUARIOS_ROLES UR, " + " OPCIONES       OP, "
				+ " OPCIONES_ROLES OPR " + "WHERE U.ID = UR.ID_USUARIO " + "AND UR.ID_ROL = OPR.ID_ROL "
				+ "AND OPR.ID_OPCION = OP.ID " + "AND OP.ACCION IS NOT NULL " + "AND U.id = :idUsuario " + ") ";
		Query query = adminEntidad.createNativeQuery(sql, Opcion.class);
		query.setParameter("idUsuario", idUsuario);
		return query.getResultList();
	}

	/**
	 * Guardar acceso directo.
	 *
	 * @param acceso the acceso
	 */
	public void guardarAccesoDirecto(AccesosDirecto acceso) {
		adminEntidad.persist(acceso);
		adminEntidad.flush();
	}

	/**
	 * Buscar opcion por nombre.
	 *
	 * @param nombre the nombre
	 * @return the oms opcione
	 */
	public Opcion buscarOpcionPorNombre(String nombre) {
		String sql = "SELECT * FROM  OPCIONES t " + "where upper(t.opcion) = upper(:opcion) " + "and t.estado = 'A'";
		Query query = adminEntidad.createNativeQuery(sql, Opcion.class);
		query.setParameter("opcion", nombre);
		@SuppressWarnings("unchecked")
		List<Opcion> lista = query.getResultList();

		if (lista.isEmpty()) {
			return null;
		} else {
			return lista.get(0);
		}
	}

	
	public Boolean obtieneOpcionesPorUsuario(String usuario, String url, String contexto) {
		Query query = adminEntidad
				.createNativeQuery("SELECT COUNT(*) " + "FROM  USUARIOS        U, " + " USUARIOS_ROLES UR, "
						+ " OPCIONES       OP, " + " OPCIONES_ROLES OPR " + "WHERE U.ID = UR.ID_USUARIO "
						+ "AND UR.ID_ROL = OPR.ID_ROL " + "AND OPR.ID_OPCION = OP.ID " + "AND OPR.ESTADO = 'A' "
						+ "AND OP.ACCION IS NOT NULL " + "AND UPPER(U.USUARIO) = ? AND  concat(?, OP.ACCION) = ? ");

		query.setParameter(1, usuario);
		query.setParameter(2, contexto);
		query.setParameter(3, url);

		int count = ((Number) query.getSingleResult()).intValue();

		return count > 0;
	}

}
