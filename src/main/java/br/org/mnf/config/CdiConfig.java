package br.org.mnf.config;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@ApplicationScoped
public class CdiConfig {

	@Produces
	@Dependent
	@PersistenceUnit(unitName = "spPU")
	private EntityManagerFactory entityManagerFactory;

	@Produces
	@RequestScoped
	public EntityManager createEntityManager() {
		return entityManagerFactory.createEntityManager();
	}

	public void close(@Disposes EntityManager entityManager) {
		entityManager.close();
	}

}
