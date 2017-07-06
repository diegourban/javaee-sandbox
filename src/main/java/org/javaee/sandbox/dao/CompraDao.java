package org.javaee.sandbox.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.javaee.sandbox.model.Compra;

public class CompraDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7228317136471142696L;
	
	@PersistenceContext
	private EntityManager entityManager;

	public void salvar(Compra compra) {
		entityManager.persist(compra);
	}
}
