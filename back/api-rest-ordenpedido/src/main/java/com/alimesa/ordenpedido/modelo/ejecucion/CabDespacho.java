package com.alimesa.ordenpedido.modelo.ejecucion;

import java.io.Serializable;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.alimesa.ordenpedido.librerias.dominio.entidades.base.EntidadBaseAuditable;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/*
  
 * 
 * */

@Entity
@Table(name = "CAB_DESPACHO")
public class CabDespacho extends EntidadBaseAuditable<Long> implements Serializable {
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
	/* 48 */ private Double totalImpuesto = Double.valueOf(0.0D);
	/* 49 */ private Double subTotal12 = Double.valueOf(0.0D);
	/* 50 */ private Double subTotal0 = Double.valueOf(0.0D);

	private String nombreVendedor;

	private String canalCreacion;
	private String codigoCliente;
	private String codigoSucursal;
	private Long diasPago;
	private String placaVehiculo;
	private String usuarioAsignado;
	private String usuarioAsignante;
	private String descripcionListaPrecio;
	private String tipoPago;
	private String codigoTicket;
	private String ciChofer;
	private String nombreChofer;
	private String camion;
	
	private String fechaAut;
	private String autorizacion;
	private String claveAcceso;
	private String telefono;
	
	private List<DetalleDespacho> listaDetallesDespacho;

	@GeneratedValue(generator = "cabDespacho", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "cabDespacho", allocationSize = 1, initialValue = 1, sequenceName = "SEC_CAB_DESPACHO")
	@Id
	@Column(name = "ID")
	public Long getId() {
		/* 61 */ return (Long) this.id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getFechaEmision() {
		/* 66 */ return this.fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		/* 70 */ this.fechaEmision = fechaEmision;
	}

	public String getRazonSocialComprador() {
		/* 74 */ return this.razonSocialComprador;
	}

	public void setRazonSocialComprador(String razonSocialComprador) {
		/* 78 */ this.razonSocialComprador = razonSocialComprador;
	}

	public String getIdentificacionComprador() {
		/* 82 */ return this.identificacionComprador;
	}

	public void setIdentificacionComprador(String identificacionComprador) {
		/* 86 */ this.identificacionComprador = identificacionComprador;
	}

	public String getDireccionComprador() {
		/* 90 */ return this.direccionComprador;
	}

	public void setDireccionComprador(String direccionComprador) {
		/* 94 */ this.direccionComprador = direccionComprador;
	}

	public Double getTotalSinImpuestos() {
		/* 98 */ return this.totalSinImpuestos;
	}

	public void setTotalSinImpuestos(Double totalSinImpuestos) {
		/* 102 */ this.totalSinImpuestos = totalSinImpuestos;
	}

	public Double getTotalDescuento() {
		/* 106 */ return this.totalDescuento;
	}

	public void setTotalDescuento(Double totalDescuento) {
		/* 110 */ this.totalDescuento = totalDescuento;
	}

	public Double getImporteTotal() {
		/* 114 */ return this.importeTotal;
	}

	public void setImporteTotal(Double importeTotal) {
		/* 118 */ this.importeTotal = importeTotal;
	}

	public String getMoneda() {
		/* 122 */ return this.moneda;
	}

	public void setMoneda(String moneda) {
		/* 126 */ this.moneda = moneda;
	}

	public String getEstadoProceso() {
		/* 130 */ return this.estadoProceso;
	}

	public void setEstadoProceso(String estadoProceso) {
		/* 134 */ this.estadoProceso = estadoProceso;
	}

	public Double getTotalImpuesto() {
		/* 138 */ return this.totalImpuesto;
	}

	public void setTotalImpuesto(Double totalImpuesto) {
		/* 142 */ this.totalImpuesto = totalImpuesto;
	}

	public Double getSubTotal12() {
		/* 146 */ return this.subTotal12;
	}

	public void setSubTotal12(Double subTotal12) {
		/* 150 */ this.subTotal12 = subTotal12;
	}

	public Double getSubTotal0() {
		/* 154 */ return this.subTotal0;
	}

	public void setSubTotal0(Double subTotal0) {
		/* 158 */ this.subTotal0 = subTotal0;
	}

	public Long getIdUsuario() {
		/* 162 */ return this.idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		/* 166 */ this.idUsuario = idUsuario;
	}

	public Long getIdSucursal() {
		/* 170 */ return this.idSucursal;
	}

	public void setIdSucursal(Long idSucursal) {
		/* 174 */ this.idSucursal = idSucursal;
	}

	public String getIdCliente() {
		/* 178 */ return this.idCliente;
	}

	public void setIdCliente(String idCliente) {
		/* 182 */ this.idCliente = idCliente;
	}

	public String getNombreVendedor() {
		/* 186 */ return this.nombreVendedor;
	}

	public void setNombreVendedor(String nombreVendedor) {
		/* 190 */ this.nombreVendedor = nombreVendedor;
	}

	public String getNumeroOden() {
		/* 194 */ return this.numeroOden;
	}

	public void setNumeroOden(String numeroOden) {
		/* 198 */ this.numeroOden = numeroOden;
	}

	public String getNumeroFactura() {
		/* 202 */ return this.numeroFactura;
	}

	public void setNumeroFactura(String numeroFactura) {
		/* 206 */ this.numeroFactura = numeroFactura;
	}

	@OneToMany(mappedBy = "cabDespacho")
	@JsonManagedReference
	public List<DetalleDespacho> getListaDetallesDespacho() {
		/* 211 */ return this.listaDetallesDespacho;
	}

	public void setListaDetallesDespacho(List<DetalleDespacho> listaDetallesDespacho) {
		/* 215 */ this.listaDetallesDespacho = listaDetallesDespacho;
	}

	public String getCanalCreacion() {
		return canalCreacion;
	}

	public void setCanalCreacion(String canalCreacion) {
		this.canalCreacion = canalCreacion;
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getCodigoSucursal() {
		return codigoSucursal;
	}

	public void setCodigoSucursal(String codigoSucursal) {
		this.codigoSucursal = codigoSucursal;
	}

	public Long getDiasPago() {
		return diasPago;
	}

	public void setDiasPago(Long diasPago) {
		this.diasPago = diasPago;
	}

	public String getPlacaVehiculo() {
		return placaVehiculo;
	}

	public void setPlacaVehiculo(String placaVehiculo) {
		this.placaVehiculo = placaVehiculo;
	}

	public String getUsuarioAsignado() {
		return usuarioAsignado;
	}

	public void setUsuarioAsignado(String usuarioAsignado) {
		this.usuarioAsignado = usuarioAsignado;
	}

	public String getUsuarioAsignante() {
		return usuarioAsignante;
	}

	public void setUsuarioAsignante(String usuarioAsignante) {
		this.usuarioAsignante = usuarioAsignante;
	}

	public String getDescripcionListaPrecio() {
		return descripcionListaPrecio;
	}

	public void setDescripcionListaPrecio(String descripcionListaPrecio) {
		this.descripcionListaPrecio = descripcionListaPrecio;
	}

	public String getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}

	public String getCodigoTicket() {
		return codigoTicket;
	}

	public void setCodigoTicket(String codigoTicket) {
		this.codigoTicket = codigoTicket;
	}

	public String getCiChofer() {
		return ciChofer;
	}

	public void setCiChofer(String ciChofer) {
		this.ciChofer = ciChofer;
	}

	public String getNombreChofer() {
		return nombreChofer;
	}

	public void setNombreChofer(String nombreChofer) {
		this.nombreChofer = nombreChofer;
	}

	public String getCamion() {
		return camion;
	}

	public void setCamion(String camion) {
		this.camion = camion;
	}

	

	public String getFechaAut() {
		return fechaAut;
	}

	public void setFechaAut(String fechaAut) {
		this.fechaAut = fechaAut;
	}

	public String getAutorizacion() {
		return autorizacion;
	}

	public void setAutorizacion(String autorizacion) {
		this.autorizacion = autorizacion;
	}

	public String getClaveAcceso() {
		return claveAcceso;
	}

	public void setClaveAcceso(String claveAcceso) {
		this.claveAcceso = claveAcceso;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}

/*
 * Location:
 * D:\apps\api-rest-ordenpedido\1.0\api-rest-ordenpedido-1.0.war!\WEB-INF\
 * classes\com\alimesa\ordenpedido\modelo\ejecucion\CabDespacho.class Java
 * compiler version: 11 (55.0) JD-Core Version: 1.1.3
 */