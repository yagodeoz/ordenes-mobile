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
import com.alimesa.ordenpedido.modelo.seguridad.AccesosDirecto;
import com.alimesa.ordenpedido.modelo.seguridad.Opcion;
import com.alimesa.ordenpedido.modelo.seguridad.Usuario;
import com.alimesa.ordenpedido.repositorios.seguridad.AccesosDirectoEAO;

// TODO: Auto-generated Javadoc
/**
 * The Class ServicioMantenedorAccesoDirecto.
 */
@Stateless
public class ServicioMantenedorAccesoDirecto
		extends ServicioMantenedorControlAuditoria<AccesosDirectoEAO, AccesosDirecto, Long> {
	
	/** The crud. */
	@EJB
	private AccesosDirectoEAO crud;

	/** The l servicio usuario. */
	@EJB
	private ServicioMantenedorUsuario lServicioUsuario;
	
	/** The l servicio opcion. */
	@EJB
	private ServicioMantenedorOpcion lServicioOpcion;
	
	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.servicio.ServicioMantenimientoEntidad#cargarConfiguracionServicio()
	 */
	@Override
	protected void cargarConfiguracionServicio() {
		System.out.println("No existen configuraciones del servicios");

	}

	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.servicio.ServicioMantenimientoEntidad#getCrud()
	 */
	@Override
	protected AccesosDirectoEAO getCrud() {
		return crud;
	}

	/**
	 * Obtener accesos asignados activos.
	 *
	 * @param id the id
	 * @return the list
	 */
	public List<String> obtenerAccesosAsignadosActivos(Long id) {

		String lQuery = "select (select opcion from ONIX_OPCIONES where id = id_opcion ) opcion  "
				+ "from ONIX_ACCESOS_DIRECTOS where id_usuario = :idUsuario  and estado = 'A'";
		HashMap<String, Object> lParametros = new HashMap<>();
		lParametros.put("idUsuario", id);
		return crud.ejecutarNativeQueryList(lQuery, lParametros, String.class);
	}
	
	/**
	 * Obtener accesos directos usuario.
	 *
	 * @param id the id
	 * @return the list
	 */
	public List<AccesosDirecto> obtenerAccesosDirectosUsuario(Long id) {

		String lQuery = "select * "
				+ "from ONIX_ACCESOS_DIRECTOS where id_usuario = :idUsuario  and estado = 'A'";
		HashMap<String, Object> lParametros = new HashMap<>();
		lParametros.put("idUsuario", id);
		return crud.ejecutarQueryNativo(lQuery, lParametros, AccesosDirecto.class);
	}
	
	/**
	 * Obtener accesos por asignar.
	 *
	 * @param id the id
	 * @return the list
	 */
	public List<String> obtenerAccesosPorAsignar(Long id) {
		String lQuery = "select opcion from ONIX_OPCIONES pOpciones  " + "where pOpciones.id in ( "
				+ "select id_opcion from ONIX_OPCIONES_ROLES rDato where rDato.id_rol in ( "
				+ "select id_rol from ONIX_USUARIOS_ROLES tDato " + "where tDato.id_usuario = :idUsuario"
				+ "	and tDato.estado = 'A' ) " + "and rDato.estado = 'A' ) " + "and pOpciones.estado = 'A' "
				+ "and pOpciones.accion is not null " + "and pOpciones.id not in ( "
				+ "select id_opcion from ONIX_ACCESOS_DIRECTOS where id_usuario = :idUsuario  and estado = 'A' )";

		HashMap<String, Object> lParametros = new HashMap<>();
		lParametros.put("idUsuario", id);
		return crud.ejecutarNativeQueryList(lQuery, lParametros, String.class);
	}

	/**
	 * Asigar acceso.
	 *
	 * @param lAccesosSeleccionados the l accesos seleccionados
	 * @param id the id
	 * @param referencia the referencia
	 * @param usuarioAutenticado the usuario autenticado
	 * @throws ErrorServicioNegocio the error servicio negocio
	 * @throws ErrorValidacionGeneral the error validacion general
	 */
	public void asigarAcceso(List<String> lAccesosSeleccionados, Long id, Long referencia, String usuarioAutenticado) throws ErrorServicioNegocio, ErrorValidacionGeneral
	{

		HashMap<String, Object> lParametros = new HashMap<>();
		lParametros.put("idUsuario", id);

		for (String opcion : lAccesosSeleccionados) {
			lParametros.put("opcion", opcion);
			List<AccesosDirecto> lAccesoBase = crud.ejecutarQueryNativo(
					"select * from ONIX_ACCESOS_DIRECTOS where id_usuario = :idUsuario "
							+ "and id_opcion in (select id from ONIX_OPCIONES where opcion = :opcion )",
					lParametros, AccesosDirecto.class);
			AccesosDirecto lAcceso = null;
			if (!lAccesoBase.isEmpty()) {
				lAcceso = lAccesoBase.get(0);
				lAcceso.setAuditoria(usuarioAutenticado);
				lAcceso.setFechaActualizacion(new Date());
				lAcceso.setEstado(GenericEAO.ESTADO_ACTIVO);
				lAcceso.setObservacion("ACTIVACION DE OPCION DESDE LA WEB POR EL USUARIO " + usuarioAutenticado);
			} else {
				lAcceso = new AccesosDirecto();
				lAcceso.setAuditoria(usuarioAutenticado);
				lAcceso.setEstado(GenericEAO.ESTADO_ACTIVO);
				lAcceso.setFechaRegistro(new Date());
				lAcceso.setObservacion("REGISTRO DE OPCION DESDE LA WEB POR EL USUARIO " + usuarioAutenticado);
				lAcceso.setPriOpcione(lServicioOpcion.obtenerObjetoPropiedad("opcion", opcion, Opcion.class));
				lAcceso.setPriUsuario(lServicioUsuario.obtenerObjetoPropiedad("usuario", usuarioAutenticado, Usuario.class));
				
			}
			guardarActualizar(lAcceso);
		}

	}

	/**
	 * Inactivar acceso.
	 *
	 * @param lAccesosSeleccionados the l accesos seleccionados
	 * @param id the id
	 * @param usuarioAutenticado the usuario autenticado
	 * @throws ErrorServicioNegocio the error servicio negocio
	 * @throws ErrorValidacionGeneral the error validacion general
	 */
	public void inactivarAcceso(List<String> lAccesosSeleccionados, Long id, String usuarioAutenticado)throws ErrorServicioNegocio, ErrorValidacionGeneral 
	{
		HashMap<String, Object> lParametros = new HashMap<>();
		lParametros.put("idUsuario", id);

		for (String opcion : lAccesosSeleccionados) {
			lParametros.put("opcion", opcion);
			List<AccesosDirecto> lAccesoBase = crud.ejecutarQueryNativo(
					"select * from ONIX_ACCESOS_DIRECTOS where id_usuario = :idUsuario "
							+ "and id_opcion in (select id from ONIX_OPCIONES where opcion = :opcion )",
					lParametros, AccesosDirecto.class);
			if (!lAccesoBase.isEmpty())
			{
				AccesosDirecto lAcceso = lAccesoBase.get(0);			
				lAcceso.setAuditoria(usuarioAutenticado);
				lAcceso.setFechaActualizacion(new Date());
				lAcceso.setEstado(GenericEAO.ESTADO_INACTIVO);
				lAcceso.setObservacion("INACTIVACION DE OPCION DESDE LA WEB POR EL USUARIO " + usuarioAutenticado);
				guardarActualizar(lAcceso);
			}
		}
		
	}

}
