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
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The pri opcione. */
	private Opcion priOpcione;

	/** The pri usuario. */
	private Usuario priUsuario;

	/**
	 * Instantiates a new oms accesos directo.
	 */
	public AccesosDirecto() {
	}

	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.dominio.entidades.IEntidadPersistible#getId()
	 */
	@GeneratedValue(generator = "secIDAcceso", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "secIDAcceso", allocationSize = 1, initialValue = 1, sequenceName = "SEC_IDACCESO")
	@Id
	@Column(name = "ID")
	public Long getId() {
		return this.id;
	}

	/**
	 * Gets the pri opcione.
	 *
	 * @return the pri opcione
	 */
	@ManyToOne
	@JoinColumn(name = "ID_OPCION")
	public Opcion getPriOpcione() {
		return priOpcione;
	}

	/**
	 * Sets the pri opcione.
	 *
	 * @param priOpcione the new pri opcione
	 */
	public void setPriOpcione(Opcion priOpcione) {
		this.priOpcione = priOpcione;
	}

	/**
	 * Gets the pri usuario.
	 *
	 * @return the pri usuario
	 */
	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	public Usuario getPriUsuario() {
		return priUsuario;
	}

	/**
	 * Sets the pri usuario.
	 *
	 * @param priUsuario the new pri usuario
	 */
	public void setPriUsuario(Usuario priUsuario) {
		this.priUsuario = priUsuario;
	}

}