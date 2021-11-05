package com.alimesa.ordenpedido.servicios.ejecucion.mantenimiento;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.alimesa.ordenpedido.configuracion.ServicioMantenedorControlAuditoria;
import com.alimesa.ordenpedido.modelo.ejecucion.Sucursal;
import com.alimesa.ordenpedido.repositorios.ejecucion.SucursalEAO;

@Stateless
public class ServicioMantenedorSucrusal
		extends ServicioMantenedorControlAuditoria<SucursalEAO, Sucursal, Long> {

	@EJB
	private SucursalEAO lCrud;

	@Override
	protected SucursalEAO getCrud() {
		// TODO Auto-generated method stub
		return lCrud;
	}

	@Override
	protected void cargarConfiguracionServicio() {
		// TODO Auto-generated method stub

	}

}
