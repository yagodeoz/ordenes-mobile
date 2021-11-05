package com.alimesa.ordenpedido.modelo.ejecucion;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.alimesa.ordenpedido.librerias.dominio.entidades.base.EntidadBaseAuditable;

@Entity
@Table(name = "CAB_COBROS")
public class CabCobro extends EntidadBaseAuditable<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String numeroFactura;
	private String numeroDocumento;
	private String idTipoFormaPago;
	private Double monto;
	private Double saldo;
	private Date fechaEmision;
	private Date fechaVencimiento;
	private Double redCheque;

	private List<DetallePago> listaPago;

	@SequenceGenerator(name = "secCabCobro", sequenceName = "SEC_CAB_COBRO")
	@GeneratedValue(generator = "secCabCobro", strategy = GenerationType.SEQUENCE)
	@Id
	public Long getId() {
		// TODO Auto-generated method stub
		return id;
	}

	public String getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
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

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	@Temporal(TemporalType.DATE)
	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	@Temporal(TemporalType.DATE)
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public Double getRedCheque() {
		return redCheque;
	}

	public void setRedCheque(Double redCheque) {
		this.redCheque = redCheque;
	}

	@OneToMany(mappedBy = "cabCobro", fetch = FetchType.EAGER)
	public List<DetallePago> getListaPago() {
		return listaPago;
	}

	public void setListaPago(List<DetallePago> listaPago) {
		this.listaPago = listaPago;
	}

}
