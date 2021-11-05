
package com.alimesa.ordenpedido.modelo.seguridad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.alimesa.ordenpedido.librerias.dominio.entidades.base.EntidadBaseAuditable;

@Entity
@Table(name = "ICONOS")
public class Icono extends EntidadBaseAuditable<Long> implements Serializable {
	private static final long serialVersionUID = 1L;
	private String lIconoFontPrimefaces;

	@GeneratedValue(generator = "secIcono", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "secIcono", allocationSize = 1, initialValue = 1, sequenceName = "SEC_ICONOS")
	@Id
	@Column(name = "ID")
	public Long getId() {
		/* 35 */ return (Long) this.id;
	}

	public String getlIconoFontPrimefaces() {
		/* 44 */ return this.lIconoFontPrimefaces;
	}

	public void setlIconoFontPrimefaces(String lIconoFontPrimefaces) {
		/* 53 */ this.lIconoFontPrimefaces = lIconoFontPrimefaces;
	}
}

/*
 * Location:
 * D:\apps\api-rest-ordenpedido\1.0\api-rest-ordenpedido-1.0.war!\WEB-INF\
 * classes\com\alimesa\ordenpedido\modelo\seguridad\Icono.class Java compiler
 * version: 11 (55.0) JD-Core Version: 1.1.3
 */