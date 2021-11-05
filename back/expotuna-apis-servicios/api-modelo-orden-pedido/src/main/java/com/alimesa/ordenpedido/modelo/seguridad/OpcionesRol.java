package com.alimesa.ordenpedido.modelo.seguridad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.alimesa.ordenpedido.librerias.dominio.entidades.base.EntidadBaseAuditable;

//PRI
@Entity
@Table(name = "OPCIONES_ROLES")
@NamedNativeQueries({
		@NamedNativeQuery(name = "OPCIONES_ROLES.OPCIONES_PADRE", query = "select * " + " from OPCIONES d "
				+ " where exists (select 'X' " + "        from OPCIONES_ROLES s " + "       where exists (select 'X' "
				+ "                from USUARIOS_ROLES t " + "               where t.id_usuario = ? "
				+ "                 and t.estado = 'A' " + "                 and t.id_rol = s.id_rol) "
				+ "         and s.estado = 'A' " + "         and s.id_opcion = d.id) " + " and d.modulo_padre is null "
				+ " and d.estado = 'A'" + " and d.orientacion = ? order by d.orden asc", resultClass = Opcion.class),

		@NamedNativeQuery(name = "OPCIONES_ROLES.OPCIONES_PADRE_ROL", query = "select * " + " from OPCIONES d "
				+ " where exists (select 'X' " + "        from OPCIONES_ROLES s "
				+ "       where s.id_rol = :idRol    and s.estado = 'A' " + "         and s.id_opcion = d.id) "
				+ " and d.modulo_padre is null " + " and d.estado = 'A'"
				+ " and d.orientacion = :orientacion order by d.orden asc", resultClass = Opcion.class) })
public class OpcionesRol extends EntidadBaseAuditable<Long> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	private Long id;

	/** The id rol. */
	private Rol idRol;

	/** The pri opcione. */
	private Opcion priOpcione;

	/**
	 * Instantiates a new oms opciones role.
	 */
	public OpcionesRol() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.onix.modulo.librerias.dominio.entidades.IEntidadPersistible#getId()
	 */
	@GeneratedValue(generator = "secOpcionesRol", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "secOpcionesRol", allocationSize = 1, initialValue = 1, sequenceName = "SEC_OPCIONESROL")
	@Id
	@Column(name = "ID")
	public Long getId() {
		return this.id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.onix.modulo.librerias.dominio.entidades.base.EntidadBase#setId(java.io.
	 * Serializable)
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the id rol.
	 *
	 * @return the id rol
	 */
	@ManyToOne
	@JoinColumn(name = "ID_ROL")
	public Rol getIdRol() {
		return idRol;
	}

	/**
	 * Sets the id rol.
	 *
	 * @param idRol the new id rol
	 */
	public void setIdRol(Rol idRol) {
		this.idRol = idRol;
	}

	/**
	 * Gets the pri opcione.
	 *
	 * @return the pri opcione
	 */
	@ManyToOne
	@JoinColumn(name = "ID_OPCION")
	public Opcion getPriOpcione() {
		return this.priOpcione;
	}

	/**
	 * Sets the pri opcione.
	 *
	 * @param priOpcione the new pri opcione
	 */
	public void setPriOpcione(Opcion priOpcione) {
		this.priOpcione = priOpcione;
	}
	
}