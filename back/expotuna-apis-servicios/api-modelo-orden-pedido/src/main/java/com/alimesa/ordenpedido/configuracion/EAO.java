package com.alimesa.ordenpedido.configuracion;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import com.alimesa.ordenpedido.librerias.dominio.entidades.IEntidadPersistible;
import com.alimesa.ordenpedido.librerias.eao.GenericEAO;

// TODO: Auto-generated Javadoc
/**
 * 
 *
 * @param <T> the generic type
 * @param <Id> the generic type
 */
public abstract class EAO<T extends IEntidadPersistible<Id>, Id extends Serializable> 
extends GenericEAO<T, Id>
{
	
	/** The admin entidad. */
	@PersistenceContext(unitName=ConstantesAplicacion.NOMBRE_UNIDAD_PERSISTENCIA)
	protected EntityManager adminEntidad;
	
	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.eao.GenericEAO#getAdminEntidad()
	 */
	public EntityManager getAdminEntidad() {
		return adminEntidad;
	}
	
	/**
	 * Find all.
	 *
	 * @param clase the clase
	 * @return the list
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> findAll(Class<T> clase) {
        javax.persistence.criteria.CriteriaQuery cq = getAdminEntidad().getCriteriaBuilder().createQuery();
        cq.select(cq.from(clase));
        return getAdminEntidad().createQuery(cq).getResultList();
    }
	
	/**
	 * Creates the.
	 *
	 * @param entity the entity
	 */
	public void create(T entity) {
        super.insertar(entity);
    }

    /**
     * Edits the.
     *
     * @param entity the entity
     */
    public void edit(T entity) {
       super.actualizar(entity);
    }

    /**
     * Removes the.
     *
     * @param entity the entity
     */
    public void remove(T entity) {
       super.eliminar(entity);
    }

    /**
     * Find.
     *
     * @param id the id
     * @param clase the clase
     * @return the t
     */
    public T find(Id id, Class<T> clase) {
        return super.obtenerObjetoPorPk(id, clase);
    }
    
    /**
     * Count.
     *
     * @param clase the clase
     * @return the int
     */
    public int count(Class<T> clase) {
        CriteriaQuery<Object> cq = getAdminEntidad().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(clase);
        cq.select(getAdminEntidad().getCriteriaBuilder().count(rt));
        return ((Long) getAdminEntidad().createQuery(cq).getSingleResult()).intValue();
    }
	
}
