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

	/**
	 * 
	 */
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
		return id;
	}

	public String getCodigoPrincipal() {
		return codigoPrincipal;
	}

	public void setCodigoPrincipal(String codigoPrincipal) {
		this.codigoPrincipal = codigoPrincipal;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getCantidad() {
		return cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	public Double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public Double getDescuento() {
		return descuento;
	}

	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}

	public Double getPrecioTotalSinImpuesto() {
		return precioTotalSinImpuesto;
	}

	public void setPrecioTotalSinImpuesto(Double precioTotalSinImpuesto) {
		this.precioTotalSinImpuesto = precioTotalSinImpuesto;
	}

	@ManyToOne
	@JoinColumn(name = "ID_PRODUCTO")
	public Producto getlProducto() {
		return lProducto;
	}

	public void setlProducto(Producto lProducto) {
		this.lProducto = lProducto;
	}

	public String getCodigoAuxiliar() {
		return codigoAuxiliar;
	}

	public void setCodigoAuxiliar(String codigoAuxiliar) {
		this.codigoAuxiliar = codigoAuxiliar;
	}

	public String getDetallesAdicionales() {
		return detallesAdicionales;
	}

	public void setDetallesAdicionales(String detallesAdicionales) {
		this.detallesAdicionales = detallesAdicionales;
	}

	public Double getPrecioTotalConImpuesto() {
		return precioTotalConImpuesto;
	}

	public void setPrecioTotalConImpuesto(Double precioTotalConImpuesto) {
		this.precioTotalConImpuesto = precioTotalConImpuesto;
	}

	
	@ManyToOne
	@JoinColumn(name = "ID_CABECERA")
	public CabOrden getCabeceraOrden() {
		return cabeceraOrden;
	}

	public void setCabeceraOrden(CabOrden cabeceraOrden) {
		this.cabeceraOrden = cabeceraOrden;
	}
	
	

}
