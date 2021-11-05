package com.alimesa.ordenpedido.vista.ejecucion.mantenimiento;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.FileUploadEvent;

import com.alimesa.ordenpedido.librerias.vista.JsfUtil;
import com.alimesa.ordenpedido.librerias.vista.beans.BeanMantenedorGenerico;
import com.alimesa.ordenpedido.librerias.vista.beans.NombresEtiquetas;
import com.alimesa.ordenpedido.librerias.vista.beans.oyentes.ControlListaEntidadesPersonalizada;
import com.alimesa.ordenpedido.librerias.vista.beans.oyentes.PostConstructListener;
import com.alimesa.ordenpedido.librerias.vista.beans.oyentes.PostSeleccionEntidadListener;
import com.alimesa.ordenpedido.librerias.vista.beans.oyentes.PostTransaccionListener;
import com.alimesa.ordenpedido.librerias.vista.beans.oyentes.PreTransaccionListener;
import com.alimesa.ordenpedido.librerias.vista.exceptions.ErrorAccionesPreTransaccion;
import com.alimesa.ordenpedido.modelo.ejecucion.CategoriaProducto;
import com.alimesa.ordenpedido.modelo.ejecucion.Empresa;
import com.alimesa.ordenpedido.modelo.ejecucion.ListaPrecioProducto;
import com.alimesa.ordenpedido.modelo.ejecucion.Producto;
import com.alimesa.ordenpedido.modelo.ejecucion.StockProducto;
import com.alimesa.ordenpedido.repositorios.ejecucion.ProductoEAO;
import com.alimesa.ordenpedido.servicios.ejecucion.mantenimiento.ServicioMantenedorCategoria;
import com.alimesa.ordenpedido.servicios.ejecucion.mantenimiento.ServicioMantenimientoDetalleProducto;
import com.alimesa.ordenpedido.servicios.ejecucion.mantenimiento.ServicioMantenimientoListaPrecioProducto;
import com.alimesa.ordenpedido.servicios.ejecucion.mantenimiento.ServicioMantenimientoProducto;
import com.alimesa.ordenpedido.servicios.ejecucion.mantenimiento.ServicioMantenimientoStockProducto;

@ManagedBean
@ViewScoped
public class BeanMantenedorProducto
		extends BeanMantenedorGenerico<ServicioMantenimientoProducto, Long, Producto, ProductoEAO> {
	@EJB
	private ServicioMantenimientoProducto servicioMantenedor;

	
	@EJB
	private ServicioMantenedorCategoria lServicioTipoProd;

	@EJB
	private ServicioMantenimientoListaPrecioProducto lServicioListaProducto;

	@EJB
	private ServicioMantenimientoDetalleProducto lServicioDetalleProducto;

	@EJB
	private ServicioMantenimientoStockProducto lServicioStockProducto;

	private Long lEmpresaID;

	private Long lTipoProducto;

	private byte[] imagen;

	private Long idImpuestoIva;

	private Long idImpuestoICE;

	private List<ListaPrecioProducto> listasPrecio;

	private List<StockProducto> stockProducto;

	private String filProducto;

	public BeanMantenedorProducto() {
		super(Producto.class);

		listasPrecio = new ArrayList<>();
		stockProducto = new ArrayList<>();
		addPostConstructuListener(new PostConstructListener() {
			@Override
			public void metodoPostConstruct() {

				lEmpresaID = 0L;
				lTipoProducto = 0L;

			}
		});

		addPostTransaccion(new PostTransaccionListener() {
			@Override
			public void metodoPostTransaccion() {
				lEmpresaID = 0L;
				lTipoProducto = 0L;
			}
		});

		addPreTransaccionServicioListener(new PreTransaccionListener() {
			@Override
			public void accionPreTransaccion() throws ErrorAccionesPreTransaccion {

				Empresa lEmpresa = new Empresa();
				lEmpresa.setId(lEmpresaID);
				CategoriaProducto lTipoProd = new CategoriaProducto();
				lTipoProd.setId(lTipoProducto);
				entidadRegistrar.setlEmpresa(lEmpresa);
				entidadRegistrar.setTipoProducto(lTipoProd);

				if (imagen != null) {

					entidadRegistrar.setImagenReferencia(imagen);

				} else {
					System.out.println("No registra avatar");
				}

			}
		});

		addControlListaEntidadesPersonalizada(new ControlListaEntidadesPersonalizada() {
			@Override
			public void cargarListaEntidades() {
				setListaEntidades(servicioMantenedor.listaUltimosProductos(20L));
			}
		});

		addPostSeleccionRegistroListener(new PostSeleccionEntidadListener<Producto, Long>() {

			@Override
			public void postSeleccionRegistro(Producto pEntidadSeleccionada) {

				lEmpresaID = pEntidadSeleccionada.getlEmpresa().getId();
				lTipoProducto = pEntidadSeleccionada.getTipoProducto().getId();
				idImpuestoIva = pEntidadSeleccionada.getIdImpuestoIVA();
				idImpuestoICE = pEntidadSeleccionada.getIdImpuestoICE();

			}
		});

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected ServicioMantenimientoProducto getServicioMantenedor() {
		// TODO Auto-generated method stub
		return servicioMantenedor;
	}

	protected void cargarListaEtiquetas() {
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TITULOPAGINA.toString(), "Mantenimiento Productos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.DESCRIPCIONPAGINA.toString(), "Mantenedor Productos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.AYUDAPAGINA.toString(), "Cree o edite Productos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TAB.toString(), "Datos Productos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERATABLA.toString(), "Lista de Productos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERADIALOGO.toString(), "Actualización de Productos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERAPANELDIALOGO.toString(), "Datos Productos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TABLAVACIA.toString(), JsfUtil.MENSAJE_INFO_SINRESULTADO);
	}

	

	public Long getlEmpresaID() {
		return lEmpresaID;
	}

	public void setlEmpresaID(Long lEmpresaID) {
		this.lEmpresaID = lEmpresaID;
	}

	public Long getlTipoProducto() {
		return lTipoProducto;
	}

	public void setlTipoProducto(Long lTipoProducto) {
		this.lTipoProducto = lTipoProducto;
	}

	public void buscarTipos(AjaxBehaviorEvent evento) {

	}

	public void calcularIVA(AjaxBehaviorEvent evento) {
		if (entidadRegistrar.getlPrecioUnitario() == null || entidadRegistrar.getlPrecioUnitario() <= 0) {
			addMensajeAdvertencia("Debe ingresar un valor positivo en el campo precio unitario");
			return;
		}

		entidadRegistrar
				.setlPrecioIVA(entidadRegistrar.getlPrecioUnitario() * (entidadRegistrar.getlPorcentajeIva() / 100)
						+ entidadRegistrar.getlPrecioUnitario());
	}

	public void calcularICE(AjaxBehaviorEvent evento) {
		if (entidadRegistrar.getlPrecioUnitario() == null || entidadRegistrar.getlPrecioUnitario() <= 0) {
			addMensajeAdvertencia("Debe ingresar un valor positivo en el campo precio unitario");
			return;
		}

		entidadRegistrar
				.setlPrecioICE(entidadRegistrar.getlPrecioUnitario() * (entidadRegistrar.getlPorcentajeICE() / 100)
						+ entidadRegistrar.getlPrecioUnitario());
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public void subir(FileUploadEvent event) {
		System.out.println("Subir archivo");
		imagen = event.getFile().getContent();
	}

	public Long getIdImpuestoIva() {
		return idImpuestoIva;
	}

	public void setIdImpuestoIva(Long idImpuestoIva) {
		this.idImpuestoIva = idImpuestoIva;
	}

	public Long getIdImpuestoICE() {
		return idImpuestoICE;
	}

	public void setIdImpuestoICE(Long idImpuestoICE) {
		this.idImpuestoICE = idImpuestoICE;
	}

	public void obtenerListaPrecio(ActionEvent evento) {

		entidadRegistrar = (Producto) evento.getComponent().getAttributes().get(getNombreAtributoCambioEstado());

		String lQuery = "select * from lista_precio_productos where id_producto = :idProducto ";
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idProducto", entidadRegistrar.getId());

		listasPrecio = lServicioListaProducto.ejecutarQueryNativoObjeto(lQuery, parametros, ListaPrecioProducto.class);

	}

	public void obtenerStock(ActionEvent evento) {

		entidadRegistrar = (Producto) evento.getComponent().getAttributes().get(getNombreAtributoCambioEstado());

		String lQuery = "select * from stock_productos where id_producto = :idProducto";
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idProducto", entidadRegistrar.getId());

		stockProducto = lServicioStockProducto.ejecutarQueryNativoObjeto(lQuery, parametros, StockProducto.class);

	}

	public List<ListaPrecioProducto> getListasPrecio() {
		return listasPrecio;
	}

	public void setListasPrecio(List<ListaPrecioProducto> listasPrecio) {
		this.listasPrecio = listasPrecio;
	}

	public List<StockProducto> getStockProducto() {
		return stockProducto;
	}

	public void setStockProducto(List<StockProducto> stockProducto) {
		this.stockProducto = stockProducto;
	}

	public String getFilProducto() {
		return filProducto;
	}

	public void setFilProducto(String filProducto) {
		this.filProducto = filProducto;
	}

	public void buscarProductos() {

		if (filProducto.trim().length() == 0) {
			setListaEntidades(servicioMantenedor.listaUltimosProductos(20L));
			return;
		}

		if (filProducto.length() < 4) {
			setListaEntidades(new ArrayList<Producto>());
			return;
		}

		String query = "select * from productos where upper(lnombre) like :criterio ";
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("criterio", "%" + filProducto.toUpperCase() + "%");
		setListaEntidades(servicioMantenedor.ejecutarQueryNativoObjeto(query, parametros, Producto.class));

	}

}
