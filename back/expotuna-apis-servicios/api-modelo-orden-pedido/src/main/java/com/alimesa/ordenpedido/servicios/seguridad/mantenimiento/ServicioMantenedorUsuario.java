package com.alimesa.ordenpedido.servicios.seguridad.mantenimiento;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.alimesa.ordenpedido.configuracion.ServicioMantenedorControlAuditoria;
import com.alimesa.ordenpedido.librerias.eao.GenericEAO;
import com.alimesa.ordenpedido.librerias.exceptions.ErrorServicioNegocio;
import com.alimesa.ordenpedido.librerias.exceptions.ErrorValidacionGeneral;
import com.alimesa.ordenpedido.librerias.generales.UtilEncriptarDataSource;
import com.alimesa.ordenpedido.librerias.servicio.oyentes.AccionTransaccionalListener;
import com.alimesa.ordenpedido.librerias.servicio.oyentes.AccionValidacionListener;
import com.alimesa.ordenpedido.librerias.servicio.oyentes.AccionValidacionSimpleListener;
import com.alimesa.ordenpedido.librerias.servicio.oyentes.PreLlenadoRegistroListener;
import com.alimesa.ordenpedido.modelo.ejecucion.Sucursal;
import com.alimesa.ordenpedido.modelo.seguridad.Rol;
import com.alimesa.ordenpedido.modelo.seguridad.Usuario;
import com.alimesa.ordenpedido.modelo.seguridad.UsuarioSucursal;
import com.alimesa.ordenpedido.modelo.seguridad.UsuariosRol;
import com.alimesa.ordenpedido.repositorios.seguridad.UsuarioEAO;
import com.alimesa.ordenpedido.servicios.ejecucion.mantenimiento.ServicioMantenedorSucrusal;

// TODO: Auto-generated Javadoc
/**
 * The Class ServicioMantenedorUsuario.
 */
@Stateless
public class ServicioMantenedorUsuario extends ServicioMantenedorControlAuditoria<UsuarioEAO, Usuario, Long> {

	/** The crud. */
	@EJB
	private UsuarioEAO crud;

	/** The servicio rol. */
	@EJB
	private ServicioMantenedorRol servicioRol;

	/** The servicio usuario rol. */
	@EJB
	private ServicioMantenedorUsuarioRol servicioUsuarioRol;

	@EJB
	private ServicioMantenedorUsuarioSucursal servicioUsuarioSucursal;

	@EJB
	private ServicioMantenedorSucrusal servicioSucursal;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.onix.modulo.librerias.servicio.ServicioMantenimientoEntidad#getCrud()
	 */
	protected UsuarioEAO getCrud() {
		return crud;
	}

	/**
	 * Lista roles.
	 *
	 * @return the list
	 */
	public List<Rol> listaRoles() {
		return servicioRol.listaObjetosActivos(Rol.class);
	}

	public List<Rol> listaRolesEmpresa() {

		String lQuery = "select b.* from ROLES b where estado = 'A' and seleccionable = 'S' ";

		return servicioRol.ejecutarQueryNativoObjeto(lQuery, new HashMap<>(), Rol.class);
	}

	/**
	 * Obtener rol por ID.
	 *
	 * @param idRol the id rol
	 * @return the oms role
	 */
	public Rol obtenerRolPorID(Long idRol) {
		return servicioRol.buscarRolPorID(idRol);
	}

	/**
	 * Lista usuario por rol.
	 *
	 * @param pIdRol the id rol
	 * @return the list
	 */
	public List<Usuario> listaUsuarioPorRol(Long pIdRol) {
		return crud.listaUsuarioPorRol(pIdRol);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.onix.modulo.librerias.servicio.ServicioMantenimientoEntidad#
	 * cargarConfiguracionServicio()
	 */
	@Override
	protected void cargarConfiguracionServicio() {

		addPostInsertListener(new AccionTransaccionalListener<Usuario, Long>() {

			@Override
			public void controlTransaccional(Usuario entidad) throws ErrorServicioNegocio, ErrorValidacionGeneral {
				List<UsuariosRol> listaUsuarioRoles = entidad.getPriUsuariosRoles();
				if (listaUsuarioRoles != null && !listaUsuarioRoles.isEmpty())
					for (UsuariosRol usuariosRoleRol : listaUsuarioRoles) {
						usuariosRoleRol.setAuditoria(entidad.getAuditoria());
						servicioUsuarioRol.guardarActualizar(usuariosRoleRol);
					}

			}
		});

		addDatosRegistroUpdateListener(new PreLlenadoRegistroListener<Usuario, Long>() {

			@Override
			public void preCargaDatosRegistro(Usuario entidad) {

			}
		});

		addDatosRegistroInsertListener(new PreLlenadoRegistroListener<Usuario, Long>() {
			@Override
			public void preCargaDatosRegistro(Usuario entidad) {
				entidad.setEsNuevo(ES_NUEVO);
				entidad.setClave(CLAVE_DEFECTO);

			}

		});

		addValidacionSimpleInsertListener(new AccionValidacionSimpleListener<Usuario, Long>() {

			@Override
			public void validacionDatos(Usuario entidad) throws ErrorValidacionGeneral {
				if (entidad.getClave().length() < 8)
					throw new ErrorValidacionGeneral("La clave del usuario debe tener más de ocho caracteres");
				try {
					entidad.setClave(UtilEncriptarDataSource.encode(entidad.getClave()));
				} catch (Throwable e) {
					throw new ErrorValidacionGeneral("Eror al encriptar la clave");
				}
			}
		});

		addValidacionTransaccionalInsertListener(new AccionValidacionListener<Usuario, Long>() {

			@Override
			public void validacionTransaccional(Usuario entidad) throws ErrorServicioNegocio {
				Usuario usuario = crud.buscarUsuario(entidad.getUsuario());
				if (usuario != null)
					throw new ErrorServicioNegocio(
							"El usuario " + usuario.getUsuario() + ", ya se encuentra registrado");

			}
		});

		addValidacionTransaccionalUpdateListener(new AccionValidacionListener<Usuario, Long>() {

			@Override
			public void validacionTransaccional(Usuario entidad) throws ErrorServicioNegocio {
				Usuario usuario = crud.buscarUsuario(entidad.getUsuario());
				if (usuario != null && !entidad.getId().equals(usuario.getId()))
					throw new ErrorServicioNegocio(
							"El usuario " + usuario.getUsuario() + ", ya se encuentra registrado para otra persona");

			}

		});

	}

	/**
	 * Obtener roles asignados.
	 *
	 * @param id the id
	 * @return the list
	 */
	public List<String> obtenerRolesAsignados(Long id) {
		String lQuery = "select distinct b.ROL from USUARIOS_ROLES a, ROLES b " + "where a.ID_USUARIO = :idUsuario  "
				+ "and a.ESTADO = 'A' " + "and b.id = a.ID_ROL " + "and b.ESTADO = 'A'";
		HashMap<String, Object> lParametros = new HashMap<>();
		lParametros.put("idUsuario", id);
		return crud.ejecutarNativeQueryList(lQuery, lParametros, String.class);
	}

	public List<String> obtenerSucursalesAsignados(Long id) {
		String lQuery = "select distinct b.nombre  " + "from USUARIOS_SUCURSALES a,  " + "     SUCURSALES b   "
				+ "where a.ID_USUARIO = :idUsuario  "
				+ "and a.ESTADO = 'A'  and b.id = a.ID_SUCURSAL  and b.ESTADO = 'A'";
		HashMap<String, Object> lParametros = new HashMap<>();
		lParametros.put("idUsuario", id);
		return crud.ejecutarNativeQueryList(lQuery, lParametros, String.class);
	}

	public List<String> obtenerSucursalesPorAsignar(Long id) {
		String lQuery = "select a.nombre from SUCURSALES a where a.id not in  "
				+ "				( select b.id from USUARIOS_SUCURSALES a, SUCURSALES b where a.ID_USUARIO = :idUsuario  "
				+ "				and b.id = a.ID_SUCURSAL and b.estado = 'A' and a.estado = 'A' ) "
				+ "				and a.estado = 'A' ";

		HashMap<String, Object> lParametros = new HashMap<>();
		lParametros.put("idUsuario", id);
		return crud.ejecutarNativeQueryList(lQuery, lParametros, String.class);
	}

	/**
	 * Obtener roles por asignar.
	 *
	 * @param usuarioAutenticado the usuario autenticado
	 * @param id                 the id
	 * @return the list
	 */
	public List<String> obtenerRolesPorAsignar(String usuarioAutenticado, Long id) {
		/*
		 * String lQuery = "select distinct e.ROL from USUARIOS_ROLES c, ROLES e " +
		 * "where c.ID_USUARIO in (select d.id from USUARIOS d where d.usuario = :usuario and d.ESTADO = 'A') "
		 * + "and c.estado = 'A' " + "and e.id = c.id_rol " + "and e.estado = 'A' " +
		 * "and  e.id not in ( " + "select b.id from USUARIOS_ROLES a, ROLES b " +
		 * "where a.ID_USUARIO = :idUsuario  " + "and b.id = a.ID_ROL " +
		 * "and b.estado = 'A' " + ")";
		 */
		String lQuery = "select a.rol from ROLES a where a.id not in  "
				+ "( select b.id from USUARIOS_ROLES a, ROLES b where a.ID_USUARIO = :idUsuario  "
				+ "and b.id = a.ID_ROL and b.estado = 'A' and a.estado = 'A' ) " + "and a.estado = 'A' "
				+ "and a.seleccionable = 'S'";

		HashMap<String, Object> lParametros = new HashMap<>();
		// lParametros.put("usuario", usuarioAutenticado);
		lParametros.put("idUsuario", id);
		System.out.println("Usuario autenticado : " + usuarioAutenticado);
		return crud.ejecutarNativeQueryList(lQuery, lParametros, String.class);
	}

	public List<String> obtenerRolesPorAsignarEmpresa(String usuarioAutenticado, Long id) {
		/*
		 * String lQuery = "select distinct e.ROL from USUARIOS_ROLES c, ROLES e " +
		 * "where c.ID_USUARIO in (select d.id from ONIX_USUARIOS d where d.usuario = :usuario and d.ESTADO = 'A') "
		 * + "and c.estado = 'A' " + "and e.id = c.id_rol " + "and e.estado = 'A' " +
		 * "and  e.id not in ( " +
		 * "select b.id from ONIX_USUARIOS_ROLES a, OMS_ROLES b " +
		 * "where a.ID_USUARIO = :idUsuario  " + "and b.id = a.ID_ROL " +
		 * "and b.estado = 'A' " + ")";
		 */
		String lQuery = "select a.rol from ROLES a where a.id not in  "
				+ "( select b.id from USUARIOS_ROLES a, ROLES b where a.ID_USUARIO = :idUsuario  "
				+ "and b.id = a.ID_ROL and b.estado = 'A' and a.estado = 'A' and b.ambito = 'E' ) "
				+ "and a.estado = 'A' " + "and a.seleccionable = 'S'";

		HashMap<String, Object> lParametros = new HashMap<>();
		// lParametros.put("usuario", usuarioAutenticado);
		lParametros.put("idUsuario", id);
		System.out.println("Usuario autenticado : " + usuarioAutenticado);
		return crud.ejecutarNativeQueryList(lQuery, lParametros, String.class);
	}

	/**
	 * Asigar roles.
	 *
	 * @param lRolesTransferidas the l roles transferidas
	 * @param usuarioAutenticado the usuario autenticado
	 * @param id                 the id
	 * @param referencia         the referencia
	 * @throws ErrorServicioNegocio   the error servicio negocio
	 * @throws ErrorValidacionGeneral the error validacion general
	 */
	public void asigarRoles(List<String> lRolesTransferidas, String usuarioAutenticado, Long id, Long referencia)
			throws ErrorServicioNegocio, ErrorValidacionGeneral {
		for (String lRol : lRolesTransferidas) {

			UsuariosRol lOmsUsuariosRole = servicioUsuarioRol.obtenerUsuarioRolPorRolUsuario(lRol, id);

			if (lOmsUsuariosRole != null) {
				lOmsUsuariosRole.setAuditoria(usuarioAutenticado);
				lOmsUsuariosRole.setFechaActualizacion(new Date());
				lOmsUsuariosRole.setEstado(GenericEAO.ESTADO_ACTIVO);
				lOmsUsuariosRole
						.setObservacion("ACTIVACION DE OPCION DESDE LA WEB POR EL USUARIO " + usuarioAutenticado);
			} else {
				Rol lOmsRol = servicioRol.obtenerObjetoPropiedad("rol", lRol, Rol.class);
				lOmsUsuariosRole = new UsuariosRol();
				lOmsUsuariosRole.setEstado(GenericEAO.ESTADO_ACTIVO);
				lOmsUsuariosRole.setObservacion("REGISTRO DE OPCION DESDE LA WEB POR EL USUARIO " + usuarioAutenticado);
				lOmsUsuariosRole.setFechaRegistro(new Date());
				lOmsUsuariosRole.setAuditoria(usuarioAutenticado);
				lOmsUsuariosRole.setPriUsuario(getCrud().find(id, Usuario.class));
				lOmsUsuariosRole.setPriRole(lOmsRol);
			}
			servicioUsuarioRol.guardarActualizar(lOmsUsuariosRole);
		}

	}

	/**
	 * Permitir eliminar usuario rol.
	 *
	 * @param idUsuario the id usuario
	 * @return true, if successful
	 * @throws ErrorServicioNegocio   the error servicio negocio
	 * @throws ErrorValidacionGeneral the error validacion general
	 */
	public boolean permitirEliminarUsuarioRol(Long idUsuario) throws ErrorServicioNegocio, ErrorValidacionGeneral {
		return servicioUsuarioRol.obtenerListaUsuarioRolIdUsuario(idUsuario).size() > 1;
	}

	

	/**
	 * Inactivar roles.
	 *
	 * @param lRolesTransferidas the l roles transferidas
	 * @param usuarioAutenticado the usuario autenticado
	 * @param id                 the id
	 * @return the integer
	 * @throws ErrorServicioNegocio   the error servicio negocio
	 * @throws ErrorValidacionGeneral the error validacion general
	 */
	public Integer inactivarRoles(List<String> lRolesTransferidas, String usuarioAutenticado, Long id)
			throws ErrorServicioNegocio, ErrorValidacionGeneral {
		Integer lResultado = 0;
		for (String lRol : lRolesTransferidas) {

			UsuariosRol lOmsUsuariosRole = servicioUsuarioRol.obtenerUsuarioRolPorRolUsuario(lRol, id);
			if (!permitirEliminarUsuarioRol(id)) {
				lResultado = 1;
				break;
			}
			if (lOmsUsuariosRole != null) {
				lOmsUsuariosRole.setAuditoria(usuarioAutenticado);
				lOmsUsuariosRole.setFechaActualizacion(new Date());
				lOmsUsuariosRole.setEstado(GenericEAO.ESTADO_INACTIVO);
				lOmsUsuariosRole
						.setObservacion("INACTIVACION DE OPCION DESDE LA WEB POR EL USUARIO " + usuarioAutenticado);
				servicioUsuarioRol.guardarActualizar(lOmsUsuariosRole);
			}
		}
		return lResultado;

	}

	public void asigarSucursales(List<String> lSucursalesTransferidas, String usuarioAutenticado, Long id,
			String referencia) throws ErrorServicioNegocio, ErrorValidacionGeneral {

		for (String lSucursal : lSucursalesTransferidas) {

			UsuarioSucursal lOmsUsuariosSucural = servicioUsuarioSucursal.obtenerUsuarioSucursal(lSucursal, id);

			if (lOmsUsuariosSucural != null) {
				lOmsUsuariosSucural.setAuditoria(usuarioAutenticado);
				lOmsUsuariosSucural.setFechaActualizacion(new Date());
				lOmsUsuariosSucural.setEstado(GenericEAO.ESTADO_ACTIVO);
				lOmsUsuariosSucural
						.setObservacion("ACTIVACION DE OPCION DESDE LA WEB POR EL USUARIO " + usuarioAutenticado);
				lOmsUsuariosSucural.setIdReferencia(referencia);
			} else {
				Sucursal lSucursalobj = servicioSucursal.obtenerObjetoPropiedad("nombre", lSucursal, Sucursal.class);
				lOmsUsuariosSucural = new UsuarioSucursal();
				lOmsUsuariosSucural.setEstado(GenericEAO.ESTADO_ACTIVO);
				lOmsUsuariosSucural
						.setObservacion("REGISTRO DE OPCION DESDE LA WEB POR EL USUARIO " + usuarioAutenticado);
				lOmsUsuariosSucural.setFechaRegistro(new Date());
				lOmsUsuariosSucural.setAuditoria(usuarioAutenticado);
				lOmsUsuariosSucural.setUsuario(getCrud().find(id, Usuario.class));
				lOmsUsuariosSucural.setSucursal(lSucursalobj);
				lOmsUsuariosSucural.setIdReferencia(referencia);
			}
			servicioUsuarioSucursal.guardarActualizar(lOmsUsuariosSucural);
		}

	}

	public Integer inactivarSucursal(List<String> lSucursales, String usuarioAutenticado, Long id)
			throws ErrorServicioNegocio, ErrorValidacionGeneral {
		Integer lResultado = 0;
		for (String lSucursal : lSucursales) {

			UsuarioSucursal lOmsUsuariosSucural = servicioUsuarioSucursal.obtenerUsuarioSucursal(lSucursal, id);

			if (!permitirEliminarUsuarioRol(id)) {
				lResultado = 1;
				break;
			}
			if (lOmsUsuariosSucural != null) {
				lOmsUsuariosSucural.setAuditoria(usuarioAutenticado);
				lOmsUsuariosSucural.setFechaActualizacion(new Date());
				lOmsUsuariosSucural.setEstado(GenericEAO.ESTADO_INACTIVO);
				lOmsUsuariosSucural
						.setObservacion("INACTIVACION DE OPCION DESDE LA WEB POR EL USUARIO " + usuarioAutenticado);
				servicioUsuarioSucursal.guardarActualizar(lOmsUsuariosSucural);
			}
		}
		return lResultado;

	}

}
