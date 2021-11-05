package com.alimesa.ordenpedido.servicios.seguridad;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.alimesa.ordenpedido.modelo.seguridad.Opcion;
import com.alimesa.ordenpedido.modelo.seguridad.OpcionesRol;
import com.alimesa.ordenpedido.modelo.seguridad.Rol;
import com.alimesa.ordenpedido.modelo.seguridad.Usuario;
import com.alimesa.ordenpedido.repositorios.seguridad.OpcionEAO;
import com.alimesa.ordenpedido.repositorios.seguridad.OpcionesRolEAO;
import com.alimesa.ordenpedido.repositorios.seguridad.RolEAO;
import com.alimesa.ordenpedido.repositorios.seguridad.UsuarioEAO;
import com.alimesa.ordenpedido.repositorios.seguridad.UsuariosRolEAO;

// TODO: Auto-generated Javadoc
/**
 * The Class ServiciosMantenimientoSeguridad.
 */
@Stateless
public class ServiciosMantenimientoSeguridad {

	/** The usuario EAO. */
	@EJB
	private UsuarioEAO usuarioEAO;

	/** The rol EAO. */
	@EJB
	private RolEAO rolEAO;

	/** The opcion EAO. */
	@EJB
	private OpcionEAO opcionEAO;

	/** The usuario rol EAO. */
	@EJB
	private UsuariosRolEAO usuarioRolEAO;

	/** The roles opcion EAO. */
	@EJB
	private OpcionesRolEAO rolesOpcionEAO;

	/**
	 * Obtener opciones por usuario.
	 *
	 * @param usuario the usuario
	 * @return the list
	 */
	public List<Opcion> obtenerOpcionesPorUsuario(String usuario) {
		return opcionEAO.obtieneOpcionesPorUsuario(usuario);
	}

	public Boolean obtenerOpcionesPorUsuarioUrl(String usuario, String url, String contexto) {
		return opcionEAO.obtieneOpcionesPorUsuario(usuario, url, contexto);
	}

	/**
	 * Lista roles activos.
	 *
	 * @return the list
	 */
	public List<Rol> listaRolesActivos() {
		return rolEAO.listaRolesActivos();
	}

	/**
	 * Listar roles disponibles.
	 *
	 * @return the list
	 */
	public List<Rol> listarRolesDisponibles() {
		return rolEAO.listarRolesDisponibles();
	}

	/**
	 * Lista opciones terminales.
	 *
	 * @param pUsuario the usuario
	 * @return the list
	 */
	public List<Opcion> listaOpcionesTerminales(String pUsuario) {
		return opcionEAO.listaOpcionesTerminales(pUsuario);
	}

	/**
	 * Lista opciones asignada.
	 *
	 * @param rol the rol
	 * @return the list
	 */
	public List<Opcion> listaOpcionesAsignada(String rol) {
		return opcionEAO.listaOpcionesAsignada(rol);
	}

	/**
	 * Obtener opcion rol.
	 *
	 * @param idOpcion the id opcion
	 * @param idRol    the id rol
	 * @return the oms opciones role
	 */
	public OpcionesRol obtenerOpcionRol(Integer idOpcion, Integer idRol) {
		return rolesOpcionEAO.obtenerOpcionRol(idOpcion, idRol);
	}

	/**
	 * Actualizar opciones rol.
	 *
	 * @param estado  the estado
	 * @param idOpRol the id op rol
	 */
	public void actualizarOpcionesRol(String estado, Integer idOpRol) {
		rolesOpcionEAO.actualizarOpcionesRol(estado, idOpRol);
	}

	/**
	 * Ingresar opcion rol.
	 *
	 * @param opcRol the opc rol
	 */
	public void ingresarOpcionRol(OpcionesRol opcRol) {
		rolesOpcionEAO.ingresarOpcionRol(opcRol);
	}

	/**
	 * Obtener opcion padre.
	 *
	 * @param opcion the opcion
	 * @return the oms opcione
	 */
	public Opcion obtenerOpcionPadre(Integer opcion) {
		return opcionEAO.obtenerOpcionPadre(opcion);
	}

	/**
	 * Cantidad opciones por padre.
	 *
	 * @param rol    the rol
	 * @param opcion the opcion
	 * @return the integer
	 */
	public Integer cantidadOpcionesPorPadre(Integer rol, Integer opcion) {
		return opcionEAO.cantidadOpcionesPorPadre(rol, opcion);
	}

	/**
	 * Guardar rol.
	 *
	 * @param rol the rol
	 */
	public void guardarRol(Rol rol) {
		rolEAO.insertar(rol);
	}

	/**
	 * Actualizar rol.
	 *
	 * @param rol the rol
	 * @return the oms role
	 */
	public Rol actualizarRol(Rol rol) {
		return rolEAO.actualizar(rol);
	}

	/**
	 * Crear usuario.
	 *
	 * @param usuario the usuario
	 */
	public void crearUsuario(Usuario usuario) {
		usuarioEAO.insertar(usuario);
	}

	/**
	 * Cod rol asesor.
	 *
	 * @return the integer
	 */
	public Integer codRolAsesor() {
		return rolEAO.codRolAsesor();
	}

	/**
	 * Obtener usuario tot.
	 *
	 * @param usuario the usuario
	 * @return the oms usuario
	 */
	public Usuario obtenerUsuarioTot(String usuario) {
		return usuarioEAO.buscarUsuario(usuario);
	}

	/**
	 * Cambio clave usuario.
	 *
	 * @param usuario the usuario
	 * @throws Throwable the throwable
	 */
	public void cambioClaveUsuario(Usuario usuario) throws Throwable {
		usuarioEAO.cambioClaveUsuario(usuario);
	}

}
