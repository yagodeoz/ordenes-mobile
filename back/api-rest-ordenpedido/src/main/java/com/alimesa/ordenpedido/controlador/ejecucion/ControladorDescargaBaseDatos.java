 package com.alimesa.ordenpedido.controlador.ejecucion;
 import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alimesa.ordenpedido.controlador.ejecucion.modelo.BaseDatosRespuesta;
import com.alimesa.ordenpedido.controlador.ejecucion.modelo.BaseDatosSolicitud;
import com.alimesa.ordenpedido.servicio.ServicioBaseDatos;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
 
 
 
 
 
 
 
 
 
 
 @RestController
 @CrossOrigin(origins = {"*"}, allowedHeaders = {"*"})
 @RequestMapping({"/basedatos"})
 @Tag(name = "ControladorDescargaBaseDatos", description = "ControladorDescargaBaseDatos")
 public class ControladorDescargaBaseDatos
 {
   @Autowired
   private ServicioBaseDatos servicioBaseDatos;
   
   @Operation(summary = "obtenerbasedatos", description = "Devuelve los datos de la base remota", tags = {"ControladorDescargaBaseDatos"})
   @ApiResponses({@ApiResponse(responseCode = "201", description = "Solicitud Aceptada", content = {@Content(schema = @Schema(implementation = BaseDatosRespuesta.class))}), @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = {@Content(schema = @Schema(implementation = String.class))}), @ApiResponse(responseCode = "409", description = "Error 409", content = {@Content(schema = @Schema(implementation = String.class))}), @ApiResponse(responseCode = "500", description = "internal server error", content = {@Content(schema = @Schema(implementation = Exception.class))})})
   @PostMapping(value = {"/obtenerbasedatos"}, consumes = {"application/json"})
   public BaseDatosRespuesta obtenerbasedatos(@Valid @RequestBody BaseDatosSolicitud baseDatosSolicitud) {
/* 42 */     System.out.println("PARAMETRO " + baseDatosSolicitud.getUsuario());
     
/* 44 */     return this.servicioBaseDatos.obtenerBaseDatosUsuario(baseDatosSolicitud.getUsuario());
   }
 }


/* Location:              D:\apps\api-rest-ordenpedido\1.0\api-rest-ordenpedido-1.0.war!\WEB-INF\classes\com\alimesa\ordenpedido\controlador\ejecucion\ControladorDescargaBaseDatos.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */