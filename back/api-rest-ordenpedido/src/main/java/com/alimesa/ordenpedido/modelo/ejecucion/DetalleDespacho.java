package com.alimesa.ordenpedido.modelo.ejecucion;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.alimesa.ordenpedido.librerias.dominio.entidades.base.EntidadBaseAuditable;
import com.fasterxml.jackson.annotation.JsonBackReference;

/*
 * 
  
  
 * 
 * */

@Entity
@Table(name = "DETALLES_DESPACHO")
public class DetalleDespacho extends EntidadBaseAuditable<Long> implements Serializable {
	private static final long serialVersionUID = 1L;
	private String codigoPrincipal;
	private String codigoAuxiliar;
	private String detallesAdicionales;
	private String descripcion;
	private Long cantidad;
	private Double precioUnitario;
	private Double descuento;
	private Double precioTotalSinImpuesto;
	private Double precioTotalConImpuesto;
	private Producto lProducto;
	private CabDespacho cabDespacho;
	private String codigoProducto;
	private String  numeroFactura;
	private String  numeroOden;
	private Double  precioPedido;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(generator = "despachoDetalle", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "despachoDetalle", allocationSize = 1, initialValue = 1, sequenceName = "SEC_DESP_DETALLE")
	public Long getId() {
		/* 45 */ return (Long) this.id;
	}

	public String getCodigoPrincipal() {
		/* 49 */ return this.codigoPrincipal;
	}

	public void setCodigoPrincipal(String codigoPrincipal) {
		/* 53 */ this.codigoPrincipal = codigoPrincipal;
	}

	public String getDescripcion() {
		/* 57 */ return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		/* 61 */ this.descripcion = descripcion;
	}

	public Long getCantidad() {
		/* 65 */ return this.cantidad;
	}

	public void setCantidad(Long cantidad) {
		/* 69 */ this.cantidad = cantidad;
	}

	public Double getPrecioUnitario() {
		/* 73 */ return this.precioUnitario;
	}

	public void setPrecioUnitario(Double precioUnitario) {
		/* 77 */ this.precioUnitario = precioUnitario;
	}

	public Double getDescuento() {
		/* 81 */ return this.descuento;
	}

	public void setDescuento(Double descuento) {
		/* 85 */ this.descuento = descuento;
	}

	public Double getPrecioTotalSinImpuesto() {
		/* 89 */ return this.precioTotalSinImpuesto;
	}

	public void setPrecioTotalSinImpuesto(Double precioTotalSinImpuesto) {
		/* 93 */ this.precioTotalSinImpuesto = precioTotalSinImpuesto;
	}

	@ManyToOne
	@JoinColumn(name = "ID_PRODUCTO")
	@JsonBackReference
	public Producto getlProducto() {
		/* 99 */ return this.lProducto;
	}

	public void setlProducto(Producto lProducto) {
		/* 103 */ this.lProducto = lProducto;
	}

	public String getCodigoAuxiliar() {
		/* 107 */ return this.codigoAuxiliar;
	}

	public void setCodigoAuxiliar(String codigoAuxiliar) {
		/* 111 */ this.codigoAuxiliar = codigoAuxiliar;
	}

	public String getDetallesAdicionales() {
		/* 115 */ return this.detallesAdicionales;
	}

	public void setDetallesAdicionales(String detallesAdicionales) {
		/* 119 */ this.detallesAdicionales = detallesAdicionales;
	}

	public Double getPrecioTotalConImpuesto() {
		/* 123 */ return this.precioTotalConImpuesto;
	}

	public void setPrecioTotalConImpuesto(Double precioTotalConImpuesto) {
		/* 127 */ this.precioTotalConImpuesto = precioTotalConImpuesto;
	}

	@ManyToOne
	@JoinColumn(name = "ID_CAB_DESPACHO")
	@JsonBackReference
	public CabDespacho getCabDespacho() {
		/* 133 */ return this.cabDespacho;
	}

	public void setCabDespacho(CabDespacho cabDespacho) {
		/* 137 */ this.cabDespacho = cabDespacho;
	}

	public String getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public String getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public String getNumeroOden() {
		return numeroOden;
	}

	public void setNumeroOden(String numeroOden) {
		this.numeroOden = numeroOden;
	}

	public Double getPrecioPedido() {
		return precioPedido;
	}

	public void setPrecioPedido(Double precioPedido) {
		this.precioPedido = precioPedido;
	}
	
	
}

/*
 * Location:
 * D:\apps\api-rest-ordenpedido\1.0\api-rest-ordenpedido-1.0.war!\WEB-INF\
 * classes\com\alimesa\ordenpedido\modelo\ejecucion\DetalleDespacho.class Java
 * compiler version: 11 (55.0) JD-Core Version: 1.1.3
 */