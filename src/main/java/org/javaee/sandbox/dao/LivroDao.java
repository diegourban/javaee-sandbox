package org.javaee.sandbox.dao;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.hibernate.jpa.QueryHints;
import org.javaee.sandbox.model.Livro;

@Stateful // será mantido estado do Dao equanto o escopo do bean estiver vivo
public class LivroDao {

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	public void salvar(Livro livro) {
		entityManager.persist(livro);
	}

	public List<Livro> listar() {
		String jpql = "select distinct(l) from Livro l left join fetch l.autores";
		return entityManager.createQuery(jpql, Livro.class).getResultList();
	}

	public List<Livro> ultimosLancamentos() {
		String jpql = "select l from Livro l order by l.dataPublicacao desc";
		return entityManager.createQuery(jpql, Livro.class).setMaxResults(5).setHint(QueryHints.HINT_CACHEABLE, true).getResultList();
	}

	public List<Livro> demaisLivros() {
		String jpql = "select l from Livro l order by l.dataPublicacao desc";
		return entityManager.createQuery(jpql, Livro.class).setFirstResult(5).setHint(QueryHints.HINT_CACHEABLE, true).getResultList();
	}

	public Livro buscarPorId(Integer id) {
		// pode causar lazy initialization exception sem contexto extendido e stateful, faz dois selects
		return entityManager.find(Livro.class, id);
		
		// planned query não precisa de contexto extendido nem stateful e faz um select apenas
		//String jpql = "select l from Livro l join fetch l.autores where l.id = :pId";
		//return entityManager.createQuery(jpql, Livro.class).setParameter("pId", id).getSingleResult();
	}

}
