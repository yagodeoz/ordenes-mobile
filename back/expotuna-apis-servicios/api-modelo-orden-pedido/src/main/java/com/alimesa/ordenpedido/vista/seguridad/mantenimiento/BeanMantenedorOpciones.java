package com.alimesa.ordenpedido.vista.seguridad.mantenimiento;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import com.alimesa.ordenpedido.librerias.vista.JsfUtil;
import com.alimesa.ordenpedido.librerias.vista.beans.BeanMantenedorGenerico;
import com.alimesa.ordenpedido.librerias.vista.beans.NombresEtiquetas;
import com.alimesa.ordenpedido.librerias.vista.beans.oyentes.PostCierreDialogo;
import com.alimesa.ordenpedido.librerias.vista.beans.oyentes.PostConstructListener;
import com.alimesa.ordenpedido.librerias.vista.beans.oyentes.PostSeleccionEntidadListener;
import com.alimesa.ordenpedido.librerias.vista.beans.oyentes.PostTransaccionListener;
import com.alimesa.ordenpedido.librerias.vista.beans.oyentes.PreTransaccionListener;
import com.alimesa.ordenpedido.librerias.vista.exceptions.ErrorAccionesPreTransaccion;
import com.alimesa.ordenpedido.modelo.seguridad.Icono;
import com.alimesa.ordenpedido.modelo.seguridad.Opcion;
import com.alimesa.ordenpedido.repositorios.seguridad.OpcionEAO;
import com.alimesa.ordenpedido.servicios.seguridad.mantenimiento.ServicioMantenedorIcono;
import com.alimesa.ordenpedido.servicios.seguridad.mantenimiento.ServicioMantenedorOpcion;

// TODO: Auto-generated Javadoc 
/**
 * The Class BeanMantenedorOpciones.
 */
@ManagedBean
@ViewScoped
public class BeanMantenedorOpciones extends BeanMantenedorGenerico<ServicioMantenedorOpcion, Long, Opcion, OpcionEAO> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The servicio mantenedor. */
	@EJB
	private ServicioMantenedorOpcion servicioMantenedor;

	/** The servicio icono. */
	@EJB
	private ServicioMantenedorIcono servicioIcono;

	/** The lista iconos. */
	private List<Icono> listaIconos;

	/** The l lista opciones padre. */
	private List<Opcion> lListaOpcionesPadre;

	/** The l lista completa opciones. */
	private List<Opcion> lListaCompletaOpciones;

	/** The l tipo opcion. */
	private String lTipoOpcion;

	/** The l id opcion. */
	private Long lIdOpcion;

	/** The icono seleccionado. */
	private Icono iconoSeleccionado;

	/** The l campos submenu. */
	private boolean lCamposSubmenu;

	/** The l campos opcion. */
	private boolean lCamposOpcion;

	/**
	 * Instantiates a new bean mantenedor opciones.
	 */
	public BeanMantenedorOpciones() {
		super(Opcion.class);
		lCamposSubmenu = false;
		lCamposOpcion = false;
		iconoSeleccionado = new Icono();

		addPostEventoCierroDialogo(new PostCierreDialogo() {
			@Override
			public void eventosCierreDialogo() {
				lCamposSubmenu = false;
				lCamposOpcion = false;
			}
		});

		addPostSeleccionRegistroListener(new PostSeleccionEntidadListener<Opcion, Long>() {

			public void postSeleccionRegistro(Opcion pEntidadSeleccionada) {

				if (pEntidadSeleccionada.getAccion() != null) {
					lCamposOpcion = true;
					lCamposSubmenu = true;
					lListaOpcionesPadre = servicioMantenedor
							.listaOpcionesSubmenu(obtenerObjetoSesion(JsfUtil.REFERENCIA_SESION));
					lIdOpcion = pEntidadSeleccionada.getModuloPadre().getId();
				} else {
					if (pEntidadSeleccionada.getModuloPadre() != null) {
						lCamposSubmenu = true;
						lCamposOpcion = false;
						lListaOpcionesPadre = servicioMantenedor
								.listaOpcionesPadre(obtenerObjetoSesion(JsfUtil.REFERENCIA_SESION));
						lIdOpcion = pEntidadSeleccionada.getModuloPadre().getId();
					} else {
						lCamposSubmenu = false;
						lCamposOpcion = false;
						entidadRegistrar.setModuloPadre(null);
					}
				}

			}
		});
		addPostTransaccion(new PostTransaccionListener() {
			@Override
			public void metodoPostTransaccion() {
				lListaOpcionesPadre = servicioMantenedor
						.listaOpcionesPadre(obtenerObjetoSesion(JsfUtil.REFERENCIA_SESION));
				// lListaSubmenu =
				// servicioMantenedor.listaOpcionesSubmenu(obtenerObjetoSesion(JsfUtil.REFERENCIA_SESION));
				lListaCompletaOpciones = servicioMantenedor
						.listaOpciones(obtenerObjetoSesion(JsfUtil.REFERENCIA_SESION));

				listaIconos = servicioIcono.listaObjetosActivos(Icono.class);
			}
		});

		addPostConstructuListener(new PostConstructListener() {

			@Override
			public void metodoPostConstruct() {
				lListaOpcionesPadre = servicioMantenedor
						.listaOpcionesPadre(obtenerObjetoSesion(JsfUtil.REFERENCIA_SESION));
				lListaCompletaOpciones = servicioMantenedor
						.listaOpciones(obtenerObjetoSesion(JsfUtil.REFERENCIA_SESION));
				listaIconos = servicioIcono.listaObjetosActivos(Icono.class);
			}
		});

		addPreTransaccionServicioListener(new PreTransaccionListener() {

			@Override
			public void accionPreTransaccion() throws ErrorAccionesPreTransaccion {

				if (lIdOpcion != null) {
					Opcion lOpcion = new Opcion();
					lOpcion.setId(lIdOpcion);
					entidadRegistrar.setModuloPadre(lOpcion);
				}
				entidadRegistrar.setOrientacion("V");
				entidadRegistrar.setTipo("P");
				entidadRegistrar.setObservacion("CREACIÃ“N DE OPCION DESDE LA CAPA WEB");
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.onix.modulo.librerias.vista.beans.BeanMantenedorGenerico#
	 * cargarListaEtiquetas()
	 */
	@Override
	protected void cargarListaEtiquetas() {
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TITULOPAGINA.toString(), "Mantenimiento Opción");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.DESCRIPCIONPAGINA.toString(), "Mantenedor Opción");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.AYUDAPAGINA.toString(), "Cree o edite los Opción");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TAB.toString(), "Datos Opción");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERATABLA.toString(), "Opciones registrados");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERADIALOGO.toString(), "Actualización Opción");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERAPANELDIALOGO.toString(), "Datos Opción");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TABLAVACIA.toString(), JsfUtil.MENSAJE_INFO_SINRESULTADO);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.onix.modulo.librerias.vista.beans.BeanMantenedorGenerico#
	 * getServicioMantenedor()
	 */
	@Override
	protected ServicioMantenedorOpcion getServicioMantenedor() {
		return servicioMantenedor;
	}

	/**
	 * Gets the l lista opciones padre.
	 *
	 * @return the l lista opciones padre
	 */
	public List<Opcion> getlListaOpcionesPadre() {
		return lListaOpcionesPadre;
	}

	/**
	 * Sets the l lista opciones padre.
	 *
	 * @param lListaOpcionesPadre the new l lista opciones padre
	 */
	public void setlListaOpcionesPadre(List<Opcion> lListaOpcionesPadre) {
		this.lListaOpcionesPadre = lListaOpcionesPadre;
	}

	/**
	 * Gets the l tipo opcion.
	 *
	 * @return the l tipo opcion
	 */
	public String getlTipoOpcion() {
		return lTipoOpcion;
	}

	/**
	 * Sets the l tipo opcion.
	 *
	 * @param lTipoOpcion the new l tipo opcion
	 */
	public void setlTipoOpcion(String lTipoOpcion) {
		this.lTipoOpcion = lTipoOpcion;
	}

	/**
	 * Gets the l lista completa opciones.
	 *
	 * @return the l lista completa opciones
	 */
	public List<Opcion> getlListaCompletaOpciones() {
		return lListaCompletaOpciones;
	}

	/**
	 * Sets the l lista completa opciones.
	 *
	 * @param lListaCompletaOpciones the new l lista completa opciones
	 */
	public void setlListaCompletaOpciones(List<Opcion> lListaCompletaOpciones) {
		this.lListaCompletaOpciones = lListaCompletaOpciones;
	}

	/**
	 * Presentar campos adicionales.
	 */
	public void presentarCamposAdicionales(AjaxBehaviorEvent evento) {
		switch (lTipoOpcion) {
		case "SUBMENU":
			lCamposSubmenu = true;
			lCamposOpcion = false;
			lListaOpcionesPadre = servicioMantenedor.listaOpcionesPadre(obtenerObjetoSesion(JsfUtil.REFERENCIA_SESION));
			break;
		case "OPCION":
			lCamposOpcion = true;
			lCamposSubmenu = true;
			lListaOpcionesPadre = servicioMantenedor
					.listaOpcionesSubmenu(obtenerObjetoSesion(JsfUtil.REFERENCIA_SESION));
			break;
		default:
			lCamposSubmenu = false;
			lCamposOpcion = false;
			break;
		}
	}

	/**
	 * Gets the l campos submenu.
	 *
	 * @return the l campos submenu
	 */
	public boolean getlCamposSubmenu() {
		return this.lCamposSubmenu;
	}

	/**
	 * Sets the l campos submenu.
	 *
	 * @param lCamposSubmenu the new l campos submenu
	 */
	public void setlCamposSubmenu(boolean lCamposSubmenu) {
		this.lCamposSubmenu = lCamposSubmenu;
	}

	/**
	 * Gets the l campos opcion.
	 *
	 * @return the l campos opcion
	 */
	public boolean getlCamposOpcion() {
		return this.lCamposOpcion;
	}

	/**
	 * Sets the l campos opcion.
	 *
	 * @param lCamposOpcion the new l campos opcion
	 */
	public void setlCamposOpcion(boolean lCamposOpcion) {
		this.lCamposOpcion = lCamposOpcion;
	}

	/**
	 * Gets the l id opcion.
	 *
	 * @return the l id opcion
	 */
	public Long getlIdOpcion() {
		return lIdOpcion;
	}

	/**
	 * Sets the l id opcion.
	 *
	 * @param lIdOpcion the new l id opcion
	 */
	public void setlIdOpcion(Long lIdOpcion) {
		this.lIdOpcion = lIdOpcion;
	}

	/**
	 * Gets the lista iconos.
	 *
	 * @return the lista iconos
	 */
	public List<Icono> getListaIconos() {
		return listaIconos;
	}

	/**
	 * Sets the lista iconos.
	 *
	 * @param listaIconos the new lista iconos
	 */
	public void setListaIconos(List<Icono> listaIconos) {
		this.listaIconos = listaIconos;
	}

	/**
	 * Gets the icono seleccionado.
	 *
	 * @return the icono seleccionado
	 */
	public Icono getIconoSeleccionado() {
		return iconoSeleccionado;
	}

	/**
	 * Sets the icono seleccionado.
	 *
	 * @param iconoSeleccionado the new icono seleccionado
	 */
	public void setIconoSeleccionado(Icono iconoSeleccionado) {
		this.iconoSeleccionado = iconoSeleccionado;
	}

	public void buscarOpcionCreada(AjaxBehaviorEvent pEvento) {

		Opcion lOpcionDB = servicioMantenedor.obtenerObjetoPropiedad("opcion", entidadRegistrar.getOpcion(),
				Opcion.class);

		if (lOpcionDB != null) {
			entidadRegistrar.setOpcion("");
			addError("La opcion " + lOpcionDB.getOpcion()
					+ ", ya se encuentra registrada, por favor revisar desde la tabla de opciones");
		}
	}

}
