package com.alimesa.ordenpedido.controlador.ejecucion.modelo;

import java.io.Serializable;

public class CabeceraDespachoMobile implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String estado;
	private String idreferencia;
	private String campo_auditoria;
	private String fecha_actualizacion;
	private String fecha_registro;
	private String observacion;
	private String direccioncomprador;
	private String estadoproceso;
	private String fechaemision;
	private String idcliente;
	private Long idsucursal;
	private Long idusuario;
	private String identificacioncomprador;
	private Double importetotal;
	private String moneda;
	private String nombrevendedor;
	private String numerofactura;
	private String numerooden;
	private String razonsocialcomprador;
	private Double subtotal0;
	private Double subtotal12;
	private Double totaldescuento;
	private Double totalimpuesto;
	private Double totalsinimpuestos;
	private String canalcreacion;
	private String codigocliente;
	private String codigosucursal;
	private Long diaspago;
	private String placavehiculo;
	private String usuarioasignado;
	private String usuarioasignante;
	private String descripcionlistaprecio;
	private String tipopago;

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

	public String getDireccioncomprador() {
		return direccioncomprador;
	}

	public void setDireccioncomprador(String direccioncomprador) {
		this.direccioncomprador = direccioncomprador;
	}

	public String getEstadoproceso() {
		return estadoproceso;
	}

	public void setEstadoproceso(String estadoproceso) {
		this.estadoproceso = estadoproceso;
	}

	public String getFechaemision() {
		return fechaemision;
	}

	public void setFechaemision(String fechaemision) {
		this.fechaemision = fechaemision;
	}

	public String getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(String idcliente) {
		this.idcliente = idcliente;
	}

	public Long getIdsucursal() {
		return idsucursal;
	}

	public void setIdsucursal(Long idsucursal) {
		this.idsucursal = idsucursal;
	}

	public Long getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(Long idusuario) {
		this.idusuario = idusuario;
	}

	public String getIdentificacioncomprador() {
		return identificacioncomprador;
	}

	public void setIdentificacioncomprador(String identificacioncomprador) {
		this.identificacioncomprador = identificacioncomprador;
	}

	public Double getImportetotal() {
		return importetotal;
	}

	public void setImportetotal(Double importetotal) {
		this.importetotal = importetotal;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getNombrevendedor() {
		return nombrevendedor;
	}

	public void setNombrevendedor(String nombrevendedor) {
		this.nombrevendedor = nombrevendedor;
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

	public String getRazonsocialcomprador() {
		return razonsocialcomprador;
	}

	public void setRazonsocialcomprador(String razonsocialcomprador) {
		this.razonsocialcomprador = razonsocialcomprador;
	}

	public Double getSubtotal0() {
		return subtotal0;
	}

	public void setSubtotal0(Double subtotal0) {
		this.subtotal0 = subtotal0;
	}

	public Double getSubtotal12() {
		return subtotal12;
	}

	public void setSubtotal12(Double subtotal12) {
		this.subtotal12 = subtotal12;
	}

	public Double getTotaldescuento() {
		return totaldescuento;
	}

	public void setTotaldescuento(Double totaldescuento) {
		this.totaldescuento = totaldescuento;
	}

	public Double getTotalimpuesto() {
		return totalimpuesto;
	}

	public void setTotalimpuesto(Double totalimpuesto) {
		this.totalimpuesto = totalimpuesto;
	}

	public Double getTotalsinimpuestos() {
		return totalsinimpuestos;
	}

	public void setTotalsinimpuestos(Double totalsinimpuestos) {
		this.totalsinimpuestos = totalsinimpuestos;
	}

	public String getCanalcreacion() {
		return canalcreacion;
	}

	public void setCanalcreacion(String canalcreacion) {
		this.canalcreacion = canalcreacion;
	}

	public String getCodigocliente() {
		return codigocliente;
	}

	public void setCodigocliente(String codigocliente) {
		this.codigocliente = codigocliente;
	}

	public String getCodigosucursal() {
		return codigosucursal;
	}

	public void setCodigosucursal(String codigosucursal) {
		this.codigosucursal = codigosucursal;
	}

	public Long getDiaspago() {
		return diaspago;
	}

	public void setDiaspago(Long diaspago) {
		this.diaspago = diaspago;
	}

	public String getPlacavehiculo() {
		return placavehiculo;
	}

	public void setPlacavehiculo(String placavehiculo) {
		this.placavehiculo = placavehiculo;
	}

	public String getUsuarioasignado() {
		return usuarioasignado;
	}

	public void setUsuarioasignado(String usuarioasignado) {
		this.usuarioasignado = usuarioasignado;
	}

	public String getUsuarioasignante() {
		return usuarioasignante;
	}

	public void setUsuarioasignante(String usuarioasignante) {
		this.usuarioasignante = usuarioasignante;
	}

	public String getDescripcionlistaprecio() {
		return descripcionlistaprecio;
	}

	public void setDescripcionlistaprecio(String descripcionlistaprecio) {
		this.descripcionlistaprecio = descripcionlistaprecio;
	}

	public String getTipopago() {
		return tipopago;
	}

	public void setTipopago(String tipopago) {
		this.tipopago = tipopago;
	}

}
