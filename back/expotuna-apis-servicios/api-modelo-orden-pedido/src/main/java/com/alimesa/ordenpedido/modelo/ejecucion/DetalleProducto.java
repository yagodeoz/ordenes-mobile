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

	public String getDescripcionDetalle() {
		return descripcionDetalle;
	}

	public void setDescripcionDetalle(String descripcionDetalle) {
		this.descripcionDetalle = descripcionDetalle;
	}

	public String getValorAuxiliar1() {
		return valorAuxiliar1;
	}

	public void setValorAuxiliar1(String valorAuxiliar1) {
		this.valorAuxiliar1 = valorAuxiliar1;
	}

	public String getValorAuxiliar2() {
		return valorAuxiliar2;
	}

	public void setValorAuxiliar2(String valorAuxiliar2) {
		this.valorAuxiliar2 = valorAuxiliar2;
	}

	public String getValorAuxiliar3() {
		return valorAuxiliar3;
	}

	public void setValorAuxiliar3(String valorAuxiliar3) {
		this.valorAuxiliar3 = valorAuxiliar3;
	}

	@Lob
	@Column(name = "IMAGEN_REFERENCIA")
	public byte[] getImagenReferencia() {
		return imagenReferencia;
	}

	/**
	 * Sets the imagen referencia.
	 *
	 * @param imagenReferencia the new imagen referencia
	 */
	public void setImagenReferencia(byte[] imagenReferencia) {
		this.imagenReferencia = imagenReferencia;
	}

}
