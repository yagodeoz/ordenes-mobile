package com.alimesa.ordenpedido.modelo.ejecucion;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.alimesa.ordenpedido.librerias.dominio.entidades.base.EntidadBaseAuditable;

@Entity
@Table(name = "EMPRESAS")
public class Empresa extends EntidadBaseAuditable<Long> implements Serializable {
	private static final long serialVersionUID = 1L;
	private String lRazonSocial;
	private String lIdentificacion;
	private String lDireccion;
	private String lEmailEmisonComprobante;
	private String lClaveCertificado;
	private Date lFechaVigenciaCertificado;
	private byte[] lCertificadoFirma;
	private String lUsuarioCertificado;
	private String lDepartamento;
	private String lRegimenTributario;
	private String lCiudad;
	private String lClaveTecnicaRangoFE;
	private String lIdentificador;
	private String lDescripcion;
	private String lDocumento;
	private String lUrl;
	private String lRutaCertificado;
	private String lFormatoCertificado;
	private Long lIdSuscriptor;
	private byte[] lLogo;
	private String lRutaLogo;
	private String lCuentaRemitente;
	private String lCuentaCopia;
	private byte[] imagenReferencia;
	private String obligadoLlevarContabilidad;
	private String numeroContribuyenteEspecial;
	private String lEnvioAutomaticoSRI;

	@GeneratedValue(generator = "FactEmpresa", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "FactEmpresa", allocationSize = 1, initialValue = 1, sequenceName = "SEC_FACT_EMPRESA")
	@Id
	@Column(name = "ID")
	public Long getId() {
		/* 119 */ return (Long) this.id;
	}

	public String getlRazonSocial() {
		/* 128 */ return this.lRazonSocial;
	}

	public void setlRazonSocial(String lRazonSocial) {
		/* 137 */ this.lRazonSocial = lRazonSocial;
	}

	@Column(name = "NIT")
	public String getlIdentificacion() {
		/* 147 */ return this.lIdentificacion;
	}

	public void setlIdentificacion(String lIdentificacion) {
		/* 156 */ this.lIdentificacion = lIdentificacion;
	}

	public String getlDireccion() {
		/* 165 */ return this.lDireccion;
	}

	public void setlDireccion(String lDireccion) {
		/* 174 */ this.lDireccion = lDireccion;
	}

	public String getlEmailEmisonComprobante() {
		/* 183 */ return this.lEmailEmisonComprobante;
	}

	public void setlEmailEmisonComprobante(String lEmailEmisonComprobante) {
		/* 192 */ this.lEmailEmisonComprobante = lEmailEmisonComprobante;
	}

	public String getlClaveCertificado() {
		/* 202 */ return this.lClaveCertificado;
	}

	public void setlClaveCertificado(String lClaveCertificado) {
		/* 211 */ this.lClaveCertificado = lClaveCertificado;
	}

	@Temporal(TemporalType.DATE)
	public Date getlFechaVigenciaCertificado() {
		/* 221 */ return this.lFechaVigenciaCertificado;
	}

	public void setlFechaVigenciaCertificado(Date lFechaVigenciaCertificado) {
		/* 230 */ this.lFechaVigenciaCertificado = lFechaVigenciaCertificado;
	}

	@Lob
	public byte[] getlCertificadoFirma() {
		/* 240 */ return this.lCertificadoFirma;
	}

	public void setlCertificadoFirma(byte[] lCertificadoFirma) {
		/* 249 */ this.lCertificadoFirma = lCertificadoFirma;
	}

	public String getlDepartamento() {
		/* 258 */ return this.lDepartamento;
	}

	public void setlDepartamento(String lDepartamento) {
		/* 267 */ this.lDepartamento = lDepartamento;
	}

	public String getlRegimenTributario() {
		/* 276 */ return this.lRegimenTributario;
	}

	public void setlRegimenTributario(String lRegimenTributario) {
		/* 285 */ this.lRegimenTributario = lRegimenTributario;
	}

	public String getlCiudad() {
		/* 294 */ return this.lCiudad;
	}

	public void setlCiudad(String lCiudad) {
		/* 303 */ this.lCiudad = lCiudad;
	}

	public String getlClaveTecnicaRangoFE() {
		/* 312 */ return this.lClaveTecnicaRangoFE;
	}

	public void setlClaveTecnicaRangoFE(String lClaveTecnicaRangoFE) {
		/* 321 */ this.lClaveTecnicaRangoFE = lClaveTecnicaRangoFE;
	}

	public String getlIdentificador() {
		/* 330 */ return this.lIdentificador;
	}

	public void setlIdentificador(String lIdentificador) {
		/* 339 */ this.lIdentificador = lIdentificador;
	}

	public String getlDescripcion() {
		/* 348 */ return this.lDescripcion;
	}

	public void setlDescripcion(String lDescripcion) {
		/* 357 */ this.lDescripcion = lDescripcion;
	}

	public String getlDocumento() {
		/* 366 */ return this.lDocumento;
	}

	public void setlDocumento(String lDocumento) {
		/* 375 */ this.lDocumento = lDocumento;
	}

	public String getlUrl() {
		/* 384 */ return this.lUrl;
	}

	public void setlUrl(String lUrl) {
		/* 393 */ this.lUrl = lUrl;
	}

	public String getlUsuarioCertificado() {
		/* 402 */ return this.lUsuarioCertificado;
	}

	public void setlUsuarioCertificado(String lUsuarioCertificado) {
		/* 411 */ this.lUsuarioCertificado = lUsuarioCertificado;
	}

	public String getlRutaCertificado() {
		/* 420 */ return this.lRutaCertificado;
	}

	public void setlRutaCertificado(String lRutaCertificado) {
		/* 429 */ this.lRutaCertificado = lRutaCertificado;
	}

	public String getlFormatoCertificado() {
		/* 438 */ return this.lFormatoCertificado;
	}

	public void setlFormatoCertificado(String lFormatoCertificado) {
		/* 447 */ this.lFormatoCertificado = lFormatoCertificado;
	}

	public Long getlIdSuscriptor() {
		/* 456 */ return this.lIdSuscriptor;
	}

	public void setlIdSuscriptor(Long lIdSuscriptor) {
		/* 465 */ this.lIdSuscriptor = lIdSuscriptor;
	}

	@Lob
	public byte[] getlLogo() {
		/* 475 */ return this.lLogo;
	}

	public void setlLogo(byte[] lLogo) {
		/* 484 */ this.lLogo = lLogo;
	}

	public String getlRutaLogo() {
		/* 493 */ return this.lRutaLogo;
	}

	public void setlRutaLogo(String lRutaLogo) {
		/* 502 */ this.lRutaLogo = lRutaLogo;
	}

	public String getlCuentaRemitente() {
		/* 506 */ return this.lCuentaRemitente;
	}

	public void setlCuentaRemitente(String lCuentaRemitente) {
		/* 510 */ this.lCuentaRemitente = lCuentaRemitente;
	}

	public String getlCuentaCopia() {
		/* 514 */ return this.lCuentaCopia;
	}

	public void setlCuentaCopia(String lCuentaCopia) {
		/* 518 */ this.lCuentaCopia = lCuentaCopia;
	}

	public String toString() {
		/* 524 */ return this.lRazonSocial;
	}

	public byte[] getImagenReferencia() {
		/* 528 */ return this.imagenReferencia;
	}

	public void setImagenReferencia(byte[] imagenReferencia) {
		/* 532 */ this.imagenReferencia = imagenReferencia;
	}

	public String getObligadoLlevarContabilidad() {
		/* 536 */ return this.obligadoLlevarContabilidad;
	}

	public void setObligadoLlevarContabilidad(String obligadoLlevarContabilidad) {
		/* 540 */ this.obligadoLlevarContabilidad = obligadoLlevarContabilidad;
	}

	public String getNumeroContribuyenteEspecial() {
		/* 544 */ return this.numeroContribuyenteEspecial;
	}

	public void setNumeroContribuyenteEspecial(String numeroContribuyenteEspecial) {
		/* 548 */ this.numeroContribuyenteEspecial = numeroContribuyenteEspecial;
	}

	public String getlEnvioAutomaticoSRI() {
		/* 552 */ return this.lEnvioAutomaticoSRI;
	}

	public void setlEnvioAutomaticoSRI(String lEnvioAutomaticoSRI) {
		/* 556 */ this.lEnvioAutomaticoSRI = lEnvioAutomaticoSRI;
	}
}

/*
 * Location:
 * D:\apps\api-rest-ordenpedido\1.0\api-rest-ordenpedido-1.0.war!\WEB-INF\
 * classes\com\alimesa\ordenpedido\modelo\ejecucion\Empresa.class Java compiler
 * version: 11 (55.0) JD-Core Version: 1.1.3
 */