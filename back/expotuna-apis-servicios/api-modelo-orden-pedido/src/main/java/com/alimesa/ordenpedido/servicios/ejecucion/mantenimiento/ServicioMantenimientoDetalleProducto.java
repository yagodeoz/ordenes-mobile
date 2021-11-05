package com.alimesa.ordenpedido.servicios.ejecucion.mantenimiento;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.alimesa.ordenpedido.configuracion.ServicioMantenedorControlAuditoria;
import com.alimesa.ordenpedido.modelo.ejecucion.DetalleProducto;
import com.alimesa.ordenpedido.repositorios.ejecucion.DetalleProductoEAO;

@Stateless
public class ServicioMantenimientoDetalleProducto extends ServicioMantenedorControlAuditoria<DetalleProductoEAO, DetalleProducto, Long> {

	@EJB
	private DetalleProductoEAO crud;

	@Override
	protected void cargarConfiguracionServicio() {
		// TODO Auto-generated method stub

	}

	@Override
	protected DetalleProductoEAO getCrud() {
		return crud;
	}

	
}
