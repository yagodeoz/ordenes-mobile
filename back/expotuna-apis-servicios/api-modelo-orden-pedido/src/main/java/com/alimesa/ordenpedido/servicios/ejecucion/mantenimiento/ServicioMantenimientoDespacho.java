package com.alimesa.ordenpedido.servicios.ejecucion.mantenimiento;

import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.alimesa.ordenpedido.configuracion.ServicioMantenedorControlAuditoria;
import com.alimesa.ordenpedido.modelo.ejecucion.CabDespacho;
import com.alimesa.ordenpedido.repositorios.ejecucion.CabDespachoEAO;

@Stateless
public class ServicioMantenimientoDespacho
		extends ServicioMantenedorControlAuditoria<CabDespachoEAO, CabDespacho, Long> {

	@EJB
	private CabDespachoEAO crud;

	@Override
	protected void cargarConfiguracionServicio() {
		// TODO Auto-generated method stub

	}

	@Override
	protected CabDespachoEAO getCrud() {
		return crud;
	}

	public List<CabDespacho> listaUltimosClientes(Long numeroRegistro) {

		String query = "SELECT * FROM CAB_DESPACHO order by fecha_registro desc LIMIT :numeroFila";
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("numeroFila", numeroRegistro);

		return crud.ejecutarQueryNativo(query, parametros, CabDespacho.class);
	}
}
