package br.org.mnf.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.org.mnf.model.User;

public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public User customFindMethod(Long id) {
		return (User) entityManager.createQuery("FROM User u WHERE u.id = :id")
				.setParameter("id", id)
				.getSingleResult();
	}
}
