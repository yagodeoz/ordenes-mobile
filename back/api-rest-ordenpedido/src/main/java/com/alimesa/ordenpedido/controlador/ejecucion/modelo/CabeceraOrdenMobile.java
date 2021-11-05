package com.alimesa.ordenpedido.controlador.ejecucion.modelo;
/*     */ 
/*     */ import com.fasterxml.jackson.annotation.JsonProperty;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CabeceraOrdenMobile
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @JsonProperty("ID")
/*     */   private Long id;
/*     */   @JsonProperty("ESTADO")
/*     */   private String estado;
/*     */   @JsonProperty("IDREFERENCIA")
/*     */   private String idreferencia;
/*     */   @JsonProperty("CAMPO_AUDITORIA")
/*     */   private String campoauditoria;
/*     */   @JsonProperty("FECHAACTUALIZACION")
/*     */   private String fechaactualizacion;
/*     */   @JsonProperty("FECHAREGISTRO")
/*     */   private String fecharegistro;
/*     */   @JsonProperty("OBSERVACION")
/*     */   private String observacion;
/*     */   @JsonProperty("DIRECCIONCOMPRADOR")
/*     */   private String direccioncomprador;
/*     */   @JsonProperty("ESTADOPROCESO")
/*     */   private String estadoproceso;
/*     */   @JsonProperty("FECHAEMISION")
/*     */   private String fechaemision;
/*     */   @JsonProperty("IDCLIENTE")
/*     */   private String idcliente;
/*     */   @JsonProperty("IDSUCURSAL")
/*     */   private Long idsucursal;
/*     */   @JsonProperty("IDUSUARIO")
/*     */   private Long idusuario;
/*     */   @JsonProperty("IDENTIFICACIONCOMPRADOR")
/*     */   private String identificacioncomprador;
/*     */   @JsonProperty("IMPORTETOTAL")
/*     */   private Double importetotal;
/*     */   @JsonProperty("MONEDA")
/*     */   private String moneda;
/*     */   @JsonProperty("NOMBREVENDEDOR")
/*     */   private String nombrevendedor;
/*     */   @JsonProperty("NUMEROODEN")
/*     */   private String numerooden;
/*     */   @JsonProperty("RAZONSOCIALCOMPRADOR")
/*     */   private String razonsocialcomprador;
/*     */   @JsonProperty("SUBTOTAL0")
/*     */   private Double subtotal0;
/*     */   @JsonProperty("SUBTOTAL12")
/*     */   private Double subtotal12;
/*     */   @JsonProperty("TOTALDESCUENTO")
/*     */   private Double totaldescuento;
/*     */   @JsonProperty("TOTALIMPUESTO")
/*     */   private Double totalimpuesto;
/*     */   @JsonProperty("TOTALSINIMPUESTOS")
/*     */   private Double totalsinimpuestos;
/*     */   @JsonProperty("CANALCREACION")
/*     */   private String canalcreacion;
/*     */   @JsonProperty("CODIGOCLIENTE")
/*     */   private String codigocliente;
/*     */   @JsonProperty("CODIGOSUCURSAL")
/*     */   private String codigosucursal;
/*     */   @JsonProperty("USUARIOASIGNADO")
/*     */   private String usuarioasignado;
/*     */   @JsonProperty("USUARIOASIGNANTE")
/*     */   private String usuarioasignante;
/*     */   
/*     */   public Long getId() {
/* 107 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Long id) {
/* 111 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getEstado() {
/* 115 */     return this.estado;
/*     */   }
/*     */   
/*     */   public void setEstado(String estado) {
/* 119 */     this.estado = estado;
/*     */   }
/*     */   
/*     */   public String getIdreferencia() {
/* 123 */     return this.idreferencia;
/*     */   }
/*     */   
/*     */   public void setIdreferencia(String idreferencia) {
/* 127 */     this.idreferencia = idreferencia;
/*     */   }
/*     */   
/*     */   public String getCampoauditoria() {
/* 131 */     return this.campoauditoria;
/*     */   }
/*     */   
/*     */   public void setCampoauditoria(String campoauditoria) {
/* 135 */     this.campoauditoria = campoauditoria;
/*     */   }
/*     */   
/*     */   public String getFechaactualizacion() {
/* 139 */     return this.fechaactualizacion;
/*     */   }
/*     */   
/*     */   public void setFechaactualizacion(String fechaactualizacion) {
/* 143 */     this.fechaactualizacion = fechaactualizacion;
/*     */   }
/*     */   
/*     */   public String getFecharegistro() {
/* 147 */     return this.fecharegistro;
/*     */   }
/*     */   
/*     */   public void setFecharegistro(String fecharegistro) {
/* 151 */     this.fecharegistro = fecharegistro;
/*     */   }
/*     */   
/*     */   public String getObservacion() {
/* 155 */     return this.observacion;
/*     */   }
/*     */   
/*     */   public void setObservacion(String observacion) {
/* 159 */     this.observacion = observacion;
/*     */   }
/*     */   
/*     */   public String getDireccioncomprador() {
/* 163 */     return this.direccioncomprador;
/*     */   }
/*     */   
/*     */   public void setDireccioncomprador(String direccioncomprador) {
/* 167 */     this.direccioncomprador = direccioncomprador;
/*     */   }
/*     */   
/*     */   public String getEstadoproceso() {
/* 171 */     return this.estadoproceso;
/*     */   }
/*     */   
/*     */   public void setEstadoproceso(String estadoproceso) {
/* 175 */     this.estadoproceso = estadoproceso;
/*     */   }
/*     */   
/*     */   public String getFechaemision() {
/* 179 */     return this.fechaemision;
/*     */   }
/*     */   
/*     */   public void setFechaemision(String fechaemision) {
/* 183 */     this.fechaemision = fechaemision;
/*     */   }
/*     */   
/*     */   public String getIdcliente() {
/* 187 */     return this.idcliente;
/*     */   }
/*     */   
/*     */   public void setIdcliente(String idcliente) {
/* 191 */     this.idcliente = idcliente;
/*     */   }
/*     */   
/*     */   public Long getIdsucursal() {
/* 195 */     return this.idsucursal;
/*     */   }
/*     */   
/*     */   public void setIdsucursal(Long idsucursal) {
/* 199 */     this.idsucursal = idsucursal;
/*     */   }
/*     */   
/*     */   public Long getIdusuario() {
/* 203 */     return this.idusuario;
/*     */   }
/*     */   
/*     */   public void setIdusuario(Long idusuario) {
/* 207 */     this.idusuario = idusuario;
/*     */   }
/*     */   
/*     */   public String getIdentificacioncomprador() {
/* 211 */     return this.identificacioncomprador;
/*     */   }
/*     */   
/*     */   public void setIdentificacioncomprador(String identificacioncomprador) {
/* 215 */     this.identificacioncomprador = identificacioncomprador;
/*     */   }
/*     */   
/*     */   public Double getImportetotal() {
/* 219 */     return this.importetotal;
/*     */   }
/*     */   
/*     */   public void setImportetotal(Double importetotal) {
/* 223 */     this.importetotal = importetotal;
/*     */   }
/*     */   
/*     */   public String getMoneda() {
/* 227 */     return this.moneda;
/*     */   }
/*     */   
/*     */   public void setMoneda(String moneda) {
/* 231 */     this.moneda = moneda;
/*     */   }
/*     */   
/*     */   public String getNombrevendedor() {
/* 235 */     return this.nombrevendedor;
/*     */   }
/*     */   
/*     */   public void setNombrevendedor(String nombrevendedor) {
/* 239 */     this.nombrevendedor = nombrevendedor;
/*     */   }
/*     */   
/*     */   public String getNumerooden() {
/* 243 */     return this.numerooden;
/*     */   }
/*     */   
/*     */   public void setNumerooden(String numerooden) {
/* 247 */     this.numerooden = numerooden;
/*     */   }
/*     */   
/*     */   public String getRazonsocialcomprador() {
/* 251 */     return this.razonsocialcomprador;
/*     */   }
/*     */   
/*     */   public void setRazonsocialcomprador(String razonsocialcomprador) {
/* 255 */     this.razonsocialcomprador = razonsocialcomprador;
/*     */   }
/*     */   
/*     */   public Double getSubtotal0() {
/* 259 */     return this.subtotal0;
/*     */   }
/*     */   
/*     */   public void setSubtotal0(Double subtotal0) {
/* 263 */     this.subtotal0 = subtotal0;
/*     */   }
/*     */   
/*     */   public Double getSubtotal12() {
/* 267 */     return this.subtotal12;
/*     */   }
/*     */   
/*     */   public void setSubtotal12(Double subtotal12) {
/* 271 */     this.subtotal12 = subtotal12;
/*     */   }
/*     */   
/*     */   public Double getTotaldescuento() {
/* 275 */     return this.totaldescuento;
/*     */   }
/*     */   
/*     */   public void setTotaldescuento(Double totaldescuento) {
/* 279 */     this.totaldescuento = totaldescuento;
/*     */   }
/*     */   
/*     */   public Double getTotalimpuesto() {
/* 283 */     return this.totalimpuesto;
/*     */   }
/*     */   
/*     */   public void setTotalimpuesto(Double totalimpuesto) {
/* 287 */     this.totalimpuesto = totalimpuesto;
/*     */   }
/*     */   
/*     */   public Double getTotalsinimpuestos() {
/* 291 */     return this.totalsinimpuestos;
/*     */   }
/*     */   
/*     */   public void setTotalsinimpuestos(Double totalsinimpuestos) {
/* 295 */     this.totalsinimpuestos = totalsinimpuestos;
/*     */   }
/*     */   
/*     */   public String getCanalcreacion() {
/* 299 */     return this.canalcreacion;
/*     */   }
/*     */   
/*     */   public void setCanalcreacion(String canalcreacion) {
/* 303 */     this.canalcreacion = canalcreacion;
/*     */   }
/*     */   
/*     */   public String getCodigocliente() {
/* 307 */     return this.codigocliente;
/*     */   }
/*     */   
/*     */   public void setCodigocliente(String codigocliente) {
/* 311 */     this.codigocliente = codigocliente;
/*     */   }
/*     */   
/*     */   public String getCodigosucursal() {
/* 315 */     return this.codigosucursal;
/*     */   }
/*     */   
/*     */   public void setCodigosucursal(String codigosucursal) {
/* 319 */     this.codigosucursal = codigosucursal;
/*     */   }
/*     */   
/*     */   public String getUsuarioasignado() {
/* 323 */     return this.usuarioasignado;
/*     */   }
/*     */   
/*     */   public void setUsuarioasignado(String usuarioasignado) {
/* 327 */     this.usuarioasignado = usuarioasignado;
/*     */   }
/*     */   
/*     */   public String getUsuarioasignante() {
/* 331 */     return this.usuarioasignante;
/*     */   }
/*     */   
/*     */   public void setUsuarioasignante(String usuarioasignante) {
/* 335 */     this.usuarioasignante = usuarioasignante;
/*     */   }
/*     */ }


/* Location:              D:\apps\api-rest-ordenpedido\1.0\api-rest-ordenpedido-1.0.war!\WEB-INF\classes\com\alimesa\ordenpedido\controlador\ejecucion\modelo\CabeceraOrdenMobile.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */