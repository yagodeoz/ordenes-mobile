package com.alimesa.ordenpedido.controlador.ejecucion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alimesa.ordenpedido.controlador.ejecucion.modelo.ListaCobrosMobile;
import com.alimesa.ordenpedido.controlador.ejecucion.modelo.ListaDespachos;
import com.alimesa.ordenpedido.controlador.ejecucion.modelo.ListaOrdenesMobile;
import com.alimesa.ordenpedido.controlador.ejecucion.modelo.RespuestaCarga;
import com.alimesa.ordenpedido.servicio.ServicioCarga;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = { "*" }, allowedHeaders = { "*" })
@RequestMapping({ "/basedatosCarga" })
@Tag(name = "ControladorCargaBaseDatos", description = "ControladorCargaBaseDatos")
public class ControladorCargaBaseDatos {
	@Autowired
	private ServicioCarga servicioCarga;

	@Operation(summary = "cargaordenes", description = "Realiza la carga de ordenes de pedido desde mobile", tags = {
			"ControladorCargaBaseDatos" })
	@ApiResponses({
			@ApiResponse(responseCode = "201", description = "Solicitud Aceptada", content = {
					@Content(schema = @Schema(implementation = RespuestaCarga.class)) }),
			@ApiResponse(responseCode = "400", description = "Solicitud inválida", content = {
					@Content(schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "409", description = "Error 409", content = {
					@Content(schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "500", description = "internal server error", content = {
					@Content(schema = @Schema(implementation = Exception.class)) }) })
	@PostMapping(value = { "/cargaordenes" }, consumes = { "application/json" })
	public RespuestaCarga cargarOrdenes(@RequestBody ListaOrdenesMobile listaOrdenes) {
		return this.servicioCarga.registrarOrden(listaOrdenes);
	}

	@Operation(summary = "cargacobros", description = "Realiza la carga de cobros desde mobile", tags = {
			"ControladorCargaBaseDatos" })
	@ApiResponses({
			@ApiResponse(responseCode = "201", description = "Solicitud Aceptada", content = {
					@Content(schema = @Schema(implementation = RespuestaCarga.class)) }),
			@ApiResponse(responseCode = "400", description = "Solicitud inválida", content = {
					@Content(schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "409", description = "Error 409", content = {
					@Content(schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "500", description = "internal server error", content = {
					@Content(schema = @Schema(implementation = Exception.class)) }) })
	@PostMapping(value = { "/cargacobros" }, consumes = { "application/json" })
	public RespuestaCarga cargacobros(@RequestBody ListaCobrosMobile listaCobros) {
		return this.servicioCarga.registrarCobros(listaCobros);
	}

	@Operation(summary = "cargadespacho", description = "Realiza la carga de despachos desde mobile", tags = {
			"ControladorCargaBaseDatos" })
	@ApiResponses({
			@ApiResponse(responseCode = "201", description = "Solicitud Aceptada", content = {
					@Content(schema = @Schema(implementation = RespuestaCarga.class)) }),
			@ApiResponse(responseCode = "400", description = "Solicitud inválida", content = {
					@Content(schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "409", description = "Error 409", content = {
					@Content(schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "500", description = "internal server error", content = {
					@Content(schema = @Schema(implementation = Exception.class)) }) })
	@PostMapping(value = { "/cargadespacho" }, consumes = { "application/json" })
	public RespuestaCarga cargadespacho(@RequestBody ListaDespachos listaDespacho) {
		return this.servicioCarga.registrarDespacho(listaDespacho);
	} 

}
