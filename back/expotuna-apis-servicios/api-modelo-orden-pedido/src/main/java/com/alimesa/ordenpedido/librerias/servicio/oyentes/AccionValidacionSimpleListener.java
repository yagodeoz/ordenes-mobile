package com.alimesa.ordenpedido.librerias.servicio.oyentes;

import java.io.Serializable;

import com.alimesa.ordenpedido.librerias.dominio.entidades.base.EntidadBaseAuditable;
import com.alimesa.ordenpedido.librerias.exceptions.ErrorValidacionGeneral;

@FunctionalInterface
public interface AccionValidacionSimpleListener<ENTIDAD extends EntidadBaseAuditable<Id>, Id extends Serializable> {

	public void validacionDatos(ENTIDAD entidad) throws ErrorValidacionGeneral;

}
