package org.javaee.sandbox.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.javaee.sandbox.models.Livro;

public class LivroDao {

	@PersistenceContext
	private EntityManager entityManager;

	public void salvar(Livro livro) {
		entityManager.persist(livro);
	}
	
}
