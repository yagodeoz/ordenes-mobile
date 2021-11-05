package com.alimesa.ordenpedido.servicios.seguridad.mantenimiento;

import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.alimesa.ordenpedido.configuracion.ServicioMantenedorControlAuditoria;
import com.alimesa.ordenpedido.librerias.exceptions.ErrorServicioNegocio;
import com.alimesa.ordenpedido.librerias.servicio.oyentes.AccionValidacionListener;
import com.alimesa.ordenpedido.modelo.seguridad.Opcion;
import com.alimesa.ordenpedido.repositorios.seguridad.OpcionEAO;

// TODO: Auto-generated Javadoc
/**
 * The Class ServicioMantenedorOpcion.
 */
@Stateless
public class ServicioMantenedorOpcion extends ServicioMantenedorControlAuditoria<OpcionEAO, Opcion, Long> {

	/** The crud. */
	@EJB
	private OpcionEAO crud;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.onix.modulo.librerias.servicio.ServicioMantenimientoEntidad#getCrud()
	 */
	protected OpcionEAO getCrud() {
		return crud;
	}

	/**
	 * Lista opciones ejecutables.
	 *
	 * @param pUsuario the usuario
	 * @return the list
	 */
	public List<Opcion> listaOpcionesEjecutables(String pUsuario) {
		return crud.listaOpcionesTerminales(pUsuario);
	}

	/**
	 * Lista opciones padre.
	 *
	 * @param lReferencia the l referencia
	 * @return the list
	 */
	public List<Opcion> listaOpcionesPadre(String lReferencia) {
		HashMap<String, Object> listaParametros = new HashMap<>();
		listaParametros.put("referencia", lReferencia);
		return crud.ejecutarQueryNativo(
				"select g.* from OPCIONES g where g.idreferencia = :referencia and g.modulo_padre is null",
				listaParametros, Opcion.class);
	}

	/**
	 * Lista opciones submenu.
	 *
	 * @param lReferencia the l referencia
	 * @return the list
	 */
	public List<Opcion> listaOpcionesSubmenu(String lReferencia) {
		HashMap<String, Object> listaParametros = new HashMap<>();
		listaParametros.put("referencia", lReferencia);
		return crud.ejecutarQueryNativo(
				"select g.* from OPCIONES g where g.idreferencia = :referencia  and g.accion is null",
				listaParametros, Opcion.class);
	}

	/**
	 * Lista opciones.
	 *
	 * @param lReferencia the l referencia
	 * @return the list
	 */
	public List<Opcion> listaOpciones(String lReferencia) {
		HashMap<String, Object> listaParametros = new HashMap<>();
		listaParametros.put("referencia", lReferencia);
		return crud.ejecutarQueryNativo("select g.* from OPCIONES g where g.idreferencia = :referencia",
				listaParametros, Opcion.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.onix.modulo.librerias.servicio.ServicioMantenimientoEntidad#
	 * cargarConfiguracionServicio()
	 */
	@Override
	protected void cargarConfiguracionServicio() {
		addValidacionTransaccionalInsertListener(new AccionValidacionListener<Opcion, Long>() {

			@Override
			public void validacionTransaccional(Opcion entidad) throws ErrorServicioNegocio {
				Opcion opcion = crud.buscarOpcionPorNombre(entidad.getDescripcion());
				if (opcion != null)
					throw new ErrorServicioNegocio(
							"La opción " + opcion.getDescripcion() + ", ya se encuentra registrado");
			}
		});

		addValidacionTransaccionalUpdateListener(new AccionValidacionListener<Opcion, Long>() {

			@Override
			public void validacionTransaccional(Opcion entidad) throws ErrorServicioNegocio {
				Opcion opcion = crud.buscarOpcionPorNombre(entidad.getDescripcion());
				if (opcion != null && !entidad.getId().equals(opcion.getId()))
					throw new ErrorServicioNegocio(
							"La opción " + opcion.getDescripcion() + ", ya se encuentra registrado para otra persona");
			}
		});
	}

}
