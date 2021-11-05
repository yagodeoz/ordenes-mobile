package com.alimesa.ordenpedido.librerias.eao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.alimesa.ordenpedido.librerias.dominio.entidades.IEntidadPersistible;

public abstract class GenericEAO<ENTIDAD extends IEntidadPersistible<Id>, Id extends Serializable> {
	private static final String ESTADO = "estado";

	private static final String CAMPO_PK = "id";

	public static final String ESTADO_ACTIVO = "A";
	public static final String ESTADO_INACTIVO = "I";

	public abstract EntityManager getAdminEntidad();

	public void insertar(ENTIDAD entidad) {
		getAdminEntidad().persist(entidad);
		getAdminEntidad().flush();
	}

	public ENTIDAD actualizar(ENTIDAD entidad) {
		return getAdminEntidad().merge(entidad);
	}

	public void eliminar(ENTIDAD entidad) {
		getAdminEntidad().remove(entidad);
	}

	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<ENTIDAD> ejecutarQueryNativo(String sql, HashMap<String, Object> parametros, Class<ENTIDAD> clase) {
		Query query = getAdminEntidad().createNativeQuery(sql, clase);
		for (Entry<String, Object> registro : parametros.entrySet())
			query.setParameter(registro.getKey(), registro.getValue());
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	protected List<ENTIDAD> ejecutarNamedQuery(String namedQuery, HashMap<String, Object> parametros) {
		Query query = getAdminEntidad().createNamedQuery(namedQuery);
		for (Entry<String, Object> registro : parametros.entrySet())
			query.setParameter(registro.getKey(), registro.getValue());
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public <T extends Serializable> List<T> ejecutarNativeQueryList(String lQueryNativo,
			Map<String, Object> lParametros, Class<T> lClase) {
		Query query = getAdminEntidad().createNativeQuery(lQueryNativo);
		for (Entry<String, Object> registro : lParametros.entrySet())
			query.setParameter(registro.getKey(), registro.getValue());
		return query.getResultList();
	}

	public List<ENTIDAD> obtenerListaObjetosPorEstado(String estado, Class<ENTIDAD> clase) {
		return obtenerListaObjetosPorCampoGenerico(ESTADO, estado, clase);
	}

	public ENTIDAD obtenerObjetoPorPk(Id clavePrimaria, Class<ENTIDAD> clase) {
		return obtenerObjetoPorCampoGenerico(CAMPO_PK, clavePrimaria, clase);
	}

	public ENTIDAD obtenerObjetoPorCampoGenerico(String clave, Object valor, Class<ENTIDAD> clase) {
		List<ENTIDAD> listaResultado = obtenerListaObjetosPorCampoGenerico(clave, valor, clase);

		return listaResultado.isEmpty() ? null : listaResultado.get(0);
	}

	public List<ENTIDAD> obtenerListaObjetosPorCampoGenerico(String clave, Object valor, Class<ENTIDAD> clase) {
		return getAdminEntidad().createQuery(construccionQueryGenerico(clase, clave, false, null), clase)
				.setParameter(clave, valor).getResultList();
	}

	public List<ENTIDAD> obtenerListaObjetosPorCampoGenerico(String clave, Object valor, Class<ENTIDAD> clase,
			Boolean orderFecha) {
		return getAdminEntidad().createQuery(construccionQueryGenerico(clase, clave, orderFecha, null), clase)
				.setParameter(clave, valor).getResultList();
	}

	public List<ENTIDAD> obtenerTodaListaObjetos(Class<ENTIDAD> clase) {
		return getAdminEntidad().createQuery(construccionQueryGenerico(clase, false), clase).getResultList();
	}

	public List<ENTIDAD> obtenerTodaListaObjetos(Class<ENTIDAD> clase, Boolean orderFecha) {
		return getAdminEntidad().createQuery(construccionQueryGenerico(clase, orderFecha), clase).getResultList();
	}

	public List<ENTIDAD> obtenerTodaListaObjetos(Class<ENTIDAD> clase, Boolean orderFecha, String pidReferencia) {
		return getAdminEntidad().createQuery(construccionQueryGenerico(clase, orderFecha, pidReferencia), clase)
				.getResultList();
	}

	public List<ENTIDAD> obtenerListaObjetosPorEstadoActivo(Class<ENTIDAD> clase) {
		return obtenerListaObjetosPorEstado(ESTADO_ACTIVO, clase);
	}

	public List<ENTIDAD> obtenerListaObjetosPorEstadoInactivo(Class<ENTIDAD> clase) {
		return obtenerListaObjetosPorEstado(ESTADO_INACTIVO, clase);
	}

	private String construccionQueryGenerico(Class<ENTIDAD> clase, Boolean orderFecha, String pidReferencia) {
		return construccionQueryGenerico(clase, null, orderFecha, pidReferencia);
	}

	private String construccionQueryGenerico(Class<ENTIDAD> clase, Boolean orderFecha) {
		return construccionQueryGenerico(clase, null, orderFecha, null);
	}

	private String construccionQueryGenerico(Class<ENTIDAD> clase, String campoParametro, Boolean orderFecha,
			String pidReferencia) {
		String query = "select modelo  from " + clase.getCanonicalName() + " modelo ";
		if (campoParametro != null) {
			query += "where " + "modelo." + campoParametro + " = :" + campoParametro;
		}

		if (pidReferencia != null) {
			if (campoParametro != null)
				query += " and ";
			else
				query += " where ";
			query += " modelo.idReferencia = (select t.idReferencia from Usuario t " + " where t.usuario = '"
					+ pidReferencia + "'" + " and t.estado = 'A') ";
		}
		if (orderFecha) {
			query += " order by modelo.fechaRegistro desc";
		}
		return query;
	}
}
