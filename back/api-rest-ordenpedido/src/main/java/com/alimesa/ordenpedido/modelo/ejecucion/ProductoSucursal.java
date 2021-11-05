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
@Table(name = "PRODUCTO_SUCURSAL")
public class ProductoSucursal extends EntidadBaseAuditable<Long> implements Serializable {
	private static final long serialVersionUID = 1L;
	private Producto producto;
	private Sucursal sucursal;
	private String codigoProducto;
	private String codigoSucursal;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(generator = "FactProductoSucursal", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "FactProductoSucursal", allocationSize = 1, initialValue = 1, sequenceName = "SEC_PRODUCTO_SUCURSAL")
	public Long getId() {
		/* 36 */ return (Long) this.id;
	}

	@ManyToOne
	@JoinColumn(name = "ID_PRODUCTO")
	public Producto getProducto() {
		/* 42 */ return this.producto;
	}

	public void setProducto(Producto producto) {
		/* 46 */ this.producto = producto;
	}

	@ManyToOne
	@JoinColumn(name = "ID_SUCURSAL")
	public Sucursal getSucursal() {
		/* 52 */ return this.sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		/* 55 */ this.sucursal = sucursal;
	}

	public String getCodigoProducto() {
		/* 59 */ return this.codigoProducto;
	}

	public void setCodigoProducto(String codigoProducto) {
		/* 63 */ this.codigoProducto = codigoProducto;
	}

	public String getCodigoSucursal() {
		/* 67 */ return this.codigoSucursal;
	}

	public void setCodigoSucursal(String codigoSucursal) {
		/* 71 */ this.codigoSucursal = codigoSucursal;
	}
}

/*
 * Location:
 * D:\apps\api-rest-ordenpedido\1.0\api-rest-ordenpedido-1.0.war!\WEB-INF\
 * classes\com\alimesa\ordenpedido\modelo\ejecucion\ProductoSucursal.class Java
 * compiler version: 11 (55.0) JD-Core Version: 1.1.3
 */