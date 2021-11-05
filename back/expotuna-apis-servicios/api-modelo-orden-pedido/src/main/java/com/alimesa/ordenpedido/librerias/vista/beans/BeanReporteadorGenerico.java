package com.alimesa.ordenpedido.librerias.vista.beans;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.Map;

import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletResponse;

import com.alimesa.ordenpedido.librerias.excepciones.ExcepcionAplicacion;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRCsvExporterParameter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRTextExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;

@SuppressWarnings("deprecation")
public abstract class BeanReporteadorGenerico extends ManagedBeanGenerico {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private FormatoReporte formatoReporte;

	public FormatoReporte getFormatoReporte() {
		return formatoReporte;
	}

	public void setFormatoReporte(FormatoReporte formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	protected Object getParametro(String pNombreParametro, ActionEvent pActionEvent) {
		return pActionEvent.getComponent().getAttributes().get(pNombreParametro);
	}

	protected abstract Map<String, Object> recuperarParametros(ActionEvent pActionEvent);

	protected abstract File recuperarArchivoJasper();

	protected abstract JRDataSource recuperarDataSource();

	private String getNombreArchivoExtensionArchivo() {
		String lNombreArchivo = getNombreArchivo();
		if (lNombreArchivo == null) {
			File lReporteJasper = recuperarArchivoJasper();
			lNombreArchivo = lReporteJasper.getName();
			int lIndice = lNombreArchivo.lastIndexOf('.');
			if (lIndice != -1)
				lNombreArchivo = lNombreArchivo.substring(0, lIndice);
		}
		lNombreArchivo = lNombreArchivo + "." + formatoReporte.getExtension();
		return lNombreArchivo;
	}

	protected abstract String getNombreArchivo();

	public void exportar(ActionEvent evento) {
		try {
			Map<String, Object> lParametros = recuperarParametros(evento);
			aniadirParametrosGenerales(lParametros);
			File lReporteJasper = recuperarArchivoJasper();
			JasperReport lReporte = (JasperReport) JRLoader.loadObject(lReporteJasper);

			if (formatoReporte == null)
				formatoReporte = FormatoReporte.HTML;

			switch (formatoReporte) {
			case HTML:
				exportarHtml(lReporte, lParametros);
				break;
			case PDF:
				exportarPDF(lReporte, lParametros);
				break;
			case CSV:
				exportarCSV(lReporte, lParametros);
				break;
			case RTF:
				exportarRtf(lReporte, lParametros);
				break;
			case TXT:
				exportarTXT(lReporte, lParametros);
				break;
			case XLS:
				exportarXls(lReporte, lParametros);
				break;
			default:
				throw new ExcepcionAplicacion("exp-1", "Error formatoNoEspecificado");
			}
			getContext().responseComplete();
		} catch (Exception e) {
			addError(e.getMessage());
		}
	}

	protected void exportarHtml(JasperReport pReporte, Map<String, Object> pParametros)
			throws JRException, IOException {
		// Creo el visializador
		JasperPrint lVisualizador = crearVisualizador(pReporte, pParametros);
		// El objeto que exportara el formato a html
		JRHtmlExporter exporter = new JRHtmlExporter();

		agregarObjetoSesion(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, lVisualizador);

		exporter.setParameter(JRExporterParameter.JASPER_PRINT, lVisualizador);
		exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, getOutputWriter());
		exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, getContextPath() + "/image?image=");

		exporter.exportReport();
	}

	protected void exportarPDF(JasperReport pReporte, Map<String, Object> pParametros) throws JRException, IOException {
		// Creo el visializador
		JasperPrint lVisualizador = crearVisualizador(pReporte, pParametros);
		// El objeto que exportara el formato a pdf
		JRPdfExporter exporter = new JRPdfExporter();
		// Los objetos para manipular el requerimiento y respuesta
		HttpServletResponse lHttpServletResponse = getResponse();
		lHttpServletResponse.setContentType("application/pdf");
		lHttpServletResponse.setHeader("Content-Disposition",
				"attachment; filename=\"" + getNombreArchivoExtensionArchivo() + "\"");

		exporter.setParameter(JRExporterParameter.JASPER_PRINT, lVisualizador);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, getOutputStream());

		exporter.exportReport();
	}

	protected void exportarRtf(JasperReport pReporte, Map<String, Object> pParametros) throws JRException, IOException {
		// Creo el visializador
		JasperPrint lVisualizador = crearVisualizador(pReporte, pParametros);
		// El objeto que exportara el formato a pdf
		JRRtfExporter exporter = new JRRtfExporter();
		// Los objetos para manipular el requerimiento y respuesta
		HttpServletResponse lHttpServletResponse = getResponse();
		lHttpServletResponse.setContentType("application/rtf");
		lHttpServletResponse.setHeader("Content-Disposition",
				"attachment; filename=\"" + getNombreArchivoExtensionArchivo() + "\"");

		exporter.setParameter(JRExporterParameter.JASPER_PRINT, lVisualizador);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, getOutputStream());
		exporter.exportReport();
	}

	protected void exportarXls(JasperReport pReporte, Map<String, Object> pParametros) throws JRException, IOException {
		JasperPrint lVisualizador = crearVisualizador(pReporte, pParametros);
		JRXlsExporter lExporter = new JRXlsExporter();
		HttpServletResponse lHttpServletResponse = getResponse();
		lHttpServletResponse.setContentType("application/xls");
		lHttpServletResponse.setHeader("Content-Disposition",
				"attachment; filename=\"" + getNombreArchivoExtensionArchivo() + "\"");

		lExporter.setParameter(JRExporterParameter.JASPER_PRINT, lVisualizador);
		lExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, getOutputStream());
		lExporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
		lExporter.setParameter(JRXlsExporterParameter.IS_FONT_SIZE_FIX_ENABLED, Boolean.TRUE);
		lExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
		lExporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
		lExporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);

		lExporter.exportReport();
	}

	protected void exportarTXT(JasperReport pReporte, Map<String, Object> pParametros) throws JRException, IOException {
		// Para ignorar la paginacion
		pParametros.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);
		// Creo el visializador
		JasperPrint lVisualizador = crearVisualizador(pReporte, pParametros);

		// El objeto que exportara el formato a txt
		JRTextExporter exporter = new JRTextExporter();
		// Los objetos para manipular el requerimiento y respuesta del servlet
		HttpServletResponse lHttpServletResponse = getResponse();
		lHttpServletResponse.setContentType("application/txt");
		lHttpServletResponse.setHeader("Content-Disposition",
				"inline; filename=\"" + getNombreArchivoExtensionArchivo() + "\"");

		exporter.setParameter(JRExporterParameter.JASPER_PRINT, lVisualizador);
		exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, getOutputWriter());

		exporter.setParameter(JRTextExporterParameter.CHARACTER_WIDTH, new Integer(8));
		exporter.setParameter(JRTextExporterParameter.CHARACTER_HEIGHT, new Integer(8));

		exporter.exportReport();
	}

	protected void exportarCSV(JasperReport pReporte, Map<String, Object> pParametros) throws JRException, IOException {
		// Para ignorar la paginacion
		pParametros.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);
		// Creo el visializador
		JasperPrint lVisualizador = crearVisualizador(pReporte, pParametros);

		// Los objetos para manipular el requerimiento y respuesta del servlet
		HttpServletResponse lHttpServletResponse = getResponse();
		lHttpServletResponse.setContentType("application/csv");
		lHttpServletResponse.setHeader("Content-Disposition",
				"inline; filename=\"" + getNombreArchivoExtensionArchivo() + "\"");

		// El objeto que exportara el formato a csv
		JRCsvExporter exporter = new JRCsvExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, lVisualizador);
		exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, getOutputWriter());
		exporter.setParameter(JRCsvExporterParameter.FIELD_DELIMITER, "|");
		exporter.setParameter(JRCsvExporterParameter.RECORD_DELIMITER, "\n");
		exporter.exportReport();
	}

	private OutputStream getOutputStream() throws IOException {
		return getResponse().getOutputStream();
	}

	private JasperPrint crearVisualizador(JasperReport l_reporte, Map<String, Object> l_parametros) throws JRException {
		JRDataSource lJrDataSource = recuperarDataSource();
		JasperPrint lJasperPrint = null;
		if (lJrDataSource == null)
			lJasperPrint = JasperFillManager.fillReport(l_reporte, l_parametros, getConexionDB());
		else
			lJasperPrint = JasperFillManager.fillReport(l_reporte, l_parametros, lJrDataSource);
		return lJasperPrint;
	}

	protected abstract Connection getConexionDB();

	protected abstract void aniadirParametrosGenerales(Map<String, Object> p_map);

}
