package com.alimesa.ordenpedido.servicios.seguridad.mantenimiento;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.alimesa.ordenpedido.configuracion.ServicioMantenedorControlAuditoria;
import com.alimesa.ordenpedido.modelo.seguridad.Icono;
import com.alimesa.ordenpedido.repositorios.seguridad.IconoEAO;


// TODO: Auto-generated Javadoc
/**
 * The Class ServicioMantenedorIcono.
 */
@Stateless
public class ServicioMantenedorIcono extends  ServicioMantenedorControlAuditoria<IconoEAO, Icono, Long>
{
	
	/** The crud. */
	@EJB
	private IconoEAO crud;
	
	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.servicio.ServicioMantenimientoEntidad#getCrud()
	 */
	protected IconoEAO getCrud() {
		return crud;
	}

	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.servicio.ServicioMantenimientoEntidad#cargarConfiguracionServicio()
	 */
	protected void cargarConfiguracionServicio() {
		System.out.println("NO EXISTE CONFIGURACIÓN ADICIONAL PARA ESTE SERVICIO ServicioMantenedorIcono " );
	}

}
