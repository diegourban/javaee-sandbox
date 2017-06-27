package org.javaee.sandbox.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.javaee.sandbox.daos.LivroDao;
import org.javaee.sandbox.models.Livro;

@Named // CDI
@RequestScoped // CDI
public class AdminLivrosBean {
	
	private Livro livro = new Livro();
	
	@Inject // CDI
	private LivroDao dao;
	
	@Transactional // JTA
	public void salvar() {
		dao.salvar(livro);
		System.out.println("Livro cadastrado: " + this.livro);
		
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

}
