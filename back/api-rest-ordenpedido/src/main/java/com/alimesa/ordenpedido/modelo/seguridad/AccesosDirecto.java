package com.alimesa.ordenpedido.modelo.seguridad;

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

@Entity
@Table(name = "ACCESOS_DIRECTOS")
public class AccesosDirecto extends EntidadBaseAuditable<Long> implements Serializable {
	private static final long serialVersionUID = 1L;
	private Opcion priOpcione;
	private Usuario priUsuario;

	@GeneratedValue(generator = "secIDAcceso", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "secIDAcceso", allocationSize = 1, initialValue = 1, sequenceName = "SEC_IDACCESO")
	@Id
	@Column(name = "ID")
	public Long getId() {
		/* 44 */ return (Long) this.id;
	}

	@ManyToOne
	@JoinColumn(name = "ID_OPCION")
	public Opcion getPriOpcione() {
		/* 55 */ return this.priOpcione;
	}

	public void setPriOpcione(Opcion priOpcione) {
		/* 64 */ this.priOpcione = priOpcione;
	}

	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	public Usuario getPriUsuario() {
		/* 75 */ return this.priUsuario;
	}

	public void setPriUsuario(Usuario priUsuario) {
		/* 84 */ this.priUsuario = priUsuario;
	}
}

/*
 * Location:
 * D:\apps\api-rest-ordenpedido\1.0\api-rest-ordenpedido-1.0.war!\WEB-INF\
 * classes\com\alimesa\ordenpedido\modelo\seguridad\AccesosDirecto.class Java
 * compiler version: 11 (55.0) JD-Core Version: 1.1.3
 */