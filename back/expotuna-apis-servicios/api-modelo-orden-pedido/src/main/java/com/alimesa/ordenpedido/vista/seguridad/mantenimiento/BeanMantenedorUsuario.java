package com.alimesa.ordenpedido.vista.seguridad.mantenimiento;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.menu.MenuModel;
import org.primefaces.util.LangUtils;

import com.alimesa.ordenpedido.librerias.exceptions.ErrorServicioNegocio;
import com.alimesa.ordenpedido.librerias.exceptions.ErrorValidacionGeneral;
import com.alimesa.ordenpedido.librerias.generales.UtilEncriptarDataSource;
import com.alimesa.ordenpedido.librerias.vista.JsfUtil;
import com.alimesa.ordenpedido.librerias.vista.anotaciones.ITestParseMenu;
import com.alimesa.ordenpedido.librerias.vista.beans.BeanMantenedorGenerico;
import com.alimesa.ordenpedido.librerias.vista.beans.NombresEtiquetas;
import com.alimesa.ordenpedido.librerias.vista.beans.oyentes.ControlListaEntidadesPersonalizada;
import com.alimesa.ordenpedido.librerias.vista.beans.oyentes.PostCierreDialogo;
import com.alimesa.ordenpedido.librerias.vista.beans.oyentes.PostConstructListener;
import com.alimesa.ordenpedido.librerias.vista.beans.oyentes.PostSeleccionEntidadListener;
import com.alimesa.ordenpedido.librerias.vista.beans.oyentes.PostTransaccionListener;
import com.alimesa.ordenpedido.librerias.vista.beans.oyentes.PreTransaccionListener;
import com.alimesa.ordenpedido.librerias.vista.exceptions.ErrorAccionesPreTransaccion;
import com.alimesa.ordenpedido.librerias.vista.interfaces.IGuardiaUsuarioSession;
import com.alimesa.ordenpedido.modelo.seguridad.Rol;
import com.alimesa.ordenpedido.modelo.seguridad.Usuario;
import com.alimesa.ordenpedido.repositorios.seguridad.UsuarioEAO;
import com.alimesa.ordenpedido.servicios.seguridad.ServicioAplicacion;
import com.alimesa.ordenpedido.servicios.seguridad.mantenimiento.ServicioMantenedorUsuario;
import com.alimesa.ordenpedido.vista.seguridad.TestParseMenu;

// TODO: Auto-generated Javadoc
/**
 * The Class BeanMantenedorUsuario.
 */
@ManagedBean(name = "beanMantenedorUsuario")
@ViewScoped
public class BeanMantenedorUsuario
		extends BeanMantenedorGenerico<ServicioMantenedorUsuario, Long, Usuario, UsuarioEAO> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The servicio mantenedor. */
	@EJB
	private ServicioMantenedorUsuario servicioMantenedor;

	/** The avatar. */
	private byte[] avatar;

	/** The roles seleccionadas. */
	private Long[] rolesSeleccionadas;

	/** The lista roles. */
	private List<Rol> listaRoles;

	/** The lista seleccion rol. */
	private DualListModel<String> listaSeleccionRol;

	/** The lista seleccion rol. */
	private DualListModel<String> listaSeleccionSucursal;

	/** The l contrasenia nueva. */
	private String lContraseniaNueva;

	/** The Constant ES_NUEVO. */
	protected static final String ES_NUEVO = "S";

	/** The servicio aplicacion. */
	@EJB
	private ServicioAplicacion servicioAplicacion;

	/** The parse menu. */
	@Inject
	@ITestParseMenu
	private TestParseMenu parseMenu;

	/** The model. */
	private MenuModel model;

	private String filUsuario;

	/**
	 * Instantiates a new bean mantenedor usuario.
	 */
	public BeanMantenedorUsuario() {
		super(Usuario.class);
		// super.entidadRegistrar.setClave(JsfUtil.CLAVE_INICIAL_DEFAULT);
		listaSeleccionRol = new DualListModel<>();
		listaSeleccionSucursal = new DualListModel<>();
		lContraseniaNueva = "";

		addPostTransaccion(new PostTransaccionListener() {

			@Override
			public void metodoPostTransaccion() {
				// entidadRegistrar.setClave(JsfUtil.CLAVE_INICIAL_DEFAULT);
				rolesSeleccionadas = null;
				lContraseniaNueva = null;
			}
		});

		addPostSeleccionRegistroListener(new PostSeleccionEntidadListener<Usuario, Long>() {
			@Override
			public void postSeleccionRegistro(Usuario pEntidadSeleccionada) {
				rolesSeleccionadas = null;
				cargatListaRoles(pEntidadSeleccionada);
				model = parseMenu.parseMenuOpciones(
						servicioAplicacion.obtenerMenu(pEntidadSeleccionada, IGuardiaUsuarioSession.TIPO_MENU),
						pEntidadSeleccionada.getId());
			}
		});

		addPreTransaccionServicioListener(new PreTransaccionListener() {

			@Override
			public void accionPreTransaccion() throws ErrorAccionesPreTransaccion {
				try {
					if (rolesSeleccionadas != null && rolesSeleccionadas.length != 0) {
						for (Long idRol : rolesSeleccionadas) {
							entidadRegistrar.agregarRol(servicioMantenedor.obtenerRolPorID(idRol));
						}
					}

				} catch (Throwable e) {
					e.printStackTrace();
					throw new ErrorAccionesPreTransaccion("Imposible registrar los roles");
				}

				if (avatar != null) {

					entidadRegistrar.setImagenReferencia(avatar);
					String lPropiedades = System.getProperty("rutaAvatar");
					entidadRegistrar.setRutaAvatar(lPropiedades + entidadRegistrar.getUsuario() + ".jpg");

					FileOutputStream fos = null;
					try {
						fos = new FileOutputStream(entidadRegistrar.getRutaAvatar());
						fos.write(avatar);
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					System.out.println("No registra avatar");
				}

				lContraseniaNueva = lContraseniaNueva == null ? "" : lContraseniaNueva;
				if (lContraseniaNueva.length() > 0) {
					entidadRegistrar.setEsNuevo(ES_NUEVO);
					try {
						entidadRegistrar.setClave(UtilEncriptarDataSource.encode(lContraseniaNueva));
					} catch (Throwable lError) {
						throw new ErrorAccionesPreTransaccion("Error al encriptar la contraseÃ±a");
					}
				}

			}
		});

		addPostConstructuListener(new PostConstructListener() {

			@Override
			public void metodoPostConstruct() {
				listaRoles = getServicioMantenedor().listaRolesEmpresa();
			}
		});

		addPostEventoCierroDialogo(new PostCierreDialogo() {

			@Override
			public void eventosCierreDialogo() {
				lContraseniaNueva = null;

			}
		});

		addControlListaEntidadesPersonalizada(new ControlListaEntidadesPersonalizada() {

			@Override
			public void cargarListaEntidades() {

			}
		});

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.onix.modulo.librerias.vista.beans.BeanMantenedorGenerico#
	 * getServicioMantenedor()
	 */
	protected ServicioMantenedorUsuario getServicioMantenedor() {
		return servicioMantenedor;
	}

	/**
	 * Cargat lista roles.
	 *
	 * @param pEntidadSeleccionada the entidad seleccionada
	 */
	private void cargatListaRoles(Usuario pEntidadSeleccionada) {
		List<String> listaOpcionesAsignadas = servicioMantenedor.obtenerRolesAsignados(pEntidadSeleccionada.getId());
		List<String> listaOpcionesPorAsignar = servicioMantenedor.obtenerRolesPorAsignar(usuarioAutenticado(),
				pEntidadSeleccionada.getId());
		listaSeleccionRol = new DualListModel<>(listaOpcionesPorAsignar, listaOpcionesAsignadas);
	}

	/**
	 * Gets the usuario actual.
	 *
	 * @return the usuario actual
	 */
	public Usuario getUsuarioActual() {
		return getEntidadRegistrar();
	}

	/**
	 * Sets the usuario actual.
	 *
	 * @param usuarioActual the new usuario actual
	 */
	public void setUsuarioActual(Usuario usuarioActual) {
		setEntidadRegistrar(usuarioActual);
	}

	/**
	 * Gets the lista roles.
	 *
	 * @return the lista roles
	 */
	public List<Rol> getListaRoles() {
		return listaRoles;
	}

	/**
	 * Sets the lista roles.
	 *
	 * @param listaRoles the new lista roles
	 */
	public void setListaRoles(List<Rol> listaRoles) {
		this.listaRoles = listaRoles;
	}

	/**
	 * Gets the roles seleccionadas.
	 *
	 * @return the roles seleccionadas
	 */
	public Long[] getRolesSeleccionadas() {
		return rolesSeleccionadas;
	}

	/**
	 * Sets the roles seleccionadas.
	 *
	 * @param rolesSeleccionadas the new roles seleccionadas
	 */
	public void setRolesSeleccionadas(Long[] rolesSeleccionadas) {
		this.rolesSeleccionadas = rolesSeleccionadas;
	}

	/**
	 * Gets the avatar.
	 *
	 * @return the avatar
	 */
	public byte[] getAvatar() {
		return avatar;
	}

	/**
	 * Sets the avatar.
	 *
	 * @param avatar the new avatar
	 */
	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}

	/**
	 * Subir.
	 *
	 * @param event the event
	 */
	public void subir(FileUploadEvent event) {
		System.out.println("Subir archivo");
		avatar = event.getFile().getContent();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.onix.modulo.librerias.vista.beans.BeanMantenedorGenerico#
	 * cargarListaEtiquetas()
	 */
	@Override
	protected void cargarListaEtiquetas() {
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TITULOPAGINA.toString(), "Mantenimiento Usuarios");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.DESCRIPCIONPAGINA.toString(), "Mantenedor Usuarios");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.AYUDAPAGINA.toString(),
				"Desde esta opción, se pueden editar o crear usuarios");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TAB.toString(), "Datos Usuario");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERATABLA.toString(), "Usuarios registrados");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERADIALOGO.toString(), "Actualización Usuario");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERAPANELDIALOGO.toString(), "Datos usuario");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TABLAVACIA.toString(), JsfUtil.MENSAJE_INFO_SINRESULTADO);

	}

	/**
	 * Gets the lista seleccion .
	 *
	 * @return the lista seleccion rol
	 */
	public DualListModel<String> getListaSeleccionRol() {
		return listaSeleccionRol;
	}

	/**
	 * Sets the lista seleccion rol.
	 *
	 * @param listaSeleccionRol the new lista seleccion rol
	 */
	public void setListaSeleccionRol(DualListModel<String> listaSeleccionRol) {
		this.listaSeleccionRol = listaSeleccionRol;
	}

	/**
	 * Control transferencia.
	 *
	 * @param pEvento the evento
	 */
	@SuppressWarnings("unchecked")
	public void controlTransferencia(TransferEvent pEvento) {

		Long referencia = obtenerObjetoSesion(JsfUtil.REFERENCIA_SESION);
		List<String> lRolesTransferidas = (List<String>) pEvento.getItems();
		try {
			if (pEvento.isAdd())

				servicioMantenedor.asigarRoles(lRolesTransferidas, usuarioAutenticado(), entidadRegistrar.getId(),
						referencia);

			else {
				if (pEvento.isRemove() && servicioMantenedor.inactivarRoles(lRolesTransferidas, usuarioAutenticado(),
						entidadRegistrar.getId()) > 0) {
					addError("El usuario debe pernecer por lo menos a un rol");
				}
			}
			cargatListaRoles(entidadRegistrar);
		} catch (ErrorServicioNegocio e) {
			e.printStackTrace();
			addError(e.getMessage());

		} catch (ErrorValidacionGeneral e) {
			e.printStackTrace();
			addError(e.getMessage());
		}

	}
	
	@SuppressWarnings("unchecked")
	public void controlTransferenciasSucursal(TransferEvent pEvento) {

		String referencia = obtenerObjetoSesion(JsfUtil.REFERENCIA_SESION);
		List<String> lRolesTransferidas = (List<String>) pEvento.getItems();
		try {
			if (pEvento.isAdd())

				servicioMantenedor.asigarSucursales(lRolesTransferidas, usuarioAutenticado(), entidadRegistrar.getId(),
						referencia);

			else {
				if (pEvento.isRemove() && servicioMantenedor.inactivarSucursal(lRolesTransferidas, usuarioAutenticado(),
						entidadRegistrar.getId()) > 0) {
					addError("El usuario debe pernecer por lo menos a una sucursal");
				}
			}
			
			cargarListaSucursales();
		
		} catch (ErrorServicioNegocio e) {
			e.printStackTrace();
			addError(e.getMessage());

		} catch (ErrorValidacionGeneral e) {
			e.printStackTrace();
			addError(e.getMessage());
		}

	}

	/**
	 * Gets the l contrasenia nueva.
	 *
	 * @return the l contrasenia nueva
	 */
	public String getlContraseniaNueva() {
		return lContraseniaNueva;
	}

	/**
	 * Sets the l contrasenia nueva.
	 *
	 * @param lContraseniaNueva the new l contrasenia nueva
	 */
	public void setlContraseniaNueva(String lContraseniaNueva) {
		this.lContraseniaNueva = lContraseniaNueva;
	}

	/**
	 * Gets the model.
	 *
	 * @return the model
	 */
	public MenuModel getModel() {
		return model;
	}

	/**
	 * Sets the model.
	 *
	 * @param model the new model
	 */
	public void setModel(MenuModel model) {
		this.model = model;
	}

	public void actualizarUsuario(ActionEvent evento) {
		entidadRegistrar = (Usuario) evento.getComponent().getAttributes().get(getNombreAtributoCambioEstado());
		postSeleccionRegistro(entidadRegistrar);
	}

	public void actualizarContrasenia(ActionEvent evento) {

		entidadRegistrar = (Usuario) evento.getComponent().getAttributes().get(getNombreAtributoCambioEstado());

		lContraseniaNueva = JsfUtil.CLAVE_INICIAL_DEFAULT;

		guardarOActualizar();

	}

	public void actualizarSucursales(ActionEvent evento) {

		entidadRegistrar = (Usuario) evento.getComponent().getAttributes().get(getNombreAtributoCambioEstado());
		cargarListaSucursales();
	}

	private void cargarListaSucursales() {
		List<String> listaSurcursalesAsignadas = servicioMantenedor
				.obtenerSucursalesAsignados(entidadRegistrar.getId());
		List<String> listaSurcursalesPorAsignar = servicioMantenedor
				.obtenerSucursalesPorAsignar(entidadRegistrar.getId());
		listaSeleccionSucursal = new DualListModel<>(listaSurcursalesPorAsignar, listaSurcursalesAsignadas);
	}

	public DualListModel<String> getListaSeleccionSucursal() {
		return listaSeleccionSucursal;
	}

	public void setListaSeleccionSucursal(DualListModel<String> listaSeleccionSucursal) {
		this.listaSeleccionSucursal = listaSeleccionSucursal;
	}

	public void buscarUsuarios() {
		if (filUsuario.length() < 3) {
			setListaEntidades(new ArrayList<Usuario>());
			return;
		}

		String query = "select * from usuarios where upper(usuario) like :criterio ";
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("criterio", "%" + filUsuario.toUpperCase() + "%");
		setListaEntidades(servicioMantenedor.ejecutarQueryNativoObjeto(query, parametros, Usuario.class));

	}

	public String getFilUsuario() {
		return filUsuario;
	}

	public void setFilUsuario(String filUsuario) {
		this.filUsuario = filUsuario;
	}

}
