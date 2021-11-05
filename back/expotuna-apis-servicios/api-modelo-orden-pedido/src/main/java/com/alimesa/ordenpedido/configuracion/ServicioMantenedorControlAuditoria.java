package com.alimesa.ordenpedido.configuracion;

import java.io.Serializable;

import javax.ejb.EJB;

import com.alimesa.ordenpedido.librerias.dominio.entidades.base.EntidadBaseAuditable;
import com.alimesa.ordenpedido.librerias.eao.GenericEAO;
import com.alimesa.ordenpedido.librerias.exceptions.ErrorServicioNegocio;
import com.alimesa.ordenpedido.librerias.servicio.ServicioMantenimientoEntidad;
import com.alimesa.ordenpedido.repositorios.seguridad.UsuarioEAO;


// TODO: Auto-generated Javadoc
/**
 * The Class ServicioMantenedorControlAuditoria.
 *
 * @param <EAO> the generic type
 * @param <ENTIDAD> the generic type
 * @param <Id> the generic type
 */
@SuppressWarnings("hiding")
public abstract class ServicioMantenedorControlAuditoria<EAO extends GenericEAO<ENTIDAD, Id>, ENTIDAD extends EntidadBaseAuditable<Id>, Id extends Serializable>

		extends ServicioMantenimientoEntidad<EAO, ENTIDAD, Id> {
	
	/** The l crud usuario. */
	@EJB
	private UsuarioEAO lCrudUsuario;

	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.servicio.ServicioMantenimientoEntidad#validarControlAuditoriaExistente(com.onix.modulo.librerias.dominio.entidades.base.EntidadBaseAuditable)
	 */
	@Override
	protected void validarControlAuditoriaExistente(ENTIDAD arg0) throws ErrorServicioNegocio {
		if ("USR_WEB".equals(arg0.getAuditoria()))
			if (lCrudUsuario.buscarUsuario(arg0.getAuditoria()) == null) {
				throw new ErrorServicioNegocio("EL USUARIO RESPONSABLE NO EXISTE");
			}
	}

}
