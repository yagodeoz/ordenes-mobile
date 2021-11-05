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
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "USUARIOS")
public class Usuario extends EntidadBaseAuditable<Long> implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String CLAVE_INICIAL_DEFAULT = "123456789";
	private String clave;
	private String usuario;
	private String correo;
	private String esNuevo;
	private String lDescripcion;
	private String lNombres;
	private String lApellidos;
	private String lEsAdmin;
	private String lTokenRecuperacionClave;
	private Date lFechaSolicitudClave;
	private Date lFechaVencimiento;
	private List<UsuariosRol> priUsuariosRoles;
	private String rolesAsignados;
	private byte[] imagenReferencia;
	private String rolPrincipal;
	private String rutaAvatar;

	@GeneratedValue(generator = "secUsuario", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "secUsuario", allocationSize = 1, initialValue = 1, sequenceName = "SEC_USUARIO")
	@Id
	@Column(name = "ID")
	public Long getId() {
		/* 98 */ return (Long) this.id;
	}

	public void setId(Long id) {
		/* 109 */ this.id = id;
	}

	@Column(name = "CLAVE")
	public String getClave() {
		/* 119 */ return this.clave;
	}

	public void setClave(String clave) {
		/* 128 */ this.clave = clave;
	}

	@Column(name = "USUARIO")
	public String getUsuario() {
		/* 138 */ return this.usuario;
	}

	public void setUsuario(String usuario) {
		/* 147 */ this.usuario = usuario;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "priUsuario", fetch = FetchType.EAGER)
	public List<UsuariosRol> getPriUsuariosRoles() {
		/* 158 */ return this.priUsuariosRoles;
	}

	public void setPriUsuariosRoles(List<UsuariosRol> priUsuariosRoles) {
		/* 167 */ this.priUsuariosRoles = priUsuariosRoles;
	}

	@Transient
	public String getRolesAsignados() {
		/* 177 */ return this.rolesAsignados;
	}

	public void setRolesAsignados(String rolesAsignados) {
		/* 186 */ this.rolesAsignados = rolesAsignados;
	}

	@Column(name = "CORREO")
	public String getCorreo() {
		/* 196 */ return this.correo;
	}

	public void setCorreo(String correo) {
		/* 205 */ this.correo = correo;
	}

	@Column(name = "esnuevo")
	public String getEsNuevo() {
		/* 215 */ return this.esNuevo;
	}

	public void setEsNuevo(String esNuevo) {
		/* 224 */ this.esNuevo = esNuevo;
	}

	public void agregarRol(UsuariosRol usuarioRol) {
		/* 233 */ if (this.priUsuariosRoles == null) {
			/* 234 */ this.priUsuariosRoles = new ArrayList<>();
		}
		/* 236 */ usuarioRol.setPriUsuario(this);
		/* 237 */ this.priUsuariosRoles.add(usuarioRol);
	}

	public void agregarRol(Rol rol) {
		/* 246 */ UsuariosRol usuarioRol = new UsuariosRol();
		/* 247 */ usuarioRol.setEstado("A");
		/* 248 */ usuarioRol.setFechaRegistro(new Date());
		/* 249 */ usuarioRol.setObservacion("DESCRIPCION ASIGNACION: " + getUsuario() + " - " + rol.getRol());
		/* 250 */ usuarioRol.setPriRole(rol);
		/* 251 */ agregarRol(usuarioRol);
	}

	@JsonIgnore
	@Lob
	@Column(name = "IMAGEN_REFERENCIA")
	public byte[] getImagenReferencia() {
		/* 263 */ return this.imagenReferencia;
	}

	public void setImagenReferencia(byte[] imagenReferencia) {
		/* 272 */ this.imagenReferencia = imagenReferencia;
	}

	public String toString() {
		/* 282 */ return this.usuario;
	}

	@Column(name = "TOKEN_RECUPERACION_CLAVE")
	public String getlTokenRecuperacionClave() {
		/* 292 */ return this.lTokenRecuperacionClave;
	}

	public void setlTokenRecuperacionClave(String lTokenRecuperacionClave) {
		/* 301 */ this.lTokenRecuperacionClave = lTokenRecuperacionClave;
	}

	@Column(name = "FECHA_SOLICTUD_CLAVE")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getlFechaSolicitudClave() {
		/* 312 */ return this.lFechaSolicitudClave;
	}

	public void setlFechaSolicitudClave(Date lFechaSolicitudClave) {
		/* 321 */ this.lFechaSolicitudClave = lFechaSolicitudClave;
	}

	@Column(name = "FECHA_VENCIMIENTO")
	@Temporal(TemporalType.DATE)
	public Date getlFechaVencimiento() {
		/* 332 */ return this.lFechaVencimiento;
	}

	public void setlFechaVencimiento(Date lFechaVencimiento) {
		/* 341 */ this.lFechaVencimiento = lFechaVencimiento;
	}

	public String getRolPrincipal() {
		/* 350 */ return this.rolPrincipal;
	}

	@Transient
	public void setRolPrincipal(String rolPrincipal) {
		/* 360 */ this.rolPrincipal = rolPrincipal;
	}

	@Column(name = "DESCRIPCION")
	public String getlDescripcion() {
		/* 370 */ return this.lDescripcion;
	}

	public void setlDescripcion(String lDescripcion) {
		/* 379 */ this.lDescripcion = lDescripcion;
	}

	public String getRutaAvatar() {
		/* 383 */ return this.rutaAvatar;
	}

	public void setRutaAvatar(String rutaAvatar) {
		/* 387 */ this.rutaAvatar = rutaAvatar;
	}

	public String getlNombres() {
		/* 391 */ return this.lNombres;
	}

	public void setlNombres(String lNombres) {
		/* 395 */ this.lNombres = lNombres;
	}

	public String getlApellidos() {
		/* 399 */ return this.lApellidos;
	}

	public void setlApellidos(String lApellidos) {
		/* 403 */ this.lApellidos = lApellidos;
	}

	public String getlEsAdmin() {
		/* 407 */ return this.lEsAdmin;
	}

	public void setlEsAdmin(String lEsAdmin) {
		/* 411 */ this.lEsAdmin = lEsAdmin;
	}
}

/*
 * Location:
 * D:\apps\api-rest-ordenpedido\1.0\api-rest-ordenpedido-1.0.war!\WEB-INF\
 * classes\com\alimesa\ordenpedido\modelo\seguridad\Usuario.class Java compiler
 * version: 11 (55.0) JD-Core Version: 1.1.3
 */