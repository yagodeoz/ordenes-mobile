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
@Table(name = "USUARIOS_ROLES")
public class UsuariosRol extends EntidadBaseAuditable<Long> implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String descripcion;
	private Rol priRole;
	private Usuario priUsuario;

	@GeneratedValue(generator = "secUsuarioRol", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "secUsuarioRol", allocationSize = 1, initialValue = 1, sequenceName = "SEC_USUARIOROL")
	@Id
	@Column(name = "ID")
	public Long getId() {
		/* 51 */ return this.id;
	}

	public void setId(Long id) {
		/* 58 */ this.id = id;
	}

	@Column(name = "DESCRIPCION")
	public String getDescripcion() {
		/* 69 */ return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		/* 78 */ this.descripcion = descripcion;
	}

	@ManyToOne
	@JoinColumn(name = "ID_ROL")
	public Rol getPriRole() {
		/* 89 */ return this.priRole;
	}

	public void setPriRole(Rol priRole) {
		/* 98 */ this.priRole = priRole;
	}

	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	public Usuario getPriUsuario() {
		/* 109 */ return this.priUsuario;
	}

	public void setPriUsuario(Usuario priUsuario) {
		/* 118 */ this.priUsuario = priUsuario;
	}
}

/*
 * Location:
 * D:\apps\api-rest-ordenpedido\1.0\api-rest-ordenpedido-1.0.war!\WEB-INF\
 * classes\com\alimesa\ordenpedido\modelo\seguridad\UsuariosRol.class Java
 * compiler version: 11 (55.0) JD-Core Version: 1.1.3
 */