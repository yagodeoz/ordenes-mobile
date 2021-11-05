package com.alimesa.ordenpedido.controlador.ejecucion.modelo;

import java.io.Serializable;
import java.util.List;

import com.alimesa.ordenpedido.modelo.ejecucion.Banco;
import com.alimesa.ordenpedido.modelo.ejecucion.CabCobro;
import com.alimesa.ordenpedido.modelo.ejecucion.CabDespacho;
import com.alimesa.ordenpedido.modelo.ejecucion.CategoriaProducto;
import com.alimesa.ordenpedido.modelo.ejecucion.Cliente;
import com.alimesa.ordenpedido.modelo.ejecucion.DetalleDespacho;
import com.alimesa.ordenpedido.modelo.ejecucion.ListaPrecio;
import com.alimesa.ordenpedido.modelo.ejecucion.ListaPrecioProducto;
import com.alimesa.ordenpedido.modelo.ejecucion.Producto;
import com.alimesa.ordenpedido.modelo.ejecucion.StockDespacho;
import com.alimesa.ordenpedido.modelo.ejecucion.Sucursal;

public class BaseDatosRespuesta implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Sucursal> listaSucursales;
	private List<Cliente> listaClientes;
	private List<Producto> listaProductos;
	private List<CategoriaProducto> listaCategoria;
	private List<ListaPrecio> listaPrecio;
	private List<ListaPrecioProducto> listaPrecioProducto;
	private List<CabCobro> listaCobros;
	private List<Banco> listaBanco;
	private List<CabDespacho> listaDespacho;
	private List<DetalleDespacho> listaDetallesDespacho;
	private List<StockDespacho> listaStockDespacho;

	public BaseDatosRespuesta(List<Sucursal> listaSucursales, List<Cliente> listaClientes,
			List<Producto> listaProductos, List<CabCobro> listaCobros, List<Banco> listaBanco, List<CabDespacho> listaDespacho, List<DetalleDespacho> listaDetallesDespacho,
			List<StockDespacho> listaStockDespacho
			) {
		/* 29 */ this.listaSucursales = listaSucursales;
		/* 30 */ this.listaClientes = listaClientes;
		/* 31 */ this.listaProductos = listaProductos;
		this.listaCobros = listaCobros;
		this.listaBanco = listaBanco;
		this.listaDespacho = listaDespacho;
		this.listaDetallesDespacho = listaDetallesDespacho;
		this.listaStockDespacho = listaStockDespacho;
	}

	public BaseDatosRespuesta() {
	}

	public List<Sucursal> getListaSucursales() {
		/* 38 */ return this.listaSucursales;
	}

	public void setListaSucursales(List<Sucursal> listaSucursales) {
		/* 41 */ this.listaSucursales = listaSucursales;
	}

	public List<Cliente> getListaClientes() {
		/* 44 */ return this.listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		/* 47 */ this.listaClientes = listaClientes;
	}

	public List<Producto> getListaProductos() {
		/* 50 */ return this.listaProductos;
	}

	public void setListaProductos(List<Producto> listaProductos) {
		/* 53 */ this.listaProductos = listaProductos;
	}

	public List<CategoriaProducto> getListaCategoria() {
		/* 56 */ return this.listaCategoria;
	}

	public void setListaCategoria(List<CategoriaProducto> listaCategoria) {
		/* 59 */ this.listaCategoria = listaCategoria;
	}

	public List<ListaPrecio> getListaPrecio() {
		/* 62 */ return this.listaPrecio;
	}

	public void setListaPrecio(List<ListaPrecio> listaPrecio) {
		/* 65 */ this.listaPrecio = listaPrecio;
	}

	public List<ListaPrecioProducto> getListaPrecioProducto() {
		/* 68 */ return this.listaPrecioProducto;
	}

	public void setListaPrecioProducto(List<ListaPrecioProducto> listaPrecioProducto) {
		/* 71 */ this.listaPrecioProducto = listaPrecioProducto;
	}

	public List<CabCobro> getListaCobros() {
		return listaCobros;
	}

	public void setListaCobros(List<CabCobro> listaCobros) {
		this.listaCobros = listaCobros;
	}

	public List<Banco> getListaBanco() {
		return listaBanco;
	}

	public void setListaBanco(List<Banco> listaBanco) {
		this.listaBanco = listaBanco;
	}

	public List<CabDespacho> getListaDespacho() {
		return listaDespacho;
	}

	public void setListaDespacho(List<CabDespacho> listaDespacho) {
		this.listaDespacho = listaDespacho;
	}

	public List<DetalleDespacho> getListaDetallesDespacho() {
		return listaDetallesDespacho;
	}

	public void setListaDetallesDespacho(List<DetalleDespacho> listaDetallesDespacho) {
		this.listaDetallesDespacho = listaDetallesDespacho;
	}

	public List<StockDespacho> getListaStockDespacho() {
		return listaStockDespacho;
	}

	public void setListaStockDespacho(List<StockDespacho> listaStockDespacho) {
		this.listaStockDespacho = listaStockDespacho;
	}
	
	
	
	
}

/*
 * Location:
 * D:\apps\api-rest-ordenpedido\1.0\api-rest-ordenpedido-1.0.war!\WEB-INF\
 * classes\com\alimesa\ordenpedido\controlador\ejecucion\modelo\
 * BaseDatosRespuesta.class Java compiler version: 11 (55.0) JD-Core Version:
 * 1.1.3
 */