package com.alimesa.ordenpedido.servicios.ejecucion.mantenimiento;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.alimesa.ordenpedido.configuracion.ServicioMantenedorControlAuditoria;
import com.alimesa.ordenpedido.modelo.ejecucion.Banco;
import com.alimesa.ordenpedido.repositorios.ejecucion.BancoEAO;

@Stateless
public class ServicioMantenimientoBanco extends ServicioMantenedorControlAuditoria<BancoEAO, Banco, Long> {

	@EJB
	private BancoEAO crud;

	@Override
	protected void cargarConfiguracionServicio() {
		// TODO Auto-generated method stub

	}

	@Override
	protected BancoEAO getCrud() {
		return crud;
	}
}
