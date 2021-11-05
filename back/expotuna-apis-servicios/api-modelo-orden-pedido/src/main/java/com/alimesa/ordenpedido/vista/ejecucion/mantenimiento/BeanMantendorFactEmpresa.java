package com.alimesa.ordenpedido.vista.ejecucion.mantenimiento;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

import com.alimesa.ordenpedido.librerias.vista.JsfUtil;
import com.alimesa.ordenpedido.librerias.vista.beans.BeanMantenedorGenerico;
import com.alimesa.ordenpedido.librerias.vista.beans.NombresEtiquetas;
import com.alimesa.ordenpedido.librerias.vista.beans.oyentes.PostConstructListener;
import com.alimesa.ordenpedido.librerias.vista.beans.oyentes.PostInicializacionEntidad;
import com.alimesa.ordenpedido.librerias.vista.beans.oyentes.PreTransaccionListener;
import com.alimesa.ordenpedido.librerias.vista.exceptions.ErrorAccionesPreTransaccion;
import com.alimesa.ordenpedido.modelo.ejecucion.Empresa;
import com.alimesa.ordenpedido.modelo.ejecucion.Sucursal;
import com.alimesa.ordenpedido.modelo.seguridad.Usuario;
import com.alimesa.ordenpedido.repositorios.ejecucion.EmpresaEAO;
import com.alimesa.ordenpedido.servicios.ejecucion.mantenimiento.ServicioMantenedorEmpresa;
import com.alimesa.ordenpedido.servicios.ejecucion.mantenimiento.ServicioMantenedorSucrusal;
import com.alimesa.ordenpedido.servicios.seguridad.mantenimiento.ServicioMantenedorUsuario;


@ManagedBean
@ViewScoped
public class BeanMantendorFactEmpresa
		extends BeanMantenedorGenerico<ServicioMantenedorEmpresa, Long, Empresa, EmpresaEAO> {

	private static final long serialVersionUID = 1L;
	
	private static final String JPG = ".jpg";

	private static final String SEPARADOR = "/";

	private static final String P12 = ".p12";

	private Date lFechaLimiteVigenciaCertificado;

	private String lClave;

	private DualListModel<String> listaSeleccionUsuario;

	private byte[] imagen;

	private List<Sucursal> lListaEstablecimiento;



	private Long idEstablecimientoSel;

	@EJB
	private ServicioMantenedorSucrusal lServicioEstablecimiento;

	@EJB
	private ServicioMantenedorUsuario lServicioUsuario;


	@EJB
	private ServicioMantenedorEmpresa lServicioMantenedorFactEmpresa;
	
	public BeanMantendorFactEmpresa() {
		super(Empresa.class);
		lClave = "";
		addPostConstructuListener(new PostConstructListener() {

			public void metodoPostConstruct() {
				lListaEstablecimiento = new ArrayList<>();
				listaSeleccionUsuario = new DualListModel<>();
				lFechaLimiteVigenciaCertificado = new Date();
				entidadRegistrar = new Empresa();
				lClave = "";
				System.out.println("pruebas");
			}
		});

		addPostEventoInicializacion(new PostInicializacionEntidad() {

			@Override
			public void eventoPostInicializacion() {
				lListaEstablecimiento = new ArrayList<>();
				entidadRegistrar = new Empresa();
				lFechaLimiteVigenciaCertificado = new Date();
				lClave = "";
			}
		});

		
		
		addPreTransaccionServicioListener(new PreTransaccionListener() {

			@Override
			public void accionPreTransaccion() throws ErrorAccionesPreTransaccion {
				entidadRegistrar.setlFormatoCertificado("pk12");
				entidadRegistrar.setlRutaCertificado("D:/resultado_test/cert_key.p12");
				entidadRegistrar.setlIdSuscriptor(1L);

			}
		});

		addPreTransaccionServicioListener(new PreTransaccionListener() {
			@Override
			public void accionPreTransaccion() throws ErrorAccionesPreTransaccion {
				if (entidadRegistrar.getId() == null) {
					Empresa lEmpresa = lServicioMantenedorFactEmpresa.obtenerObjetoPropiedad("lIdentificacion",
							entidadRegistrar.getlIdentificacion(), Empresa.class);
					if (lEmpresa != null)
						throw new ErrorAccionesPreTransaccion("La empresa con identificación : "
								+ lEmpresa.getlIdentificacion() + ", ya se encuentra registrada");
				}

				if (imagen != null) {

					entidadRegistrar.setImagenReferencia(imagen);

				} else {
					System.out.println("No registra avatar");
				}

			}
		});

	}
	
	public List<Sucursal> getlListaEstablecimiento() {
		return lListaEstablecimiento;
	}

	public void setlListaEstablecimiento(List<Sucursal> lListaEstablecimiento) {
		this.lListaEstablecimiento = lListaEstablecimiento;
	}

	/**
	 * 
	 */
	

	protected void cargarListaEtiquetas() {
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TITULOPAGINA.toString(), "Mantenimiento Empresas");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.DESCRIPCIONPAGINA.toString(), "Mantenedor Empresas");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.AYUDAPAGINA.toString(), "Cree o edite Empresas");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TAB.toString(), "Datos Empresas");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERATABLA.toString(), "Lista de Empresas");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERADIALOGO.toString(), "Actualización de Empresas");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERAPANELDIALOGO.toString(), "Datos Empresas");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TABLAVACIA.toString(), JsfUtil.MENSAJE_INFO_SINRESULTADO);
	}

	protected ServicioMantenedorEmpresa getServicioMantenedor() {
		return lServicioMantenedorFactEmpresa;
	}

	public void subirCertificado(ActionEvent lEvento) {
		entidadRegistrar = (Empresa) lEvento.getComponent().getAttributes().get("EMPRESA");
	}

	public void subirLogo(ActionEvent lEvento) {
		entidadRegistrar = (Empresa) lEvento.getComponent().getAttributes().get("EMPRESA");
	}

	public void asignarUsuarios(ActionEvent lEvento) {
		entidadRegistrar = (Empresa) lEvento.getComponent().getAttributes().get("EMPRESA");

		cargarUsuariosAsignados();

	}

	public Date getlFechaLimiteVigenciaCertificado() {
		return lFechaLimiteVigenciaCertificado;
	}

	public void setlFechaLimiteVigenciaCertificado(Date lFechaLimiteVigenciaCertificado) {
		this.lFechaLimiteVigenciaCertificado = lFechaLimiteVigenciaCertificado;
	}

	public String getlClave() {
		return lClave;
	}

	public void setlClave(String lClave) {
		this.lClave = lClave;
	}

	public DualListModel<String> getListaSeleccionUsuario() {
		return listaSeleccionUsuario;
	}

	public void setListaSeleccionUsuario(DualListModel<String> listaSeleccionUsuario) {
		this.listaSeleccionUsuario = listaSeleccionUsuario;
	}

	public void controlTransferencia(TransferEvent pEvento) {

		@SuppressWarnings("unchecked")
		List<String> lUsuariosTransferidos = (List<String>) pEvento.getItems();
		try {
			if (pEvento.isAdd()) {
				agregarUsuariosEmpresa(lUsuariosTransferidos);
			} else {
				if (pEvento.isRemove()) {
					eliminarUsuariosEmpresa(lUsuariosTransferidos);
				}
			}
			cargarUsuariosAsignados();
		} catch (Exception e) {
			e.printStackTrace();
			addError(e.getMessage());

		}
	}

	private void eliminarUsuariosEmpresa(List<String> lUsuariosTransferidos) {

		Empresa lEmpresa = lServicioMantenedorFactEmpresa.obtenerObjtoPK(entidadRegistrar.getId(),
				Empresa.class);
		
	}

	private void agregarUsuariosEmpresa(List<String> lUsuariosTransferidos) {
		try {
			Empresa lEmpresa = lServicioMantenedorFactEmpresa.obtenerObjtoPK(entidadRegistrar.getId(),
					Empresa.class);
			for (String lUsuario : lUsuariosTransferidos) {
				Usuario lUsuarioDB = lServicioUsuario.obtenerObjetoPropiedad("usuario", lUsuario, Usuario.class);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			addError("No es posible continuar con la asignación");
		}

	}

	private void cargarUsuariosAsignados() {
		List<String> lListaUsuariosAsignados = new ArrayList<String>();
		List<String> lListaUsuariosPorAsignar = new ArrayList<String>();
		listaSeleccionUsuario = new DualListModel<>(lListaUsuariosPorAsignar, lListaUsuariosAsignados);
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

	public void sucursal(ActionEvent evento) {
		entidadRegistrar = (Empresa) evento.getComponent().getAttributes().get(getNombreAtributoCambioEstado());
		listarEstblecimiento();

	}

	private void listarEstblecimiento() {
		HashMap<String, Object> lParametro = new HashMap<>();
		lParametro.put("idEmpresa", entidadRegistrar.getId());
		String lquery = "select * from SUCURSALES where ID_EMPRESA = :idEmpresa and estado = 'A'";
		lListaEstablecimiento = lServicioEstablecimiento.ejecutarQueryNativoObjeto(lquery, lParametro,
				Sucursal.class);
	}

	public void puntosEmison(ActionEvent evento) {
		entidadRegistrar = (Empresa) evento.getComponent().getAttributes().get(getNombreAtributoCambioEstado());

		listarEstblecimiento();
		if (lListaEstablecimiento.size() == 0) {
			addMensajeAdvertencia(
					"Por favor ingrese los SUCURSALES para la empresa " + entidadRegistrar.getlRazonSocial());
			return;
		}

		Sucursal lEstablecimientoDefecto = lListaEstablecimiento.get(0);
		idEstablecimientoSel = lEstablecimientoDefecto.getId();
		listaPuntosEstablecimiento(idEstablecimientoSel);

	}

	private void listaPuntosEstablecimiento(Long idEstablcimiento) {
		HashMap<String, Object> lParametro = new HashMap<>();
		lParametro.put("idEstablecimiento", idEstablcimiento);
		String lquery = "select * from PUNTO_EMISION where ID_ESTABLECIMIENTO = :idEstablecimiento and estado = 'A'";
	}

	public ServicioMantenedorSucrusal getlServicioEstablecimiento() {
		return lServicioEstablecimiento;
	}

	public void setlServicioEstablecimiento(ServicioMantenedorSucrusal lServicioEstablecimiento) {
		this.lServicioEstablecimiento = lServicioEstablecimiento;
	}

	public void agregarEstablecimiento() {
		if (lListaEstablecimiento == null)
			lListaEstablecimiento = new ArrayList<>();

		if (lListaEstablecimiento.size() > 0) {
			Sucursal lActual = lListaEstablecimiento.get(lListaEstablecimiento.size() - 1);
			if (lActual.getId() != null)
				lListaEstablecimiento.add(new Sucursal());
		} else {
			lListaEstablecimiento.add(new Sucursal());
		}

	}

	public void onRowCancel(RowEditEvent event) {
		Sucursal lEstable = (Sucursal) event.getObject();
		if (lEstable.getId() == null) {
			listarEstblecimiento();
		} else {
			lEstable.setEstado("I");
			lEstable.setAuditoria(JsfUtil.getUsuarioAutenticado().getName());
			lEstable.setFechaActualizacion(new Date());
			try {
				lServicioEstablecimiento.guardarActualizar(lEstable);
				listarEstblecimiento();
			} catch (Exception e) {
				e.printStackTrace();
				addMensajeAdvertencia("Imposible realizar la transacción");
			}
		}
	}

	public void onRowEdit(RowEditEvent event) {
		Sucursal lEstable = (Sucursal) event.getObject();

		if (lEstable.getId() == null) {
			lEstable.setlEmpresa(entidadRegistrar);
			lEstable.setEstado("A");
			lEstable.setFechaRegistro(new Date());
			lEstable.setObservacion("REGISTRO DE ESTABLECIMIENTO");
		} else {
			lEstable.setFechaActualizacion(new Date());
		}
		lEstable.setAuditoria(JsfUtil.getUsuarioAutenticado().getName());

		try {
			lServicioEstablecimiento.guardarActualizar(lEstable);
			listarEstblecimiento();
		} catch (Exception e) {
			e.printStackTrace();
			addMensajeAdvertencia("Imposible realizar la transacción");
		}
	}

	public void agregarPunto() {
		

	}

	public void buscarPuntos(AjaxBehaviorEvent evento) {
		listaPuntosEstablecimiento(idEstablecimientoSel);
	}

	public void onRowEditPunto(RowEditEvent event) {

		
	}

	public void onRowCancelPunto(RowEditEvent event) {
		
	}

	

	public Long getIdEstablecimientoSel() {
		return idEstablecimientoSel;
	}

	public void setIdEstablecimientoSel(Long idEstablecimientoSel) {
		this.idEstablecimientoSel = idEstablecimientoSel;
	}

}
