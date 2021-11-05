 package com.alimesa.ordenpedido.modelo.ejecucion;
 
 import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.alimesa.ordenpedido.librerias.dominio.entidades.base.EntidadBaseAuditable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 @Entity
 @Table(name = "PRODUCTOS")
 public class Producto
   extends EntidadBaseAuditable<Long>
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   private String lCodigoProducto;
   private String lCodigoAuxiliar;
   private String lDescripcion;
   private Double lExistencia;
   private String lNombre;
   private Double lPrecioUnitario;
   private Long idImpuestoIVA;
   private Long idImpuestoICE;
   private Double lPrecioIVA;
   private Double lPrecioICE;
   private Integer lImpuestoIva;
   private Integer lImpuestoIce;
   private String lDetallesAdicionales;
   private Double lPorcentajeIva;
   private Double lPorcentajeICE;
   private Empresa lEmpresa;
   private CategoriaProducto tipoProducto;
   private byte[] imagenReferencia;
   private List<DetalleProducto> listaDetalles;
   private List<ListaPrecioProducto> listaPrecios;
   private List<StockProducto> listaStock;
   
   @Id
   @Column(name = "id", unique = true, nullable = false)
   @GeneratedValue(generator = "FactProducto", strategy = GenerationType.SEQUENCE)
   @SequenceGenerator(name = "FactProducto", allocationSize = 1, initialValue = 1, sequenceName = "SEC_POS_PRODUCTO")
   public Long getId() {
/*  82 */     return (Long)this.id;
   }
   
   public String getlDescripcion() {
/*  86 */     return this.lDescripcion;
   }
   
   public void setlDescripcion(String lDescripcion) {
/*  90 */     this.lDescripcion = lDescripcion;
   }
   
   public Double getlExistencia() {
/*  94 */     return this.lExistencia;
   }
   
   public void setlExistencia(Double lExistencia) {
/*  98 */     this.lExistencia = lExistencia;
   }
   
   public String getlNombre() {
/* 102 */     return this.lNombre;
   }
   
   public void setlNombre(String lNombre) {
/* 106 */     this.lNombre = lNombre;
   }
   
   public Double getlPrecioUnitario() {
/* 110 */     return this.lPrecioUnitario;
   }
   
   public void setlPrecioUnitario(Double lPrecioUnitario) {
/* 114 */     this.lPrecioUnitario = lPrecioUnitario;
   }
   
   public Integer getlImpuestoIva() {
/* 118 */     return this.lImpuestoIva;
   }
   
   public void setlImpuestoIva(Integer lImpuestoIva) {
/* 122 */     this.lImpuestoIva = lImpuestoIva;
   }
   
   public Integer getlImpuestoIce() {
/* 126 */     return this.lImpuestoIce;
   }
   
   public void setlImpuestoIce(Integer lImpuestoIce) {
/* 130 */     this.lImpuestoIce = lImpuestoIce;
   }
   
   public String getlCodigoProducto() {
/* 134 */     return this.lCodigoProducto;
   }
   
   public void setlCodigoProducto(String lCodigoProducto) {
/* 138 */     this.lCodigoProducto = lCodigoProducto;
   }
   
   public String getlDetallesAdicionales() {
/* 142 */     return this.lDetallesAdicionales;
   }
   
   public void setlDetallesAdicionales(String lDetallesAdicionales) {
/* 146 */     this.lDetallesAdicionales = lDetallesAdicionales;
   }
   
   public Double getlPrecioIVA() {
/* 150 */     return this.lPrecioIVA;
   }
   
   public void setlPrecioIVA(Double lPrecioIVA) {
/* 154 */     this.lPrecioIVA = lPrecioIVA;
   }
   
   @ManyToOne
   @JoinColumn(name = "ID_EMPRESA")
   @JsonBackReference
   public Empresa getlEmpresa() {
/* 161 */     return this.lEmpresa;
   }
   
   public void setlEmpresa(Empresa lEmpresa) {
/* 165 */     this.lEmpresa = lEmpresa;
   }
   
   @ManyToOne
   @JoinColumn(name = "ID_TIPO")
   public CategoriaProducto getTipoProducto() {
/* 171 */     return this.tipoProducto;
   }
   
   public void setTipoProducto(CategoriaProducto tipoProducto) {
/* 175 */     this.tipoProducto = tipoProducto;
   }
   
   @Lob
   @Column(name = "IMAGEN_REFERENCIA")
   public byte[] getImagenReferencia() {
/* 181 */     return this.imagenReferencia;
   }
 
 
 
 
 
   
   public void setImagenReferencia(byte[] imagenReferencia) {
/* 190 */     this.imagenReferencia = imagenReferencia;
   }
   
   public String getlCodigoAuxiliar() {
/* 194 */     return this.lCodigoAuxiliar;
   }
   
   public void setlCodigoAuxiliar(String lCodigoAuxiliar) {
/* 198 */     this.lCodigoAuxiliar = lCodigoAuxiliar;
   }
   
   public Double getlPorcentajeIva() {
/* 202 */     return this.lPorcentajeIva;
   }
   
   public void setlPorcentajeIva(Double lPorcentajeIva) {
/* 206 */     this.lPorcentajeIva = lPorcentajeIva;
   }
   
   public Double getlPrecioICE() {
/* 210 */     return this.lPrecioICE;
   }
   
   public void setlPrecioICE(Double lPrecioICE) {
/* 214 */     this.lPrecioICE = lPrecioICE;
   }
   
   public Double getlPorcentajeICE() {
/* 218 */     return this.lPorcentajeICE;
   }
   
   public void setlPorcentajeICE(Double lPorcentajeICE) {
/* 222 */     this.lPorcentajeICE = lPorcentajeICE;
   }
   
   public Long getIdImpuestoIVA() {
/* 226 */     return this.idImpuestoIVA;
   }
   
   public void setIdImpuestoIVA(Long idImpuestoIVA) {
/* 230 */     this.idImpuestoIVA = idImpuestoIVA;
   }
   
   public Long getIdImpuestoICE() {
/* 234 */     return this.idImpuestoICE;
   }
   
   public void setIdImpuestoICE(Long idImpuestoICE) {
/* 238 */     this.idImpuestoICE = idImpuestoICE;
   }
   
   @OneToMany(fetch = FetchType.EAGER, mappedBy = "producto")
   @JsonManagedReference
   public List<DetalleProducto> getListaDetalles() {
/* 244 */     return this.listaDetalles;
   }
   
   public void setListaDetalles(List<DetalleProducto> listaDetalles) {
/* 248 */     this.listaDetalles = listaDetalles;
   }
   
   @OneToMany(mappedBy = "producto")
   @JsonManagedReference
   public List<ListaPrecioProducto> getListaPrecios() {
/* 254 */     return this.listaPrecios;
   }
   
   public void setListaPrecios(List<ListaPrecioProducto> listaPrecios) {
/* 258 */     this.listaPrecios = listaPrecios;
   }
   
   @OneToMany(mappedBy = "producto")
   @JsonManagedReference
   public List<StockProducto> getListaStock() {
/* 264 */     return this.listaStock;
   }
   
   public void setListaStock(List<StockProducto> listaStock) {
/* 268 */     this.listaStock = listaStock;
   }
 }


/* Location:              D:\apps\api-rest-ordenpedido\1.0\api-rest-ordenpedido-1.0.war!\WEB-INF\classes\com\alimesa\ordenpedido\modelo\ejecucion\Producto.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */