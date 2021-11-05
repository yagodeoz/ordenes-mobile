package com.alimesa.ordenpedido.loginmodule.servicios;

import java.util.List;

import com.alimesa.ordenpedido.librerias.excepciones.ExcepcionLogin;


public interface IServiciosLoginModule 
{
	String getClaveUsuario(String pUsuario);
	List<String> getNombreRoles(String pUsuario);
	boolean validarUsuarioPassword(String pUsuario,String pPassword) throws ExcepcionLogin;
	
}   
   