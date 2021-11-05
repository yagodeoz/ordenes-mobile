
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
 * 
 * */

@Entity
@Table(name = "STOCK_PRODUCTOS")
public class StockProducto extends EntidadBaseAuditable<Long> implements Serializable {
	private static final long serialVersionUID = 1L;
	private Producto producto;
	private String descripcionStock;
	private String nombreBodega;
	private String codigoSucursal;
	private String nombreSucursal;
	private Long cantidadEntera;
	private Double cantidadDecimal;
	private String descripcionesAdicionales;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(generator = "FactStkProducto", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "FactStkProducto", allocationSize = 1, initialValue = 1, sequenceName = "SEC_STOCK_PRODUCTO")
	public Long getId() {
		/* 38 */ return (Long) this.id;
	}

	@ManyToOne
	@JoinColumn(name = "ID_PRODUCTO")
	@JsonBackReference
	public Producto getProducto() {
		/* 45 */ return this.producto;
	}

	public String getDescripcionStock() {
		/* 49 */ return this.descripcionStock;
	}

	public void setDescripcionStock(String descripcionStock) {
		/* 53 */ this.descripcionStock = descripcionStock;
	}

	public String getNombreBodega() {
		/* 57 */ return this.nombreBodega;
	}

	public void setNombreBodega(String nombreBodega) {
		/* 61 */ this.nombreBodega = nombreBodega;
	}

	public String getCodigoSucursal() {
		/* 65 */ return this.codigoSucursal;
	}

	public void setCodigoSucursal(String codigoSucursal) {
		/* 69 */ this.codigoSucursal = codigoSucursal;
	}

	public String getNombreSucursal() {
		/* 73 */ return this.nombreSucursal;
	}

	public void setNombreSucursal(String nombreSucursal) {
		/* 77 */ this.nombreSucursal = nombreSucursal;
	}

	public Long getCantidadEntera() {
		/* 81 */ return this.cantidadEntera;
	}

	public void setCantidadEntera(Long cantidadEntera) {
		/* 85 */ this.cantidadEntera = cantidadEntera;
	}

	public Double getCantidadDecimal() {
		/* 89 */ return this.cantidadDecimal;
	}

	public void setCantidadDecimal(Double cantidadDecimal) {
		/* 93 */ this.cantidadDecimal = cantidadDecimal;
	}

	public String getDescripcionesAdicionales() {
		/* 97 */ return this.descripcionesAdicionales;
	}

	public void setDescripcionesAdicionales(String descripcionesAdicionales) {
		/* 101 */ this.descripcionesAdicionales = descripcionesAdicionales;
	}

	public void setProducto(Producto producto) {
		/* 105 */ this.producto = producto;
	}
}

/*
 * Location:
 * D:\apps\api-rest-ordenpedido\1.0\api-rest-ordenpedido-1.0.war!\WEB-INF\
 * classes\com\alimesa\ordenpedido\modelo\ejecucion\StockProducto.class Java
 * compiler version: 11 (55.0) JD-Core Version: 1.1.3
 */