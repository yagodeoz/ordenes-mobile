
package com.alimesa.ordenpedido.modelo.seguridad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.alimesa.ordenpedido.librerias.dominio.entidades.base.EntidadBaseAuditable;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ROLES")
public class Rol extends EntidadBaseAuditable<Long> implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String aplicaAud;
	private String rol;
	private String tipoRol;
	private List<OpcionesRol> listaOpcionesRoles;
	private List<UsuariosRol> priUsuariosRoles;
	private String lPaginaPrincipal;
	private String seleccionable;
	private String ambito;

	@GeneratedValue(generator = "secRole", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "secRole", allocationSize = 1, initialValue = 1, sequenceName = "SEC_ROLE")
	@Id
	@Column(name = "ID")
	public Long getId() {
		/* 71 */ return this.id;
	}

	public void setId(Long id) {
		/* 82 */ this.id = id;
	}

	@Column(name = "APLICA_AUD")
	public String getAplicaAud() {
		/* 92 */ return this.aplicaAud;
	}

	public void setAplicaAud(String aplicaAud) {
		/* 101 */ this.aplicaAud = aplicaAud;
	}

	@Column(name = "ROL")
	public String getRol() {
		/* 111 */ return this.rol;
	}

	public void setRol(String rol) {
		/* 120 */ this.rol = rol;
	}

	@Column(name = "TIPO_ROL")
	public String getTipoRol() {
		/* 130 */ return this.tipoRol;
	}

	public void setTipoRol(String tipoRol) {
		/* 139 */ this.tipoRol = tipoRol;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "priRole")
	public List<UsuariosRol> getPriUsuariosRoles() {
		/* 150 */ return this.priUsuariosRoles;
	}

	public void setPriUsuariosRoles(List<UsuariosRol> priUsuariosRoles) {
		/* 159 */ this.priUsuariosRoles = priUsuariosRoles;
	}

	public UsuariosRol addPriUsuariosRole(UsuariosRol priUsuariosRole) {
		/* 169 */ getPriUsuariosRoles().add(priUsuariosRole);
		/* 170 */ priUsuariosRole.setPriRole(this);

		/* 172 */ return priUsuariosRole;
	}

	public UsuariosRol removePriUsuariosRole(UsuariosRol priUsuariosRole) {
		/* 182 */ getPriUsuariosRoles().remove(priUsuariosRole);
		/* 183 */ priUsuariosRole.setPriRole(null);

		/* 185 */ return priUsuariosRole;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "idRol")
	public List<OpcionesRol> getListaOpcionesRoles() {
		/* 196 */ return this.listaOpcionesRoles;
	}

	public void setListaOpcionesRoles(List<OpcionesRol> listaOpcionesRoles) {
		/* 205 */ this.listaOpcionesRoles = listaOpcionesRoles;
	}

	public String toString() {
		/* 216 */ return this.rol;
	}

	public void agregarOpcionesRoles(OpcionesRol opcionRole) {
		/* 225 */ if (this.listaOpcionesRoles == null) {
			/* 226 */ this.listaOpcionesRoles = new ArrayList<>();
		}
		/* 228 */ opcionRole.setIdRol(this);
		/* 229 */ this.listaOpcionesRoles.add(opcionRole);
	}

	public void agregarOpcionesRoles(List<OpcionesRol> opcionesRoles) {
		/* 238 */ for (OpcionesRol opcionRol : opcionesRoles) {
			/* 239 */ agregarOpcionesRoles(opcionRol);
		}
	}

	public void agregarOpciones(List<Opcion> opciones) {
		/* 249 */ for (Opcion opcion : opciones) {
			/* 250 */ OpcionesRol opcionesRoles = new OpcionesRol();
			/* 251 */ opcionesRoles.setEstado("A");
			/* 252 */ opcionesRoles.setFechaRegistro(new Date());
			/* 253 */ opcionesRoles
					.setObservacion("DESCRIPCION ASIGNACION: " + getRol() + " - " + opcion.getDescripcion());
			/* 254 */ opcionesRoles.setPriOpcione(opcion);
			/* 255 */ agregarOpcionesRoles(opcionesRoles);
		}
	}

	public void agregarOpciones(Opcion opcion) {
		/* 266 */ OpcionesRol opcionesRoles = new OpcionesRol();
		/* 267 */ opcionesRoles.setEstado("A");
		/* 268 */ opcionesRoles.setFechaRegistro(new Date());
		/* 269 */ opcionesRoles.setObservacion("DESCRIPCION ASIGNACION: " + getRol() + " - " + opcion.getDescripcion());
		/* 270 */ opcionesRoles.setPriOpcione(opcion);
		/* 271 */ agregarOpcionesRoles(opcionesRoles);
	}

	@Column(name = "PAGINA_PRINCIPAL")
	public String getlPaginaPrincipal() {
		/* 282 */ return this.lPaginaPrincipal;
	}

	public void setlPaginaPrincipal(String lPaginaPrincipal) {
		/* 291 */ this.lPaginaPrincipal = lPaginaPrincipal;
	}

	@Column(name = "SELECCIONABLE")
	public String getSeleccionable() {
		/* 301 */ return this.seleccionable;
	}

	public void setSeleccionable(String seleccionable) {
		/* 310 */ this.seleccionable = seleccionable;
	}

	public String getAmbito() {
		/* 314 */ return this.ambito;
	}

	public void setAmbito(String ambito) {
		/* 318 */ this.ambito = ambito;
	}
}

/*
 * Location:
 * D:\apps\api-rest-ordenpedido\1.0\api-rest-ordenpedido-1.0.war!\WEB-INF\
 * classes\com\alimesa\ordenpedido\modelo\seguridad\Rol.class Java compiler
 * version: 11 (55.0) JD-Core Version: 1.1.3
 */