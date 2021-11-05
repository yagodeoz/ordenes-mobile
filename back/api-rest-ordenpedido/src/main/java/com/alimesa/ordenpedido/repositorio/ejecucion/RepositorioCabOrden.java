package com.alimesa.ordenpedido.repositorio.ejecucion;

import com.alimesa.ordenpedido.modelo.ejecucion.CabOrden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioCabOrden extends JpaRepository<CabOrden, Long> {
}

/*
 * Location:
 * D:\apps\api-rest-ordenpedido\1.0\api-rest-ordenpedido-1.0.war!\WEB-INF\
 * classes\com\alimesa\ordenpedido\repositorio\ejecucion\RepositorioCabOrden.
 * class Java compiler version: 11 (55.0) JD-Core Version: 1.1.3
 */