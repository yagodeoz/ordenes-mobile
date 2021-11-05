package com.alimesa.ordenpedido.vista.seguridad.mantenimiento;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.menu.MenuModel;

import com.alimesa.ordenpedido.librerias.exceptions.ErrorServicioNegocio;
import com.alimesa.ordenpedido.librerias.exceptions.ErrorValidacionGeneral;
import com.alimesa.ordenpedido.librerias.vista.JsfUtil;
import com.alimesa.ordenpedido.librerias.vista.anotaciones.ITestParseMenu;
import com.alimesa.ordenpedido.librerias.vista.beans.BeanMantenedorGenerico;
import com.alimesa.ordenpedido.librerias.vista.beans.NombresEtiquetas;
import com.alimesa.ordenpedido.librerias.vista.beans.oyentes.PostConstructListener;
import com.alimesa.ordenpedido.librerias.vista.beans.oyentes.PostSeleccionEntidadListener;
import com.alimesa.ordenpedido.librerias.vista.beans.oyentes.PostTransaccionListener;
import com.alimesa.ordenpedido.librerias.vista.beans.oyentes.ValidadorIngresoDatosListener;
import com.alimesa.ordenpedido.librerias.vista.exceptions.ErrorValidacionVisual;
import com.alimesa.ordenpedido.librerias.vista.interfaces.IGuardiaUsuarioSession;
import com.alimesa.ordenpedido.modelo.seguridad.Opcion;
import com.alimesa.ordenpedido.modelo.seguridad.OpcionesRol;
import com.alimesa.ordenpedido.modelo.seguridad.Rol;
import com.alimesa.ordenpedido.repositorios.seguridad.RolEAO;
import com.alimesa.ordenpedido.servicios.seguridad.ServicioAplicacion;
import com.alimesa.ordenpedido.servicios.seguridad.mantenimiento.ServicioMantenedorRol;
import com.alimesa.ordenpedido.vista.seguridad.TestParseMenu;

// TODO: Auto-generated Javadoc
/**
 * The Class BeanMantenedorRol.
 */
@ManagedBean
@ViewScoped
public class BeanMantenedorRol extends BeanMantenedorGenerico<ServicioMantenedorRol, Long, Rol, RolEAO> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The servicio mantenedor. */
	@EJB
	private ServicioMantenedorRol servicioMantenedor;

	/** The opciones seleccionadas. */
	private Long[] opcionesSeleccionadas;

	/** The lista opciones terminales. */
	private List<Opcion> listaOpcionesTerminales;

	/** The lista seleccion opcion. */
	private DualListModel<String> listaSeleccionOpcion;

	/** The model. */
	private MenuModel model;

	/** The servicio aplicacion. */
	@EJB
	private ServicioAplicacion servicioAplicacion;

	/** The parse menu. */
	@Inject
	@ITestParseMenu
	private TestParseMenu parseMenu;

	/**
	 * Instantiates a new bean mantenedor rol.
	 */
	public BeanMantenedorRol() {
		super(Rol.class);
		listaSeleccionOpcion = new DualListModel<>();
		addValidacionListener(new ValidadorIngresoDatosListener() {

			@Override
			public void validarDatosIngreso() throws ErrorValidacionVisual {
				try {
					if (opcionesSeleccionadas != null && opcionesSeleccionadas.length != 0) {
						for (Long descripcionOpcion : opcionesSeleccionadas)
							entidadRegistrar.agregarOpciones(servicioMantenedor.obtenerOpcionPorID(descripcionOpcion));
					}

				} catch (Throwable e) {
					e.printStackTrace();
					throw new ErrorValidacionVisual("Imposible registrar los roles");
				}

			}
		});

		addPostTransaccion(new PostTransaccionListener() {
			@Override
			public void metodoPostTransaccion() {
				opcionesSeleccionadas = null;

			}
		});

		addPostConstructuListener(new PostConstructListener() {

			@Override
			public void metodoPostConstruct() {
				listaOpcionesTerminales = servicioMantenedor.listaOpcionesEjecutables(usuarioAutenticado());
			}
		});

		addPostSeleccionRegistroListener(new PostSeleccionEntidadListener<Rol, Long>() {

			@Override
			public void postSeleccionRegistro(Rol pEntidadSeleccionada) {
				opcionesSeleccionadas = null;
				cargarOpcionesRoles(pEntidadSeleccionada);
				model = parseMenu.parseMenuOpciones(
						servicioAplicacion.obtenerMenu(pEntidadSeleccionada, IGuardiaUsuarioSession.TIPO_MENU),
						pEntidadSeleccionada.getRol());
			}

		});
	}

	/**
	 * Cargar opciones roles.
	 *
	 * @param pEntidadSeleccionada the entidad seleccionada
	 */
	private void cargarOpcionesRoles(Rol pEntidadSeleccionada) {
		List<String> listaOpcionesAsignadas = servicioMantenedor
				.obtenerOpcionesAsignadasRol(pEntidadSeleccionada.getId());
		
		
		List<String> listaOpcionesPorAsignar = servicioMantenedor.obtenerOpcionesPorAsignarRol(usuarioAutenticado(),
				pEntidadSeleccionada.getId(), obtenerObjetoSesion(JsfUtil.REFERENCIA_SESION));
		listaSeleccionOpcion = new DualListModel<>(listaOpcionesPorAsignar, listaOpcionesAsignadas);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.onix.modulo.librerias.vista.beans.BeanMantenedorGenerico#
	 * getServicioMantenedor()
	 */
	protected ServicioMantenedorRol getServicioMantenedor() {
		return servicioMantenedor;
	}

	/**
	 * Gets the lista opciones terminales.
	 *
	 * @return the lista opciones terminales
	 */
	public List<Opcion> getListaOpcionesTerminales() {
		return listaOpcionesTerminales;
	}

	/**
	 * Sets the lista opciones terminales.
	 *
	 * @param listaOpcionesTerminales the new lista opciones terminales
	 */
	public void setListaOpcionesTerminales(List<Opcion> listaOpcionesTerminales) {
		this.listaOpcionesTerminales = listaOpcionesTerminales;
	}

	/**
	 * Gets the opciones seleccionadas.
	 *
	 * @return the opciones seleccionadas
	 */
	public Long[] getOpcionesSeleccionadas() {
		return opcionesSeleccionadas;
	}

	/**
	 * Sets the opciones seleccionadas.
	 *
	 * @param opcionesSeleccionadas the new opciones seleccionadas
	 */
	public void setOpcionesSeleccionadas(Long[] opcionesSeleccionadas) {
		this.opcionesSeleccionadas = opcionesSeleccionadas;
	}

	/**
	 * Obtener opciones asignadas rol.
	 *
	 * @param opcionesRoles the opciones roles
	 * @return the list
	 */
	public List<Opcion> obtenerOpcionesAsignadasRol(List<OpcionesRol> opcionesRoles) {
		List<Opcion> opcionesRolesAsig = new ArrayList<>();
		for (OpcionesRol opcionRol : opcionesRoles) {
			if (opcionRol.getPriOpcione().getAccion() != null) {
				opcionRol.getPriOpcione().setAuditoria(opcionRol.getAuditoria());
				opcionesRolesAsig.add(opcionRol.getPriOpcione());
			}
		}
		return opcionesRolesAsig;
	}

	/**
	 * Control transferencia.
	 *
	 * @param pEvento the evento
	 */
	public void controlTransferencia(TransferEvent pEvento) {

		Long referencia = obtenerObjetoSesion(JsfUtil.REFERENCIA_SESION);
		@SuppressWarnings("unchecked")
		List<String> lOpcionesTransferidas = (List<String>) pEvento.getItems();
		try {
			if (pEvento.isAdd())

				servicioMantenedor.asigarOpciones(lOpcionesTransferidas, usuarioAutenticado(), entidadRegistrar.getId(),
						referencia);

			else {
				if (pEvento.isRemove())
					servicioMantenedor.inactivarOpciones(lOpcionesTransferidas, usuarioAutenticado(),
							entidadRegistrar.getId());
			}
			cargarOpcionesRoles(this.entidadRegistrar);
		} catch (ErrorServicioNegocio e) {
			e.printStackTrace();
			addError(e.getMessage());

		} catch (ErrorValidacionGeneral e) {
			e.printStackTrace();
			addError(e.getMessage());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.onix.modulo.librerias.vista.beans.BeanMantenedorGenerico#
	 * cargarListaEtiquetas()
	 */
	@Override
	protected void cargarListaEtiquetas() {
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TITULOPAGINA.toString(), "Mantenimiento Roles");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.DESCRIPCIONPAGINA.toString(), "Mantenedor Roles");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.AYUDAPAGINA.toString(), "Cree o edite los Roles");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TAB.toString(), "Datos Rol");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERATABLA.toString(), "Roles registrados");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERADIALOGO.toString(), "Actualización Rol");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERAPANELDIALOGO.toString(), "Datos Rol");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TABLAVACIA.toString(), JsfUtil.MENSAJE_INFO_SINRESULTADO);
	}

	/**
	 * Gets the lista seleccion opcion.
	 *
	 * @return the lista seleccion opcion
	 */
	public DualListModel<String> getListaSeleccionOpcion() {
		return listaSeleccionOpcion;
	}

	/**
	 * Sets the lista seleccion opcion.
	 *
	 * @param listaSeleccionOpcion the new lista seleccion opcion
	 */
	public void setListaSeleccionOpcion(DualListModel<String> listaSeleccionOpcion) {
		this.listaSeleccionOpcion = listaSeleccionOpcion;
	}

	/**
	 * Gets the model.
	 *
	 * @return the model
	 */
	public MenuModel getModel() {
		return model;
	}

	/**
	 * Sets the model.
	 *
	 * @param model the new model
	 */
	public void setModel(MenuModel model) {
		this.model = model;
	}
}
