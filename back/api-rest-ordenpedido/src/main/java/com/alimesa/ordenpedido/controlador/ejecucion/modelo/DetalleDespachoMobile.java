package com.alimesa.ordenpedido.controlador.ejecucion.modelo;

import java.io.Serializable;

public class DetalleDespachoMobile implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String estado;
	private String idreferencia;
	private String campo_auditoria;
	private String fecha_actualizacion;
	private String fecha_registro;
	private String observacion;
	private Double cantidad;
	private String codigoauxiliar;
	private String codigoprincipal;
	private String descripcion;
	private String descuento;
	private String detallesadicionales;
	private Double preciototalconimpuesto;
	private Double preciototalsinimpuesto;
	private Double preciounitario;
	private Long id_cab_despacho;
	private Long id_producto;
	private String codigoproducto;
	private String numerofactura;
	private String numerooden;
	private Double preciopedido;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getIdreferencia() {
		return idreferencia;
	}

	public void setIdreferencia(String idreferencia) {
		this.idreferencia = idreferencia;
	}

	public String getCampo_auditoria() {
		return campo_auditoria;
	}

	public void setCampo_auditoria(String campo_auditoria) {
		this.campo_auditoria = campo_auditoria;
	}

	public String getFecha_actualizacion() {
		return fecha_actualizacion;
	}

	public void setFecha_actualizacion(String fecha_actualizacion) {
		this.fecha_actualizacion = fecha_actualizacion;
	}

	public String getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(String fecha_registro) {
		this.fecha_registro = fecha_registro;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public String getCodigoauxiliar() {
		return codigoauxiliar;
	}

	public void setCodigoauxiliar(String codigoauxiliar) {
		this.codigoauxiliar = codigoauxiliar;
	}

	public String getCodigoprincipal() {
		return codigoprincipal;
	}

	public void setCodigoprincipal(String codigoprincipal) {
		this.codigoprincipal = codigoprincipal;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescuento() {
		return descuento;
	}

	public void setDescuento(String descuento) {
		this.descuento = descuento;
	}

	public String getDetallesadicionales() {
		return detallesadicionales;
	}

	public void setDetallesadicionales(String detallesadicionales) {
		this.detallesadicionales = detallesadicionales;
	}

	public Double getPreciototalconimpuesto() {
		return preciototalconimpuesto;
	}

	public void setPreciototalconimpuesto(Double preciototalconimpuesto) {
		this.preciototalconimpuesto = preciototalconimpuesto;
	}

	public Double getPreciototalsinimpuesto() {
		return preciototalsinimpuesto;
	}

	public void setPreciototalsinimpuesto(Double preciototalsinimpuesto) {
		this.preciototalsinimpuesto = preciototalsinimpuesto;
	}

	public Double getPreciounitario() {
		return preciounitario;
	}

	public void setPreciounitario(Double preciounitario) {
		this.preciounitario = preciounitario;
	}

	public Long getId_cab_despacho() {
		return id_cab_despacho;
	}

	public void setId_cab_despacho(Long id_cab_despacho) {
		this.id_cab_despacho = id_cab_despacho;
	}

	public Long getId_producto() {
		return id_producto;
	}

	public void setId_producto(Long id_producto) {
		this.id_producto = id_producto;
	}

	public String getCodigoproducto() {
		return codigoproducto;
	}

	public void setCodigoproducto(String codigoproducto) {
		this.codigoproducto = codigoproducto;
	}

	public String getNumerofactura() {
		return numerofactura;
	}

	public void setNumerofactura(String numerofactura) {
		this.numerofactura = numerofactura;
	}

	public String getNumerooden() {
		return numerooden;
	}

	public void setNumerooden(String numerooden) {
		this.numerooden = numerooden;
	}

	public Double getPreciopedido() {
		return preciopedido;
	}

	public void setPreciopedido(Double preciopedido) {
		this.preciopedido = preciopedido;
	}

}
