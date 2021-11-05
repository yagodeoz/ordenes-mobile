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
@Table(name = "LISTA_PRECIO_PRODUCTOS")
public class ListaPrecioProducto extends EntidadBaseAuditable<Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Producto producto;
	private String nombreListaPrecio;
	private Double precio1;
	private Double precio2;
	private Double precio3;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(generator = "FactPrecioProducto", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "FactPrecioProducto", allocationSize = 1, initialValue = 1, sequenceName = "SEC_PRECIO_PRODUCTO")
	public Long getId() {
		return id;
	}

	@ManyToOne
	@JoinColumn(name = "ID_PRODUCTO")
	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public String getNombreListaPrecio() {
		return nombreListaPrecio;
	}

	public void setNombreListaPrecio(String nombreListaPrecio) {
		this.nombreListaPrecio = nombreListaPrecio;
	}

	public Double getPrecio1() {
		return precio1;
	}

	public void setPrecio1(Double precio1) {
		this.precio1 = precio1;
	}

	public Double getPrecio2() {
		return precio2;
	}

	public void setPrecio2(Double precio2) {
		this.precio2 = precio2;
	}

	public Double getPrecio3() {
		return precio3;
	}

	public void setPrecio3(Double precio3) {
		this.precio3 = precio3;
	}

}
