package com.alimesa.ordenpedido.repositorio.seguridad;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alimesa.ordenpedido.modelo.ejecucion.Sucursal;

@Repository
public interface RepositorioSucursal extends JpaRepository<Sucursal, Long> {
	@Query(nativeQuery = true, value = "select * from sucursales where id in ( select id_sucursal from usuarios_sucursales where id_usuario in ( select id from usuarios  where usuario = :usuario ) ) ")
	List<Sucursal> obtenerSucursalesUsuario(@Param("usuario") String paramString);
}

/*
 * Location:
 * D:\apps\api-rest-ordenpedido\1.0\api-rest-ordenpedido-1.0.war!\WEB-INF\
 * classes\com\alimesa\ordenpedido\repositorio\seguridad\RepositorioSucursal.
 * class Java compiler version: 11 (55.0) JD-Core Version: 1.1.3
 */