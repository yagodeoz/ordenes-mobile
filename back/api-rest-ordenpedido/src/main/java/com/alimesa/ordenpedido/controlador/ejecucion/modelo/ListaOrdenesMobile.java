package com.alimesa.ordenpedido.controlador.ejecucion.modelo;

import java.io.Serializable;
import java.util.List;

public class ListaOrdenesMobile implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<CabeceraOrdenMobile> cabeceraOrdenes;

	private List<DetalleOrdenMobile> detalleOrdenes;

	public List<CabeceraOrdenMobile> getCabeceraOrdenes() {
		return cabeceraOrdenes;
	}

	public void setCabeceraOrdenes(List<CabeceraOrdenMobile> cabeceraOrdenes) {
		this.cabeceraOrdenes = cabeceraOrdenes;
	}

	public List<DetalleOrdenMobile> getDetalleOrdenes() {
		return detalleOrdenes;
	}

	public void setDetalleOrdenes(List<DetalleOrdenMobile> detalleOrdenes) {
		this.detalleOrdenes = detalleOrdenes;
	}

}

/*
 * Location:
 * D:\apps\api-rest-ordenpedido\1.0\api-rest-ordenpedido-1.0.war!\WEB-INF\
 * classes\com\alimesa\ordenpedido\controlador\ejecucion\modelo\
 * ListaOrdenesMobile.class Java compiler version: 11 (55.0) JD-Core Version:
 * 1.1.3
 */