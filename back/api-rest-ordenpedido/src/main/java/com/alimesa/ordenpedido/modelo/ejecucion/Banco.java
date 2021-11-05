package com.alimesa.ordenpedido.modelo.ejecucion;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.alimesa.ordenpedido.librerias.dominio.entidades.base.EntidadBaseAuditable;

@Entity
@Table(name = "BANCO")
public class Banco extends EntidadBaseAuditable<Long> implements Serializable {
	private static final long serialVersionUID = 1L;
	private String codigoBanco;
	private String nombreBanco;

	@SequenceGenerator(sequenceName = "SEC_DETALLE_PAGO", name = "secBanco")
	@GeneratedValue(generator = "secBanco", strategy = GenerationType.SEQUENCE)
	@Id
	public Long getId() {
		/* 29 */ return (Long) this.id;
	}

	public String getCodigoBanco() {
		/* 33 */ return this.codigoBanco;
	}

	public void setCodigoBanco(String codigoBanco) {
		/* 37 */ this.codigoBanco = codigoBanco;
	}

	public String getNombreBanco() {
		/* 41 */ return this.nombreBanco;
	}

	public void setNombreBanco(String nombreBanco) {
		/* 45 */ this.nombreBanco = nombreBanco;
	}
}

/*
 * Location:
 * D:\apps\api-rest-ordenpedido\1.0\api-rest-ordenpedido-1.0.war!\WEB-INF\
 * classes\com\alimesa\ordenpedido\modelo\ejecucion\Banco.class Java compiler
 * version: 11 (55.0) JD-Core Version: 1.1.3
 */