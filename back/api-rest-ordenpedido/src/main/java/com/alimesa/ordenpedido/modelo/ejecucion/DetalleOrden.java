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

@Entity
@Table(name = "DETALLES_ORDEN")
public class DetalleOrden extends EntidadBaseAuditable<Long> implements Serializable {
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
	private CabOrden cabeceraOrden;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(generator = "OrdDetalle", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "OrdDetalle", allocationSize = 1, initialValue = 1, sequenceName = "SEC_ORD_DETALLE")
	public Long getId() {
		/* 44 */ return (Long) this.id;
	}

	public String getCodigoPrincipal() {
		/* 48 */ return this.codigoPrincipal;
	}

	public void setCodigoPrincipal(String codigoPrincipal) {
		/* 52 */ this.codigoPrincipal = codigoPrincipal;
	}

	public String getDescripcion() {
		/* 56 */ return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		/* 60 */ this.descripcion = descripcion;
	}

	public Long getCantidad() {
		/* 64 */ return this.cantidad;
	}

	public void setCantidad(Long cantidad) {
		/* 68 */ this.cantidad = cantidad;
	}

	public Double getPrecioUnitario() {
		/* 72 */ return this.precioUnitario;
	}

	public void setPrecioUnitario(Double precioUnitario) {
		/* 76 */ this.precioUnitario = precioUnitario;
	}

	public Double getDescuento() {
		/* 80 */ return this.descuento;
	}

	public void setDescuento(Double descuento) {
		/* 84 */ this.descuento = descuento;
	}

	public Double getPrecioTotalSinImpuesto() {
		/* 88 */ return this.precioTotalSinImpuesto;
	}

	public void setPrecioTotalSinImpuesto(Double precioTotalSinImpuesto) {
		/* 92 */ this.precioTotalSinImpuesto = precioTotalSinImpuesto;
	}

	@ManyToOne
	@JoinColumn(name = "ID_PRODUCTO")
	public Producto getlProducto() {
		/* 98 */ return this.lProducto;
	}

	public void setlProducto(Producto lProducto) {
		/* 102 */ this.lProducto = lProducto;
	}

	public String getCodigoAuxiliar() {
		/* 106 */ return this.codigoAuxiliar;
	}

	public void setCodigoAuxiliar(String codigoAuxiliar) {
		/* 110 */ this.codigoAuxiliar = codigoAuxiliar;
	}

	public String getDetallesAdicionales() {
		/* 114 */ return this.detallesAdicionales;
	}

	public void setDetallesAdicionales(String detallesAdicionales) {
		/* 118 */ this.detallesAdicionales = detallesAdicionales;
	}

	public Double getPrecioTotalConImpuesto() {
		/* 122 */ return this.precioTotalConImpuesto;
	}

	public void setPrecioTotalConImpuesto(Double precioTotalConImpuesto) {
		/* 126 */ this.precioTotalConImpuesto = precioTotalConImpuesto;
	}

	@ManyToOne
	@JoinColumn(name = "ID_CABECERA")
	public CabOrden getCabeceraOrden() {
		/* 133 */ return this.cabeceraOrden;
	}

	public void setCabeceraOrden(CabOrden cabeceraOrden) {
		/* 137 */ this.cabeceraOrden = cabeceraOrden;
	}
}

/*
 * Location:
 * D:\apps\api-rest-ordenpedido\1.0\api-rest-ordenpedido-1.0.war!\WEB-INF\
 * classes\com\alimesa\ordenpedido\modelo\ejecucion\DetalleOrden.class Java
 * compiler version: 11 (55.0) JD-Core Version: 1.1.3
 */