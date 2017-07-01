package org.javaee.sandbox.bean;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import org.javaee.sandbox.dao.LivroDao;
import org.javaee.sandbox.model.Livro;

@Model
public class HomeBean {
	
	@Inject
	private LivroDao livroDao;
	
	public List<Livro> ultimosLancamentos() {
		return livroDao.ultimosLancamentos();
	}
	
	public List<Livro> demaisLivros() {
		return livroDao.demaisLivros();
	}

}
