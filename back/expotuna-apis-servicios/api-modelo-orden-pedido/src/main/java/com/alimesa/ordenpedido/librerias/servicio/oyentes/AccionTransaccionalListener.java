package com.alimesa.ordenpedido.librerias.servicio.oyentes;

import java.io.Serializable;

import com.alimesa.ordenpedido.librerias.dominio.entidades.base.EntidadBaseAuditable;
import com.alimesa.ordenpedido.librerias.exceptions.ErrorServicioNegocio;
import com.alimesa.ordenpedido.librerias.exceptions.ErrorValidacionGeneral;

@FunctionalInterface
public interface AccionTransaccionalListener<ENTIDAD extends EntidadBaseAuditable<Id>, Id extends Serializable> {

	public void controlTransaccional(ENTIDAD entidad) throws ErrorServicioNegocio, ErrorValidacionGeneral;
}
