package com.alimesa.ordenpedido.controlador.ejecucion.modelo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DetalleOrdenMobile implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("ID")
	private Long id;
	@JsonProperty("ESTADO")
	private String estado;
	@JsonProperty("IDREFERENCIA")
	private String idreferencia;
	@JsonProperty("FECHAACTUALIZACION")
	private String fechaactualizacion;
	@JsonProperty("FECHAREGISTRO")
	private String fecharegistro;
	@JsonProperty("OBSERVACION")
	private String observacion;
	@JsonProperty("CANTIDAD")
	private Long cantidad;
	@JsonProperty("CODIGOAUXILIAR")
	private String codigoauxiliar;
	@JsonProperty("CODIGOPRINCIPAL")
	private String codigoprincipal;
	@JsonProperty("DESCRIPCION")
	private String descripcion;
	@JsonProperty("DESCUENTO")
	private Double descuento;
	@JsonProperty("DETALLESADICIONALES")
	private String detallesadicionales;
	@JsonProperty("PRECIOTOTALCONIMPUESTO")
	private Double preciototalconimpuesto;
	@JsonProperty("PRECIOTOTALSINIMPUESTO")
	private Double preciototalsinimpuesto;
	@JsonProperty("PRECIOUNITARIO")
	private Double preciounitario;
	@JsonProperty("ID_CABECERA")
	private Long idcabecera;
	@JsonProperty("ID_PRODUCTO")
	private Long idproducto;
	@JsonProperty("NUMEROORDEN")
	private String numeroorden;

	public Long getId() {
		/* 74 */ return this.id;
	}

	public void setId(Long id) {
		/* 78 */ this.id = id;
	}

	public String getEstado() {
		/* 82 */ return this.estado;
	}

	public void setEstado(String estado) {
		/* 86 */ this.estado = estado;
	}

	public String getIdreferencia() {
		/* 90 */ return this.idreferencia;
	}

	public void setIdreferencia(String idreferencia) {
		/* 94 */ this.idreferencia = idreferencia;
	}

	public String getFechaactualizacion() {
		/* 98 */ return this.fechaactualizacion;
	}

	public void setFechaactualizacion(String fechaactualizacion) {
		/* 102 */ this.fechaactualizacion = fechaactualizacion;
	}

	public String getFecharegistro() {
		/* 106 */ return this.fecharegistro;
	}

	public void setFecharegistro(String fecharegistro) {
		/* 110 */ this.fecharegistro = fecharegistro;
	}

	public String getObservacion() {
		/* 114 */ return this.observacion;
	}

	public void setObservacion(String observacion) {
		/* 118 */ this.observacion = observacion;
	}

	public Long getCantidad() {
		/* 122 */ return this.cantidad;
	}

	public void setCantidad(Long cantidad) {
		/* 126 */ this.cantidad = cantidad;
	}

	public String getCodigoauxiliar() {
		/* 130 */ return this.codigoauxiliar;
	}

	public void setCodigoauxiliar(String codigoauxiliar) {
		/* 134 */ this.codigoauxiliar = codigoauxiliar;
	}

	public String getCodigoprincipal() {
		/* 138 */ return this.codigoprincipal;
	}

	public void setCodigoprincipal(String codigoprincipal) {
		/* 142 */ this.codigoprincipal = codigoprincipal;
	}

	public String getDescripcion() {
		/* 146 */ return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		/* 150 */ this.descripcion = descripcion;
	}

	public Double getDescuento() {
		/* 154 */ return this.descuento;
	}

	public void setDescuento(Double descuento) {
		/* 158 */ this.descuento = descuento;
	}

	public String getDetallesadicionales() {
		/* 162 */ return this.detallesadicionales;
	}

	public void setDetallesadicionales(String detallesadicionales) {
		/* 166 */ this.detallesadicionales = detallesadicionales;
	}

	public Double getPreciototalconimpuesto() {
		/* 170 */ return this.preciototalconimpuesto;
	}

	public void setPreciototalconimpuesto(Double preciototalconimpuesto) {
		/* 174 */ this.preciototalconimpuesto = preciototalconimpuesto;
	}

	public Double getPreciototalsinimpuesto() {
		/* 178 */ return this.preciototalsinimpuesto;
	}

	public void setPreciototalsinimpuesto(Double preciototalsinimpuesto) {
		/* 182 */ this.preciototalsinimpuesto = preciototalsinimpuesto;
	}

	public Double getPreciounitario() {
		/* 186 */ return this.preciounitario;
	}

	public void setPreciounitario(Double preciounitario) {
		/* 190 */ this.preciounitario = preciounitario;
	}

	public Long getIdcabecera() {
		/* 194 */ return this.idcabecera;
	}

	public void setIdcabecera(Long idcabecera) {
		/* 198 */ this.idcabecera = idcabecera;
	}

	public Long getIdproducto() {
		/* 202 */ return this.idproducto;
	}

	public void setIdproducto(Long idproducto) {
		/* 206 */ this.idproducto = idproducto;
	}

	public String getNumeroorden() {
		/* 210 */ return this.numeroorden;
	}

	public void setNumeroorden(String numeroorden) {
		/* 214 */ this.numeroorden = numeroorden;
	}
}

/*
 * Location:
 * D:\apps\api-rest-ordenpedido\1.0\api-rest-ordenpedido-1.0.war!\WEB-INF\
 * classes\com\alimesa\ordenpedido\controlador\ejecucion\modelo\
 * DetalleOrdenMobile.class Java compiler version: 11 (55.0) JD-Core Version:
 * 1.1.3
 */