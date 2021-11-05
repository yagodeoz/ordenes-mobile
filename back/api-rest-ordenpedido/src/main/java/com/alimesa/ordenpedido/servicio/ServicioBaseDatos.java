
package com.alimesa.ordenpedido.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alimesa.ordenpedido.controlador.ejecucion.modelo.BaseDatosRespuesta;
import com.alimesa.ordenpedido.repositorio.ejecucion.RepositorioBanco;
import com.alimesa.ordenpedido.repositorio.ejecucion.RepositorioCabCobro;
import com.alimesa.ordenpedido.repositorio.ejecucion.RepositorioCabDespacho;
import com.alimesa.ordenpedido.repositorio.ejecucion.RepositorioCategoria;
import com.alimesa.ordenpedido.repositorio.ejecucion.RepositorioCliente;
import com.alimesa.ordenpedido.repositorio.ejecucion.RepositorioDetalleDespacho;
import com.alimesa.ordenpedido.repositorio.ejecucion.RepositorioListaPrecio;
import com.alimesa.ordenpedido.repositorio.ejecucion.RepositorioListaPrecioProducto;
import com.alimesa.ordenpedido.repositorio.ejecucion.RepositorioProductos;
import com.alimesa.ordenpedido.repositorio.ejecucion.RepositorioStockDespacho;
import com.alimesa.ordenpedido.repositorio.seguridad.RepositorioSucursal;

@Service
public class ServicioBaseDatos {
	@Autowired
	private RepositorioSucursal repositorioSucursal;
	@Autowired
	private RepositorioCliente repositorioCliente;
	@Autowired
	private RepositorioProductos repositorioProductos;
	@Autowired
	private RepositorioListaPrecio reposiatorioListaPrecio;
	@Autowired
	private RepositorioCategoria reposiatorioCategoria;
	@Autowired
	private RepositorioListaPrecioProducto reposiatorioListaPrecioProd;
	@Autowired
	private RepositorioCabCobro repositorioCabCobro;
	@Autowired
	private RepositorioBanco repositorioBanco;

	@Autowired
	private RepositorioCabDespacho repositorioCabDespacho;
	@Autowired
	private RepositorioDetalleDespacho repositorioDetallesDespacho;

	@Autowired
	private RepositorioStockDespacho repositorioStockDespacho;

	public BaseDatosRespuesta obtenerBaseDatosUsuario(String usuario) {
		/* 37 */ BaseDatosRespuesta baseRespuesta = new BaseDatosRespuesta();

		/* 39 */ baseRespuesta
				.setListaSucursales(this.repositorioSucursal.obtenerSucursalesUsuario(usuario.toUpperCase()));
		/* 40 */ baseRespuesta.setListaClientes(this.repositorioCliente.findAll());
		/* 41 */ baseRespuesta.setListaCategoria(this.reposiatorioCategoria.findAll());
		/* 42 */ baseRespuesta.setListaPrecio(this.reposiatorioListaPrecio.findAll());
		/* 43 */ baseRespuesta.setListaPrecioProducto(
				this.reposiatorioListaPrecioProd.obtenerListaPrecioProductos(usuario.toUpperCase()));

		/* 45 */ baseRespuesta.setListaProductos(this.repositorioProductos.obtenerProductos(usuario.toUpperCase()));

		baseRespuesta.setListaCobros(repositorioCabCobro.obtenerCobrosUsuarioAsignado(usuario.toUpperCase()));
		baseRespuesta.setListaBanco(repositorioBanco.findAll());
		baseRespuesta.setListaStockDespacho(repositorioStockDespacho.obtenerListaStockDespacho(usuario.toUpperCase()));
		baseRespuesta.setListaDetallesDespacho(repositorioDetallesDespacho.obtenerDetallesDespacho(usuario.toUpperCase()));		
		baseRespuesta.setListaDespacho(repositorioCabDespacho.obrtenerCabeceraDespacho(usuario.toUpperCase()));
		

		/* 46 */ return baseRespuesta;
	}
}

/*
 * Location:
 * D:\apps\api-rest-ordenpedido\1.0\api-rest-ordenpedido-1.0.war!\WEB-INF\
 * classes\com\alimesa\ordenpedido\servicio\ServicioBaseDatos.class Java
 * compiler version: 11 (55.0) JD-Core Version: 1.1.3
 */