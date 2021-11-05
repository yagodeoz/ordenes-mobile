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

@Entity
@Table(name = "OPCIONES_ROLES")
@NamedNativeQueries({
		@NamedNativeQuery(name = "OPCIONES_ROLES.OPCIONES_PADRE", query = "select *  from OPCIONES d  where exists (select 'X'         from OPCIONES_ROLES s        where exists (select 'X'                 from USUARIOS_ROLES t                where t.id_usuario = ?                  and t.estado = 'A'                  and t.id_rol = s.id_rol)          and s.estado = 'A'          and s.id_opcion = d.id)  and d.modulo_padre is null  and d.estado = 'A' and d.orientacion = ? order by d.orden asc", resultClass = Opcion.class),
		@NamedNativeQuery(name = "OPCIONES_ROLES.OPCIONES_PADRE_ROL", query = "select *  from OPCIONES d  where exists (select 'X'         from OPCIONES_ROLES s        where s.id_rol = :idRol    and s.estado = 'A'          and s.id_opcion = d.id)  and d.modulo_padre is null  and d.estado = 'A' and d.orientacion = :orientacion order by d.orden asc", resultClass = Opcion.class) })
public class OpcionesRol extends EntidadBaseAuditable<Long> implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Rol idRol;
	private Opcion priOpcione;

	@GeneratedValue(generator = "secOpcionesRol", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "secOpcionesRol", allocationSize = 1, initialValue = 1, sequenceName = "SEC_OPCIONESROL")
	@Id
	@Column(name = "ID")
	public Long getId() {
		/* 65 */ return this.id;
	}

	public void setId(Long id) {
		/* 76 */ this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "ID_ROL")
	public Rol getIdRol() {
		/* 87 */ return this.idRol;
	}

	public void setIdRol(Rol idRol) {
		/* 96 */ this.idRol = idRol;
	}

	@ManyToOne
	@JoinColumn(name = "ID_OPCION")
	public Opcion getPriOpcione() {
		/* 107 */ return this.priOpcione;
	}

	public void setPriOpcione(Opcion priOpcione) {
		/* 116 */ this.priOpcione = priOpcione;
	}
}

/*
 * Location:
 * D:\apps\api-rest-ordenpedido\1.0\api-rest-ordenpedido-1.0.war!\WEB-INF\
 * classes\com\alimesa\ordenpedido\modelo\seguridad\OpcionesRol.class Java
 * compiler version: 11 (55.0) JD-Core Version: 1.1.3
 */