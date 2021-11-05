package com.alimesa.ordenpedido.servicios.ejecucion.mantenimiento;

import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.alimesa.ordenpedido.configuracion.ServicioMantenedorControlAuditoria;
import com.alimesa.ordenpedido.modelo.ejecucion.CabCobro;
import com.alimesa.ordenpedido.repositorios.ejecucion.CabCobroEAO;

@Stateless
public class ServicioMantenimientoCobro extends ServicioMantenedorControlAuditoria<CabCobroEAO, CabCobro, Long> {

	@EJB
	private CabCobroEAO crud;

	@Override
	protected void cargarConfiguracionServicio() {
		// TODO Auto-generated method stub

	}

	@Override
	protected CabCobroEAO getCrud() {
		return crud;
	}

	public List<CabCobro> listaUltimosClientes(Long numeroRegistro) {

		String query = "SELECT * FROM cab_cobros order by fecha_registro desc LIMIT :numeroFila";
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("numeroFila", numeroRegistro);

		return crud.ejecutarQueryNativo(query, parametros, CabCobro.class);

	}

}
