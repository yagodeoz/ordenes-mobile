package com.alimesa.ordenpedido.librerias.vista.beans;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alimesa.ordenpedido.librerias.vista.JsfUtil;

public abstract class ManagedBeanGenerico implements Serializable {

	/**
	 * 
	 */
	public static final String SERVLET_PDF = "/servlets/report/PDF";

	private static final long serialVersionUID = 1L;

	protected FacesContext getContext() {
		return FacesContext.getCurrentInstance();
	}

	protected HttpSession getsession() {

		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}

	protected HttpServletRequest getRequest() {
		return (HttpServletRequest) getContext().getExternalContext().getRequest();
	}

	protected HttpServletResponse getResponse() {
		return (HttpServletResponse) getContext().getExternalContext().getResponse();
	}

	public void addMensaje(String msg) {
		addMensaje(msg, FacesMessage.SEVERITY_INFO);
	}

	public void addError(String msg) {
		addMensaje(msg, FacesMessage.SEVERITY_ERROR);
	}

	public void addMensaje(String msg, String detail) {
		addMensaje(msg, detail, FacesMessage.SEVERITY_INFO);
	}

	public void addError(String msg, String detail) {
		addMensaje(msg, detail, FacesMessage.SEVERITY_ERROR);
	}

	private void addMensaje(String msg, Severity severity) {
		FacesMessage message = new FacesMessage(msg);
		message.setSeverity(severity);
		FacesContext ctx = getContext();
		ctx.addMessage(null, message);
	}

	private void addMensaje(String msg, String detail, Severity severity) {
		FacesMessage message = new FacesMessage(msg, detail);
		message.setSeverity(severity);
		FacesContext ctx = getContext();
		ctx.addMessage(null, message);
	}

	public String getMensaje(String key) {
		return (String) getExpresion("etiqueta['" + key + "']");
	}

	public boolean isAutenticado() {
		return JsfUtil.isAutenticado();
	}

	private Object getExpresion(String expression) {
		FacesContext ctx = getContext();
		ExpressionFactory factory = ctx.getApplication().getExpressionFactory();
		ValueExpression ex = factory.createValueExpression(ctx.getELContext(), "#{" + expression + "}", Object.class);
		return ex.getValue(ctx.getELContext());

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
		String errorMsgAjax = e2.toString();
		for (StackTraceElement err : e2.getStackTrace()) {
			errorMsgAjax = errorMsgAjax + "\n" + err.toString();
		}
		String[] resultado = new String[3];
		resultado[0] = errorNameAjax;
		resultado[1] = errorMsgAjax;
		return resultado;
	}

	public HttpServletRequest getHttpServletRequest() {
		return JsfUtil.getRequest();
	}

	public String getCurrentTimeZone() {
		return Calendar.getInstance().getTimeZone().getID();
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

	protected PrintWriter getOutputWriter() throws IOException {
		return getResponse().getWriter();
	}

	public static String getContextPath() {
		return JsfUtil.getRequest().getContextPath();
	}
}
