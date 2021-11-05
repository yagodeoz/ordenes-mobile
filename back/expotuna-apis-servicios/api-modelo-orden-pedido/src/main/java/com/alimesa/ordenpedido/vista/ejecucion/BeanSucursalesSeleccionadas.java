package com.alimesa.ordenpedido.vista.ejecucion;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.alimesa.ordenpedido.modelo.ejecucion.Sucursal;

@ManagedBean
@SessionScoped
public class BeanSucursalesSeleccionadas implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Sucursal> listaSucursalesSeleccionadas;
	
	private List<Sucursal> listaSucursales;

	public List<Sucursal> getListaSucursalesSeleccionadas() {
		return listaSucursalesSeleccionadas;
	}

	public void setListaSucursalesSeleccionadas(List<Sucursal> listaSucursalesSeleccionadas) {
		this.listaSucursalesSeleccionadas = listaSucursalesSeleccionadas;
	}

	public List<Sucursal> getListaSucursales() {
		return listaSucursales;
	}

	public void setListaSucursales(List<Sucursal> listaSucursales) {
		this.listaSucursales = listaSucursales;
	}
	
	
	
	

}
