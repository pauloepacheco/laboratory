package br.com.ulbra.tcc.services.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * The Abstract Dao Class
 * 
 * @author Paulo Pacheco
 *	
 * @param <T> the generic type
 * @param <ID> the id
 */
public class AbstractDao<T, ID extends Serializable> {
		
    private transient Class<T> persistentClass;    
	protected transient EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public AbstractDao() {    	
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().
				getGenericSuperclass()).getActualTypeArguments()[0];
    }
	
	@PersistenceContext()
    public void setEntityManager(EntityManager entityMgrToSet) {    
        this.entityManager = entityMgrToSet;
    }
		
    public T findById(ID theId) {
    
        return entityManager.find(persistentClass, theId);
    }
    
    public void persist(T entity) {
    
        entityManager.persist(entity);
    }
    
    public void merge(T entity) {
    
        entityManager.merge(entity);
    }
    
    public void remove(T entity) {
    
        entityManager.remove(entity);
    }
    
    @SuppressWarnings("unchecked")
	public List<T> findAll() {
        	
        return entityManager.createQuery("Select t from " + 
        		persistentClass.getSimpleName() + " t").getResultList();
    }

}
