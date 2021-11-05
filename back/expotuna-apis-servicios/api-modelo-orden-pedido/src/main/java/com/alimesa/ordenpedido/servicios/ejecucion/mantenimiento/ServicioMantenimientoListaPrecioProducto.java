package com.alimesa.ordenpedido.servicios.ejecucion.mantenimiento;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.alimesa.ordenpedido.configuracion.ServicioMantenedorControlAuditoria;
import com.alimesa.ordenpedido.modelo.ejecucion.ListaPrecioProducto;
import com.alimesa.ordenpedido.repositorios.ejecucion.ListaPrecioProductoEAO;

@Stateless
public class ServicioMantenimientoListaPrecioProducto extends ServicioMantenedorControlAuditoria<ListaPrecioProductoEAO, ListaPrecioProducto, Long> {

	@EJB
	private ListaPrecioProductoEAO crud;

	@Override
	protected void cargarConfiguracionServicio() {
		// TODO Auto-generated method stub

	}

	@Override
	protected ListaPrecioProductoEAO getCrud() {
		return crud;
	}


}
