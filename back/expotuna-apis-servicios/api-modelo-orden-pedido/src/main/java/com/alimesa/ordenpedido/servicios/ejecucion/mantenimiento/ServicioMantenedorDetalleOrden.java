package com.alimesa.ordenpedido.servicios.ejecucion.mantenimiento;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.alimesa.ordenpedido.configuracion.ServicioMantenedorControlAuditoria;
import com.alimesa.ordenpedido.modelo.ejecucion.DetalleOrden;
import com.alimesa.ordenpedido.repositorios.ejecucion.DetalleOrdenEAO;

@Stateless
public class ServicioMantenedorDetalleOrden
		extends ServicioMantenedorControlAuditoria<DetalleOrdenEAO, DetalleOrden, Long> {

	@EJB
	private DetalleOrdenEAO lCrud;

	@Override
	protected DetalleOrdenEAO getCrud() {
		// TODO Auto-generated method stub
		return lCrud;
	}

	@Override
	protected void cargarConfiguracionServicio() {
		// TODO Auto-generated method stub

	}

}
