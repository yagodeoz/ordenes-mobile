
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
 @Table(name = "LISTA_PRECIO")
 public class ListaPrecio extends EntidadBaseAuditable<Long> implements Serializable
 {
	 private static final long serialVersionUID = 1L;
	 private String codigoListaPrecio;
	 private String descripcion;

	
	 @GeneratedValue(generator = "FactListaPrecio", strategy = GenerationType.SEQUENCE)
	 @SequenceGenerator(name = "FactListaPrecio", allocationSize = 1, initialValue = 1, sequenceName = "SEC_LISTA_PRECIO")
	 @Id
	 @Column(name = "ID")
	 public Long getId() {
		/* 32 */ return (Long) this.id;
		 }

	
	 public String getCodigoListaPrecio() {
		/* 36 */ return this.codigoListaPrecio;
		 }

	
	 public void setCodigoListaPrecio(String codigoListaPrecio) {
		/* 40 */ this.codigoListaPrecio = codigoListaPrecio;
		 }

	
	 public String getDescripcion() {
		/* 44 */ return this.descripcion;
		 }

	
	 public void setDescripcion(String descripcion) {
		/* 48 */ this.descripcion = descripcion;
		 }
	 }

/*
 * Location:
 * D:\apps\api-rest-ordenpedido\1.0\api-rest-ordenpedido-1.0.war!\WEB-INF\
 * classes\com\alimesa\ordenpedido\modelo\ejecucion\ListaPrecio.class Java
 * compiler version: 11 (55.0) JD-Core Version: 1.1.3
 */