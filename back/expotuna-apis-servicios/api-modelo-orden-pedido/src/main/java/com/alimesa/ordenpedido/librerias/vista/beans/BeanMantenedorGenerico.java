package com.alimesa.ordenpedido.librerias.vista.beans;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.primefaces.PrimeFaces;

import com.alimesa.ordenpedido.librerias.dominio.entidades.base.EntidadBaseAuditable;
import com.alimesa.ordenpedido.librerias.eao.GenericEAO;
import com.alimesa.ordenpedido.librerias.exceptions.ErrorServicioNegocio;
import com.alimesa.ordenpedido.librerias.exceptions.ErrorValidacionGeneral;
import com.alimesa.ordenpedido.librerias.servicio.ServicioMantenimientoEntidad;
import com.alimesa.ordenpedido.librerias.vista.JsfUtil;
import com.alimesa.ordenpedido.librerias.vista.beans.oyentes.ControlDialogo;
import com.alimesa.ordenpedido.librerias.vista.beans.oyentes.ControlListaEntidadesPersonalizada;
import com.alimesa.ordenpedido.librerias.vista.beans.oyentes.PostCierreDialogo;
import com.alimesa.ordenpedido.librerias.vista.beans.oyentes.PostConstructListener;
import com.alimesa.ordenpedido.librerias.vista.beans.oyentes.PostErrorTransaccionListener;
import com.alimesa.ordenpedido.librerias.vista.beans.oyentes.PostInicializacionEntidad;
import com.alimesa.ordenpedido.librerias.vista.beans.oyentes.PostSeleccionEntidadListener;
import com.alimesa.ordenpedido.librerias.vista.beans.oyentes.PostTransaccionListener;
import com.alimesa.ordenpedido.librerias.vista.beans.oyentes.PreTransaccionListener;
import com.alimesa.ordenpedido.librerias.vista.beans.oyentes.ValidadorIngresoDatosListener;
import com.alimesa.ordenpedido.librerias.vista.exceptions.ErrorAccionesPreTransaccion;
import com.alimesa.ordenpedido.librerias.vista.exceptions.ErrorNoAutenticacion;
import com.alimesa.ordenpedido.librerias.vista.exceptions.ErrorValidacionVisual;

//La herencia de este componente debe ser un managed bean
public abstract class BeanMantenedorGenerico<SERVICIO extends ServicioMantenimientoEntidad<EAO, ENTIDAD, Id>, Id extends Serializable, ENTIDAD extends EntidadBaseAuditable<Id>, EAO extends GenericEAO<ENTIDAD, Id>>
		implements Serializable {

	private static final String USUARIO_GENERICO_WEB = "USR_WEB";

	protected ENTIDAD entidadRegistrar;

	protected ENTIDAD entidadSelecionable;

	protected HashMap<String, String> listaEtiquetasPantalla;

	private PostSeleccionEntidadListener<ENTIDAD, Id> lPostSeleccionEntidad;
	private PostErrorTransaccionListener lPostErrorTransaccion;
	private PreTransaccionListener lPreTransaccionLister;
	private ValidadorIngresoDatosListener lValidadorIngreso;
	private PostConstructListener lPostConstruct;
	private PostTransaccionListener lPostTransaccion;
	private PostCierreDialogo lPostCirreDialog;

	private ControlDialogo lControlDialogos;

	private PostInicializacionEntidad lPostInicializacion;

	private ControlListaEntidadesPersonalizada lControlListaEntidades;

	private List<ENTIDAD> listaEntidades;

	private Class<ENTIDAD> clase;

	private String nombreAtributoCambioEstado;
	private String nombreAtributoDialogoAlterno;
	private String nombreDialogoDefecto = "dialogoMantenimiento";

	public static final String SERVLET_PDF = "/servlets/report/PDF";

	public static final String PRESENTAR_DIALOGO = "show";

	public static final String OCULTAR_DIALOGO = "hide";

	private String lSimboloDinero;
	
	private String lSeparadorDecimal;
	
	private String lSeparadorMiles;
	
	/**
	 * IN USUARIO RESPONSA
	 */
	private static final long serialVersionUID = 1L;

	protected BeanMantenedorGenerico(Class<ENTIDAD> entidadClase) {
		clase = entidadClase;
		incializarEntidad();
		listaEtiquetasPantalla = new HashMap<>();
		cargarListaEtiquetas();
		lSimboloDinero = "$ ";
		lSeparadorDecimal = ",";
		lSeparadorMiles = ".";
	}

	public ENTIDAD getEntidadRegistrar() {
		return entidadRegistrar;
	}

	public void setEntidadRegistrar(ENTIDAD entidadRegistrar) {
		this.entidadRegistrar = entidadRegistrar;
	}

	public List<ENTIDAD> getListaEntidades() {
		return listaEntidades;
	}

	public void setListaEntidades(List<ENTIDAD> listaEntidades) {
		this.listaEntidades = listaEntidades;
	}

	@PostConstruct
	protected void init() {
		try {
			System.out.println("**********************************************");
			System.out.println("TRANSACCION " + (isAutenticado() ? "CON AUTENTICACION " : "SIN AUTENTICACION"));
			System.out.println("**********************************************");

			cargarListaEntidades();

			nombreAtributoCambioEstado = "ENTIDAD_CAMBIAR";
			nombreAtributoDialogoAlterno = "DIALOGO_ALTERNO";
			metodoPostConstruct();
		} catch (Throwable e) {
			listaEntidades = new ArrayList<ENTIDAD>();
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	protected void cargarListaEntidades() {

		if (lControlListaEntidades == null) {
			listaEntidades = (List<ENTIDAD>) (!isAutenticado() ? new ArrayList<>()
					: getServicioMantenedor().listaObjetos(clase, true, usuarioAutenticado()));
		} else {
			lControlListaEntidades.cargarListaEntidades();
		}
	}

	protected FacesContext getContext() {
		return JsfUtil.getContextoJSF();
	}

	protected HttpSession getsession() {

		return JsfUtil.getsession();
	}

	protected HttpServletRequest getRequest() {
		return JsfUtil.getRequest();
	}

	protected HttpServletResponse getResponse() {
		return JsfUtil.getResponse();
	}

	public void addMensaje(String msg) {
		JsfUtil.addMessageInfo(msg);
	}

	public void addMensajeAdvertencia(String msg) {
		JsfUtil.addMessageWarning(msg);
	}

	public void addError(String msg) {
		JsfUtil.addMessageError(msg);
	}

	public String getMensaje(String key) {
		return (String) getExpresion("etiqueta['" + key + "']");
	}

	public boolean isAutenticado() {
		return JsfUtil.isAutenticado();
	}

	public String usuarioAutenticado() {
		return JsfUtil.getUsuarioAutenticado().getName();
	}

	private Object getExpresion(String expression) {
		FacesContext ctx = getContext();
		ExpressionFactory factory = ctx.getApplication().getExpressionFactory();
		ValueExpression ex = factory.createValueExpression(ctx.getELContext(), "#{" + expression + "}", Object.class);
		return ex.getValue(ctx.getELContext());

	}

	public String obtenerIPRemota() {
		String lIpAddress = JsfUtil.getRequest().getHeader("X-FORWARDED-FOR");
		if (lIpAddress == null) {
			lIpAddress = JsfUtil.getRequest().getRemoteAddr();
		}
		return lIpAddress;
	}

	protected HashMap<String, Object> getAtributosVisibles() {
		@SuppressWarnings("rawtypes")
		Class clase = this.getClass();
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		try {

			Method[] metodos = clase.getMethods();
			for (Method metodo : metodos) {
				String nombreMetodo = metodo.getName();
				if (nombreMetodo.toUpperCase().indexOf("GET") != -1) {
					Object obj = metodo.invoke(this);
					if (obj != null) {
						parametros.put(nombreMetodo.toUpperCase(), obj);
					}
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return parametros;
	}

	protected void redireccionarPagina(String urlPagina) throws IOException {
		FacesContext ctx = getContext();
		ExternalContext extContext = ctx.getExternalContext();
		String url = extContext.encodeActionURL(ctx.getApplication().getViewHandler().getActionURL(ctx, urlPagina));
		System.out.println("url a redireccionar " + url);
		extContext.redirect(url);
	}

	public String[] ajaxErrorHandler(Exception e2) {
		System.out.println("Error Handler");
		e2.printStackTrace();
		String errorNameAjax = e2.getMessage();
		String errorMsgAjax = "" + e2;
		for (StackTraceElement err : e2.getStackTrace())
			errorMsgAjax = errorMsgAjax + "\n" + err.toString();
		String[] resultado = new String[3];
		resultado[0] = errorNameAjax;
		resultado[1] = errorMsgAjax;
		return resultado;
	}

	public String getCurrentTimeZone() {
		return Calendar.getInstance().getTimeZone().getID();
	}

	public void seleccionVisual() {
		entidadRegistrar = entidadSelecionable;
	}

	public ENTIDAD getEntidadSelecionable() {
		return entidadSelecionable;
	}

	public void setEntidadSelecionable(ENTIDAD entidadSelecionable) {
		this.entidadSelecionable = entidadSelecionable;
	}

	public void guardarOActualizar(boolean presentarMensaje, boolean pValidarAutenticado) {
		try {
			validacionesIngreso();
			if (pValidarAutenticado)
				cargarDatosRastreoRegistro();
			else
				this.entidadRegistrar.setAuditoria(USUARIO_GENERICO_WEB);
			accionesPreTransaccionServicio();

			getServicioMantenedor().guardarActualizar(entidadRegistrar);
			metodoPostTransaccion();

			controlDialogoAbierto();

			if (presentarMensaje) {
				System.out.println("********************************************");
				System.out.println("MENSAJE " + getMensajeTransaccion());
				System.out.println("********************************************");
				addMensaje(getMensajeTransaccion().length() >= 2 ? getMensajeTransaccion()
						: JsfUtil.MENSAJE_INFO_OPERACION);
			}

		} catch (ErrorValidacionVisual e1) {
			e1.printStackTrace();
			if (presentarMensaje)
				addError(e1.getMensajeUsuario());
			System.out.println("Error validaciones visuales, no borrar nada");
		} catch (ErrorNoAutenticacion e1) {
			e1.printStackTrace();
			if (presentarMensaje)
				addError(e1.getMensajeUsuario());
			System.out.println("Error usuario no autenticado, hay que hacer algo");
		} catch (ErrorAccionesPreTransaccion e1) {
			e1.printStackTrace();
			if (presentarMensaje)
				addError(e1.getMensajeUsuario());
			System.out.println("Error acciones pre transaccion");

		} catch (ErrorServicioNegocio e1) {
			e1.printStackTrace();
			metodoPostErrorTransaccion();
			if (presentarMensaje)
				addError(e1.getMessage());
		} catch (ErrorValidacionGeneral e2) {
			e2.printStackTrace();
			metodoPostErrorTransaccion();
			if (presentarMensaje)
				addError(e2.getMessage());
		} catch (Throwable e) {
			e.printStackTrace();
			metodoPostErrorTransaccion();
			if (presentarMensaje)
				addError(JsfUtil.MENSAJE_ERROR_OPERACION);
		}

	}

	protected void controlDialogoAbierto() {
		if (lControlDialogos == null) {
			boolean lCerrarDialogo = entidadRegistrar.getId() != null;
			if (lCerrarDialogo)
				cierreDialogoDefecto();
		} else {
			lControlDialogos.controlDialogoAbierto();
		}
	}

	private void cierreDialogoDefecto() {
		PrimeFaces.current().executeScript("PF('dialogoMantenimiento').hide();");
	}

	protected void cierreDialogo(String pNombreDialogo) {
		PrimeFaces.current().executeScript("PF('" + pNombreDialogo + "').hide();");
	}

	private void cargarDatosRastreoRegistro() throws ErrorNoAutenticacion {
		try {
			this.entidadRegistrar.setAuditoria(JsfUtil.getUsuarioAutenticado().getName());
			this.entidadRegistrar.setIdReferencia(obtenerObjetoSesion(JsfUtil.REFERENCIA_SESION));
		} catch (Throwable e1) {
			e1.printStackTrace();
			throw new ErrorNoAutenticacion("No es posible realizar esta operaci�n si ning�n usuario est� autenticado");
		}
	}

	public void guardarOActualizar() {
		guardarOActualizar(true, true);
	}

	public void registroSinAutenticacion() {
		guardarOActualizar(true, false);
	}

	protected void metodoPostTransaccion() {
		System.out.println("**********************************************");
		System.out.println("TRANSACCION " + (isAutenticado() ? "CON AUTENTICACION " : "SIN AUTENTICACION"));
		System.out.println("**********************************************");
		cargarListaEntidades();

		if (lPostTransaccion != null)
			lPostTransaccion.metodoPostTransaccion();
		else
			System.out.println(
					"NO EXISTE LISTENER REGISTRADO PARA LA POST TRANSACCION " + this.getClass().getCanonicalName());

		incializarEntidad();
	}

	public String getEstadoActivo() {
		return GenericEAO.ESTADO_ACTIVO;
	}

	public String getEstadoInactivo() {
		return GenericEAO.ESTADO_INACTIVO;
	}

	@SuppressWarnings("unchecked")
	public void actualizarEntidad(ActionEvent evento) {
		entidadRegistrar = (ENTIDAD) evento.getComponent().getAttributes().get(nombreAtributoCambioEstado);
		String lNombreDialogo = (String) evento.getComponent().getAttributes().get(nombreAtributoDialogoAlterno);
		lNombreDialogo = lNombreDialogo == null ? nombreDialogoDefecto : lNombreDialogo;
		postSeleccionRegistro(entidadRegistrar);

		actualizarComponenteVisual(lNombreDialogo);
		presentarDialogo(lNombreDialogo);

	}

	@SuppressWarnings("unchecked")
	public void actualizarEntidadFormulario(ActionEvent evento) {
		entidadRegistrar = (ENTIDAD) evento.getComponent().getAttributes().get(nombreAtributoCambioEstado);
		postSeleccionRegistro(entidadRegistrar);

	}

	@SuppressWarnings("unchecked")
	public void seleccionarEntidad(ActionEvent evento) {
		entidadRegistrar = (ENTIDAD) evento.getComponent().getAttributes().get(nombreAtributoCambioEstado);
		postSeleccionRegistro(entidadRegistrar);
	}

	@SuppressWarnings("unchecked")
	public void cambiarEstado(ActionEvent evento) {
		entidadSelecionable = (ENTIDAD) evento.getComponent().getAttributes().get(nombreAtributoCambioEstado);
		try {
			entidadSelecionable.setEstado(!entidadSelecionable.getEstado().equals(GenericEAO.ESTADO_ACTIVO)
					? GenericEAO.ESTADO_ACTIVO : GenericEAO.ESTADO_INACTIVO);
		} catch (Exception e) {
			entidadSelecionable.setEstado(GenericEAO.ESTADO_ACTIVO);
			addMensajeAdvertencia("No se encontr� un estado registrado, se procede a activar el registro");
		}
		try {
			getServicioMantenedor().servicioCambioEstado(entidadSelecionable);
			addMensaje(JsfUtil.MENSAJE_INFO_OPERACION);
		} catch (ErrorServicioNegocio error) {
			error.printStackTrace();
			addError(error.getMessage());
		} catch (Throwable error) {
			error.printStackTrace();
			addError(JsfUtil.MENSAJE_ERROR_OPERACION);
		}
		metodoPostTransaccion();
	}

	public String getNombreAtributoCambioEstado() {
		return nombreAtributoCambioEstado;
	}

	private void ejecutarComponenteVisual(String funcionEjecutar) {
		PrimeFaces.current().executeScript(funcionEjecutar);
	}

	private String prepararComandoDialogo(String nombreDialogo, String accion) {
		return "PF('" + nombreDialogo + "')." + accion + "()";
	}

	public void presentarDialogo(String nombreDialogo) {
		ejecutarComponenteVisual(prepararComandoDialogo(nombreDialogo, PRESENTAR_DIALOGO));
	}

	public void ocultarDialogo(String nombreDialogo) {
		ejecutarComponenteVisual(prepararComandoDialogo(nombreDialogo, OCULTAR_DIALOGO));
	}

	private String prepararComandoActualizarComponente(String idComponente) {

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(":#{p:component('" + idComponente + "')}");
		return stringBuilder + "";
	}

	public void actualizarComponenteVisual(String idComponente) {
		PrimeFaces.current().executeScript(prepararComandoActualizarComponente(idComponente));
	}

	// ********************************
	// Desde aqui se consultan las etieutas
	// ********************************
	public String getTituloPagina() {
		String lTitulo = listaEtiquetasPantalla.get(NombresEtiquetas.TITULOPAGINA.toString());
		return lTitulo == null ? "" : lTitulo;
	}

	public String getMensajeTablaVacia() {
		String lTablaVacia = listaEtiquetasPantalla.get(NombresEtiquetas.TABLAVACIA.toString());
		return lTablaVacia == null ? "" : lTablaVacia;
	}

	public String getDescripcionPagina() {
		String lDescripcionPagina = listaEtiquetasPantalla.get(NombresEtiquetas.DESCRIPCIONPAGINA.toString());
		return lDescripcionPagina == null ? "" : lDescripcionPagina;
	}

	public String getMensajeTransaccion() {
		String lMensajeTransaccion = listaEtiquetasPantalla.get(NombresEtiquetas.MENSAJE_TRANSACCION.toString());
		return lMensajeTransaccion == null ? "" : lMensajeTransaccion;
	}

	public String getAyudaPagina() {
		String lAyudaPagina = listaEtiquetasPantalla.get((NombresEtiquetas.AYUDAPAGINA + ""));
		return lAyudaPagina == null ? "" : lAyudaPagina;
	}

	public String getTab() {
		String lTab = listaEtiquetasPantalla.get((NombresEtiquetas.TAB + ""));
		return lTab == null ? "" : lTab;
	}

	public String getCabeceraTabla() {
		String lCabeceraTabla = listaEtiquetasPantalla.get((NombresEtiquetas.CABECERATABLA + ""));
		return lCabeceraTabla == null ? "" : lCabeceraTabla;
	}

	public String getCabeceraDialogo() {
		String lCabeceraDialogo = listaEtiquetasPantalla.get((NombresEtiquetas.CABECERADIALOGO + ""));
		return lCabeceraDialogo == null ? "" : lCabeceraDialogo;
	}

	public String getCabeceraPanelDialogo() {
		String lCabeceraPanelDialogo = listaEtiquetasPantalla.get((NombresEtiquetas.CABECERAPANELDIALOGO + ""));
		return lCabeceraPanelDialogo == null ? "" : lCabeceraPanelDialogo;
	}

	// ***********************************************
	// Metodos abtractos para la inicializacion
	// ***********************************************
	protected abstract void cargarListaEtiquetas();

	protected void metodoPostConstruct() {
		if (lPostConstruct != null)
			lPostConstruct.metodoPostConstruct();
		else
			System.out.println("NO EXISTE LISTENER REGISTRADO PARA EL METODO POST CONSTRUCT "
					+ this.getClass().getCanonicalName());
	}

	protected void addPostConstructuListener(PostConstructListener pPostConstruct) {
		lPostConstruct = pPostConstruct;
	}

	protected void addControlDialogo(ControlDialogo pControlDialogo) {
		lControlDialogos = pControlDialogo;
	}

	// **********************************
	// Metodos para las transacciones
	// **********************************
	// Validacion de ingreso se debe
	protected abstract SERVICIO getServicioMantenedor();

	protected void validacionesIngreso() throws ErrorValidacionVisual {
		if (lValidadorIngreso != null)
			lValidadorIngreso.validarDatosIngreso();
		else
			System.out.println("NO EXISTE LISTENER REGISTRADO PARA LA VALIDACION DE INGRESO "
					+ this.getClass().getCanonicalName());
	}

	protected void addValidacionListener(ValidadorIngresoDatosListener pValidadorListener) {
		lValidadorIngreso = pValidadorListener;
	}

	protected void accionesPreTransaccionServicio() throws ErrorAccionesPreTransaccion {
		if (lPreTransaccionLister != null)
			lPreTransaccionLister.accionPreTransaccion();
		else
			System.out.println(
					"NO EXISTE LISTENER REGISTRADO PARA LA PRE TRANSACCION " + this.getClass().getCanonicalName());
	}

	protected void addPreTransaccionServicioListener(PreTransaccionListener pPreTransaccionListener) {
		lPreTransaccionLister = pPreTransaccionListener;
	}

	protected void metodoPostErrorTransaccion() {
		if (lPostErrorTransaccion != null)
			lPostErrorTransaccion.accionPostTransaccion();
		else
			System.out.println("NO EXISTE LISTENER REGISTRADO PARA LA POST ERROR TRANSACCION "
					+ this.getClass().getCanonicalName());
	}

	protected void addPostErrorTransaccionListener(PostErrorTransaccionListener pPostErrorTransaccionListener) {
		lPostErrorTransaccion = pPostErrorTransaccionListener;
	}

	protected void postSeleccionRegistro(ENTIDAD pEntidadSeleccionada) {
		if (lPostSeleccionEntidad != null)
			lPostSeleccionEntidad.postSeleccionRegistro(pEntidadSeleccionada);
		else
			System.out.println("NO EXISTE LISTENER REGISTRADO PARA LA POST SELECCION REGISTRO "
					+ this.getClass().getCanonicalName());
	}

	protected void addPostSeleccionRegistroListener(
			PostSeleccionEntidadListener<ENTIDAD, Id> pPostSeleccionEntidadListener) {
		lPostSeleccionEntidad = pPostSeleccionEntidadListener;
	}

	protected void addPostTransaccion(PostTransaccionListener pPostTransaccionListener) {
		lPostTransaccion = pPostTransaccionListener;
	}

	protected void addPostEventoCierroDialogo(PostCierreDialogo pPostCirreDialog) {
		lPostCirreDialog = pPostCirreDialog;
	}

	protected void addPostEventoInicializacion(PostInicializacionEntidad pPostInicializacion) {
		lPostInicializacion = pPostInicializacion;
	}

	public void agregarObjetoSesion(String pNombreAtributo, Serializable pObjetoGuardar) {
		getsession().setAttribute(pNombreAtributo, pObjetoGuardar);
	}

	public void agregarObjetoSesion(Map<String, Serializable> pAtributosSesion) {
		for (Entry<String, Serializable> lParObjetoClave : pAtributosSesion.entrySet())
			agregarObjetoSesion(lParObjetoClave.getKey(), lParObjetoClave.getValue());
	}

	@SuppressWarnings("unchecked")
	public <T extends Serializable> T obtenerObjetoSesion(String pNombreParametro) {
		return (T) getsession().getAttribute(pNombreParametro);
	}

	public void eliminarObjetoSesion(String pNombreParametro) {
		getsession().removeAttribute(pNombreParametro);
	}

	public String getNombreAtributoDialogoAlterno() {
		return nombreAtributoDialogoAlterno;
	}

	public void setNombreAtributoDialogoAlterno(String nombreAtributoDialogoAlterno) {
		this.nombreAtributoDialogoAlterno = nombreAtributoDialogoAlterno;
	}

	public void eventoControlCierreDialogo() {

		postCierreDialogo();

		incializarEntidad();
	}

	private void postCierreDialogo() {
		if (lPostCirreDialog != null)
			lPostCirreDialog.eventosCierreDialogo();
		else
			System.out.println(
					"NO EXISTE LISTENER REGISTRADO PARA LA POST CIERRE DIALOGO " + this.getClass().getCanonicalName());
	}

	@SuppressWarnings("deprecation")
	private void incializarEntidad() {
		try {
			entidadRegistrar = clase.newInstance();
			if (lPostInicializacion != null)
				lPostInicializacion.eventoPostInicializacion();
			else
				System.out.println("NO EXISTE LISTENER REGISTRADO PARA LA POST INICIALIZACION "
						+ this.getClass().getCanonicalName());

		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public void addControlListaEntidadesPersonalizada(ControlListaEntidadesPersonalizada lControlLista) {
		lControlListaEntidades = lControlLista;
	}
	
	public String diaActual ()
	{
		return new SimpleDateFormat("DDD, MMM, YYYY").format(new Date());
	}

	public String getlSimboloDinero() {
		return lSimboloDinero;
	}

	public void setlSimboloDinero(String lSimboloDinero) {
		this.lSimboloDinero = lSimboloDinero;
	}

	public String getlSeparadorDecimal() {
		return lSeparadorDecimal;
	}

	public void setlSeparadorDecimal(String lSeparadorDecimal) {
		this.lSeparadorDecimal = lSeparadorDecimal;
	}

	public String getlSeparadorMiles() {
		return lSeparadorMiles;
	}

	public void setlSeparadorMiles(String lSeparadorMiles) {
		this.lSeparadorMiles = lSeparadorMiles;
	}
	
	

}
