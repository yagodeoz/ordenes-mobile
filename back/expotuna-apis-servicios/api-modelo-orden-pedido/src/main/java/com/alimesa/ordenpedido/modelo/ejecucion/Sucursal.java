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

@Entity
@Table(name="SUCURSALES")
public class Sucursal extends  EntidadBaseAuditable<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String direccion;
	private String codigo;
	private Empresa lEmpresa;
	
	@GeneratedValue(generator = "FactEstablecimiento", strategy = GenerationType.SEQUENCE)
   	@SequenceGenerator(name = "FactEstablecimiento", allocationSize = 1, initialValue = 1, 
   	sequenceName = "SEC_FACT_ESTABLECIMIENTO")
   	@Id
   	@Column(name = "ID")
	public Long getId() {
		return id;
	}
	
	
	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getDireccion() {
		return direccion;
	}



	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}



	public String getCodigo() {
		return codigo;
	}



	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@ManyToOne
	@JoinColumn(name="ID_EMPRESA")
	public Empresa getlEmpresa() {
		return lEmpresa;
	}


	public void setlEmpresa(Empresa lEmpresa) {
		this.lEmpresa = lEmpresa;
	}
	
	

}
