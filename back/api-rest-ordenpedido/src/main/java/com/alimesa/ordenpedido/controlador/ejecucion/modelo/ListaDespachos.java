package com.alimesa.ordenpedido.controlador.ejecucion.modelo;

import java.io.Serializable;
import java.util.List;

public class ListaDespachos implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<CabeceraDespachoMobile> listaCabeceraDespacho;

	private List<DetalleDespachoMobile> listaDetalleDespacho;

	public List<CabeceraDespachoMobile> getListaCabeceraDespacho() {
		return listaCabeceraDespacho;
	}

	public void setListaCabeceraDespacho(List<CabeceraDespachoMobile> listaCabeceraDespacho) {
		this.listaCabeceraDespacho = listaCabeceraDespacho;
	}

	public List<DetalleDespachoMobile> getListaDetalleDespacho() {
		return listaDetalleDespacho;
	}

	public void setListaDetalleDespacho(List<DetalleDespachoMobile> listaDetalleDespacho) {
		this.listaDetalleDespacho = listaDetalleDespacho;
	}

}
