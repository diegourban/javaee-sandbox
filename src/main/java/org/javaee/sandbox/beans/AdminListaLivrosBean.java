package org.javaee.sandbox.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import org.javaee.sandbox.daos.LivroDao;
import org.javaee.sandbox.models.Livro;

@Model // envolve @Named e @RequestScoped
public class AdminListaLivrosBean {
	
	@Inject
	private LivroDao livroDao;
	
	private List<Livro> livros = new ArrayList<>();

	public List<Livro> getLivros() {
		this.livros = livroDao.listar();
		return livros;
	}
	
}
