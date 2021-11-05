package com.alimesa.ordenpedido.librerias.vista.beans.oyentes;

import com.alimesa.ordenpedido.librerias.vista.exceptions.ErrorValidacionVisual;

@FunctionalInterface
public interface ValidadorIngresoDatosListener 
{
	public void validarDatosIngreso() throws ErrorValidacionVisual;
	//
}
