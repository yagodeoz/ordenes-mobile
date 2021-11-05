
package com.alimesa.ordenpedido.servicio;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alimesa.ordenpedido.controlador.seguridad.modelo.UsuarioRespuesta;
import com.alimesa.ordenpedido.controlador.seguridad.modelo.UsuarioSolicitud;
import com.alimesa.ordenpedido.librerias.generales.UtilEncriptarDataSource;
import com.alimesa.ordenpedido.modelo.seguridad.Rol;
import com.alimesa.ordenpedido.modelo.seguridad.Usuario;
import com.alimesa.ordenpedido.modelo.seguridad.UsuarioSucursal;
import com.alimesa.ordenpedido.modelo.seguridad.UsuariosRol;
import com.alimesa.ordenpedido.repositorio.seguridad.RepositorioRol;
import com.alimesa.ordenpedido.repositorio.seguridad.RepositorioUsuario;
import com.alimesa.ordenpedido.repositorio.seguridad.RepositorioUsuarioRol;
import com.alimesa.ordenpedido.repositorio.seguridad.RepositorioUsuarioSucursal;

@Service
public class ServicioSeguridad {
	@Autowired
	private RepositorioUsuario repUsuario;
	@Autowired
	private RepositorioRol repRol;
	@Autowired
	private RepositorioUsuarioRol repUsuarioRol;
	@Autowired
	private RepositorioUsuarioSucursal repUsuarioSucursal;

	public List<Usuario> listaUsuarios() {
		/* 37 */ return this.repUsuario.findAll();
	}

	public List<Rol> listaRoles() {
		/* 42 */ return this.repRol.findAll();
	}

	public List<UsuariosRol> listaUsuarioRoles() {
		/* 47 */ return this.repUsuarioRol.findAll();
	}

	public List<UsuarioSucursal> listaUsuarioSucursal() {
		/* 52 */ return this.repUsuarioSucursal.findAll();
	}

	public UsuarioRespuesta obtenerUsuarioCredenciales(UsuarioSolicitud credenciales) {
		/* 57 */ UsuarioRespuesta usuarioResp = new UsuarioRespuesta();

		/* 59 */ System.out.println(credenciales.getUsuario());

		try {
			/* 62 */ System.out.println(UtilEncriptarDataSource.encode(credenciales.getPassword()));
			/* 63 */ List<Usuario> listaUsuario = this.repUsuario.obtenerUsuarioCredencial(
					credenciales.getUsuario().toUpperCase(),
					/* 64 */ UtilEncriptarDataSource.encode(credenciales.getPassword()));

			/* 66 */ if (listaUsuario.isEmpty()) {
				/* 67 */ return usuarioResp;
			}
			/* 69 */ listaUsuario.forEach(dato -> {
				System.out.println();

				usuarioResp.setApellidos(dato.getlApellidos());

				try {
					String clave = new String(UtilEncriptarDataSource.decode(dato.getClave()));
					usuarioResp.setClave(clave);
					/* 77 */ } catch (Exception e) {
					e.printStackTrace();
				}

				usuarioResp.setCorreo(dato.getCorreo());

				usuarioResp.setDescripcion(dato.getlDescripcion());
				usuarioResp.setEstado(dato.getEstado());
				usuarioResp.setFechaactualizacion(
						(new SimpleDateFormat("yyyy-MM-dd")).format(dato.getFechaActualizacion()));
				usuarioResp.setFecharegistro((new SimpleDateFormat("yyyy-MM-dd")).format(dato.getFechaRegistro()));
				usuarioResp.setId(dato.getId());
				usuarioResp.setNombres(dato.getlNombres());
				usuarioResp.setUsuario(dato.getUsuario());
			});
			/* 91 */ } catch (Exception e) {
			/* 92 */ e.printStackTrace();
		}

		/* 95 */ return usuarioResp;
	}
}

/*
 * Location:
 * D:\apps\api-rest-ordenpedido\1.0\api-rest-ordenpedido-1.0.war!\WEB-INF\
 * classes\com\alimesa\ordenpedido\servicio\ServicioSeguridad.class Java
 * compiler version: 11 (55.0) JD-Core Version: 1.1.3
 */