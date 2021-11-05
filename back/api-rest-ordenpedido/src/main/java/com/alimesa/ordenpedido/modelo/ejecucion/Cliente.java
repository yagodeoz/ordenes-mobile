package com.alimesa.ordenpedido.modelo.ejecucion;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.alimesa.ordenpedido.librerias.dominio.entidades.base.EntidadBaseAuditable;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "CLIENTES")
public class Cliente extends EntidadBaseAuditable<Long> implements Serializable {
	private static final long serialVersionUID = 1L;
	private String tipoIdentificacionComprador;
	private String razonSocialComprador;
	private String identificacionComprador;
	private String direccionComprador;
	private String emailsComprador;
	private String celularComprador;
	private Double cupo;
	private String codigoCiudad;
	private String formaPago;
	private String codigoListaPrecio;
	private String codigoCliente;
	private Empresa lEmpresa;

	@GeneratedValue(generator = "FactCliente", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "FactCliente", allocationSize = 1, initialValue = 1, sequenceName = "SEC_FACT_CLIENTE")
	@Id
	@Column(name = "ID")
	public Long getId() {
		/* 45 */ return (Long) this.id;
	}

	public String getTipoIdentificacionComprador() {
		/* 50 */ return this.tipoIdentificacionComprador;
	}

	public void setTipoIdentificacionComprador(String tipoIdentificacionComprador) {
		/* 53 */ this.tipoIdentificacionComprador = tipoIdentificacionComprador;
	}

	public String getRazonSocialComprador() {
		/* 56 */ return this.razonSocialComprador;
	}

	public void setRazonSocialComprador(String razonSocialComprador) {
		/* 59 */ this.razonSocialComprador = razonSocialComprador;
	}

	public String getIdentificacionComprador() {
		/* 62 */ return this.identificacionComprador;
	}

	public void setIdentificacionComprador(String identificacionComprador) {
		/* 65 */ this.identificacionComprador = identificacionComprador;
	}

	public String getDireccionComprador() {
		/* 68 */ return this.direccionComprador;
	}

	public void setDireccionComprador(String direccionComprador) {
		/* 71 */ this.direccionComprador = direccionComprador;
	}

	public String getEmailsComprador() {
		/* 74 */ return this.emailsComprador;
	}

	public void setEmailsComprador(String emailsComprador) {
		/* 77 */ this.emailsComprador = emailsComprador;
	}

	public String getCelularComprador() {
		/* 80 */ return this.celularComprador;
	}

	public void setCelularComprador(String celularComprador) {
		/* 83 */ this.celularComprador = celularComprador;
	}

	@ManyToOne
	@JoinColumn(name = "ID_EMPRESA")
	@JsonBackReference
	public Empresa getlEmpresa() {
		/* 90 */ return this.lEmpresa;
	}

	public void setlEmpresa(Empresa lEmpresa) {
		/* 93 */ this.lEmpresa = lEmpresa;
	}

	public Double getCupo() {
		/* 98 */ return this.cupo;
	}

	public void setCupo(Double cupo) {
		/* 103 */ this.cupo = cupo;
	}

	public String getCodigoCiudad() {
		/* 108 */ return this.codigoCiudad;
	}

	public void setCodigoCiudad(String codigoCiudad) {
		/* 113 */ this.codigoCiudad = codigoCiudad;
	}

	public String getFormaPago() {
		/* 118 */ return this.formaPago;
	}

	public void setFormaPago(String formaPago) {
		/* 123 */ this.formaPago = formaPago;
	}

	public String getCodigoListaPrecio() {
		/* 128 */ return this.codigoListaPrecio;
	}

	public void setCodigoListaPrecio(String codigoListaPrecio) {
		/* 133 */ this.codigoListaPrecio = codigoListaPrecio;
	}

	public String getCodigoCliente() {
		/* 138 */ return this.codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		/* 143 */ this.codigoCliente = codigoCliente;
	}
}

/*
 * Location:
 * D:\apps\api-rest-ordenpedido\1.0\api-rest-ordenpedido-1.0.war!\WEB-INF\
 * classes\com\alimesa\ordenpedido\modelo\ejecucion\Cliente.class Java compiler
 * version: 11 (55.0) JD-Core Version: 1.1.3
 */