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
		return id;
	}

	public Long getNumeroPago() {
		return numeroPago;
	}

	public void setNumeroPago(Long numeroPago) {
		this.numeroPago = numeroPago;
	}

	public Long getNumeroComprobante() {
		return numeroComprobante;
	}

	public void setNumeroComprobante(Long numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}

	public Double getValorPago() {
		return valorPago;
	}

	public void setValorPago(Double valorPago) {
		this.valorPago = valorPago;
	}

	public String getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getNumeroCheque() {
		return numeroCheque;
	}

	public void setNumeroCheque(String numeroCheque) {
		this.numeroCheque = numeroCheque;
	}

	@Temporal(TemporalType.DATE)
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public Double getValorCheque() {
		return valorCheque;
	}

	public void setValorCheque(Double valorCheque) {
		this.valorCheque = valorCheque;
	}

	@ManyToOne
	@JoinColumn(name = "ID_CAB_COBRO")
	public CabCobro getCabCobro() {
		return cabCobro;
	}

	public void setCabCobro(CabCobro cabCobro) {
		this.cabCobro = cabCobro;
	}

	public Long getIdBanco() {
		return idBanco;
	}

	public void setIdBanco(Long idBanco) {
		this.idBanco = idBanco;
	}

	public String getNombreBanco() {
		return nombreBanco;
	}

	public void setNombreBanco(String nombreBanco) {
		this.nombreBanco = nombreBanco;
	}

	public String getCodigoBanco() {
		return codigoBanco;
	}

	public void setCodigoBanco(String codigoBanco) {
		this.codigoBanco = codigoBanco;
	}
	
	

}
