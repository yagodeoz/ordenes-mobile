package com.alimesa.ordenpedido.modelo.seguridad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.alimesa.ordenpedido.librerias.dominio.entidades.base.EntidadBaseAuditable;

@Entity
@Table(name = "USUARIOS")
public class Usuario extends EntidadBaseAuditable<Long> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant CLAVE_INICIAL_DEFAULT. */
	public static final String CLAVE_INICIAL_DEFAULT = "123456789";

	/** The clave. */
	private String clave;

	/** The usuario. */
	private String usuario;

	/** The correo. */
	private String correo;

	/** The es nuevo. */
	private String esNuevo;

	/** The l descripcion. */
	private String lDescripcion;

	/** The l nombres. */
	private String lNombres;

	/** The l apellidos. */
	private String lApellidos;

	/** The l esAdmin. */
	private String lEsAdmin;

	/** The l token recuperacion clave. */
	private String lTokenRecuperacionClave;

	/** The l fecha solicitud clave. */
	private Date lFechaSolicitudClave;

	/** The l fecha vencimiento. */
	private Date lFechaVencimiento;

	/** The pri usuarios roles. */
	private List<UsuariosRol> priUsuariosRoles;

	/** The roles asignados. */
	private String rolesAsignados;

	/** The imagen referencia. */
	private byte[] imagenReferencia;

	/** The rol principal. */
	private String rolPrincipal;

	private String rutaAvatar;

	/**
	 * Instantiates a new oms usuario.
	 */
	public Usuario() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.onix.modulo.librerias.dominio.entidades.IEntidadPersistible#getId()
	 */
	@GeneratedValue(generator = "secUsuario", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "secUsuario", allocationSize = 1, initialValue = 1, sequenceName = "SEC_USUARIO")
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
	 * Gets the clave.
	 *
	 * @return the clave
	 */
	@Column(name = "CLAVE")
	public String getClave() {
		return this.clave;
	}

	/**
	 * Sets the clave.
	 *
	 * @param clave the new clave
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}

	/**
	 * Gets the usuario.
	 *
	 * @return the usuario
	 */
	@Column(name = "USUARIO")
	public String getUsuario() {
		return this.usuario;
	}

	/**
	 * Sets the usuario.
	 *
	 * @param usuario the new usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Gets the pri usuarios roles.
	 *
	 * @return the pri usuarios roles
	 */
	@OneToMany(mappedBy = "priUsuario", fetch = FetchType.EAGER)
	public List<UsuariosRol> getPriUsuariosRoles() {
		return this.priUsuariosRoles;
	}

	/**
	 * Sets the pri usuarios roles.
	 *
	 * @param priUsuariosRoles the new pri usuarios roles
	 */
	public void setPriUsuariosRoles(List<UsuariosRol> priUsuariosRoles) {
		this.priUsuariosRoles = priUsuariosRoles;
	}

	/**
	 * Gets the roles asignados.
	 *
	 * @return the roles asignados
	 */
	@Transient
	public String getRolesAsignados() {
		return rolesAsignados;
	}

	/**
	 * Sets the roles asignados.
	 *
	 * @param rolesAsignados the new roles asignados
	 */
	public void setRolesAsignados(String rolesAsignados) {
		this.rolesAsignados = rolesAsignados;
	}

	/**
	 * Gets the correo.
	 *
	 * @return the correo
	 */
	@Column(name = "CORREO")
	public String getCorreo() {
		return correo;
	}

	/**
	 * Sets the correo.
	 *
	 * @param correo the new correo
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	/**
	 * Gets the es nuevo.
	 *
	 * @return the es nuevo
	 */
	public String getEsNuevo() {
		return esNuevo;
	}

	/**
	 * Sets the es nuevo.
	 *
	 * @param esNuevo the new es nuevo
	 */
	@Column(name = "ES_NUEVO")
	public void setEsNuevo(String esNuevo) {
		this.esNuevo = esNuevo;
	}

	/**
	 * Agregar rol.
	 *
	 * @param usuarioRol the usuario rol
	 */
	public void agregarRol(UsuariosRol usuarioRol) {
		if (priUsuariosRoles == null) {
			priUsuariosRoles = new ArrayList<>();
		}
		usuarioRol.setPriUsuario(this);
		priUsuariosRoles.add(usuarioRol);
	}

	/**
	 * Agregar rol.
	 *
	 * @param rol the rol
	 */
	public void agregarRol(Rol rol) {
		UsuariosRol usuarioRol = new UsuariosRol();
		usuarioRol.setEstado("A");
		usuarioRol.setFechaRegistro(new Date());
		usuarioRol.setObservacion("DESCRIPCION ASIGNACION: " + this.getUsuario() + " - " + rol.getRol());
		usuarioRol.setPriRole(rol);
		agregarRol(usuarioRol);
	}

	/**
	 * Gets the imagen referencia.
	 *
	 * @return the imagen referencia
	 */
	@Lob
	@Column(name = "IMAGEN_REFERENCIA")
	public byte[] getImagenReferencia() {
		return imagenReferencia;
	}

	/**
	 * Sets the imagen referencia.
	 *
	 * @param imagenReferencia the new imagen referencia
	 */
	public void setImagenReferencia(byte[] imagenReferencia) {
		this.imagenReferencia = imagenReferencia;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return usuario;
	}

	/**
	 * Gets the l token recuperacion clave.
	 *
	 * @return the l token recuperacion clave
	 */
	@Column(name = "TOKEN_RECUPERACION_CLAVE")
	public String getlTokenRecuperacionClave() {
		return lTokenRecuperacionClave;
	}

	/**
	 * Sets the l token recuperacion clave.
	 *
	 * @param lTokenRecuperacionClave the new l token recuperacion clave
	 */
	public void setlTokenRecuperacionClave(String lTokenRecuperacionClave) {
		this.lTokenRecuperacionClave = lTokenRecuperacionClave;
	}

	/**
	 * Gets the l fecha solicitud clave.
	 *
	 * @return the l fecha solicitud clave
	 */
	@Column(name = "FECHA_SOLICTUD_CLAVE")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getlFechaSolicitudClave() {
		return lFechaSolicitudClave;
	}

	/**
	 * Sets the l fecha solicitud clave.
	 *
	 * @param lFechaSolicitudClave the new l fecha solicitud clave
	 */
	public void setlFechaSolicitudClave(Date lFechaSolicitudClave) {
		this.lFechaSolicitudClave = lFechaSolicitudClave;
	}

	/**
	 * Gets the l fecha vencimiento.
	 *
	 * @return the l fecha vencimiento
	 */
	@Column(name = "FECHA_VENCIMIENTO")
	@Temporal(TemporalType.DATE)
	public Date getlFechaVencimiento() {
		return lFechaVencimiento;
	}

	/**
	 * Sets the l fecha vencimiento.
	 *
	 * @param lFechaVencimiento the new l fecha vencimiento
	 */
	public void setlFechaVencimiento(Date lFechaVencimiento) {
		this.lFechaVencimiento = lFechaVencimiento;
	}

	/**
	 * Gets the rol principal.
	 *
	 * @return the rol principal
	 */
	public String getRolPrincipal() {
		return rolPrincipal;
	}

	/**
	 * Sets the rol principal.
	 *
	 * @param rolPrincipal the new rol principal
	 */
	@Transient
	public void setRolPrincipal(String rolPrincipal) {
		this.rolPrincipal = rolPrincipal;
	}

	/**
	 * Gets the l descripcion.
	 *
	 * @return the l descripcion
	 */
	@Column(name = "DESCRIPCION")
	public String getlDescripcion() {
		return lDescripcion;
	}

	/**
	 * Sets the l descripcion.
	 *
	 * @param lDescripcion the new l descripcion
	 */
	public void setlDescripcion(String lDescripcion) {
		this.lDescripcion = lDescripcion;
	}

	public String getRutaAvatar() {
		return rutaAvatar;
	}

	public void setRutaAvatar(String rutaAvatar) {
		this.rutaAvatar = rutaAvatar;
	}

	public String getlNombres() {
		return lNombres;
	}

	public void setlNombres(String lNombres) {
		this.lNombres = lNombres;
	}

	public String getlApellidos() {
		return lApellidos;
	}

	public void setlApellidos(String lApellidos) {
		this.lApellidos = lApellidos;
	}

	public String getlEsAdmin() {
		return lEsAdmin;
	}

	public void setlEsAdmin(String lEsAdmin) {
		this.lEsAdmin = lEsAdmin;
	}
	
	

}
