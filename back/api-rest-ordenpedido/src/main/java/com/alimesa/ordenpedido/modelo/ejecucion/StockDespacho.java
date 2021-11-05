package com.alimesa.ordenpedido.modelo.ejecucion;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.alimesa.ordenpedido.librerias.dominio.entidades.base.EntidadBaseAuditable;

@Entity
@Table(name = "STOCK_DESPACHO")
public class StockDespacho extends EntidadBaseAuditable<Long> implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String canalCreacion;
	private Long  cantidadEntera;
	private String codigoProducto;
	private String  codigoSucursal;
	private Double  contidadDecimal;
	private Long  idSucursal;
	private Long  idUsuario;
	private String placaCamion;
	private String usuarioAsignado;
	private String usuarioAsignante;
	private Double precioUnitario;
	private Double precioConImpuesto;
	private Double precionSinImpuesto;
	private Double saldo;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(generator = "FactStkDespacho", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "FactStkDespacho", allocationSize = 1, initialValue = 1, sequenceName = "SEC_STOCK_DESPACHO")
	public Long getId() {
		/* 38 */ return (Long) this.id;
	}

	public String getCanalCreacion() {
		return canalCreacion;
	}

	public void setCanalCreacion(String canalCreacion) {
		this.canalCreacion = canalCreacion;
	}

	public Long getCantidadEntera() {
		return cantidadEntera;
	}

	public void setCantidadEntera(Long cantidadEntera) {
		this.cantidadEntera = cantidadEntera;
	}

	public String getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public String getCodigoSucursal() {
		return codigoSucursal;
	}

	public void setCodigoSucursal(String codigoSucursal) {
		this.codigoSucursal = codigoSucursal;
	}

	public Double getContidadDecimal() {
		return contidadDecimal;
	}

	public void setContidadDecimal(Double contidadDecimal) {
		this.contidadDecimal = contidadDecimal;
	}

	public Long getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(Long idSucursal) {
		this.idSucursal = idSucursal;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getPlacaCamion() {
		return placaCamion;
	}

	public void setPlacaCamion(String placaCamion) {
		this.placaCamion = placaCamion;
	}

	public String getUsuarioAsignado() {
		return usuarioAsignado;
	}

	public void setUsuarioAsignado(String usuarioAsignado) {
		this.usuarioAsignado = usuarioAsignado;
	}

	public String getUsuarioAsignante() {
		return usuarioAsignante;
	}

	public void setUsuarioAsignante(String usuarioAsignante) {
		this.usuarioAsignante = usuarioAsignante;
	}

	public Double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public Double getPrecioConImpuesto() {
		return precioConImpuesto;
	}

	public void setPrecioConImpuesto(Double precioConImpuesto) {
		this.precioConImpuesto = precioConImpuesto;
	}

	public Double getPrecionSinImpuesto() {
		return precionSinImpuesto;
	}

	public void setPrecionSinImpuesto(Double precionSinImpuesto) {
		this.precionSinImpuesto = precionSinImpuesto;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	
	
}
