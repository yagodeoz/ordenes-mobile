package com.alimesa.ordenpedido.librerias.vista.beans.oyentes;

import java.io.Serializable;

import com.alimesa.ordenpedido.librerias.dominio.entidades.base.EntidadBaseAuditable;

@FunctionalInterface
public interface PostSeleccionEntidadListener<ENTIDAD extends EntidadBaseAuditable<Id>, Id extends Serializable>
{

	public void postSeleccionRegistro(ENTIDAD pEntidadSeleccionada);
	
}
