package com.alimesa.ordenpedido.librerias.vista.interfaces;

import java.io.Serializable;
import java.util.List;



public interface IParseMenu <T extends Serializable, F extends Serializable> 
{

	public F parseMenuOpciones (List<T> opciones);
	
	public F parseMenuOpciones (List<T> opciones, Long lUsuario);
	
	public F parseMenuOpciones (List<T> opciones, String lRol);
	
}
