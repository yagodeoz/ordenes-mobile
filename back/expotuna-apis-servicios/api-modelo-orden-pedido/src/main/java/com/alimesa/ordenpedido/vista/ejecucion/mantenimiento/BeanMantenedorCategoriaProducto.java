package com.alimesa.ordenpedido.vista.ejecucion.mantenimiento;

import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

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
import com.alimesa.ordenpedido.repositorios.ejecucion.CategoriaProductoEAO;
import com.alimesa.ordenpedido.servicios.ejecucion.mantenimiento.ServicioMantenedorCategoria;
import com.alimesa.ordenpedido.servicios.ejecucion.mantenimiento.ServicioMantenedorEmpresa;



@ManagedBean
@ViewScoped
public class BeanMantenedorCategoriaProducto
		extends BeanMantenedorGenerico<ServicioMantenedorCategoria, Long, CategoriaProducto, CategoriaProductoEAO> {
	@EJB
	private ServicioMantenedorCategoria servicioMantenedor;

	@Inject
	private ServicioMantenedorEmpresa lServicioUsuarioEmpresa;

	private List<Empresa> lListaEmpresaCombo;

	private Long lEmpresaID;

	public BeanMantenedorCategoriaProducto() {
		super(CategoriaProducto.class);

		addPostTransaccion(new PostTransaccionListener() {
			@Override
			public void metodoPostTransaccion() {
				lEmpresaID = 0L;
			}
		});

		addPreTransaccionServicioListener(new PreTransaccionListener() {

			@Override
			public void accionPreTransaccion() throws ErrorAccionesPreTransaccion {
				Empresa lEmpresa = new Empresa();
				lEmpresa.setId(lEmpresaID);
				entidadRegistrar.setlEmpresa(lEmpresa);

			}
		});

		addPostConstructuListener(new PostConstructListener() {

			@Override
			public void metodoPostConstruct() {
				lEmpresaID = 0L;
				lListaEmpresaCombo = obtenerEmpresaUsuario();
			}
		});

		addControlListaEntidadesPersonalizada(new ControlListaEntidadesPersonalizada() {

			@Override
			public void cargarListaEntidades() {
				HashMap<String, Object> lParametrosEmpresa = new HashMap<>();
				lParametrosEmpresa.put("usuario", JsfUtil.getUsuarioAutenticado().getName());
				String lQuery = "select *  from POS_TIPO_PRODUCTO where ID_EMPRESA  in ( "
						+ "select b.id_empresa from usuarios a, usuario_empresa b "
						+ "where a.usuario = :usuario " + "and a.estado = 'A' " + "and b.id_usuario = a.id "
						+ "and b.estado = 'A') " + "and estado = 'A'";
				List<CategoriaProducto> lListaTipo = servicioMantenedor.ejecutarQueryNativoObjeto(lQuery,
						lParametrosEmpresa, CategoriaProducto.class);
				setListaEntidades(lListaTipo);
			}
		});

		addPostSeleccionRegistroListener(new PostSeleccionEntidadListener<CategoriaProducto, Long>() {

			@Override
			public void postSeleccionRegistro(CategoriaProducto pEntidadSeleccionada) {
				lEmpresaID = pEntidadSeleccionada.getlEmpresa().getId();

			}
		});
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected ServicioMantenedorCategoria getServicioMantenedor() {
		// TODO Auto-generated method stub
		return servicioMantenedor;
	}

	protected void cargarListaEtiquetas() {
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TITULOPAGINA.toString(), "Mantenimiento Tipo Productos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.DESCRIPCIONPAGINA.toString(), "Mantenedor Tipo Productos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.AYUDAPAGINA.toString(), "Cree o edite Tipo Productos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TAB.toString(), "Datos  Tipo Productos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERATABLA.toString(), "Lista de Tipo Productos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERADIALOGO.toString(), "Actualización de Tipo Productos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERAPANELDIALOGO.toString(), "Datos Tipo Productos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TABLAVACIA.toString(), JsfUtil.MENSAJE_INFO_SINRESULTADO);
	}

	private List<Empresa> obtenerEmpresaUsuario() {
		HashMap<String, Object> lParametrosEmpresa = new HashMap<>();
		lParametrosEmpresa.put("usuario", JsfUtil.getUsuarioAutenticado().getName());
		String lQuery = "select * from onix_empresa where id in ( "
				+ "select b.id_empresa from onix_usuarios a, onix_usuario_empresa b " + "where a.usuario = :usuario "
				+ "and a.estado = 'A' " + "and b.id_usuario = a.id " + "and b.estado = 'A') " + "and estado = 'A'";
		List<Empresa> lListaEmpresa = lServicioUsuarioEmpresa.ejecutarQueryNativoObjeto(lQuery, lParametrosEmpresa,
				Empresa.class);
		return lListaEmpresa;
	}

	public List<Empresa> getlListaEmpresaCombo() {
		return lListaEmpresaCombo;
	}

	public void setlListaEmpresaCombo(List<Empresa> lListaEmpresaCombo) {
		this.lListaEmpresaCombo = lListaEmpresaCombo;
	}

	public Long getlEmpresaID() {
		return lEmpresaID;
	}

	public void setlEmpresaID(Long lEmpresaID) {
		this.lEmpresaID = lEmpresaID;
	}

}
