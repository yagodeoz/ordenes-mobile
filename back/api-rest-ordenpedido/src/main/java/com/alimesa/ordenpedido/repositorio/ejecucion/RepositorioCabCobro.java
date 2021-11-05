package com.alimesa.ordenpedido.repositorio.ejecucion;

import com.alimesa.ordenpedido.modelo.ejecucion.CabCobro;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioCabCobro extends JpaRepository<CabCobro, Long> {

	@Query(nativeQuery = true, value = "select * from CAB_COBROS where usuarioAsignado = :usuario and estado = 'A' ")
	List<CabCobro> obtenerCobrosUsuarioAsignado(@Param("usuario") String usuario);
}

/*
 * Location:
 * D:\apps\api-rest-ordenpedido\1.0\api-rest-ordenpedido-1.0.war!\WEB-INF\
 * classes\com\alimesa\ordenpedido\repositorio\ejecucion\RepositorioCabCobro.
 * class Java compiler version: 11 (55.0) JD-Core Version: 1.1.3
 */