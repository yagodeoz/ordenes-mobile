package com.alimesa.ordenpedido.repositorio.ejecucion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alimesa.ordenpedido.modelo.ejecucion.ProductoSucursal;

@Repository
public interface RepositorioProductoSucursal extends JpaRepository<ProductoSucursal, Long> {
}

/*
 * Location:
 * D:\apps\api-rest-ordenpedido\1.0\api-rest-ordenpedido-1.0.war!\WEB-INF\
 * classes\com\alimesa\ordenpedido\repositorio\ejecucion\
 * RepositorioProductoSucursal.class Java compiler version: 11 (55.0) JD-Core
 * Version: 1.1.3
 */