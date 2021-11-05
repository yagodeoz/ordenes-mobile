package com.alimesa.ordenpedido.modelo.ejecucion;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.alimesa.ordenpedido.librerias.dominio.entidades.base.EntidadBaseAuditable;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "DETALLES_PRODUCTOS")
public class DetalleProducto extends EntidadBaseAuditable<Long> implements Serializable {
	private static final long serialVersionUID = 1L;
	private Producto producto;
	private String descripcionDetalle;
	private String valorAuxiliar1;
	private String valorAuxiliar2;
	private String valorAuxiliar3;
	private byte[] imagenReferencia;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(generator = "FactDetProducto", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "FactDetProducto", allocationSize = 1, initialValue = 1, sequenceName = "SEC_DET_PRODUCTO")
	public Long getId() {
		/* 39 */ return (Long) this.id;
	}

	@ManyToOne
	@JoinColumn(name = "ID_PRODUCTO")
	@JsonBackReference
	public Producto getProducto() {
		/* 46 */ return this.producto;
	}

	public void setProducto(Producto producto) {
		/* 50 */ this.producto = producto;
	}

	public String getDescripcionDetalle() {
		/* 54 */ return this.descripcionDetalle;
	}

	public void setDescripcionDetalle(String descripcionDetalle) {
		/* 58 */ this.descripcionDetalle = descripcionDetalle;
	}

	public String getValorAuxiliar1() {
		/* 62 */ return this.valorAuxiliar1;
	}

	public void setValorAuxiliar1(String valorAuxiliar1) {
		/* 66 */ this.valorAuxiliar1 = valorAuxiliar1;
	}

	public String getValorAuxiliar2() {
		/* 70 */ return this.valorAuxiliar2;
	}

	public void setValorAuxiliar2(String valorAuxiliar2) {
		/* 74 */ this.valorAuxiliar2 = valorAuxiliar2;
	}

	public String getValorAuxiliar3() {
		/* 78 */ return this.valorAuxiliar3;
	}

	public void setValorAuxiliar3(String valorAuxiliar3) {
		/* 82 */ this.valorAuxiliar3 = valorAuxiliar3;
	}

	@Lob
	@Column(name = "IMAGEN_REFERENCIA")
	public byte[] getImagenReferencia() {
		/* 88 */ return this.imagenReferencia;
	}

	public void setImagenReferencia(byte[] imagenReferencia) {
		/* 97 */ this.imagenReferencia = imagenReferencia;
	}
}

/*
 * Location:
 * D:\apps\api-rest-ordenpedido\1.0\api-rest-ordenpedido-1.0.war!\WEB-INF\
 * classes\com\alimesa\ordenpedido\modelo\ejecucion\DetalleProducto.class Java
 * compiler version: 11 (55.0) JD-Core Version: 1.1.3
 */