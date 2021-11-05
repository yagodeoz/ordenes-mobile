package com.alimesa.ordenpedido.vista.seguridad;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.alimesa.ordenpedido.librerias.vista.anotaciones.ITestServicioAutorizacion;
import com.alimesa.ordenpedido.librerias.vista.anotaciones.ITestUsuarioSession;
import com.alimesa.ordenpedido.librerias.vista.interfaces.IServicioAutorizacion;
import com.alimesa.ordenpedido.librerias.vista.interfaces.IUsuarioSession;
import com.alimesa.ordenpedido.modelo.seguridad.Usuario;
import com.alimesa.ordenpedido.servicios.seguridad.ServiciosMantenimientoSeguridad;

// TODO: Auto-generated Javadoc
/**
 * The Class ServicioAutorizacion.
 */
@ITestServicioAutorizacion
@RequestScoped
public class ServicioAutorizacion implements IServicioAutorizacion {

	/** The servicios mantenimiento seguridad. */
	@EJB
	private ServiciosMantenimientoSeguridad serviciosMantenimientoSeguridad;

	/** The usuario session. */
	@Inject
	@ITestUsuarioSession
	private IUsuarioSession<Usuario> usuarioSession;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.onix.modulo.librerias.vista.interfaces.IServicioAutorizacion#
	 * validarOpcionUsuario(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean validarOpcionUsuario(String url, String usuario, String contextPath) {
		return serviciosMantenimientoSeguridad.obtenerOpcionesPorUsuarioUrl(usuario, url, contextPath);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.onix.modulo.librerias.vista.interfaces.IServicioAutorizacion#
	 * obtenerUsuarioAutenticado()
	 */
	@Override
	public String obtenerUsuarioAutenticado() {
		// TODO Auto-generated method stub
		return usuarioSession.getUsuarioSession().getUsuario();
	}

}
