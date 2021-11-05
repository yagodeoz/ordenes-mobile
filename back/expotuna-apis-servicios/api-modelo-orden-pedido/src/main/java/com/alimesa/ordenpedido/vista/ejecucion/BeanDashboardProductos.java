package com.alimesa.ordenpedido.vista.ejecucion;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.pandora.domain.Product;
import org.primefaces.pandora.service.ProductService;

@Named
@ViewScoped
public class BeanDashboardProductos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Product> products1;
	
	@Inject
    private ProductService productService;

	@PostConstruct
	public void init() {
		products1 = productService.getProducts();
	}

}
