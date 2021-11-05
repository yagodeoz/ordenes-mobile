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
@Table(name = "CAB_ORDEN")
public class CabOrden extends EntidadBaseAuditable<Long> implements Serializable {
	private static final long serialVersionUID = 1L;
	private String numeroOden;
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
	/* 47 */ private Double totalImpuesto = Double.valueOf(0.0D);
	/* 48 */ private Double subTotal12 = Double.valueOf(0.0D);
	/* 49 */ private Double subTotal0 = Double.valueOf(0.0D);

	private String nombreVendedor;

	private String usuarioAsignado;

	private String usuarioAsignante;

	private String canalCreacion;

	private String codigoCliente;

	private List<DetalleOrden> listaDetOrden;

	@GeneratedValue(generator = "OrdCabecera", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "OrdCabecera", allocationSize = 1, initialValue = 1, sequenceName = "SEC_ORD_CABECERA")
	@Id
	@Column(name = "ID")
	public Long getId() {
		/* 70 */ return (Long) this.id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getFechaEmision() {
		/* 75 */ return this.fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		/* 79 */ this.fechaEmision = fechaEmision;
	}

	public String getRazonSocialComprador() {
		/* 83 */ return this.razonSocialComprador;
	}

	public void setRazonSocialComprador(String razonSocialComprador) {
		/* 87 */ this.razonSocialComprador = razonSocialComprador;
	}

	public String getIdentificacionComprador() {
		/* 91 */ return this.identificacionComprador;
	}

	public void setIdentificacionComprador(String identificacionComprador) {
		/* 95 */ this.identificacionComprador = identificacionComprador;
	}

	public String getDireccionComprador() {
		/* 99 */ return this.direccionComprador;
	}

	public void setDireccionComprador(String direccionComprador) {
		/* 103 */ this.direccionComprador = direccionComprador;
	}

	public Double getTotalSinImpuestos() {
		/* 107 */ return this.totalSinImpuestos;
	}

	public void setTotalSinImpuestos(Double totalSinImpuestos) {
		/* 111 */ this.totalSinImpuestos = totalSinImpuestos;
	}

	public Double getTotalDescuento() {
		/* 115 */ return this.totalDescuento;
	}

	public void setTotalDescuento(Double totalDescuento) {
		/* 119 */ this.totalDescuento = totalDescuento;
	}

	public Double getImporteTotal() {
		/* 123 */ return this.importeTotal;
	}

	public void setImporteTotal(Double importeTotal) {
		/* 127 */ this.importeTotal = importeTotal;
	}

	public String getMoneda() {
		/* 131 */ return this.moneda;
	}

	public void setMoneda(String moneda) {
		/* 135 */ this.moneda = moneda;
	}

	public String getEstadoProceso() {
		/* 139 */ return this.estadoProceso;
	}

	public void setEstadoProceso(String estadoProceso) {
		/* 143 */ this.estadoProceso = estadoProceso;
	}

	public Double getTotalImpuesto() {
		/* 147 */ return this.totalImpuesto;
	}

	public void setTotalImpuesto(Double totalImpuesto) {
		/* 151 */ this.totalImpuesto = totalImpuesto;
	}

	public Double getSubTotal12() {
		/* 155 */ return this.subTotal12;
	}

	public void setSubTotal12(Double subTotal12) {
		/* 159 */ this.subTotal12 = subTotal12;
	}

	public Double getSubTotal0() {
		/* 163 */ return this.subTotal0;
	}

	public void setSubTotal0(Double subTotal0) {
		/* 167 */ this.subTotal0 = subTotal0;
	}

	public Long getIdUsuario() {
		/* 171 */ return this.idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		/* 175 */ this.idUsuario = idUsuario;
	}

	public Long getIdSucursal() {
		/* 179 */ return this.idSucursal;
	}

	public void setIdSucursal(Long idSucursal) {
		/* 183 */ this.idSucursal = idSucursal;
	}

	public String getIdCliente() {
		/* 187 */ return this.idCliente;
	}

	public void setIdCliente(String idCliente) {
		/* 191 */ this.idCliente = idCliente;
	}

	public String getNombreVendedor() {
		/* 195 */ return this.nombreVendedor;
	}

	public void setNombreVendedor(String nombreVendedor) {
		/* 199 */ this.nombreVendedor = nombreVendedor;
	}

	@OneToMany(mappedBy = "cabeceraOrden", fetch = FetchType.EAGER)
	public List<DetalleOrden> getListaDetOrden() {
		/* 204 */ return this.listaDetOrden;
	}

	public void setListaDetOrden(List<DetalleOrden> listaDetOrden) {
		/* 208 */ this.listaDetOrden = listaDetOrden;
	}

	public String getNumeroOden() {
		/* 212 */ return this.numeroOden;
	}

	public void setNumeroOden(String numeroOden) {
		/* 216 */ this.numeroOden = numeroOden;
	}

	public String getUsuarioAsignado() {
		/* 220 */ return this.usuarioAsignado;
	}

	public void setUsuarioAsignado(String usuarioAsignado) {
		/* 224 */ this.usuarioAsignado = usuarioAsignado;
	}

	public String getUsuarioAsignante() {
		/* 228 */ return this.usuarioAsignante;
	}

	public void setUsuarioAsignante(String usuarioAsignante) {
		/* 232 */ this.usuarioAsignante = usuarioAsignante;
	}

	public String getCanalCreacion() {
		/* 236 */ return this.canalCreacion;
	}

	public void setCanalCreacion(String canalCreacion) {
		/* 240 */ this.canalCreacion = canalCreacion;
	}

	public String getCodigoCliente() {
		/* 244 */ return this.codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		/* 248 */ this.codigoCliente = codigoCliente;
	}
}

/*
 * Location:
 * D:\apps\api-rest-ordenpedido\1.0\api-rest-ordenpedido-1.0.war!\WEB-INF\
 * classes\com\alimesa\ordenpedido\modelo\ejecucion\CabOrden.class Java compiler
 * version: 11 (55.0) JD-Core Version: 1.1.3
 */