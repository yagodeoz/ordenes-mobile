package com.alimesa.ordenpedido.servicios.ejecucion.mantenimiento;

import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.alimesa.ordenpedido.configuracion.ServicioMantenedorControlAuditoria;
import com.alimesa.ordenpedido.modelo.ejecucion.Cliente;
import com.alimesa.ordenpedido.repositorios.ejecucion.ClienteEAO;

@Stateless
public class ServicioMantenedorCliente extends ServicioMantenedorControlAuditoria<ClienteEAO, Cliente, Long> {

	@EJB
	private ClienteEAO lCrud;

	@Override
	protected ClienteEAO getCrud() {
		// TODO Auto-generated method stub
		return lCrud;
	}

	@Override
	protected void cargarConfiguracionServicio() {
		// TODO Auto-generated method stub

	}

	public List<Cliente> listaUltimosClientes(Long numeroRegistro) {

		String query = "SELECT * FROM clientes order by fecha_registro desc LIMIT :numeroFila";
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("numeroFila", numeroRegistro);

		return lCrud.ejecutarQueryNativo(query, parametros, Cliente.class);

	}

}
