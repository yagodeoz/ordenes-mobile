package com.alimesa.ordenpedido.controlador.seguridad;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alimesa.ordenpedido.controlador.seguridad.modelo.UsuarioRespuesta;
import com.alimesa.ordenpedido.controlador.seguridad.modelo.UsuarioSolicitud;
import com.alimesa.ordenpedido.servicio.ServicioSeguridad;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = { "*" }, allowedHeaders = { "*" })
@RequestMapping({ "/acceso" })
@Tag(name = "ControlAccesoAplicacion", description = "ControlAccesoAplicacion")
public class ControlAccesoAplicacion {
	@Autowired
	private ServicioSeguridad servicioSeguridad;

	@Operation(summary = "login", description = "Devuelve el usuario autenticado", tags = { "ControladorSeguridad" })
	@ApiResponses({
			@ApiResponse(responseCode = "201", description = "Solicitud Aceptada", content = {
					@Content(schema = @Schema(implementation = UsuarioRespuesta.class)) }),
			@ApiResponse(responseCode = "400", description = "Solicitud inválida", content = {
					@Content(schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "409", description = "Error 409", content = {
					@Content(schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "500", description = "internal server error", content = {
					@Content(schema = @Schema(implementation = Exception.class)) }) })
	@PostMapping(value = { "/login" }, consumes = { "application/json" })
	public UsuarioRespuesta autenticarUsuario(@Valid @RequestBody UsuarioSolicitud usuarioSolicitud) {
		/* 42 */ System.out.println(usuarioSolicitud.getPassword());
		/* 43 */ System.out.println(usuarioSolicitud.getUsuario());

		/* 45 */ return this.servicioSeguridad.obtenerUsuarioCredenciales(usuarioSolicitud);
	}
}

/*
 * Location:
 * D:\apps\api-rest-ordenpedido\1.0\api-rest-ordenpedido-1.0.war!\WEB-INF\
 * classes\com\alimesa\ordenpedido\controlador\seguridad\ControlAccesoAplicacion
 * .class Java compiler version: 11 (55.0) JD-Core Version: 1.1.3
 */