package com.alimesa.ordenpedido.repositorio.seguridad;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alimesa.ordenpedido.modelo.seguridad.Usuario;

@Repository
public interface RepositorioUsuario extends JpaRepository<Usuario, Long> {
	@Query(nativeQuery = true, value = "select * from USUARIOS  where usuario = :usuario and clave = :clave ")
	List<Usuario> obtenerUsuarioCredencial(@Param("usuario") String paramString1, @Param("clave") String paramString2);
}

/*
 * Location:
 * D:\apps\api-rest-ordenpedido\1.0\api-rest-ordenpedido-1.0.war!\WEB-INF\
 * classes\com\alimesa\ordenpedido\repositorio\seguridad\RepositorioUsuario.
 * class Java compiler version: 11 (55.0) JD-Core Version: 1.1.3
 */