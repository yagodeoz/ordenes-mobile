package com.alimesa.ordenpedido.repositorio.ejecucion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alimesa.ordenpedido.modelo.ejecucion.ListaPrecioProducto;

@Repository
public interface RepositorioListaPrecioProducto extends JpaRepository<ListaPrecioProducto, Long> {
	@Query(nativeQuery = true, value = "select * from lista_precio_productos where id_producto in (  select id from productos  where id in (\tselect id_producto from producto_sucursal where id_sucursal  in (\t\tselect id from sucursales where id in ( select id_sucursal from usuarios_sucursales where id_usuario in ( \t\tselect id from usuarios  where usuario = :usuario ) )\t\t\t)))")
	List<ListaPrecioProducto> obtenerListaPrecioProductos(@Param("usuario") String paramString);
}

/*
 * Location:
 * D:\apps\api-rest-ordenpedido\1.0\api-rest-ordenpedido-1.0.war!\WEB-INF\
 * classes\com\alimesa\ordenpedido\repositorio\ejecucion\
 * RepositorioListaPrecioProducto.class Java compiler version: 11 (55.0) JD-Core
 * Version: 1.1.3
 */