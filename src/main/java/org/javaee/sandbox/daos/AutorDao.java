package org.javaee.sandbox.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.javaee.sandbox.models.Autor;

public class AutorDao {

	@PersistenceContext
	private EntityManager entityManager;

	@PersistenceContext
	public List<Autor> listar() {
		return entityManager.createQuery("select a from Autor a", Autor.class).getResultList();
	}

}
