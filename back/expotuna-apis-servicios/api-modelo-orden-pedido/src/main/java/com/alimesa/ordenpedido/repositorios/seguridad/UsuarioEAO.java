package com.alimesa.ordenpedido.repositorios.seguridad;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import com.alimesa.ordenpedido.configuracion.EAO;
import com.alimesa.ordenpedido.modelo.seguridad.Usuario;



// TODO: Auto-generated Javadoc
/**
 * The Class OmsUsuarioEAO.
 */
@Stateless
@LocalBean
public class UsuarioEAO extends EAO<Usuario, Long>{
	
/**
 * Obtener usuarios.
 *
 * @param idUsuario the id usuario
 * @return the list
 */
/*
	public PriUsuarioEAO() 
	{	
		super(PriUsuario.class);
		
	}*/
	@SuppressWarnings("unchecked")
	public List<Usuario> obtenerUsuarios(Long idUsuario) {
		String sql = "select * from  USUARIOS u where u.id= :idUsr";

		Query query = adminEntidad.createNativeQuery(sql, Usuario.class );
		query.setParameter("idUsr",idUsuario );
		List<Usuario> resultado = query.getResultList();
		return resultado;
		}
	
	/**
	 * Obtener usuarios activos.
	 *
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<Usuario> obtenerUsuariosActivos() {
		String sql = "select * from  USUARIOS u where u.estado = 'A' order by u.id desc";

		Query query = adminEntidad.createNativeQuery(sql, Usuario.class );
		
		List<Usuario> resultado = query.getResultList();
		return resultado;
		}
	
	/**
	 * Buscar usuario.
	 *
	 * @param usuario the usuario
	 * @return the oms usuario
	 */
	@SuppressWarnings("unchecked")
	public Usuario buscarUsuario(String usuario) {
		String sql = "select * from  USUARIOS u where u.usuario = :usuario";

		Query query = adminEntidad.createNativeQuery(sql, Usuario.class );
		query.setParameter("usuario",usuario );
		List<Usuario> resultado = query.getResultList();
		return resultado.isEmpty()?null:resultado.get(0);
		}  
	
	/**
	 * Actualiza datos usuario.
	 *
	 * @param usr the usr
	 */
	public void actualizaDatosUsuario(Usuario usr){
		adminEntidad.merge(usr);
		adminEntidad.flush();
		adminEntidad.clear();
	}
	
	/**
	 * Cambio clave usuario.
	 *
	 * @param usuario the usuario
	 */
	public void cambioClaveUsuario(Usuario usuario) 
	{
		String lsQuery = "update  USUARIOS  " + "set clave = ?, "
				+ "fecha_actualizacion = ?, esnuevo = ?, estado = ? " + "where id = ? ";
		Query query = adminEntidad.createNativeQuery(lsQuery);
		query.setParameter(1, usuario.getClave());
		query.setParameter(2, new Date());
		query.setParameter(3, usuario.getEsNuevo());
		query.setParameter(4, usuario.getEstado());
		query.setParameter(5, usuario.getId());
		query.executeUpdate();
	}

	/**
	 * Lista usuario por rol.
	 *
	 * @param pIdRol the id rol
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<Usuario> listaUsuarioPorRol(Long pIdRol) {
		// TODO Auto-generated method stub
		return adminEntidad.createNativeQuery
				("select * from  USUARIOS u "
						+ "where u.id in ( "
						+ "select d.id_usuario from  USUARIOS_ROLES d "
						+ "where d.id_rol = :idRol "
						+ "and d.estado = 'A' ) and u.estado = 'A' ", Usuario.class)
				.setParameter("idRol", pIdRol).getResultList();
	}
	
}
