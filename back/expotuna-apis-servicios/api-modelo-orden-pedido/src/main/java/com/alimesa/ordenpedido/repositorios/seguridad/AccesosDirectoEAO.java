package com.alimesa.ordenpedido.repositorios.seguridad;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.alimesa.ordenpedido.configuracion.EAO;
import com.alimesa.ordenpedido.modelo.seguridad.AccesosDirecto;


@Stateless
@LocalBean
public class AccesosDirectoEAO extends EAO<AccesosDirecto, Long> {

	public AccesosDirecto obtenerAcceso(Long idAcceso) {
		return obtenerObjetoPorPk(idAcceso, AccesosDirecto.class);
	}

}
