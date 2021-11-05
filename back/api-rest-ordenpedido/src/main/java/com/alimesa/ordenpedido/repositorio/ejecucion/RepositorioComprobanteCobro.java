package com.alimesa.ordenpedido.repositorio.ejecucion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alimesa.ordenpedido.modelo.ejecucion.ComprobantePago;


@Repository
public interface RepositorioComprobanteCobro extends JpaRepository<ComprobantePago, Long> {

}
