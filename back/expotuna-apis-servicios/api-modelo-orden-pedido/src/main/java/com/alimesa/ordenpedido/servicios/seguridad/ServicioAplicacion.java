package com.alimesa.ordenpedido.servicios.seguridad;


import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.alimesa.ordenpedido.modelo.seguridad.Opcion;
import com.alimesa.ordenpedido.modelo.seguridad.Rol;
import com.alimesa.ordenpedido.modelo.seguridad.Usuario;
import com.alimesa.ordenpedido.repositorios.seguridad.OpcionEAO;
import com.alimesa.ordenpedido.repositorios.seguridad.OpcionesRolEAO;
import com.alimesa.ordenpedido.repositorios.seguridad.UsuarioEAO;






@Stateless
public class ServicioAplicacion
{

	/** The opciones roles EAO. */
	@EJB
	private OpcionesRolEAO opcionesRolesEAO;
	
	/** The opciones EAO. */
	@EJB
	private OpcionEAO opcionesEAO;
	

	
	
	
	/** The usuario EAO. */
	@EJB
	private UsuarioEAO usuarioEAO;
	
	
	
	/**
	 * Obtener menu.
	 *
	 * @param usuario the usuario
	 * @param orientacion the orientacion
	 * @return the list
	 */
	public List<Opcion> obtenerMenu(Usuario usuario, String orientacion) 
	{
		return opcionesRolesEAO.obtenerListaOpcionesRol(usuario.getId(), orientacion);
	}
	
	/**
	 * Obtener menu.
	 *
	 * @param rol the rol
	 * @param orientacion the orientacion
	 * @return the list
	 */
	public List<Opcion> obtenerMenu(Rol rol, String orientacion) 
	{
		return opcionesRolesEAO.obtenerListaOpcionesRolUsuario(rol.getId(), orientacion);
	}
	
	
	/**
	 * Verificar permiso opcion.
	 *
	 * @param opcion the opcion
	 * @param usuario the usuario
	 * @return true, if successful
	 */
	public boolean verificarPermisoOpcion(Opcion opcion, Usuario usuario)
	{
		return opcionesEAO.permitirAccesoOpcion(opcion, usuario);
	}
	
	/**
	 * Verificar permiso opcion.
	 *
	 * @param opcion the opcion
	 * @param pIdUsuario the id usuario
	 * @return true, if successful
	 */
	public boolean verificarPermisoOpcion(Opcion opcion, Long pIdUsuario)
	{
		return opcionesEAO.permitirAccesoOpcion(opcion, pIdUsuario);
	}
	
	/**
	 * Verificar permiso opcion.
	 *
	 * @param opcion the opcion
	 * @param pRol the rol
	 * @return true, if successful
	 */
	public boolean verificarPermisoOpcion(Opcion opcion, String pRol)
	{
		return opcionesEAO.permitirAccesoOpcion(opcion, pRol);
	}
	
	
}