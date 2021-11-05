/*
 * 
 */
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


// TODO: Auto-generated Javadoc
/**
 * The Class FactEmpresa.
 */
@Entity
@Table(name="EMPRESAS")
public class Empresa extends  EntidadBaseAuditable<Long> implements Serializable
{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The l razon social. */
	private String lRazonSocial;
	
	/** The l identificacion. */
	private String lIdentificacion;
	
	/** The l direccion. */
	private String lDireccion;
	
	/** The l email emison comprobante. */
	private String lEmailEmisonComprobante;
	
	/** The l clave certificado. */
	private String lClaveCertificado;
	
	/** The l fecha vigencia certificado. */
	private Date lFechaVigenciaCertificado;
	
	/** The l certificado firma. */
	private byte[] lCertificadoFirma;
	
	/** The l usuario certificado. */
	private String lUsuarioCertificado;
	
	/** The l departamento. */
	private String lDepartamento;
	
	/** The l regimen tributario. */
	private String lRegimenTributario;
	
	/** The l ciudad. */
	private String lCiudad;
	
	/** The l clave tecnica rango FE. */
	private String lClaveTecnicaRangoFE;
	
	/** The l identificador. */
	private String lIdentificador;
	
	/** The l descripcion. */
	private String lDescripcion;
	
	/** The l documento. */
	private String lDocumento;
	
	/** The l url. */
	private String lUrl;
	
	/** The l ruta certificado. */
	private String lRutaCertificado;
	
	/** The l formato certificado. */
	private String lFormatoCertificado;
	
	/** The l id suscriptor. */
	private Long lIdSuscriptor;
	
	/** The l logo. */
	private byte[] lLogo;
	
	/** The l ruta logo. */
	private String lRutaLogo;
	
	private String lCuentaRemitente;
	
	private String lCuentaCopia;
	
	private byte[] imagenReferencia;
	
	private String obligadoLlevarContabilidad;
	
	private String numeroContribuyenteEspecial;
	
	private String lEnvioAutomaticoSRI;
	
	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.dominio.entidades.IEntidadPersistible#getId()
	 */
	@GeneratedValue(generator = "FactEmpresa", strategy = GenerationType.SEQUENCE)
   	@SequenceGenerator(name = "FactEmpresa", allocationSize = 1, initialValue = 1, 
   	sequenceName = "SEC_FACT_EMPRESA")
   	@Id
   	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	/**
	 * Gets the l razon social.
	 *
	 * @return the l razon social
	 */
	public String getlRazonSocial() {
		return lRazonSocial;
	}

	/**
	 * Sets the l razon social.
	 *
	 * @param lRazonSocial the new l razon social
	 */
	public void setlRazonSocial(String lRazonSocial) {
		this.lRazonSocial = lRazonSocial;
	}

	/**
	 * Gets the l identificacion.
	 *
	 * @return the l identificacion
	 */
	@Column(name="NIT")
	public String getlIdentificacion() {
		return lIdentificacion;
	}

	/**
	 * Sets the l identificacion.
	 *
	 * @param lIdentificacion the new l identificacion
	 */
	public void setlIdentificacion(String lIdentificacion) {
		this.lIdentificacion = lIdentificacion;
	}

	/**
	 * Gets the l direccion.
	 *
	 * @return the l direccion
	 */
	public String getlDireccion() {
		return lDireccion;
	}

	/**
	 * Sets the l direccion.
	 *
	 * @param lDireccion the new l direccion
	 */
	public void setlDireccion(String lDireccion) {
		this.lDireccion = lDireccion;
	}

	/**
	 * Gets the l email emison comprobante.
	 *
	 * @return the l email emison comprobante
	 */
	public String getlEmailEmisonComprobante() {
		return lEmailEmisonComprobante;
	}

	/**
	 * Sets the l email emison comprobante.
	 *
	 * @param lEmailEmisonComprobante the new l email emison comprobante
	 */
	public void setlEmailEmisonComprobante(String lEmailEmisonComprobante) {
		this.lEmailEmisonComprobante = lEmailEmisonComprobante;
	}

	
	/**
	 * Gets the l clave certificado.
	 *
	 * @return the l clave certificado
	 */
	public String getlClaveCertificado() {
		return lClaveCertificado;
	}

	/**
	 * Sets the l clave certificado.
	 *
	 * @param lClaveCertificado the new l clave certificado
	 */
	public void setlClaveCertificado(String lClaveCertificado) {
		this.lClaveCertificado = lClaveCertificado;
	}

	/**
	 * Gets the l fecha vigencia certificado.
	 *
	 * @return the l fecha vigencia certificado
	 */
	@Temporal(TemporalType.DATE)
	public Date getlFechaVigenciaCertificado() {
		return lFechaVigenciaCertificado;
	}

	/**
	 * Sets the l fecha vigencia certificado.
	 *
	 * @param lFechaVigenciaCertificado the new l fecha vigencia certificado
	 */
	public void setlFechaVigenciaCertificado(Date lFechaVigenciaCertificado) {
		this.lFechaVigenciaCertificado = lFechaVigenciaCertificado;
	}

	/**
	 * Gets the l certificado firma.
	 *
	 * @return the l certificado firma
	 */
	@Lob
	public byte[] getlCertificadoFirma() {
		return lCertificadoFirma;
	}

	/**
	 * Sets the l certificado firma.
	 *
	 * @param lCertificadoFirma the new l certificado firma
	 */
	public void setlCertificadoFirma(byte[] lCertificadoFirma) {
		this.lCertificadoFirma = lCertificadoFirma;
	}

	/**
	 * Gets the l departamento.
	 *
	 * @return the l departamento
	 */
	public String getlDepartamento() {
		return lDepartamento;
	}

	/**
	 * Sets the l departamento.
	 *
	 * @param lDepartamento the new l departamento
	 */
	public void setlDepartamento(String lDepartamento) {
		this.lDepartamento = lDepartamento;
	}

	/**
	 * Gets the l regimen tributario.
	 *
	 * @return the l regimen tributario
	 */
	public String getlRegimenTributario() {
		return lRegimenTributario;
	}

	/**
	 * Sets the l regimen tributario.
	 *
	 * @param lRegimenTributario the new l regimen tributario
	 */
	public void setlRegimenTributario(String lRegimenTributario) {
		this.lRegimenTributario = lRegimenTributario;
	}

	/**
	 * Gets the l ciudad.
	 *
	 * @return the l ciudad
	 */
	public String getlCiudad() {
		return lCiudad;
	}

	/**
	 * Sets the l ciudad.
	 *
	 * @param lCiudad the new l ciudad
	 */
	public void setlCiudad(String lCiudad) {
		this.lCiudad = lCiudad;
	}

	/**
	 * Gets the l clave tecnica rango FE.
	 *
	 * @return the l clave tecnica rango FE
	 */
	public String getlClaveTecnicaRangoFE() {
		return lClaveTecnicaRangoFE;
	}

	/**
	 * Sets the l clave tecnica rango FE.
	 *
	 * @param lClaveTecnicaRangoFE the new l clave tecnica rango FE
	 */
	public void setlClaveTecnicaRangoFE(String lClaveTecnicaRangoFE) {
		this.lClaveTecnicaRangoFE = lClaveTecnicaRangoFE;
	}

	/**
	 * Gets the l identificador.
	 *
	 * @return the l identificador
	 */
	public String getlIdentificador() {
		return lIdentificador;
	}

	/**
	 * Sets the l identificador.
	 *
	 * @param lIdentificador the new l identificador
	 */
	public void setlIdentificador(String lIdentificador) {
		this.lIdentificador = lIdentificador;
	}

	/**
	 * Gets the l descripcion.
	 *
	 * @return the l descripcion
	 */
	public String getlDescripcion() {
		return lDescripcion;
	}

	/**
	 * Sets the l descripcion.
	 *
	 * @param lDescripcion the new l descripcion
	 */
	public void setlDescripcion(String lDescripcion) {
		this.lDescripcion = lDescripcion;
	}

	/**
	 * Gets the l documento.
	 *
	 * @return the l documento
	 */
	public String getlDocumento() {
		return lDocumento;
	}

	/**
	 * Sets the l documento.
	 *
	 * @param lDocumento the new l documento
	 */
	public void setlDocumento(String lDocumento) {
		this.lDocumento = lDocumento;
	}

	/**
	 * Gets the l url.
	 *
	 * @return the l url
	 */
	public String getlUrl() {
		return lUrl;
	}

	/**
	 * Sets the l url.
	 *
	 * @param lUrl the new l url
	 */
	public void setlUrl(String lUrl) {
		this.lUrl = lUrl;
	}

	/**
	 * Gets the l usuario certificado.
	 *
	 * @return the l usuario certificado
	 */
	public String getlUsuarioCertificado() {
		return lUsuarioCertificado;
	}

	/**
	 * Sets the l usuario certificado.
	 *
	 * @param lUsuarioCertificado the new l usuario certificado
	 */
	public void setlUsuarioCertificado(String lUsuarioCertificado) {
		this.lUsuarioCertificado = lUsuarioCertificado;
	}

	/**
	 * Gets the l ruta certificado.
	 *
	 * @return the l ruta certificado
	 */
	public String getlRutaCertificado() {
		return lRutaCertificado;
	}

	/**
	 * Sets the l ruta certificado.
	 *
	 * @param lRutaCertificado the new l ruta certificado
	 */
	public void setlRutaCertificado(String lRutaCertificado) {
		this.lRutaCertificado = lRutaCertificado;
	}

	/**
	 * Gets the l formato certificado.
	 *
	 * @return the l formato certificado
	 */
	public String getlFormatoCertificado() {
		return lFormatoCertificado;
	}

	/**
	 * Sets the l formato certificado.
	 *
	 * @param lFormatoCertificado the new l formato certificado
	 */
	public void setlFormatoCertificado(String lFormatoCertificado) {
		this.lFormatoCertificado = lFormatoCertificado;
	}

	/**
	 * Gets the l id suscriptor.
	 *
	 * @return the l id suscriptor
	 */
	public Long getlIdSuscriptor() {
		return lIdSuscriptor;
	}

	/**
	 * Sets the l id suscriptor.
	 *
	 * @param lIdSuscriptor the new l id suscriptor
	 */
	public void setlIdSuscriptor(Long lIdSuscriptor) {
		this.lIdSuscriptor = lIdSuscriptor;
	}

	/**
	 * Gets the l logo.
	 *
	 * @return the l logo
	 */
	@Lob
	public byte[] getlLogo() {
		return lLogo;
	}

	/**
	 * Sets the l logo.
	 *
	 * @param lLogo the new l logo
	 */
	public void setlLogo(byte[] lLogo) {
		this.lLogo = lLogo;
	}

	/**
	 * Gets the l ruta logo.
	 *
	 * @return the l ruta logo
	 */
	public String getlRutaLogo() {
		return lRutaLogo;
	}

	/**
	 * Sets the l ruta logo.
	 *
	 * @param lRutaLogo the new l ruta logo
	 */
	public void setlRutaLogo(String lRutaLogo) {
		this.lRutaLogo = lRutaLogo;
	}

	public String getlCuentaRemitente() {
		return lCuentaRemitente;
	}

	public void setlCuentaRemitente(String lCuentaRemitente) {
		this.lCuentaRemitente = lCuentaRemitente;
	}

	public String getlCuentaCopia() {
		return lCuentaCopia;
	}

	public void setlCuentaCopia(String lCuentaCopia) {
		this.lCuentaCopia = lCuentaCopia;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return lRazonSocial;
	}

	public byte[] getImagenReferencia() {
		return imagenReferencia;
	}

	public void setImagenReferencia(byte[] imagenReferencia) {
		this.imagenReferencia = imagenReferencia;
	}

	public String getObligadoLlevarContabilidad() {
		return obligadoLlevarContabilidad;
	}

	public void setObligadoLlevarContabilidad(String obligadoLlevarContabilidad) {
		this.obligadoLlevarContabilidad = obligadoLlevarContabilidad;
	}

	public String getNumeroContribuyenteEspecial() {
		return numeroContribuyenteEspecial;
	}

	public void setNumeroContribuyenteEspecial(String numeroContribuyenteEspecial) {
		this.numeroContribuyenteEspecial = numeroContribuyenteEspecial;
	}

	public String getlEnvioAutomaticoSRI() {
		return lEnvioAutomaticoSRI;
	}

	public void setlEnvioAutomaticoSRI(String lEnvioAutomaticoSRI) {
		this.lEnvioAutomaticoSRI = lEnvioAutomaticoSRI;
	}
	
	
	
}
