package com.alimesa.ordenpedido.vista.seguridad;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

import com.alimesa.ordenpedido.librerias.generales.UtilEncriptarDataSource;
import com.alimesa.ordenpedido.librerias.vista.anotaciones.ITestAutenticacion;
import com.alimesa.ordenpedido.librerias.vista.interfaces.IServiciosAutenticacion;
import com.alimesa.ordenpedido.modelo.seguridad.Usuario;
import com.alimesa.ordenpedido.servicios.seguridad.ServicioAutenticacion;

// TODO: Auto-generated Javadoc
/**
 * The Class TestAutenticar.
 */
@ITestAutenticacion
@RequestScoped
public class TestAutenticar implements IServiciosAutenticacion<Usuario, BeanLogin> {

	/** The autenticar. */
	@EJB
	private ServicioAutenticacion autenticar;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.onix.modulo.librerias.vista.interfaces.IServiciosAutenticacion#autenticar
	 * (java.io.Serializable)
	 */
	public Usuario autenticar(BeanLogin datosAutenticar) throws Exception {
		Usuario usuari = null;
		try {

			usuari = autenticar.autenticar(datosAutenticar.getUsuario().toUpperCase(),
					UtilEncriptarDataSource.encode(datosAutenticar.getClave()));

		} catch (Exception e) {
			throw new Exception("No existe");
		}

		return usuari;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.onix.modulo.librerias.vista.interfaces.IServiciosAutenticacion#
	 * autenticarDominio(java.io.Serializable)
	 */
	public boolean autenticarDominio(BeanLogin datosAutenticar) {

		return true;
	}
}
