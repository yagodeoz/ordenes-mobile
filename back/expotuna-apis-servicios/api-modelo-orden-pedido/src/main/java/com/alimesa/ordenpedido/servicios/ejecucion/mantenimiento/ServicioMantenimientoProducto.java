package com.alimesa.ordenpedido.servicios.ejecucion.mantenimiento;

import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.alimesa.ordenpedido.configuracion.ServicioMantenedorControlAuditoria;
import com.alimesa.ordenpedido.modelo.ejecucion.Producto;
import com.alimesa.ordenpedido.repositorios.ejecucion.ProductoEAO;

@Stateless
public class ServicioMantenimientoProducto extends ServicioMantenedorControlAuditoria<ProductoEAO, Producto, Long> {

	@EJB
	private ProductoEAO crud;

	@Override
	protected void cargarConfiguracionServicio() {
		// TODO Auto-generated method stub

	}

	@Override
	protected ProductoEAO getCrud() {
		return crud;
	}

	public List<Producto> listaUltimosProductos(Long numeroRegistro) {

		String query = "SELECT * FROM productos order by fecha_registro desc LIMIT :numeroFila";
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("numeroFila", numeroRegistro);

		List<Producto> resultado = crud.ejecutarQueryNativo(query, parametros, Producto.class);

		resultado.forEach(n -> System.out.println(n.getlCodigoAuxiliar()));

		return resultado;

	}

}
