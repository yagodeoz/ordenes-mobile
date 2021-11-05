package com.alimesa.ordenpedido.servicios.ejecucion.mantenimiento;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.alimesa.ordenpedido.configuracion.ServicioMantenedorControlAuditoria;
import com.alimesa.ordenpedido.modelo.ejecucion.StockProducto;
import com.alimesa.ordenpedido.repositorios.ejecucion.StockProductoEAO;

@Stateless
public class ServicioMantenimientoStockProducto extends ServicioMantenedorControlAuditoria<StockProductoEAO, StockProducto, Long> {

	@EJB
	private StockProductoEAO crud;

	@Override
	protected void cargarConfiguracionServicio() {
	}

	@Override
	protected StockProductoEAO getCrud() {
		return crud;
	}
	

}
