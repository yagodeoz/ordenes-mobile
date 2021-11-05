package com.alimesa.ordenpedido.librerias.timer;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

public abstract class TimerEjecutorIntervaloTiempo {

	@Resource
	private TimerService timerService;

	private DatosEjecucionTimer lDatosEjecucionTimer;

	@PostConstruct
	private void init() {
		lDatosEjecucionTimer = inicializacionTimer();
		temporizarEjecucion();

	}

	protected abstract DatosEjecucionTimer inicializacionTimer();

	private void temporizarEjecucion() {
		timerService.createSingleActionTimer(60000 * lDatosEjecucionTimer.getlIntervaloEjecucion(),
				new TimerConfig(lDatosEjecucionTimer.getlNombreTimer(), lDatosEjecucionTimer.getlPersistente()));
	}

	@Timeout
	public void execute(Timer timer) {

		System.out.println("____________________________________________");
		System.out.println("Inicio de ejecución Servicio timer : " + timer.getInfo());
		System.out.println("Ejecución actual : " + new Date());
		try {
			ejecutarAccionRecurrente();
		} catch (Throwable lError) {
			lError.printStackTrace();
		} finally {
			System.out.println("Fin de ejecución Servicio timer : " + timer.getInfo());
			System.out.println("____________________________________________");
			timer.cancel();
			temporizarEjecucion();
		}
		
	}

	protected abstract void ejecutarAccionRecurrente() throws Throwable;

}
