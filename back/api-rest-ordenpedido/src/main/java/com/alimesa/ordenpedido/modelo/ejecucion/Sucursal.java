package com.alimesa.ordenpedido.modelo.ejecucion;

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
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "SUCURSALES")
public class Sucursal extends EntidadBaseAuditable<Long> implements Serializable {
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String direccion;
	private String codigo;
	private Empresa lEmpresa;

	@GeneratedValue(generator = "FactEstablecimiento", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "FactEstablecimiento", allocationSize = 1, initialValue = 1, sequenceName = "SEC_FACT_ESTABLECIMIENTO")
	@Id
	@Column(name = "ID")
	public Long getId() {
		/* 37 */ return (Long) this.id;
	}

	public String getNombre() {
		/* 42 */ return this.nombre;
	}

	public void setNombre(String nombre) {
		/* 48 */ this.nombre = nombre;
	}

	public String getDireccion() {
		/* 54 */ return this.direccion;
	}

	public void setDireccion(String direccion) {
		/* 60 */ this.direccion = direccion;
	}

	public String getCodigo() {
		/* 66 */ return this.codigo;
	}

	public void setCodigo(String codigo) {
		/* 72 */ this.codigo = codigo;
	}

	@ManyToOne
	@JoinColumn(name = "ID_EMPRESA")
	@JsonBackReference
	public Empresa getlEmpresa() {
		/* 79 */ return this.lEmpresa;
	}

	public void setlEmpresa(Empresa lEmpresa) {
		/* 84 */ this.lEmpresa = lEmpresa;
	}
}

/*
 * Location:
 * D:\apps\api-rest-ordenpedido\1.0\api-rest-ordenpedido-1.0.war!\WEB-INF\
 * classes\com\alimesa\ordenpedido\modelo\ejecucion\Sucursal.class Java compiler
 * version: 11 (55.0) JD-Core Version: 1.1.3
 */