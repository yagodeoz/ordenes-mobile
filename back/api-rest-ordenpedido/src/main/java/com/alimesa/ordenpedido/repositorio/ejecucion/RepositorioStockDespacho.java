package com.alimesa.ordenpedido.repositorio.ejecucion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alimesa.ordenpedido.modelo.ejecucion.StockDespacho;

@Repository
public interface RepositorioStockDespacho extends JpaRepository<StockDespacho, Long> {

	@Query(nativeQuery = true, value = "select * from STOCK_DESPACHO where usuarioasignado = :usuario and estado = 'A' ")
	public List<StockDespacho> obtenerListaStockDespacho(@Param("usuario") String usuario);

}
