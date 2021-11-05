
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
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "CAB_COBROS")
public class CabCobro extends EntidadBaseAuditable<Long> implements Serializable {
	private static final long serialVersionUID = 1L;
	private String numeroFactura;
	private String numeroDocumento;
	private String idTipoFormaPago;
	private Double monto;
	private Double saldo;
	private Date fechaEmision;
	private Date fechaVencimiento;
	private Double redCheque;
	private String canalCreacion;
	private String banco;
	private Long codigoBanco;
	private String  codigoCliente;
	private String  codigoSucursal;
	private String  estadoProceso;
	private Long  idCliente;
	private Long  idSucursal;
	private Long  idUsuario;
	private String  usuarioAsignado;
	private String  usuarioAsignante;
	private Long diasVencido;
	
	@JsonBackReference
	private List<DetallePago> listaPago;

	@SequenceGenerator(name = "secCabCobro", sequenceName = "SEC_CAB_COBRO")
	@GeneratedValue(generator = "secCabCobro", strategy = GenerationType.SEQUENCE)
	@Id
	public Long getId() {
		/* 44 */ return (Long) this.id;
	}

	public String getNumeroFactura() {
		/* 48 */ return this.numeroFactura;
	}

	public void setNumeroFactura(String numeroFactura) {
		/* 52 */ this.numeroFactura = numeroFactura;
	}

	public String getNumeroDocumento() {
		/* 56 */ return this.numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		/* 60 */ this.numeroDocumento = numeroDocumento;
	}

	public String getIdTipoFormaPago() {
		/* 64 */ return this.idTipoFormaPago;
	}

	public void setIdTipoFormaPago(String idTipoFormaPago) {
		/* 68 */ this.idTipoFormaPago = idTipoFormaPago;
	}

	public Double getMonto() {
		/* 72 */ return this.monto;
	}

	public void setMonto(Double monto) {
		/* 76 */ this.monto = monto;
	}

	public Double getSaldo() {
		/* 80 */ return this.saldo;
	}

	public void setSaldo(Double saldo) {
		/* 84 */ this.saldo = saldo;
	}

	@Temporal(TemporalType.DATE)
	public Date getFechaEmision() {
		/* 89 */ return this.fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		/* 93 */ this.fechaEmision = fechaEmision;
	}

	@Temporal(TemporalType.DATE)
	public Date getFechaVencimiento() {
		/* 98 */ return this.fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		/* 102 */ this.fechaVencimiento = fechaVencimiento;
	}

	public Double getRedCheque() {
		/* 106 */ return this.redCheque;
	}

	public void setRedCheque(Double redCheque) {
		/* 110 */ this.redCheque = redCheque;
	}

	@OneToMany(mappedBy = "cabCobro", fetch = FetchType.EAGER)
	public List<DetallePago> getListaPago() {
		/* 115 */ return this.listaPago;
	}

	public void setListaPago(List<DetallePago> listaPago) {
		/* 119 */ this.listaPago = listaPago;
	}

	public String getCanalCreacion() {
		return canalCreacion;
	}

	public void setCanalCreacion(String canalCreacion) {
		this.canalCreacion = canalCreacion;
	}

	public Long getCodigoBanco() {
		return codigoBanco;
	}

	public void setCodigoBanco(Long codigoBanco) {
		this.codigoBanco = codigoBanco;
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getCodigoSucursal() {
		return codigoSucursal;
	}

	public void setCodigoSucursal(String codigoSucursal) {
		this.codigoSucursal = codigoSucursal;
	}

	public String getEstadoProceso() {
		return estadoProceso;
	}

	public void setEstadoProceso(String estadoProceso) {
		this.estadoProceso = estadoProceso;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
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

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public Long getDiasVencido() {
		return diasVencido;
	}

	public void setDiasVencido(Long diasVencido) {
		this.diasVencido = diasVencido;
	}

	
	
	
}

/*
 * Location:
 * D:\apps\api-rest-ordenpedido\1.0\api-rest-ordenpedido-1.0.war!\WEB-INF\
 * classes\com\alimesa\ordenpedido\modelo\ejecucion\CabCobro.class Java compiler
 * version: 11 (55.0) JD-Core Version: 1.1.3
 */