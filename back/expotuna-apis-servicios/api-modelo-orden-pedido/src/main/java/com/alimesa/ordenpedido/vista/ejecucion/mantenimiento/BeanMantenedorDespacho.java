package com.alimesa.ordenpedido.vista.ejecucion.mantenimiento;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.alimesa.ordenpedido.librerias.vista.beans.BeanMantenedorGenerico;
import com.alimesa.ordenpedido.librerias.vista.beans.oyentes.ControlListaEntidadesPersonalizada;
import com.alimesa.ordenpedido.modelo.ejecucion.CabDespacho;
import com.alimesa.ordenpedido.repositorios.ejecucion.CabDespachoEAO;
import com.alimesa.ordenpedido.servicios.ejecucion.mantenimiento.ServicioMantenimientoDespacho;

@ManagedBean
@ViewScoped
public class BeanMantenedorDespacho
		extends BeanMantenedorGenerico<ServicioMantenimientoDespacho, Long, CabDespacho, CabDespachoEAO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private ServicioMantenimientoDespacho servicioMantenedor;

	private String filCriterio;

	public BeanMantenedorDespacho() {
		super(CabDespacho.class);

		addControlListaEntidadesPersonalizada(new ControlListaEntidadesPersonalizada() {
			@Override
			public void cargarListaEntidades() {
				setListaEntidades(servicioMantenedor.listaUltimosClientes(20L));
			}
		});

	}

	@Override
	protected void cargarListaEtiquetas() {
		// TODO Auto-generated method stub

	}

	@Override
	protected ServicioMantenimientoDespacho getServicioMantenedor() {
		// TODO Auto-generated method stub
		return servicioMantenedor;
	}

	public String getFilCriterio() {
		return filCriterio;
	}

	public void setFilCriterio(String filCriterio) {
		this.filCriterio = filCriterio;
	}

	public void buscarRegistrosCriterio() {

		if (filCriterio.trim().length() == 0) {
			setListaEntidades(servicioMantenedor.listaUltimosClientes(20L));
			return;
		}

		if (filCriterio.length() < 4) {
			setListaEntidades(new ArrayList<CabDespacho>());
			return;
		}

		String query = "select * from CAB_DESPACHO where upper(razonsocialcomprador) like :criterio";
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("criterio", "%" + filCriterio.toUpperCase() + "%");
		setListaEntidades(servicioMantenedor.ejecutarQueryNativoObjeto(query, parametros, CabDespacho.class));

	}

}
