package org.javaee.sandbox.bean;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import org.javaee.sandbox.dao.LivroDao;
import org.javaee.sandbox.model.Livro;

@Model // encapsula @Named e @RequestScoped
public class AdminListaLivrosBean {
	
	@Inject
	private LivroDao livroDao;
	
	private List<Livro> livros = new ArrayList<>();

	public List<Livro> getLivros() {
		this.livros = livroDao.listar();
		return livros;
	}
	
}
