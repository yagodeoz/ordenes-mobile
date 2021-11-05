package com.alimesa.ordenpedido.vista.ejecucion.mantenimiento;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.alimesa.ordenpedido.librerias.vista.JsfUtil;
import com.alimesa.ordenpedido.librerias.vista.beans.BeanMantenedorGenerico;
import com.alimesa.ordenpedido.librerias.vista.beans.NombresEtiquetas;
import com.alimesa.ordenpedido.librerias.vista.beans.oyentes.ControlListaEntidadesPersonalizada;
import com.alimesa.ordenpedido.librerias.vista.beans.oyentes.PostConstructListener;
import com.alimesa.ordenpedido.librerias.vista.beans.oyentes.PostSeleccionEntidadListener;
import com.alimesa.ordenpedido.librerias.vista.beans.oyentes.PostTransaccionListener;
import com.alimesa.ordenpedido.librerias.vista.beans.oyentes.PreTransaccionListener;
import com.alimesa.ordenpedido.librerias.vista.exceptions.ErrorAccionesPreTransaccion;
import com.alimesa.ordenpedido.modelo.ejecucion.Cliente;
import com.alimesa.ordenpedido.repositorios.ejecucion.ClienteEAO;
import com.alimesa.ordenpedido.servicios.ejecucion.mantenimiento.ServicioMantenedorCliente;

@ManagedBean
@ViewScoped
public class BeanMantenedorCliente
		extends BeanMantenedorGenerico<ServicioMantenedorCliente, Long, Cliente, ClienteEAO> {
	@EJB
	private ServicioMantenedorCliente servicioMantenedor;

	private String filCriterio;

	public BeanMantenedorCliente() {
		super(Cliente.class);

		
		addPostConstructuListener(new PostConstructListener() {
			@Override
			public void metodoPostConstruct() {

			}
		});

		addPostTransaccion(new PostTransaccionListener() {
			@Override
			public void metodoPostTransaccion() {
			}
		});

		addPreTransaccionServicioListener(new PreTransaccionListener() {
			@Override
			public void accionPreTransaccion() throws ErrorAccionesPreTransaccion {

			}
		});

		addControlListaEntidadesPersonalizada(new ControlListaEntidadesPersonalizada() {
			@Override
			public void cargarListaEntidades() {
				setListaEntidades(servicioMantenedor.listaUltimosClientes(20L));
			}
		});

		addPostSeleccionRegistroListener(new PostSeleccionEntidadListener<Cliente, Long>() {

			@Override
			public void postSeleccionRegistro(Cliente pEntidadSeleccionada) {

			}
		});

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected ServicioMantenedorCliente getServicioMantenedor() {
		// TODO Auto-generated method stub
		return servicioMantenedor;
	}

	protected void cargarListaEtiquetas() {
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TITULOPAGINA.toString(), "Mantenimiento Productos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.DESCRIPCIONPAGINA.toString(), "Mantenedor Productos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.AYUDAPAGINA.toString(), "Cree o edite Productos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TAB.toString(), "Datos Productos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERATABLA.toString(), "Lista de Productos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERADIALOGO.toString(), "Actualización de Productos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERAPANELDIALOGO.toString(), "Datos Productos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TABLAVACIA.toString(), JsfUtil.MENSAJE_INFO_SINRESULTADO);
	}

	
	public void buscarRegistrosCriterio() {

		if (filCriterio.trim().length() == 0) {
			setListaEntidades(servicioMantenedor.listaUltimosClientes(20L));
			return;
		}

		if (filCriterio.length() < 4) {
			setListaEntidades(new ArrayList<Cliente>());
			return;
		}

		String query = "select * from clientes where upper(razonsocialcomprador) like :criterio ";
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("criterio", "%" + filCriterio.toUpperCase() + "%");
		setListaEntidades(servicioMantenedor.ejecutarQueryNativoObjeto(query, parametros, Cliente.class));

	}

	public String getFilCriterio() {
		return filCriterio;
	}

	public void setFilCriterio(String filCriterio) {
		this.filCriterio = filCriterio;
	}
	
	

}
