package com.alimesa.ordenpedido.vista.seguridad;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.alimesa.ordenpedido.librerias.vista.anotaciones.ITestServicioMenuOpcionesHorizontal;
import com.alimesa.ordenpedido.librerias.vista.anotaciones.ITestUsuarioSession;
import com.alimesa.ordenpedido.librerias.vista.interfaces.IGuardiaUsuarioSession;
import com.alimesa.ordenpedido.librerias.vista.interfaces.IServiciosMenu;
import com.alimesa.ordenpedido.librerias.vista.interfaces.IUsuarioSession;
import com.alimesa.ordenpedido.modelo.seguridad.Opcion;
import com.alimesa.ordenpedido.modelo.seguridad.Usuario;
import com.alimesa.ordenpedido.servicios.seguridad.ServicioAplicacion;

// TODO: Auto-generated Javadoc
/**
 * The Class TestServiciosMenuHorizontal.
 */
@ITestServicioMenuOpcionesHorizontal
@RequestScoped
public class TestServiciosMenuHorizontal implements IServiciosMenu<Opcion> {

	/** The parametro obtener opciones. */
	@Inject
	@ITestUsuarioSession
	private IUsuarioSession<Usuario> parametroObtenerOpciones;

	/** The servicio aplicacion. */
	@EJB
	private ServicioAplicacion servicioAplicacion;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.onix.modulo.librerias.vista.interfaces.IServiciosMenu#getMenuOpciones()
	 */
	public List<Opcion> getMenuOpciones() {
		return servicioAplicacion.obtenerMenu(parametroObtenerOpciones.getUsuarioSession(),
				IGuardiaUsuarioSession.TIPO_MENU);
	}

}
