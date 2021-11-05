package com.alimesa.ordenpedido.modelo.ejecucion;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.alimesa.ordenpedido.librerias.dominio.entidades.base.EntidadBaseAuditable;

@Entity
@Table(name = "CAB_DESPACHO")
public class CabDespacho extends EntidadBaseAuditable<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String numeroOden;
	private String numeroFactura;

	private Long idUsuario;
	private Long idSucursal;
	private String estadoProceso;
	private String idCliente;

	private Date fechaEmision;
	private String razonSocialComprador;
	private String identificacionComprador;
	private String direccionComprador;

	private Double totalSinImpuestos;
	private Double totalDescuento;
	private Double importeTotal;
	private String moneda;

	private Double totalImpuesto = 0D;
	private Double subTotal12 = 0D;
	private Double subTotal0 = 0D;

	private String nombreVendedor;

	private List<DetalleDespacho> listaDetallesDespacho;

	@GeneratedValue(generator = "cabDespacho", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "cabDespacho", allocationSize = 1, initialValue = 1, sequenceName = "SEC_CAB_DESPACHO")
	@Id
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getRazonSocialComprador() {
		return razonSocialComprador;
	}

	public void setRazonSocialComprador(String razonSocialComprador) {
		this.razonSocialComprador = razonSocialComprador;
	}

	public String getIdentificacionComprador() {
		return identificacionComprador;
	}

	public void setIdentificacionComprador(String identificacionComprador) {
		this.identificacionComprador = identificacionComprador;
	}

	public String getDireccionComprador() {
		return direccionComprador;
	}

	public void setDireccionComprador(String direccionComprador) {
		this.direccionComprador = direccionComprador;
	}

	public Double getTotalSinImpuestos() {
		return totalSinImpuestos;
	}

	public void setTotalSinImpuestos(Double totalSinImpuestos) {
		this.totalSinImpuestos = totalSinImpuestos;
	}

	public Double getTotalDescuento() {
		return totalDescuento;
	}

	public void setTotalDescuento(Double totalDescuento) {
		this.totalDescuento = totalDescuento;
	}

	public Double getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(Double importeTotal) {
		this.importeTotal = importeTotal;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getEstadoProceso() {
		return estadoProceso;
	}

	public void setEstadoProceso(String estadoProceso) {
		this.estadoProceso = estadoProceso;
	}

	public Double getTotalImpuesto() {
		return totalImpuesto;
	}

	public void setTotalImpuesto(Double totalImpuesto) {
		this.totalImpuesto = totalImpuesto;
	}

	public Double getSubTotal12() {
		return subTotal12;
	}

	public void setSubTotal12(Double subTotal12) {
		this.subTotal12 = subTotal12;
	}

	public Double getSubTotal0() {
		return subTotal0;
	}

	public void setSubTotal0(Double subTotal0) {
		this.subTotal0 = subTotal0;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(Long idSucursal) {
		this.idSucursal = idSucursal;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombreVendedor() {
		return nombreVendedor;
	}

	public void setNombreVendedor(String nombreVendedor) {
		this.nombreVendedor = nombreVendedor;
	}

	public String getNumeroOden() {
		return numeroOden;
	}

	public void setNumeroOden(String numeroOden) {
		this.numeroOden = numeroOden;
	}

	public String getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	@OneToMany(mappedBy = "cabDespacho", fetch = FetchType.EAGER)
	public List<DetalleDespacho> getListaDetallesDespacho() {
		return listaDetallesDespacho;
	}

	public void setListaDetallesDespacho(List<DetalleDespacho> listaDetallesDespacho) {
		this.listaDetallesDespacho = listaDetallesDespacho;
	}

}
