package com.alimesa.ordenpedido.librerias.vista.interfaces;

public interface IServicioAutorizacion {

	public boolean validarOpcionUsuario(String url, String usuario, String contextPath);

	public String obtenerUsuarioAutenticado();
}
