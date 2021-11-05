package com.alimesa.ordenpedido.controlador.ejecucion.modelo;

import java.io.Serializable;

public class ComprobanteCobroMobile implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	private String estado;
	private String idreferencia;
	private String campo_auditoria;
	private String fecha_actualizacion;
	private String fecha_registro;
	private String observacion;
	private String fechaemision;
	private String fechavencimiento;
	private String idtipoformapago;
	private Double monto;
	private String numeropago;
	private String comprobante;
	private String banco;
	private String codigobanco;
	private String cuenta;
	private String cheque;
	private Double valorcheque;
	private String usuarioasignado;
	private String usuarioasignante;
	private String codigocliente;
	private String nombrecliente;
	private String identificacion;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getIdreferencia() {
		return idreferencia;
	}
	public void setIdreferencia(String idreferencia) {
		this.idreferencia = idreferencia;
	}
	public String getCampo_auditoria() {
		return campo_auditoria;
	}
	public void setCampo_auditoria(String campo_auditoria) {
		this.campo_auditoria = campo_auditoria;
	}
	public String getFecha_actualizacion() {
		return fecha_actualizacion;
	}
	public void setFecha_actualizacion(String fecha_actualizacion) {
		this.fecha_actualizacion = fecha_actualizacion;
	}
	public String getFecha_registro() {
		return fecha_registro;
	}
	public void setFecha_registro(String fecha_registro) {
		this.fecha_registro = fecha_registro;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getFechaemision() {
		return fechaemision;
	}
	public void setFechaemision(String fechaemision) {
		this.fechaemision = fechaemision;
	}
	public String getFechavencimiento() {
		return fechavencimiento;
	}
	public void setFechavencimiento(String fechavencimiento) {
		this.fechavencimiento = fechavencimiento;
	}
	public String getIdtipoformapago() {
		return idtipoformapago;
	}
	public void setIdtipoformapago(String idtipoformapago) {
		this.idtipoformapago = idtipoformapago;
	}
	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	public String getNumeropago() {
		return numeropago;
	}
	public void setNumeropago(String numeropago) {
		this.numeropago = numeropago;
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
	public String getCodigobanco() {
		return codigobanco;
	}
	public void setCodigobanco(String codigobanco) {
		this.codigobanco = codigobanco;
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
	public Double getValorcheque() {
		return valorcheque;
	}
	public void setValorcheque(Double valorcheque) {
		this.valorcheque = valorcheque;
	}
	public String getUsuarioasignado() {
		return usuarioasignado;
	}
	public void setUsuarioasignado(String usuarioasignado) {
		this.usuarioasignado = usuarioasignado;
	}
	public String getUsuarioasignante() {
		return usuarioasignante;
	}
	public void setUsuarioasignante(String usuarioasignante) {
		this.usuarioasignante = usuarioasignante;
	}
	public String getCodigocliente() {
		return codigocliente;
	}
	public void setCodigocliente(String codigocliente) {
		this.codigocliente = codigocliente;
	}
	public String getNombrecliente() {
		return nombrecliente;
	}
	public void setNombrecliente(String nombrecliente) {
		this.nombrecliente = nombrecliente;
	}
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
}
