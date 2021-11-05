
package com.alimesa.ordenpedido.servicio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alimesa.ordenpedido.controlador.ejecucion.modelo.CabeceraOrdenMobile;
import com.alimesa.ordenpedido.controlador.ejecucion.modelo.DetalleOrdenMobile;
import com.alimesa.ordenpedido.controlador.ejecucion.modelo.ListaCobrosMobile;
import com.alimesa.ordenpedido.controlador.ejecucion.modelo.ListaDespachos;
import com.alimesa.ordenpedido.controlador.ejecucion.modelo.ListaOrdenesMobile;
import com.alimesa.ordenpedido.controlador.ejecucion.modelo.RespuestaCarga;
import com.alimesa.ordenpedido.modelo.ejecucion.CabOrden;
import com.alimesa.ordenpedido.modelo.ejecucion.DetalleOrden;
import com.alimesa.ordenpedido.repositorio.ejecucion.RepositorioCabOrden;
import com.alimesa.ordenpedido.repositorio.ejecucion.RepositorioDetOrden;

@Service
public class ServicioCarga {
	@Autowired
	private RepositorioCabOrden repCabOrden;
	@Autowired
	private RepositorioDetOrden repDetOrden;

	public RespuestaCarga registrarOrden(ListaOrdenesMobile listaOrden) {
		/* 32 */ RespuestaCarga resp = new RespuestaCarga();

		/* 34 */ listaOrden.getCabeceraOrdenes().forEach(cabecera -> {
			List<DetalleOrdenMobile> listaDetallesOrd = (List<DetalleOrdenMobile>) listaOrden.getDetalleOrdenes()
					.stream().filter(det -> det.getIdreferencia().equals(cabecera.getIdreferencia()))
					.collect(Collectors.toList());

			registrarOrdenes(cabecera, listaDetallesOrd);
		});

		/* 44 */ resp.setMensaje("REGISTROS DE ORDENES");
		/* 45 */ resp.setRegistrosAfectados(Long.valueOf(Long.parseLong("" + listaOrden.getCabeceraOrdenes().size())));
		/* 46 */ resp.setTipoSegistrosAfectados("ORDENES");

		/* 48 */ return resp;
	}

	private void registrarOrdenes(CabeceraOrdenMobile cabecera, List<DetalleOrdenMobile> listaDetallesOrd) {
		/* 54 */ CabOrden cabOrden = new CabOrden();

		/* 56 */ cabOrden.setAuditoria(cabecera.getCampoauditoria());
		/* 57 */ cabOrden.setDireccionComprador(cabecera.getDireccioncomprador());
		/* 58 */ cabOrden.setEstado(cabecera.getEstado());
		/* 59 */ cabOrden.setEstadoProceso("REP");
		/* 60 */ cabOrden.setFechaActualizacion(new Date());
		try {
			/* 62 */ cabOrden.setFechaEmision((new SimpleDateFormat("dd/MM/yyyy")).parse(cabecera.getFechaemision()));
			/* 63 */ } catch (ParseException e) {
			/* 64 */ cabOrden.setFechaEmision(new Date());
			/* 65 */ e.printStackTrace();
		}

		try {
			/* 69 */ cabOrden.setFechaRegistro((new SimpleDateFormat("dd/MM/yyyy")).parse(cabecera.getFecharegistro()));
			/* 70 */ } catch (ParseException e) {

			/* 72 */ e.printStackTrace();
		}

		/* 75 */ cabOrden.setIdentificacionComprador(cabecera.getIdentificacioncomprador());
		/* 76 */ cabOrden.setIdReferencia(cabecera.getIdreferencia());
		/* 77 */ cabOrden.setImporteTotal(cabecera.getImportetotal());
		/* 78 */ cabOrden.setMoneda(cabecera.getMoneda());
		/* 79 */ cabOrden.setNombreVendedor(cabecera.getNombrevendedor());
		/* 80 */ cabOrden.setObservacion(cabecera.getObservacion());
		/* 81 */ cabOrden.setRazonSocialComprador(cabecera.getRazonsocialcomprador());
		/* 82 */ cabOrden.setSubTotal0(cabecera.getSubtotal0());
		/* 83 */ cabOrden.setSubTotal12(cabecera.getSubtotal12());
		/* 84 */ cabOrden.setTotalDescuento(cabecera.getTotaldescuento());
		/* 85 */ cabOrden.setTotalImpuesto(cabecera.getTotalimpuesto());
		/* 86 */ cabOrden.setTotalSinImpuestos(cabecera.getTotalsinimpuestos());
		/* 87 */ cabOrden.setUsuarioAsignado(cabecera.getUsuarioasignado());
		/* 88 */ cabOrden.setUsuarioAsignante(cabecera.getUsuarioasignante());

		/* 90 */ cabOrden.setCanalCreacion(cabecera.getCanalcreacion());
		/* 91 */ cabOrden.setCodigoCliente(cabecera.getCodigocliente());

		/* 93 */
		//this.repCabOrden.save(cabOrden);
		

		/* 95 */ listaDetallesOrd.forEach(det -> {
			DetalleOrden detOrden = new DetalleOrden();
			//detOrden.setCabeceraOrden(reg);
			detOrden.setCantidad(det.getCantidad());
			detOrden.setCodigoAuxiliar(det.getCodigoauxiliar());
			detOrden.setCodigoPrincipal(det.getCodigoprincipal());
			detOrden.setDescripcion(det.getDescripcion());
			detOrden.setDescuento(det.getDescuento());
			detOrden.setDetallesAdicionales(det.getDetallesadicionales());
			detOrden.setEstado(det.getEstado());
			detOrden.setFechaActualizacion(new Date());
			detOrden.setFechaRegistro(new Date());
			detOrden.setIdReferencia(det.getIdreferencia());
			detOrden.setObservacion(det.getObservacion());
			detOrden.setPrecioTotalConImpuesto(det.getPreciototalconimpuesto());
			detOrden.setPrecioTotalSinImpuesto(det.getPreciototalsinimpuesto());
			detOrden.setPrecioUnitario(det.getPreciounitario());
			//this.repDetOrden.save(detOrden);
		});
		
		System.out.println("*********************************************************");
		System.out.println("replicacion al ERP e insercion de datos ORDENES");
		System.out.println("*********************************************************");
	}

	public RespuestaCarga registrarCobros(ListaCobrosMobile listaCobro) {
		RespuestaCarga resp = new RespuestaCarga();

		
		System.out.println("*********************************************************");
		System.out.println("replicacion al ERP e insercion de datos COBROS");
		System.out.println("*********************************************************");
		
		resp.setMensaje("REGISTROS DE COBROS");
		resp.setRegistrosAfectados(Long.valueOf(Long.parseLong("" + listaCobro.getListaComprobante().size())));
		resp.setTipoSegistrosAfectados("COBROS");

		return resp;
	}

	public RespuestaCarga registrarDespacho(ListaDespachos listaDespacho) {
		RespuestaCarga resp = new RespuestaCarga();

		
		System.out.println("*********************************************************");
		System.out.println("replicacion al ERP e insercion de datos DESPACHOS");
		System.out.println("*********************************************************");
		
		resp.setMensaje("REGISTROS DE DESPACHO");
		resp.setRegistrosAfectados(Long.valueOf(Long.parseLong("" + listaDespacho.getListaCabeceraDespacho().size())));
		resp.setTipoSegistrosAfectados("DESPACHO");

		return resp;
	}

}