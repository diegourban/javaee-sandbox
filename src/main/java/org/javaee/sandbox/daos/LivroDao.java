package org.javaee.sandbox.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.javaee.sandbox.models.Livro;

public class LivroDao {

	@PersistenceContext
	private EntityManager entityManager;

	public void salvar(Livro livro) {
		entityManager.persist(livro);
	}

	public List<Livro> listar() {
		String jpql = "select distict(l) from Livro l join fetch l.autores";

		return entityManager.createQuery(jpql, Livro.class).getResultList();
	}

}
