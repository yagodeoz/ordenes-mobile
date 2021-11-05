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
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	private Long id;

	/** The accion. */
	private String accion;

	/** The descripcion. */
	private String descripcion;

	/** The modulo padre. */
	private Opcion moduloPadre;

	/** The opcion. */
	private String opcion;

	/** The orden. */
	private Long orden;

	/** The orientacion. */
	private String orientacion;

	/** The rutaimagen. */
	private String rutaimagen;

	/** The tipo. */
	private String tipo;

	/** The pri opciones roles. */
	private List<OpcionesRol> priOpcionesRoles;

	/** The opciones hijas. */
	private List<Opcion> opcionesHijas;

	/**
	 * Instantiates a new oms opcione.
	 */
	public Opcion() {
	}

	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.dominio.entidades.IEntidadPersistible#getId()
	 */
	@GeneratedValue(generator = "secOpciones", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "secOpciones", allocationSize = 1, initialValue = 1, sequenceName = "SEC_OPCIONES")
	@Id
	@Column(name = "ID")
	public Long getId() {
		return this.id;
	}

	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.dominio.entidades.base.EntidadBase#setId(java.io.Serializable)
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the accion.
	 *
	 * @return the accion
	 */
	@Column(name = "ACCION")
	/**/
	public String getAccion() {
		return this.accion;
	}

	/**
	 * Sets the accion.
	 *
	 * @param accion the new accion
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	@Column(name = "DESCRIPCION")
	/**/
	public String getDescripcion() {
		return this.descripcion;
	}

	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion the new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Gets the modulo padre.
	 *
	 * @return the modulo padre
	 */
	@ManyToOne
	@JoinColumn(name = "MODULO_PADRE")
	public Opcion getModuloPadre() {
		return moduloPadre;
	}

	/**
	 * Sets the modulo padre.
	 *
	 * @param moduloPadre the new modulo padre
	 */
	public void setModuloPadre(Opcion moduloPadre) {
		this.moduloPadre = moduloPadre;
	}

	/**
	 * Gets the opcion.
	 *
	 * @return the opcion
	 */
	@Column(name = "OPCION")
	public String getOpcion() {
		return this.opcion;
	}

	/**
	 * Sets the opcion.
	 *
	 * @param opcion the new opcion
	 */
	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}

	/**
	 * Gets the orden.
	 *
	 * @return the orden
	 */
	@Column(name = "ORDEN")
	/**/
	public Long getOrden() {
		return this.orden;
	}

	/**
	 * Sets the orden.
	 *
	 * @param orden the new orden
	 */
	public void setOrden(Long orden) {
		this.orden = orden;
	}

	/**
	 * Gets the orientacion.
	 *
	 * @return the orientacion
	 */
	@Column(name = "ORIENTACION")
	/**/
	public String getOrientacion() {
		return this.orientacion;
	}

	/**
	 * Sets the orientacion.
	 *
	 * @param orientacion the new orientacion
	 */
	public void setOrientacion(String orientacion) {
		this.orientacion = orientacion;
	}

	/**
	 * Gets the rutaimagen.
	 *
	 * @return the rutaimagen
	 */
	@Column(name = "RUTAIMAGEN")
	/**/
	public String getRutaimagen() {
		return this.rutaimagen;
	}

	/**
	 * Sets the rutaimagen.
	 *
	 * @param rutaimagen the new rutaimagen
	 */
	public void setRutaimagen(String rutaimagen) {
		this.rutaimagen = rutaimagen;
	}

	/**
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	@Column(name = "TIPO")
	/**/
	public String getTipo() {
		return this.tipo;
	}

	/**
	 * Sets the tipo.
	 *
	 * @param tipo the new tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Gets the pri opciones roles.
	 *
	 * @return the pri opciones roles
	 */
	// bi-directional many-to-one association to PriOpcionesRole
	@OneToMany(mappedBy = "priOpcione")
	public List<OpcionesRol> getPriOpcionesRoles() {
		return this.priOpcionesRoles;
	}

	/**
	 * Sets the pri opciones roles.
	 *
	 * @param priOpcionesRoles the new pri opciones roles
	 */
	public void setPriOpcionesRoles(List<OpcionesRol> priOpcionesRoles) {
		this.priOpcionesRoles = priOpcionesRoles;
	}

	/**
	 * Adds the pri opciones role.
	 *
	 * @param priOpcionesRole the pri opciones role
	 * @return the oms opciones role
	 */
	public OpcionesRol addPriOpcionesRole(OpcionesRol priOpcionesRole) {
		getPriOpcionesRoles().add(priOpcionesRole);
		priOpcionesRole.setPriOpcione(this);

		return priOpcionesRole;
	}

	/**
	 * Removes the pri opciones role.
	 *
	 * @param priOpcionesRole the pri opciones role
	 * @return the oms opciones role
	 */
	public OpcionesRol removePriOpcionesRole(OpcionesRol priOpcionesRole) {
		getPriOpcionesRoles().remove(priOpcionesRole);
		priOpcionesRole.setPriOpcione(null);

		return priOpcionesRole;
	}

	/**
	 * Gets the opciones hijas.
	 *
	 * @return the opciones hijas
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "moduloPadre")
	@OrderBy("orden ASC")
	public List<Opcion> getOpcionesHijas() {
		return opcionesHijas;
	}

	/**
	 * Sets the opciones hijas.
	 *
	 * @param opcionesHijas the new opciones hijas
	 */
	public void setOpcionesHijas(List<Opcion> opcionesHijas) {
		this.opcionesHijas = opcionesHijas;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return descripcion;
	}
	
}