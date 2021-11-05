package com.alimesa.ordenpedido.servicios.ejecucion.mantenimiento;

import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.alimesa.ordenpedido.configuracion.ServicioMantenedorControlAuditoria;
import com.alimesa.ordenpedido.modelo.ejecucion.CabOrden;
import com.alimesa.ordenpedido.repositorios.ejecucion.CabOrdenEAO;

@Stateless
public class ServicioMantenedorCabOrden
		extends ServicioMantenedorControlAuditoria<CabOrdenEAO, CabOrden, Long> {

	@EJB
	private CabOrdenEAO lCrud;

	@Override
	protected CabOrdenEAO getCrud() {
		// TODO Auto-generated method stub
		return lCrud;
	}

	@Override
	protected void cargarConfiguracionServicio() {
		// TODO Auto-generated method stub

	}
	
	public List<CabOrden> listaUltimosOrdenes(Long numeroRegistro) {

		String query = "SELECT * FROM CAB_ORDEN order by fecha_registro desc LIMIT :numeroFila";
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("numeroFila", numeroRegistro);

		return lCrud.ejecutarQueryNativo(query, parametros, CabOrden.class);

	}

}
