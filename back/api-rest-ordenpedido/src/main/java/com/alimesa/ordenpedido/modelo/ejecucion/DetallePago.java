package com.alimesa.ordenpedido.modelo.ejecucion;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.alimesa.ordenpedido.librerias.dominio.entidades.base.EntidadBaseAuditable;

@Entity
@Table(name = "DETALLE_PAGO")
public class DetallePago extends EntidadBaseAuditable<Long> implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long numeroPago;
	private Long numeroComprobante;
	private Double valorPago;
	private String tipoPago;
	private Long idBanco;
	private String codigoBanco;
	private String nombreBanco;
	private String cuenta;
	private String numeroCheque;
	private Date fechaVencimiento;
	private Double valorCheque;
	private CabCobro cabCobro;

	@SequenceGenerator(sequenceName = "SEC_DETALLE_PAGO", name = "secDetPago")
	@GeneratedValue(generator = "secDetPago", strategy = GenerationType.SEQUENCE)
	@Id
	public Long getId() {
		/* 43 */ return (Long) this.id;
	}

	public Long getNumeroPago() {
		/* 47 */ return this.numeroPago;
	}

	public void setNumeroPago(Long numeroPago) {
		/* 51 */ this.numeroPago = numeroPago;
	}

	public Long getNumeroComprobante() {
		/* 55 */ return this.numeroComprobante;
	}

	public void setNumeroComprobante(Long numeroComprobante) {
		/* 59 */ this.numeroComprobante = numeroComprobante;
	}

	public Double getValorPago() {
		/* 63 */ return this.valorPago;
	}

	public void setValorPago(Double valorPago) {
		/* 67 */ this.valorPago = valorPago;
	}

	public String getTipoPago() {
		/* 71 */ return this.tipoPago;
	}

	public void setTipoPago(String tipoPago) {
		/* 75 */ this.tipoPago = tipoPago;
	}

	public String getCuenta() {
		/* 79 */ return this.cuenta;
	}

	public void setCuenta(String cuenta) {
		/* 83 */ this.cuenta = cuenta;
	}

	public String getNumeroCheque() {
		/* 87 */ return this.numeroCheque;
	}

	public void setNumeroCheque(String numeroCheque) {
		/* 91 */ this.numeroCheque = numeroCheque;
	}

	@Temporal(TemporalType.DATE)
	public Date getFechaVencimiento() {
		/* 96 */ return this.fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		/* 100 */ this.fechaVencimiento = fechaVencimiento;
	}

	public Double getValorCheque() {
		/* 104 */ return this.valorCheque;
	}

	public void setValorCheque(Double valorCheque) {
		/* 108 */ this.valorCheque = valorCheque;
	}

	@ManyToOne
	@JoinColumn(name = "ID_CAB_COBRO")
	public CabCobro getCabCobro() {
		/* 114 */ return this.cabCobro;
	}

	public void setCabCobro(CabCobro cabCobro) {
		/* 118 */ this.cabCobro = cabCobro;
	}

	public Long getIdBanco() {
		/* 122 */ return this.idBanco;
	}

	public void setIdBanco(Long idBanco) {
		/* 126 */ this.idBanco = idBanco;
	}

	public String getNombreBanco() {
		/* 130 */ return this.nombreBanco;
	}

	public void setNombreBanco(String nombreBanco) {
		/* 134 */ this.nombreBanco = nombreBanco;
	}

	public String getCodigoBanco() {
		/* 138 */ return this.codigoBanco;
	}

	public void setCodigoBanco(String codigoBanco) {
		/* 142 */ this.codigoBanco = codigoBanco;
	}
}

/*
 * Location:
 * D:\apps\api-rest-ordenpedido\1.0\api-rest-ordenpedido-1.0.war!\WEB-INF\
 * classes\com\alimesa\ordenpedido\modelo\ejecucion\DetallePago.class Java
 * compiler version: 11 (55.0) JD-Core Version: 1.1.3
 */