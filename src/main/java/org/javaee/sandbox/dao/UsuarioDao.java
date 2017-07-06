package org.javaee.sandbox.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.javaee.sandbox.model.Usuario;

public class UsuarioDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	public void salvar(Usuario usuario) {
		entityManager.persist(usuario);
	}
}
