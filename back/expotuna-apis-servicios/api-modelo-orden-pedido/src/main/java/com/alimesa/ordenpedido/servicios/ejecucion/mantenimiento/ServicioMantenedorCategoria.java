package com.alimesa.ordenpedido.servicios.ejecucion.mantenimiento;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.alimesa.ordenpedido.configuracion.ServicioMantenedorControlAuditoria;
import com.alimesa.ordenpedido.modelo.ejecucion.CategoriaProducto;
import com.alimesa.ordenpedido.repositorios.ejecucion.CategoriaProductoEAO;

@Stateless
public class ServicioMantenedorCategoria
		extends ServicioMantenedorControlAuditoria<CategoriaProductoEAO, CategoriaProducto, Long> {

	@EJB
	private CategoriaProductoEAO crud;

	@Override
	protected CategoriaProductoEAO getCrud() {
		// TODO Auto-generated method stub
		return crud;
	}

	@Override
	protected void cargarConfiguracionServicio() {
		System.out.println("SIN NADA QUE CONFIGURAR");
	}

}
