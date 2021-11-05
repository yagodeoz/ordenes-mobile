package com.alimesa.ordenpedido.repositorio.ejecucion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alimesa.ordenpedido.modelo.ejecucion.DetalleDespacho;

@Repository
public interface RepositorioDetalleDespacho extends JpaRepository<DetalleDespacho, Long> {

	@Query(nativeQuery = true, value = "select * from DETALLES_DESPACHO where ID_CAB_DESPACHO in ( select id from CAB_DESPACHO where usuarioasignado = :usuario and estado = 'A' )")
	List<DetalleDespacho> obtenerDetallesDespacho(@Param("usuario") String usuario);
}

