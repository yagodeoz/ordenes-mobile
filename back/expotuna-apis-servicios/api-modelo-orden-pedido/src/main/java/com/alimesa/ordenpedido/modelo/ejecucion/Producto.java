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

/**
 * The persistent class for the producto database table.
 * 
 */
@Entity
@Table(name = "PRODUCTOS")
public class Producto extends EntidadBaseAuditable<Long> implements Serializable {
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

	public Producto() {
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(generator = "FactProducto", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "FactProducto", allocationSize = 1, initialValue = 1, sequenceName = "SEC_POS_PRODUCTO")
	public Long getId() {
		return id;
	}

	public String getlDescripcion() {
		return lDescripcion;
	}

	public void setlDescripcion(String lDescripcion) {
		this.lDescripcion = lDescripcion;
	}

	public Double getlExistencia() {
		return lExistencia;
	}

	public void setlExistencia(Double lExistencia) {
		this.lExistencia = lExistencia;
	}

	public String getlNombre() {
		return lNombre;
	}

	public void setlNombre(String lNombre) {
		this.lNombre = lNombre;
	}

	public Double getlPrecioUnitario() {
		return lPrecioUnitario;
	}

	public void setlPrecioUnitario(Double lPrecioUnitario) {
		this.lPrecioUnitario = lPrecioUnitario;
	}

	public Integer getlImpuestoIva() {
		return lImpuestoIva;
	}

	public void setlImpuestoIva(Integer lImpuestoIva) {
		this.lImpuestoIva = lImpuestoIva;
	}

	public Integer getlImpuestoIce() {
		return lImpuestoIce;
	}

	public void setlImpuestoIce(Integer lImpuestoIce) {
		this.lImpuestoIce = lImpuestoIce;
	}

	public String getlCodigoProducto() {
		return lCodigoProducto;
	}

	public void setlCodigoProducto(String lCodigoProducto) {
		this.lCodigoProducto = lCodigoProducto;
	}

	public String getlDetallesAdicionales() {
		return lDetallesAdicionales;
	}

	public void setlDetallesAdicionales(String lDetallesAdicionales) {
		this.lDetallesAdicionales = lDetallesAdicionales;
	}

	public Double getlPrecioIVA() {
		return lPrecioIVA;
	}

	public void setlPrecioIVA(Double lPrecioIVA) {
		this.lPrecioIVA = lPrecioIVA;
	}

	@ManyToOne
	@JoinColumn(name = "ID_EMPRESA")
	public Empresa getlEmpresa() {
		return lEmpresa;
	}

	public void setlEmpresa(Empresa lEmpresa) {
		this.lEmpresa = lEmpresa;
	}

	@ManyToOne
	@JoinColumn(name = "ID_TIPO")
	public CategoriaProducto getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(CategoriaProducto tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

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

	public String getlCodigoAuxiliar() {
		return lCodigoAuxiliar;
	}

	public void setlCodigoAuxiliar(String lCodigoAuxiliar) {
		this.lCodigoAuxiliar = lCodigoAuxiliar;
	}

	public Double getlPorcentajeIva() {
		return lPorcentajeIva;
	}

	public void setlPorcentajeIva(Double lPorcentajeIva) {
		this.lPorcentajeIva = lPorcentajeIva;
	}

	public Double getlPrecioICE() {
		return lPrecioICE;
	}

	public void setlPrecioICE(Double lPrecioICE) {
		this.lPrecioICE = lPrecioICE;
	}

	public Double getlPorcentajeICE() {
		return lPorcentajeICE;
	}

	public void setlPorcentajeICE(Double lPorcentajeICE) {
		this.lPorcentajeICE = lPorcentajeICE;
	}

	public Long getIdImpuestoIVA() {
		return idImpuestoIVA;
	}

	public void setIdImpuestoIVA(Long idImpuestoIVA) {
		this.idImpuestoIVA = idImpuestoIVA;
	}

	public Long getIdImpuestoICE() {
		return idImpuestoICE;
	}

	public void setIdImpuestoICE(Long idImpuestoICE) {
		this.idImpuestoICE = idImpuestoICE;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "producto")
	public List<DetalleProducto> getListaDetalles() {
		return listaDetalles;
	}

	public void setListaDetalles(List<DetalleProducto> listaDetalles) {
		this.listaDetalles = listaDetalles;
	}

	@OneToMany(mappedBy = "producto")
	public List<ListaPrecioProducto> getListaPrecios() {
		return listaPrecios;
	}

	public void setListaPrecios(List<ListaPrecioProducto> listaPrecios) {
		this.listaPrecios = listaPrecios;
	}

	@OneToMany(mappedBy = "producto")
	public List<StockProducto> getListaStock() {
		return listaStock;
	}

	public void setListaStock(List<StockProducto> listaStock) {
		this.listaStock = listaStock;
	}

}