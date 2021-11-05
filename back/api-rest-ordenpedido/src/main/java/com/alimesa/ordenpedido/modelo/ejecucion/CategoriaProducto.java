
package com.alimesa.ordenpedido.modelo.ejecucion;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.alimesa.ordenpedido.librerias.dominio.entidades.base.EntidadBaseAuditable;

@Entity
@Table(name = "CATEGORIAS_PRODUCTOS")
public class CategoriaProducto extends EntidadBaseAuditable<Long> implements Serializable {
	private static final long serialVersionUID = 1L;
	private String lTipoProducto;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(generator = "FactTipoProducto", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "FactTipoProducto", allocationSize = 1, initialValue = 1, sequenceName = "SEC_POS_TIPO_PRODUCTO")
	public Long getId() {
		/* 28 */ return (Long) this.id;
	}

	public String getlTipoProducto() {
		/* 32 */ return this.lTipoProducto;
	}

	public void setlTipoProducto(String lTipoProducto) {
		/* 36 */ this.lTipoProducto = lTipoProducto;
	}
}

/*
 * Location:
 * D:\apps\api-rest-ordenpedido\1.0\api-rest-ordenpedido-1.0.war!\WEB-INF\
 * classes\com\alimesa\ordenpedido\modelo\ejecucion\CategoriaProducto.class Java
 * compiler version: 11 (55.0) JD-Core Version: 1.1.3
 */