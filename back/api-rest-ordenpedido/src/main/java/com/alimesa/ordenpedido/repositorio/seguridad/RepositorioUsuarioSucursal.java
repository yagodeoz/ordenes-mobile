package com.alimesa.ordenpedido.repositorio.seguridad;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alimesa.ordenpedido.modelo.seguridad.UsuarioSucursal;

@Repository
public interface RepositorioUsuarioSucursal extends JpaRepository<UsuarioSucursal, Long> {
}

/*
 * Location:
 * D:\apps\api-rest-ordenpedido\1.0\api-rest-ordenpedido-1.0.war!\WEB-INF\
 * classes\com\alimesa\ordenpedido\repositorio\seguridad\
 * RepositorioUsuarioSucursal.class Java compiler version: 11 (55.0) JD-Core
 * Version: 1.1.3
 */