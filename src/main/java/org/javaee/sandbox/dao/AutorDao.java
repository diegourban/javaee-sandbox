package org.javaee.sandbox.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.javaee.sandbox.model.Autor;

public class AutorDao {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Autor> listar() {
		return entityManager.createQuery("select a from Autor a", Autor.class).getResultList();
	}

}
