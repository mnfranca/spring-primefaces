package br.org.mnf.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public abstract class DaoImpl<T extends Serializable, I extends Serializable> implements Dao<T, I> {

	@PersistenceContext
	private EntityManager entityManager;

	private Class<T> entityClass;

	public DaoImpl(Class<T> entityClass) {
		super();
		this.entityClass = entityClass;
	}

	@Override
	public T get(I id) {
		return entityManager.find(entityClass, id);
	}

	@Override
	public List<T> getAll() {
		return entityManager.createQuery(String.format("FROM %s e", entityClass.getSimpleName()), entityClass)
				.getResultList();
	}
	
	@Override
	public T update(T entity) {
		return entityManager.merge(entity);
	}
	
	@Override
	public void insert(T entity) {
		entityManager.persist(entity);
	}
	
	@Override
	public void delete(T entity) {
		entityManager.remove(entity);
	}
	
	@Override
	public void deleteById(I id) {
		entityManager.remove(get(id));
	}
	
	public List<T> findByNamedQuery(String namedQuery, Map<String, Object> parameters) {
		TypedQuery<T> query = entityManager.createNamedQuery(namedQuery, entityClass);
		parameters.keySet().stream().forEach(key -> query.setParameter(key, parameters.get(key)));
		return query.getResultList();
	}

}
