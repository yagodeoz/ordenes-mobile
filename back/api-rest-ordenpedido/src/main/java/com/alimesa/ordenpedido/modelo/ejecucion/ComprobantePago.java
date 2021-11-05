package com.alimesa.ordenpedido.modelo.ejecucion;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.alimesa.ordenpedido.librerias.dominio.entidades.base.EntidadBaseAuditable;

@Entity
@Table(name = "COMPROBANTE_PAGO")
public class ComprobantePago extends EntidadBaseAuditable<Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date fechaEmision;
	private Date fechaVencimiento;
	private String idTipoFormaPago;
	private Double monto;
	private String numeroPago;
	private String comprobante;
	private String banco;
	private Long codigoBanco;
	private String cuenta;
	private String cheque;
	private Double valorCheque;
	private String usuarioAsignado;
	private String usuarioAsignante;
	private String codigoCliente;
	private String nombreCliente;
	private String identificacion;

	@SequenceGenerator(name = "secComprobanteCobro", sequenceName = "SEC_COMP_COBRO")
	@GeneratedValue(generator = "secComprobanteCobro", strategy = GenerationType.SEQUENCE)
	@Id
	public Long getId() {
		/* 44 */ return (Long) this.id;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getIdTipoFormaPago() {
		return idTipoFormaPago;
	}

	public void setIdTipoFormaPago(String idTipoFormaPago) {
		this.idTipoFormaPago = idTipoFormaPago;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public String getNumeroPago() {
		return numeroPago;
	}

	public void setNumeroPago(String numeroPago) {
		this.numeroPago = numeroPago;
	}

	public String getComprobante() {
		return comprobante;
	}

	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public Long getCodigoBanco() {
		return codigoBanco;
	}

	public void setCodigoBanco(Long codigoBanco) {
		this.codigoBanco = codigoBanco;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getCheque() {
		return cheque;
	}

	public void setCheque(String cheque) {
		this.cheque = cheque;
	}

	public Double getValorCheque() {
		return valorCheque;
	}

	public void setValorCheque(Double valorCheque) {
		this.valorCheque = valorCheque;
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

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	
	
	
}
