package com.alimesa.ordenpedido.modelo.seguridad;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.alimesa.ordenpedido.librerias.dominio.entidades.base.EntidadBaseAuditable;

@Entity
@Table(name = "OPCIONES")
public class Opcion extends EntidadBaseAuditable<Long> implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String accion;
	private String descripcion;
	private com.alimesa.ordenpedido.modelo.seguridad.Opcion moduloPadre;
	private String opcion;
	private Long orden;
	private String orientacion;
	private String rutaimagen;
	private String tipo;
	private List<OpcionesRol> priOpcionesRoles;
	private List<com.alimesa.ordenpedido.modelo.seguridad.Opcion> opcionesHijas;

	@GeneratedValue(generator = "secOpciones", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "secOpciones", allocationSize = 1, initialValue = 1, sequenceName = "SEC_OPCIONES")
	@Id
	@Column(name = "ID")
	public Long getId() {
		/* 76 */ return this.id;
	}

	public void setId(Long id) {
		/* 83 */ this.id = id;
	}

	@Column(name = "ACCION")
	public String getAccion() {
		/* 94 */ return this.accion;
	}

	public void setAccion(String accion) {
		/* 103 */ this.accion = accion;
	}

	@Column(name = "DESCRIPCION")
	public String getDescripcion() {
		/* 114 */ return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		/* 123 */ this.descripcion = descripcion;
	}

	@ManyToOne
	@JoinColumn(name = "MODULO_PADRE")
	public com.alimesa.ordenpedido.modelo.seguridad.Opcion getModuloPadre() {
		/* 134 */ return this.moduloPadre;
	}

	public void setModuloPadre(com.alimesa.ordenpedido.modelo.seguridad.Opcion moduloPadre) {
		/* 143 */ this.moduloPadre = moduloPadre;
	}

	@Column(name = "OPCION")
	public String getOpcion() {
		/* 153 */ return this.opcion;
	}

	public void setOpcion(String opcion) {
		/* 162 */ this.opcion = opcion;
	}

	@Column(name = "ORDEN")
	public Long getOrden() {
		/* 173 */ return this.orden;
	}

	public void setOrden(Long orden) {
		/* 182 */ this.orden = orden;
	}

	@Column(name = "ORIENTACION")
	public String getOrientacion() {
		/* 193 */ return this.orientacion;
	}

	public void setOrientacion(String orientacion) {
		/* 202 */ this.orientacion = orientacion;
	}

	@Column(name = "RUTAIMAGEN")
	public String getRutaimagen() {
		/* 213 */ return this.rutaimagen;
	}

	public void setRutaimagen(String rutaimagen) {
		/* 222 */ this.rutaimagen = rutaimagen;
	}

	@Column(name = "TIPO")
	public String getTipo() {
		/* 233 */ return this.tipo;
	}

	public void setTipo(String tipo) {
		/* 242 */ this.tipo = tipo;
	}

	@OneToMany(mappedBy = "priOpcione")
	public List<OpcionesRol> getPriOpcionesRoles() {
		/* 253 */ return this.priOpcionesRoles;
	}

	public void setPriOpcionesRoles(List<OpcionesRol> priOpcionesRoles) {
		/* 262 */ this.priOpcionesRoles = priOpcionesRoles;
	}

	public OpcionesRol addPriOpcionesRole(OpcionesRol priOpcionesRole) {
		/* 272 */ getPriOpcionesRoles().add(priOpcionesRole);
		/* 273 */ priOpcionesRole.setPriOpcione(this);

		/* 275 */ return priOpcionesRole;
	}

	public OpcionesRol removePriOpcionesRole(OpcionesRol priOpcionesRole) {
		/* 285 */ getPriOpcionesRoles().remove(priOpcionesRole);
		/* 286 */ priOpcionesRole.setPriOpcione(null);

		/* 288 */ return priOpcionesRole;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "moduloPadre")
	@OrderBy("orden ASC")
	public List<com.alimesa.ordenpedido.modelo.seguridad.Opcion> getOpcionesHijas() {
		/* 299 */ return this.opcionesHijas;
	}

	public void setOpcionesHijas(List<com.alimesa.ordenpedido.modelo.seguridad.Opcion> opcionesHijas) {
		/* 308 */ this.opcionesHijas = opcionesHijas;
	}

	public String toString() {
		/* 317 */ return this.descripcion;
	}
}

/*
 * Location:
 * D:\apps\api-rest-ordenpedido\1.0\api-rest-ordenpedido-1.0.war!\WEB-INF\
 * classes\com\alimesa\ordenpedido\modelo\seguridad\Opcion.class Java compiler
 * version: 11 (55.0) JD-Core Version: 1.1.3
 */