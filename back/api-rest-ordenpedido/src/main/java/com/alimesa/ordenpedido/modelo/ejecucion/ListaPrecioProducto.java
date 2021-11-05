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

@Entity
@Table(name = "LISTA_PRECIO_PRODUCTOS")
public class ListaPrecioProducto extends EntidadBaseAuditable<Long> implements Serializable {
	private static final long serialVersionUID = 1L;
	private Producto producto;
	private ListaPrecio listaPrecio;
	private String codigoProducto;
	private String codigoListaPrecio;
	private Double precioUnitario;
	private Double precioCaja;
	private Double precioLibra;
	private String descripcionUnidad;
	private String codigoUnidad;
	private Double porcentajeDescuento;
	private Double porcentajeIVA;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(generator = "FactPrecioProducto", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "FactPrecioProducto", allocationSize = 1, initialValue = 1, sequenceName = "SEC_PRECIO_PRODUCTO")
	public Long getId() {
		/* 43 */ return (Long) this.id;
	}

	@ManyToOne
	@JoinColumn(name = "ID_PRODUCTO")
	@JsonBackReference
	public Producto getProducto() {
		/* 50 */ return this.producto;
	}

	public void setProducto(Producto producto) {
		/* 54 */ this.producto = producto;
	}

	@ManyToOne
	@JoinColumn(name = "ID_LISTA_PRODUCTO")
	@JsonBackReference
	public ListaPrecio getListaPrecio() {
		/* 61 */ return this.listaPrecio;
	}

	public void setListaPrecio(ListaPrecio listaPrecio) {
		/* 65 */ this.listaPrecio = listaPrecio;
	}

	public Double getPrecioUnitario() {
		/* 69 */ return this.precioUnitario;
	}

	public void setPrecioUnitario(Double precioUnitario) {
		/* 73 */ this.precioUnitario = precioUnitario;
	}

	public Double getPrecioCaja() {
		/* 77 */ return this.precioCaja;
	}

	public void setPrecioCaja(Double precioCaja) {
		/* 81 */ this.precioCaja = precioCaja;
	}

	public Double getPrecioLibra() {
		/* 85 */ return this.precioLibra;
	}

	public void setPrecioLibra(Double precioLibra) {
		/* 89 */ this.precioLibra = precioLibra;
	}

	public String getDescripcionUnidad() {
		/* 93 */ return this.descripcionUnidad;
	}

	public void setDescripcionUnidad(String descripcionUnidad) {
		/* 97 */ this.descripcionUnidad = descripcionUnidad;
	}

	public Double getPorcentajeDescuento() {
		/* 101 */ return this.porcentajeDescuento;
	}

	public void setPorcentajeDescuento(Double porcentajeDescuento) {
		/* 105 */ this.porcentajeDescuento = porcentajeDescuento;
	}

	public Double getPorcentajeIVA() {
		/* 109 */ return this.porcentajeIVA;
	}

	public void setPorcentajeIVA(Double porcentajeIVA) {
		/* 113 */ this.porcentajeIVA = porcentajeIVA;
	}

	public String getCodigoProducto() {
		/* 117 */ return this.codigoProducto;
	}

	public void setCodigoProducto(String codigoProducto) {
		/* 121 */ this.codigoProducto = codigoProducto;
	}

	public String getCodigoListaPrecio() {
		/* 125 */ return this.codigoListaPrecio;
	}

	public void setCodigoListaPrecio(String codigoListaPrecio) {
		/* 129 */ this.codigoListaPrecio = codigoListaPrecio;
	}

	public String getCodigoUnidad() {
		/* 133 */ return this.codigoUnidad;
	}

	public void setCodigoUnidad(String codigoUnidad) {
		/* 137 */ this.codigoUnidad = codigoUnidad;
	}
}

/*
 * Location:
 * D:\apps\api-rest-ordenpedido\1.0\api-rest-ordenpedido-1.0.war!\WEB-INF\
 * classes\com\alimesa\ordenpedido\modelo\ejecucion\ListaPrecioProducto.class
 * Java compiler version: 11 (55.0) JD-Core Version: 1.1.3
 */