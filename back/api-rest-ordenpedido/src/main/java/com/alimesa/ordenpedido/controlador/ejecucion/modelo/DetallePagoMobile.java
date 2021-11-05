package com.alimesa.ordenpedido.controlador.ejecucion.modelo;

import java.io.Serializable;

public class DetallePagoMobile implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String estado;
	private String idreferencia;
	private String campo_auditoria;
	private String fecha_actualizacion;
	private String fecha_registro;
	private String observacion;
	private String codigobanco;
	private String cuenta;
	private String fechavencimiento;
	private String idbanco;
	private String nombrebanco;
	private String numerocheque;
	private String numerocomprobante;
	private Double numeropago;
	private String tipopago;
	private Double valorcheque;
	private Double valorpago;
	private String id_cab_cobro;
	private String numerocomprobanteerp;
	private String numerodocumento;
	private String numerofactura;
	private String numeropagoerp;

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

	public String getFechavencimiento() {
		return fechavencimiento;
	}

	public void setFechavencimiento(String fechavencimiento) {
		this.fechavencimiento = fechavencimiento;
	}

	public String getIdbanco() {
		return idbanco;
	}

	public void setIdbanco(String idbanco) {
		this.idbanco = idbanco;
	}

	public String getNombrebanco() {
		return nombrebanco;
	}

	public void setNombrebanco(String nombrebanco) {
		this.nombrebanco = nombrebanco;
	}

	public String getNumerocheque() {
		return numerocheque;
	}

	public void setNumerocheque(String numerocheque) {
		this.numerocheque = numerocheque;
	}

	public String getNumerocomprobante() {
		return numerocomprobante;
	}

	public void setNumerocomprobante(String numerocomprobante) {
		this.numerocomprobante = numerocomprobante;
	}

	public Double getNumeropago() {
		return numeropago;
	}

	public void setNumeropago(Double numeropago) {
		this.numeropago = numeropago;
	}

	public String getTipopago() {
		return tipopago;
	}

	public void setTipopago(String tipopago) {
		this.tipopago = tipopago;
	}

	public Double getValorcheque() {
		return valorcheque;
	}

	public void setValorcheque(Double valorcheque) {
		this.valorcheque = valorcheque;
	}

	public Double getValorpago() {
		return valorpago;
	}

	public void setValorpago(Double valorpago) {
		this.valorpago = valorpago;
	}

	public String getId_cab_cobro() {
		return id_cab_cobro;
	}

	public void setId_cab_cobro(String id_cab_cobro) {
		this.id_cab_cobro = id_cab_cobro;
	}

	public String getNumerocomprobanteerp() {
		return numerocomprobanteerp;
	}

	public void setNumerocomprobanteerp(String numerocomprobanteerp) {
		this.numerocomprobanteerp = numerocomprobanteerp;
	}

	public String getNumerodocumento() {
		return numerodocumento;
	}

	public void setNumerodocumento(String numerodocumento) {
		this.numerodocumento = numerodocumento;
	}

	public String getNumerofactura() {
		return numerofactura;
	}

	public void setNumerofactura(String numerofactura) {
		this.numerofactura = numerofactura;
	}

	public String getNumeropagoerp() {
		return numeropagoerp;
	}

	public void setNumeropagoerp(String numeropagoerp) {
		this.numeropagoerp = numeropagoerp;
	}

}
