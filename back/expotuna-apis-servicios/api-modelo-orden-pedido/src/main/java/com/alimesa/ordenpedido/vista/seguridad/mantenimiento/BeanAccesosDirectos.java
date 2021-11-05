package com.alimesa.ordenpedido.vista.seguridad.mantenimiento;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

import com.alimesa.ordenpedido.librerias.exceptions.ErrorServicioNegocio;
import com.alimesa.ordenpedido.librerias.exceptions.ErrorValidacionGeneral;
import com.alimesa.ordenpedido.librerias.vista.JsfUtil;
import com.alimesa.ordenpedido.librerias.vista.anotaciones.ITestUsuarioSession;
import com.alimesa.ordenpedido.librerias.vista.beans.BeanMantenedorGenerico;
import com.alimesa.ordenpedido.librerias.vista.beans.NombresEtiquetas;
import com.alimesa.ordenpedido.librerias.vista.beans.oyentes.PostConstructListener;
import com.alimesa.ordenpedido.librerias.vista.beans.oyentes.PostTransaccionListener;
import com.alimesa.ordenpedido.librerias.vista.interfaces.IUsuarioSession;
import com.alimesa.ordenpedido.modelo.seguridad.AccesosDirecto;
import com.alimesa.ordenpedido.modelo.seguridad.Usuario;
import com.alimesa.ordenpedido.repositorios.seguridad.AccesosDirectoEAO;
import com.alimesa.ordenpedido.servicios.seguridad.mantenimiento.ServicioMantenedorAccesoDirecto;


// TODO: Auto-generated Javadoc
/**
 * The Class BeanAccesosDirectos.
 */
@ManagedBean
@ViewScoped
public class BeanAccesosDirectos
		extends BeanMantenedorGenerico<ServicioMantenedorAccesoDirecto, Long, AccesosDirecto, AccesosDirectoEAO> {

	/** The lista seleccion acceso. */
	private DualListModel<String> listaSeleccionAcceso;

	/** The usuario session. */
	@Inject
	@ITestUsuarioSession
	private IUsuarioSession<Usuario> usuarioSession;

	/** The l lista accesos activos. */
	private List<AccesosDirecto> lListaAccesosActivos;

	/** The l servicio mantenedor. */
	@EJB
	private ServicioMantenedorAccesoDirecto lServicioMantenedor;

	/**
	 * Instantiates a new bean accesos directos.
	 */
	public BeanAccesosDirectos() {
		super(AccesosDirecto.class);
		listaSeleccionAcceso = new DualListModel<>();
		addPostTransaccion(new PostTransaccionListener() {

			@Override
			public void metodoPostTransaccion() {
				setListaEntidades(lServicioMantenedor.obtenerAccesosDirectosUsuario(usuarioSession.getUsuarioSession().getId()));

			}
		});

		addPostConstructuListener(new PostConstructListener() {
			public void metodoPostConstruct() {
				lListaAccesosActivos = lServicioMantenedor.obtenerAccesosDirectosUsuario(usuarioSession.getUsuarioSession().getId());
				cargatListaAccesos();
			}
		});

	}

	/**
	 * Cargat lista accesos.
	 */
	private void cargatListaAccesos() {
		listaSeleccionAcceso = new DualListModel<>(
				lServicioMantenedor.obtenerAccesosPorAsignar(usuarioSession.getUsuarioSession().getId()),
				lServicioMantenedor.obtenerAccesosAsignadosActivos(usuarioSession.getUsuarioSession().getId()));
	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.vista.beans.BeanMantenedorGenerico#cargarListaEtiquetas()
	 */
	@Override
	protected void cargarListaEtiquetas() {
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TITULOPAGINA.toString(), "Mantenimiento Accesos Directos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.DESCRIPCIONPAGINA.toString(), "Mantenedor Accesos Directos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.AYUDAPAGINA.toString(), "Cree o edite Accesos Directoss");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TAB.toString(), "Datos Accesos Directos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERATABLA.toString(), "Accesos Directoss registrados");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERADIALOGO.toString(),
				"Actualización de Accesos Directos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERAPANELDIALOGO.toString(), "Datos Accesos Directos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERATABLA.toString(), "Accesos Directoss registrados");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TABLAVACIA.toString(), JsfUtil.MENSAJE_INFO_SINRESULTADO);

	}

	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.vista.beans.BeanMantenedorGenerico#getServicioMantenedor()
	 */
	@Override
	protected ServicioMantenedorAccesoDirecto getServicioMantenedor() {
		return lServicioMantenedor;
	}

	/**
	 * Gets the lista seleccion acceso.
	 *
	 * @return the lista seleccion acceso
	 */
	public DualListModel<String> getListaSeleccionAcceso() {
		return listaSeleccionAcceso;
	}

	/**
	 * Sets the lista seleccion acceso.
	 *
	 * @param listaSeleccionAcceso the new lista seleccion acceso
	 */
	public void setListaSeleccionAcceso(DualListModel<String> listaSeleccionAcceso) {
		this.listaSeleccionAcceso = listaSeleccionAcceso;

	}
	
	/**
	 * Cargar lista accesos directos.
	 */
	public void cargarListaAccesosDirectos()
	{
		lListaAccesosActivos = lServicioMantenedor.obtenerAccesosDirectosUsuario(usuarioSession.getUsuarioSession().getId());
	}
	

	/**
	 * Control transferencia.
	 *
	 * @param pEvento the evento
	 */
	public void controlTransferencia(TransferEvent pEvento) {

		Long referencia = obtenerObjetoSesion(JsfUtil.REFERENCIA_SESION);
		List<String> lAccesosSeleccionados = (List<String>) pEvento.getItems();
		try {
			if (pEvento.isAdd())

				lServicioMantenedor.asigarAcceso(lAccesosSeleccionados, usuarioSession.getUsuarioSession().getId(),
						referencia, usuarioSession.getUsuarioSession().getUsuario());

			else {
				if (pEvento.isRemove())
					lServicioMantenedor.inactivarAcceso(lAccesosSeleccionados,
							usuarioSession.getUsuarioSession().getId(),
							usuarioSession.getUsuarioSession().getUsuario());

			}
			cargatListaAccesos();
			lListaAccesosActivos = lServicioMantenedor.obtenerAccesosDirectosUsuario(usuarioSession.getUsuarioSession().getId());
		} catch (ErrorServicioNegocio e) {
			e.printStackTrace();
			addError(e.getMessage());

		} catch (ErrorValidacionGeneral e) {
			e.printStackTrace();
			addError(e.getMessage());
		}
	}

	/**
	 * Gets the l lista accesos activos.
	 *
	 * @return the l lista accesos activos
	 */
	public List<AccesosDirecto> getlListaAccesosActivos() {
		return lListaAccesosActivos;
	}

	/**
	 * Sets the l lista accesos activos.
	 *
	 * @param lListaAccesosActivos the new l lista accesos activos
	 */
	public void setlListaAccesosActivos(List<AccesosDirecto> lListaAccesosActivos) {
		this.lListaAccesosActivos = lListaAccesosActivos;
	}

}
