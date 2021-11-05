package com.alimesa.ordenpedido.controlador.seguridad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alimesa.ordenpedido.modelo.seguridad.Rol;
import com.alimesa.ordenpedido.modelo.seguridad.Usuario;
import com.alimesa.ordenpedido.modelo.seguridad.UsuarioSucursal;
import com.alimesa.ordenpedido.modelo.seguridad.UsuariosRol;
import com.alimesa.ordenpedido.servicio.ServicioSeguridad;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping({ "/seguridad" })
@Tag(name = "ControladorSeguridad", description = "ControladorSeguridad")
public class ControladorSeguridad {
	@Autowired
	private ServicioSeguridad servicioSeguridad;

	@Operation(summary = "listaUsuarios", description = "Devuelve el listado de usuarios.", tags = {
			"ControladorSeguridad" })
	@ApiResponses({
			@ApiResponse(responseCode = "201", description = "Solicitud Aceptada", content = {
					@Content(schema = @Schema(implementation = Usuario.class)) }),
			@ApiResponse(responseCode = "400", description = "Solicitud inválida", content = {
					@Content(schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "409", description = "Error 409", content = {
					@Content(schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "500", description = "internal server error", content = {
					@Content(schema = @Schema(implementation = Exception.class)) }) })
	@GetMapping({ "/listaUsuarios" })
	public List<Usuario> listaUsuarios() throws Exception {
		/* 40 */ return this.servicioSeguridad.listaUsuarios();
	}

	@Operation(summary = "listaRoles", description = "Devuelve el listado de roles.", tags = { "ControladorSeguridad" })
	@ApiResponses({
			@ApiResponse(responseCode = "201", description = "Solicitud Aceptada", content = {
					@Content(schema = @Schema(implementation = Rol.class)) }),
			@ApiResponse(responseCode = "400", description = "Solicitud inválida", content = {
					@Content(schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "409", description = "Error 409", content = {
					@Content(schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "500", description = "internal server error", content = {
					@Content(schema = @Schema(implementation = Exception.class)) }) })
	@GetMapping({ "/listaRoles" })
	public List<Rol> listaRoles() throws Exception {
		/* 51 */ return this.servicioSeguridad.listaRoles();
	}

	@Operation(summary = "listaUsuariosRoles", description = "Devuelve el listado de usuarios roles", tags = {
			"ControladorSeguridad" })
	@ApiResponses({
			@ApiResponse(responseCode = "201", description = "Solicitud Aceptada", content = {
					@Content(schema = @Schema(implementation = UsuariosRol.class)) }),
			@ApiResponse(responseCode = "400", description = "Solicitud inválida", content = {
					@Content(schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "409", description = "Error 409", content = {
					@Content(schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "500", description = "internal server error", content = {
					@Content(schema = @Schema(implementation = Exception.class)) }) })
	@GetMapping({ "/listaUsuariosRoles" })
	public List<UsuariosRol> listaUsuariosRoles() throws Exception {
		/* 63 */ return this.servicioSeguridad.listaUsuarioRoles();
	}

	@Operation(summary = "listaUsuariosSucursales", description = "Devuelve la lista de usuarios sucursal", tags = {
			"ControladorSeguridad" })
	@ApiResponses({
			@ApiResponse(responseCode = "201", description = "Solicitud Aceptada", content = {
					@Content(schema = @Schema(implementation = UsuarioSucursal.class)) }),
			@ApiResponse(responseCode = "400", description = "Solicitud inválida", content = {
					@Content(schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "409", description = "Error 409", content = {
					@Content(schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "500", description = "internal server error", content = {
					@Content(schema = @Schema(implementation = Exception.class)) }) })
	@GetMapping({ "/listaUsuariosSucursales" })
	public List<UsuarioSucursal> listaUsuarioSucursal() throws Exception {
		/* 75 */ return this.servicioSeguridad.listaUsuarioSucursal();
	}
}

/*
 * Location:
 * D:\apps\api-rest-ordenpedido\1.0\api-rest-ordenpedido-1.0.war!\WEB-INF\
 * classes\com\alimesa\ordenpedido\controlador\seguridad\ControladorSeguridad.
 * class Java compiler version: 11 (55.0) JD-Core Version: 1.1.3
 */