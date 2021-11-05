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
		return id;
	}

	@ManyToOne
	@JoinColumn(name = "ID_PRODUCTO")
	public Producto getProducto() {
		return producto;
	}

	public String getDescripcionStock() {
		return descripcionStock;
	}

	public void setDescripcionStock(String descripcionStock) {
		this.descripcionStock = descripcionStock;
	}

	public String getNombreBodega() {
		return nombreBodega;
	}

	public void setNombreBodega(String nombreBodega) {
		this.nombreBodega = nombreBodega;
	}

	public String getCodigoSucursal() {
		return codigoSucursal;
	}

	public void setCodigoSucursal(String codigoSucursal) {
		this.codigoSucursal = codigoSucursal;
	}

	public String getNombreSucursal() {
		return nombreSucursal;
	}

	public void setNombreSucursal(String nombreSucursal) {
		this.nombreSucursal = nombreSucursal;
	}

	public Long getCantidadEntera() {
		return cantidadEntera;
	}

	public void setCantidadEntera(Long cantidadEntera) {
		this.cantidadEntera = cantidadEntera;
	}

	public Double getCantidadDecimal() {
		return cantidadDecimal;
	}

	public void setCantidadDecimal(Double cantidadDecimal) {
		this.cantidadDecimal = cantidadDecimal;
	}

	public String getDescripcionesAdicionales() {
		return descripcionesAdicionales;
	}

	public void setDescripcionesAdicionales(String descripcionesAdicionales) {
		this.descripcionesAdicionales = descripcionesAdicionales;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}
