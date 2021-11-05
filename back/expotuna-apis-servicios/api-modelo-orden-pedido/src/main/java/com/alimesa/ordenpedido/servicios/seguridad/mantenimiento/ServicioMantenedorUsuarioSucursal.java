package com.alimesa.ordenpedido.servicios.seguridad.mantenimiento;

import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.alimesa.ordenpedido.configuracion.ServicioMantenedorControlAuditoria;
import com.alimesa.ordenpedido.modelo.ejecucion.Sucursal;
import com.alimesa.ordenpedido.modelo.seguridad.UsuarioSucursal;
import com.alimesa.ordenpedido.repositorios.ejecucion.UsuarioSucursalEAO;
import com.alimesa.ordenpedido.servicios.ejecucion.mantenimiento.ServicioMantenedorSucrusal;

@Stateless
public class ServicioMantenedorUsuarioSucursal
		extends ServicioMantenedorControlAuditoria<UsuarioSucursalEAO, UsuarioSucursal, Long> {

	@EJB
	private UsuarioSucursalEAO crud;

	@EJB
	private ServicioMantenedorSucrusal servicioSucursal;

	protected UsuarioSucursalEAO getCrud() {
		return crud;
	}

	protected void cargarConfiguracionServicio() {
		System.out.println("NO EXISTE CONFIGURACIÓN ADICIONAL PARA ESTE SERVICIO ServicioMantenedorUsuarioSucursal ");
	}

	public UsuarioSucursal obtenerUsuarioSucursal(String lSucursal, Long id) {

		String query = "select * from usuarios_sucursales a where a.id_usuario = :idUsuario and a.id_sucursal in "
				+ "	( " + "	select id from sucursales where upper(nombre) = :sucursal  " + "	and estado = 'A' "
				+ "	 ) " + "	and a.estado = 'A'";

		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("sucursal", lSucursal.toUpperCase());
		parametros.put("idUsuario", id);

		List<UsuarioSucursal> listaUsuarioSucursal = crud.ejecutarNativeQueryList(query, parametros,
				UsuarioSucursal.class);

		if (listaUsuarioSucursal.isEmpty())
			return null;
		else
			return listaUsuarioSucursal.get(0);
	}

	public List<Sucursal> obtenerListasSucursalUsuario(Long idUsuario) {

		String lQuery = "select b.* " + "from USUARIOS_SUCURSALES a,  " + "     SUCURSALES b   "
				+ "where a.ID_USUARIO = :idUsuario  "
				+ "and a.ESTADO = 'A'  and b.id = a.ID_SUCURSAL  and b.ESTADO = 'A'";

		return servicioSucursal.ejecutarQueryNativoObjeto(lQuery, new HashMap<String, Object>() {
			static final long serialVersionUID = 1L;
			{
				put("idUsuario", idUsuario);
			}
		}, Sucursal.class);
	}

}
