package com.alimesa.ordenpedido.repositorio.ejecucion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alimesa.ordenpedido.modelo.ejecucion.CabDespacho;

@Repository
public interface RepositorioCabDespacho extends JpaRepository<CabDespacho, Long> {

	
	
	@Query(nativeQuery = true, value = "select * from CAB_DESPACHO where usuarioasignado = :usuario and estado = 'A' ")
	List<CabDespacho> obrtenerCabeceraDespacho(@Param("usuario") String usuario);
	
}
