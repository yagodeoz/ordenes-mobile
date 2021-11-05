package com.alimesa.ordenpedido.librerias.vista.interfaces;

import java.io.Serializable;
import java.util.List;


public interface IServiciosMenu<T extends Serializable> 
{
	public List<T>	getMenuOpciones ();
}
