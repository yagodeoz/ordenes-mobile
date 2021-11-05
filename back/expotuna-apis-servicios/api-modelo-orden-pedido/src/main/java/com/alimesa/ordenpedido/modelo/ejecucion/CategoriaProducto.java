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
@Table(name = "CATEGORIAS_PRODUCTOS")
public class CategoriaProducto extends EntidadBaseAuditable<Long> implements Serializable {
	private static final long serialVersionUID = 1L;

	private String lTipoProducto;
	private Empresa lEmpresa;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(generator = "FactTipoProducto", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "FactTipoProducto", allocationSize = 1, initialValue = 1, sequenceName = "SEC_POS_TIPO_PRODUCTO")
	public Long getId() {
		return id;
	}

	public String getlTipoProducto() {
		return lTipoProducto;
	}

	public void setlTipoProducto(String lTipoProducto) {
		this.lTipoProducto = lTipoProducto;
	}

	@ManyToOne
	@JoinColumn(name = "ID_EMPRESA")
	public Empresa getlEmpresa() {
		return lEmpresa;
	}

	public void setlEmpresa(Empresa lEmpresa) {
		this.lEmpresa = lEmpresa;
	}

}
