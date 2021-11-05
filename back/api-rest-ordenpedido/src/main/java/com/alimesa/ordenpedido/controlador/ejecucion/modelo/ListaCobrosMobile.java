package com.alimesa.ordenpedido.controlador.ejecucion.modelo;

import java.io.Serializable;
import java.util.List;

public class ListaCobrosMobile implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<ComprobanteCobroMobile> listaComprobante;
	private List<DetallePagoMobile> listaDetallePagos;

	public List<ComprobanteCobroMobile> getListaComprobante() {
		return listaComprobante;
	}

	public void setListaComprobante(List<ComprobanteCobroMobile> listaComprobante) {
		this.listaComprobante = listaComprobante;
	}

	public List<DetallePagoMobile> getListaDetallePagos() {
		return listaDetallePagos;
	}

	public void setListaDetallePagos(List<DetallePagoMobile> listaDetallePagos) {
		this.listaDetallePagos = listaDetallePagos;
	}

}
