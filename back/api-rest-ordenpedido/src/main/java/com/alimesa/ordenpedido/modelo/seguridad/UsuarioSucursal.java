
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
import com.alimesa.ordenpedido.modelo.ejecucion.Sucursal;

@Entity
@Table(name = "USUARIOS_SUCURSALES")
public class UsuarioSucursal extends EntidadBaseAuditable<Long> implements Serializable {
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private Sucursal sucursal;

	@GeneratedValue(generator = "secUsuarioSUC", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "secUsuarioSUC", allocationSize = 1, initialValue = 1, sequenceName = "SEC_USUARIO_SUC")
	@Id
	@Column(name = "ID")
	public Long getId() {
		/* 36 */ return (Long) this.id;
	}

	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	public Usuario getUsuario() {
		/* 42 */ return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		/* 47 */ this.usuario = usuario;
	}

	@ManyToOne
	@JoinColumn(name = "ID_SUCURSAL")
	public Sucursal getSucursal() {
		/* 53 */ return this.sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		/* 57 */ this.sucursal = sucursal;
	}
}

/*
 * Location:
 * D:\apps\api-rest-ordenpedido\1.0\api-rest-ordenpedido-1.0.war!\WEB-INF\
 * classes\com\alimesa\ordenpedido\modelo\seguridad\UsuarioSucursal.class Java
 * compiler version: 11 (55.0) JD-Core Version: 1.1.3
 */