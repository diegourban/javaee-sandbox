package org.javaee.sandbox.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.javaee.sandbox.model.SystemUser;

public class SecurityDao {

	@PersistenceContext
	private EntityManager entityManager;

	public SystemUser buscarPorEmail(String email) {
		return entityManager.createQuery("select su from SystemUser su where su.email = :email", SystemUser.class)
				.setParameter("email", email).getSingleResult();
	}

}
