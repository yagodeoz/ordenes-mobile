package com.alimesa.ordenpedido.vista.ejecucion.mantenimiento;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.alimesa.ordenpedido.librerias.vista.beans.BeanMantenedorGenerico;
import com.alimesa.ordenpedido.librerias.vista.beans.oyentes.ControlListaEntidadesPersonalizada;
import com.alimesa.ordenpedido.modelo.ejecucion.CabCobro;
import com.alimesa.ordenpedido.repositorios.ejecucion.CabCobroEAO;
import com.alimesa.ordenpedido.servicios.ejecucion.mantenimiento.ServicioMantenimientoCobro;

@ManagedBean
@ViewScoped
public class BeanMantenedorCobro
		extends BeanMantenedorGenerico<ServicioMantenimientoCobro, Long, CabCobro, CabCobroEAO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private ServicioMantenimientoCobro servicioMantenedor;

	private String filCriterio;

	public BeanMantenedorCobro() {
		super(CabCobro.class);

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

	public Long obtenerDiasVencimiento(Date secondDate, Date firstDate) {

		long diff = secondDate.getTime() - firstDate.getTime();

		TimeUnit time = TimeUnit.DAYS;
		return time.convert(diff, TimeUnit.MILLISECONDS);

	}

	@Override
	protected ServicioMantenimientoCobro getServicioMantenedor() {
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
			setListaEntidades(new ArrayList<CabCobro>());
			return;
		}

		String query = "select * from CAB_COBROS where upper(numeroFactura) like :criterio ";
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("criterio", "%" + filCriterio.toUpperCase() + "%");
		setListaEntidades(servicioMantenedor.ejecutarQueryNativoObjeto(query, parametros, CabCobro.class));

	}

}
