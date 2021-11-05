package com.alimesa.ordenpedido.librerias.servicio.oyentes;

import java.io.Serializable;

import com.alimesa.ordenpedido.librerias.dominio.entidades.base.EntidadBaseAuditable;
import com.alimesa.ordenpedido.librerias.exceptions.ErrorServicioNegocio;

@FunctionalInterface
public interface AccionValidacionListener<ENTIDAD extends EntidadBaseAuditable<Id>, Id extends Serializable> {

	public void validacionTransaccional(ENTIDAD entidad) throws ErrorServicioNegocio;

}
