package com.alimesa.ordenpedido.librerias.servicio.oyentes;

import java.io.Serializable;

import com.alimesa.ordenpedido.librerias.dominio.entidades.base.EntidadBaseAuditable;

@FunctionalInterface
public interface PreLlenadoRegistroListener <ENTIDAD extends EntidadBaseAuditable<Id>, Id extends Serializable>{

	public void preCargaDatosRegistro(ENTIDAD entidad);
	
}
