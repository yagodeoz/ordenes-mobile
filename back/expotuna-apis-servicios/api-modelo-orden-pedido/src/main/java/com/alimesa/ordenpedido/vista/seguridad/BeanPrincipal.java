package com.alimesa.ordenpedido.vista.seguridad;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import com.alimesa.ordenpedido.librerias.vista.anotaciones.ITestUsuarioSession;
import com.alimesa.ordenpedido.librerias.vista.interfaces.IUsuarioSession;
import com.alimesa.ordenpedido.modelo.seguridad.Usuario;



// TODO: Auto-generated Javadoc
/**
 * The Class BeanPrincipal.
 */
@ManagedBean(name = "beanPrincipal")
@ViewScoped
public class BeanPrincipal {

	/** The modelo. */
	private ScheduleModel modelo;
	
	@Inject
	@ITestUsuarioSession
	private IUsuarioSession<Usuario> usuarioSession;

	/** The line model 1. */
	private LineChartModel lineModel1;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		modelo = new DefaultScheduleModel();

		lineModel1 = initLinearModel();
		lineModel1.setTitle("Crecimiento Registros Usuarios");
		lineModel1.setLegendPosition("e");
		Axis yAxis = lineModel1.getAxis(AxisType.Y);
		yAxis.setMin(0);
		yAxis.setMax(10);
		lineModel1.setExtender("skinChart");
	}

	/**
	 * Gets the modelo.
	 *
	 * @return the modelo
	 */
	public ScheduleModel getModelo() {
		return modelo;
	}

	/**
	 * Sets the modelo.
	 *
	 * @param modelo
	 *            the new modelo
	 */
	public void setModelo(ScheduleModel modelo) {
		this.modelo = modelo;
	}

	/**
	 * Inits the linear model.
	 *
	 * @return the line chart model
	 */
	private LineChartModel initLinearModel() {
		LineChartModel model = new LineChartModel();

		LineChartSeries series1 = new LineChartSeries();
		series1.setLabel("Usuario");

		series1.set(1, 2);
		series1.set(2, 1);
		series1.set(3, 3);
		series1.set(4, 6);
		series1.set(5, 8);

		LineChartSeries series2 = new LineChartSeries();
		series2.setLabel("Roles");

		series2.set(1, 6);
		series2.set(2, 3);
		series2.set(3, 2);
		series2.set(4, 7);
		series2.set(5, 9);

		model.addSeries(series1);
		model.addSeries(series2);

		return model;
	}

	/**
	 * Gets the line model 1.
	 *
	 * @return the line model 1
	 */
	public LineChartModel getLineModel1() {
		return lineModel1;
	}

	/**
	 * Sets the line model 1.
	 *
	 * @param lineModel1
	 *            the new line model 1
	 */
	public void setLineModel1(LineChartModel lineModel1) {
		this.lineModel1 = lineModel1;
	}

	public IUsuarioSession<Usuario> getUsuarioSession() {
		return usuarioSession;
	}

	public void setUsuarioSession(IUsuarioSession<Usuario> usuarioSession) {
		this.usuarioSession = usuarioSession;
	}
	
	
	
}
