package com.alimesa.ordenpedido.librerias.vista.beans.oyentes;

import com.alimesa.ordenpedido.librerias.vista.exceptions.ErrorAccionesPreTransaccion;

@FunctionalInterface
public interface PreTransaccionListener 
{

	public void accionPreTransaccion() throws ErrorAccionesPreTransaccion;
	
}
