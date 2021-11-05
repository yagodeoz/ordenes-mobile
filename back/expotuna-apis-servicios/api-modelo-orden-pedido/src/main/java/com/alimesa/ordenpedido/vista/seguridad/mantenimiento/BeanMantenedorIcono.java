package com.alimesa.ordenpedido.vista.seguridad.mantenimiento;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.alimesa.ordenpedido.librerias.vista.JsfUtil;
import com.alimesa.ordenpedido.librerias.vista.beans.BeanMantenedorGenerico;
import com.alimesa.ordenpedido.librerias.vista.beans.NombresEtiquetas;
import com.alimesa.ordenpedido.librerias.vista.beans.oyentes.PostTransaccionListener;
import com.alimesa.ordenpedido.modelo.seguridad.Icono;
import com.alimesa.ordenpedido.repositorios.seguridad.IconoEAO;
import com.alimesa.ordenpedido.servicios.seguridad.mantenimiento.ServicioMantenedorIcono;

// TODO: Auto-generated Javadoc
/**
 * The Class BeanMantenedorIcono.
 */
@ManagedBean
@ViewScoped
public class BeanMantenedorIcono 
extends BeanMantenedorGenerico<ServicioMantenedorIcono, Long, Icono, IconoEAO>
{

	/**
	 * Instantiates a new bean mantenedor icono.
	 */
	public BeanMantenedorIcono() {
		super( Icono.class);
		addPostTransaccion(new PostTransaccionListener() {
			@Override
			public void metodoPostTransaccion() {
				
			}
		});
	}

	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The l servicio. */
	@EJB
	private ServicioMantenedorIcono lServicio;
	
	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.vista.beans.BeanMantenedorGenerico#cargarListaEtiquetas()
	 */
	@Override
	protected void cargarListaEtiquetas() {
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TITULOPAGINA.toString(), "Mantenimiento Íconos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.DESCRIPCIONPAGINA.toString(), "Mantenedor Íconos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.AYUDAPAGINA.toString(), "Cree o edite Íconos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TAB.toString(), "Datos Íconos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERATABLA.toString(), "Íconos registrados");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERADIALOGO.toString(), "Actualizaciónn de Íconos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERAPANELDIALOGO.toString(), "Datos Íconos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERATABLA.toString(), "Íconos registrados");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TABLAVACIA.toString(), JsfUtil.MENSAJE_INFO_SINRESULTADO);
	}

	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.vista.beans.BeanMantenedorGenerico#getServicioMantenedor()
	 */
	@Override
	protected ServicioMantenedorIcono getServicioMantenedor() {
		return lServicio;
	}

}
