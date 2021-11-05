package com.alimesa.ordenpedido.librerias.servicio;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import com.alimesa.ordenpedido.librerias.dominio.entidades.base.EntidadBaseAuditable;
import com.alimesa.ordenpedido.librerias.eao.GenericEAO;
import com.alimesa.ordenpedido.librerias.exceptions.ErrorServicioNegocio;
import com.alimesa.ordenpedido.librerias.exceptions.ErrorValidacionGeneral;
import com.alimesa.ordenpedido.librerias.servicio.oyentes.AccionTransaccionalListener;
import com.alimesa.ordenpedido.librerias.servicio.oyentes.AccionValidacionListener;
import com.alimesa.ordenpedido.librerias.servicio.oyentes.AccionValidacionSimpleListener;
import com.alimesa.ordenpedido.librerias.servicio.oyentes.PreLlenadoRegistroListener;

public abstract class ServicioMantenimientoEntidad<EAO extends GenericEAO<ENTIDAD, Id>, ENTIDAD extends EntidadBaseAuditable<Id>, Id extends Serializable> {
	protected static final String OBSERVACION_APLICACION = "NO EXISTE OBSERVACION REGISTRADA, ESTO ES UNA LEYENDA LLENADA POR DEFECTO";
	protected static final String REFERENCIA = "USR_WEB";
	protected static final String ES_NUEVO = "S";
	protected static final String CLAVE_DEFECTO = "123456789";

	private PreLlenadoRegistroListener<ENTIDAD, Id> lCargarDatosRegitrosInsert;
	private PreLlenadoRegistroListener<ENTIDAD, Id> lCargarDatosRegitrosUpdate;

	private AccionTransaccionalListener<ENTIDAD, Id> prePersistenciaListener;
	private AccionTransaccionalListener<ENTIDAD, Id> postPersistenciaListener;
	private AccionTransaccionalListener<ENTIDAD, Id> preMergeListener;
	private AccionTransaccionalListener<ENTIDAD, Id> postMergeListener;

	private AccionValidacionListener<ENTIDAD, Id> validacionBasePersistenciaListener;
	private AccionValidacionSimpleListener<ENTIDAD, Id> validacionSimplePersistenciaListener;

	private AccionValidacionListener<ENTIDAD, Id> validacionBaseMergeListener;
	private AccionValidacionSimpleListener<ENTIDAD, Id> validacionSimpleMergeListener;

	protected abstract EAO getCrud(); 

	@PostConstruct
	public void inicializacionAdicional() {
		cargarConfiguracionServicio();
	}

	protected abstract void cargarConfiguracionServicio();

	public void guardarActualizar(ENTIDAD entidad) throws ErrorServicioNegocio, ErrorValidacionGeneral {
		if (entidad.getId() == null) {
			preLlenadoRegistro(entidad);
			validacionesBasicasGuardar(entidad);
			validacionesConBaseGuardar(entidad);
		} else {
			preLlenadoActualizacion(entidad);
			validacionesBasicasActualizar(entidad);
			validacionesConBaseActualizar(entidad);
		}
		try {
			if (entidad.getId() == null) {
				prePersistencia(entidad);
				getCrud().insertar(entidad);
				postPersistencia(entidad);
			} else {
				preActualizacion(entidad);
				getCrud().actualizar(entidad);
				postActualizacion(entidad);
			}

		} catch (Throwable e) {
			e.printStackTrace();
			throw new ErrorServicioNegocio(e);
		}
	}

	// ******************************************************************
	// Cargar datos por defecto
	// ******************************************************************
	private void preLlenadoRegistro(ENTIDAD entidad) {
		entidad.setEstado(GenericEAO.ESTADO_ACTIVO);
		entidad.setFechaRegistro(new Date());
		String lObservacion = entidad.getObservacion() == null ? "" : entidad.getObservacion();
		entidad.setObservacion(lObservacion.length() < 3 ? OBSERVACION_APLICACION : lObservacion);
		if (lCargarDatosRegitrosInsert != null)
			lCargarDatosRegitrosInsert.preCargaDatosRegistro(entidad);
		else
			System.out.println("NO SE HA REGISTRADO UN LISTENER PARA EL LLENADO DEL REGISTRO EN EL INSERT "
					+ entidad.getClass().getName() + "preLlenadoRegistro");
	}

	private void preLlenadoActualizacion(ENTIDAD entidad) {
		entidad.setFechaActualizacion(new Date());
		String lObservacion = entidad.getObservacion() == null ? "" : entidad.getObservacion();
		entidad.setObservacion(lObservacion.length() < 3 ? OBSERVACION_APLICACION : lObservacion);
		if (lCargarDatosRegitrosUpdate != null)
			lCargarDatosRegitrosUpdate.preCargaDatosRegistro(entidad);
		else
			System.out.println("NO SE HA REGISTRADO UN LISTENER PARA EL LLENADO DEL REGISTRO EN EL UPDATE "
					+ entidad.getClass().getName() + "preLlenadoActualizacion");
	}

	// ******************************************************************
	// Eventos pre y post transaccionales
	// ******************************************************************

	private void postActualizacion(ENTIDAD entidad) throws ErrorServicioNegocio, ErrorValidacionGeneral {
		if (postMergeListener != null)
			postMergeListener.controlTransaccional(entidad);
		else
			System.out.println("NO SE AGREGÓ UN OYENTE PARA EJECUTAR LA POSTMERGE DE LA ENTIDAD "
					+ entidad.getClass().getCanonicalName());
	}

	private void postPersistencia(ENTIDAD entidad) throws ErrorServicioNegocio, ErrorValidacionGeneral {
		if (postPersistenciaListener != null)
			postPersistenciaListener.controlTransaccional(entidad);
		else
			System.out.println("NO SE AGREGÓ UN OYENTE PARA EJECUTAR LA POSTPERSISTENCE DE LA ENTIDAD "
					+ entidad.getClass().getCanonicalName());
	}

	private void preActualizacion(ENTIDAD entidad) throws ErrorServicioNegocio, ErrorValidacionGeneral {
		if (preMergeListener != null)
			preMergeListener.controlTransaccional(entidad);
		else
			System.out.println("NO SE AGREGÓ UN OYENTE PARA EJECUTAR LA PREMERGE DE LA ENTIDAD "
					+ entidad.getClass().getCanonicalName());
	}

	private void prePersistencia(ENTIDAD entidad) throws ErrorServicioNegocio, ErrorValidacionGeneral {
		if (prePersistenciaListener != null)
			prePersistenciaListener.controlTransaccional(entidad);
		else
			System.out.println("NO SE AGREGÓ UN OYENTE PARA EJECUTAR LA PREPERSISTENCIA DE LA ENTIDAD "
					+ entidad.getClass().getCanonicalName());
	}

	// ******************************************************************
	// Eventos validaciones simples y con base actualizar y guardar
	// ******************************************************************

	private void validarControlAuditoria(ENTIDAD entidad) throws ErrorValidacionGeneral {
		String lUsuario = entidad.getAuditoria();
		lUsuario = lUsuario == null ? "" : lUsuario;
		if (lUsuario.length() < 3)
			throw new ErrorValidacionGeneral(
					"No es posible realizar la transacci�n, el registro no tiene un usuario responsable del evento");
	}

	private void validacionesConBaseActualizar(ENTIDAD entidad) throws ErrorServicioNegocio {
		validarControlAuditoriaExistente(entidad);

		if (validacionBaseMergeListener != null)
			validacionBaseMergeListener.validacionTransaccional(entidad);
		else
			System.out.println("NO SE AGREGÓ UN OYENTE PARA EJECUTAR LA VALIDACION TRANSACCIONAL MERGE DE LA ENTIDAD "
					+ entidad.getClass().getCanonicalName());
	}

	private void validacionesConBaseGuardar(ENTIDAD entidad) throws ErrorServicioNegocio {
		validarControlAuditoriaExistente(entidad);
		if (validacionBasePersistenciaListener != null)
			validacionBasePersistenciaListener.validacionTransaccional(entidad);
		else
			System.out.println(
					"NO SE AGREGÓ UN OYENTE PARA EJECUTAR LA VALIDACION TRANSACCIONAL PERSISTENCE DE LA ENTIDAD "
							+ entidad.getClass().getCanonicalName());

	}

	private void validacionesBasicasActualizar(ENTIDAD entidad) throws ErrorValidacionGeneral {
		validarControlAuditoria(entidad);

		if (validacionSimpleMergeListener != null)
			validacionSimpleMergeListener.validacionDatos(entidad);
		else
			System.out.println("NO SE AGREGÓ UN OYENTE PARA EJECUTAR LA VALIDACION SIMPLE ACTUALIZAR DE LA ENTIDAD "
					+ entidad.getClass().getCanonicalName());

	}

	private void validacionesBasicasGuardar(ENTIDAD entidad) throws ErrorValidacionGeneral {
		validarControlAuditoria(entidad);

		if (validacionSimplePersistenciaListener != null)
			validacionSimplePersistenciaListener.validacionDatos(entidad);
		else
			System.out.println("NO SE AGREGÓ UN OYENTE PARA EJECUTAR LA VALIDACION SIMPLE PERSISTENCE DE LA ENTIDAD "
					+ entidad.getClass().getCanonicalName());
	}

	public List<ENTIDAD> listaObjetos(Class<ENTIDAD> clase) {

		return getCrud().obtenerTodaListaObjetos(clase);
	}

	public List<ENTIDAD> listaObjetos(Class<ENTIDAD> clase, Boolean orderFecha) {
		return getCrud().obtenerTodaListaObjetos(clase, orderFecha);
	}

	public List<ENTIDAD> listaObjetos(Class<ENTIDAD> clase, Boolean orderFecha, String pUsuarioAutenticado) {

		return getCrud().obtenerTodaListaObjetos(clase, orderFecha, pUsuarioAutenticado);
	}

	public List<ENTIDAD> listaObjetosActivos(Class<ENTIDAD> clase) {

		return getCrud().obtenerListaObjetosPorEstadoActivo(clase);
	}

	public List<ENTIDAD> listaObjetosInActivos(Class<ENTIDAD> clase) {

		return getCrud().obtenerListaObjetosPorEstadoInactivo(clase);
	}

	public ENTIDAD obtenerObjtoPK(Id clave, Class<ENTIDAD> clase) {
		return getCrud().obtenerObjetoPorPk(clave, clase);
	}

	public ENTIDAD obtenerObjetoPropiedad(String clave, Object valor, Class<ENTIDAD> clase) {
		return getCrud().obtenerObjetoPorCampoGenerico(clave, valor, clase);
	}

	public void servicioCambioEstado(ENTIDAD entidad) throws ErrorServicioNegocio {
		getCrud().actualizar(entidad);
	}

	protected boolean existeEntidadPropiedad(String clave, Object valor, Class<ENTIDAD> clase) {
		ENTIDAD entidad = getCrud().obtenerObjetoPorCampoGenerico(clave, valor, clase);
		return entidad != null;
	}

	// ***************************
	// AGREGAR INTERFACES
	// ***************************
	protected void addPreInsertListener(AccionTransaccionalListener<ENTIDAD, Id> pEventoPreInsert) {
		prePersistenciaListener = pEventoPreInsert;
	}

	protected void addPostInsertListener(AccionTransaccionalListener<ENTIDAD, Id> pEventoPostInsert) {
		postPersistenciaListener = pEventoPostInsert;
	}

	protected void addPreUpdateListener(AccionTransaccionalListener<ENTIDAD, Id> pEventoPreUpdate) {
		preMergeListener = pEventoPreUpdate;
	}

	protected void addPostUpdateListener(AccionTransaccionalListener<ENTIDAD, Id> pEventoPostUpdate) {
		postMergeListener = pEventoPostUpdate;
	}

	protected void addValidacionTransaccionalInsertListener(
			AccionValidacionListener<ENTIDAD, Id> pEventoValidacionPreinsert) {
		validacionBasePersistenciaListener = pEventoValidacionPreinsert;
	}

	protected void addValidacionTransaccionalUpdateListener(
			AccionValidacionListener<ENTIDAD, Id> pEventoValidacionPreupdate) {
		validacionBaseMergeListener = pEventoValidacionPreupdate;
	}

	protected void addValidacionSimpleUpdateListener(
			AccionValidacionSimpleListener<ENTIDAD, Id> pEventoValidacionSimplePreupdate) {
		validacionSimpleMergeListener = pEventoValidacionSimplePreupdate;
	}

	protected void addValidacionSimpleInsertListener(
			AccionValidacionSimpleListener<ENTIDAD, Id> pEventoValidacionSimplePreinsert) {
		validacionSimplePersistenciaListener = pEventoValidacionSimplePreinsert;
	}

	protected void addDatosRegistroInsertListener(PreLlenadoRegistroListener<ENTIDAD, Id> pListenerLlenado) {
		lCargarDatosRegitrosInsert = pListenerLlenado;
	}

	protected void addDatosRegistroUpdateListener(PreLlenadoRegistroListener<ENTIDAD, Id> pListenerLlenado) {
		lCargarDatosRegitrosUpdate = pListenerLlenado;
	}

	/***
	 * 
	 */

	public EntityManager obtenerCrud() {
		return getCrud().getAdminEntidad();
	}

	/***
	 * 
	 */
	/**
	 * @param entidad
	 * @throws ErrorServicioNegocio
	 *             Se refiere a la logica incluida para la validacion de los
	 *             usuarios existentes
	 */
	protected abstract void validarControlAuditoriaExistente(ENTIDAD entidad) throws ErrorServicioNegocio;

	public List<ENTIDAD> ejecutarQueryNativoObjeto(String pQuery, HashMap<String, Object> pParametros,
			Class<ENTIDAD> clase) {
		return getCrud().ejecutarQueryNativo(pQuery, pParametros, clase);
	}

}
