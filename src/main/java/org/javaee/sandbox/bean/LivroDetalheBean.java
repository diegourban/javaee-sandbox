package org.javaee.sandbox.bean;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import org.javaee.sandbox.dao.LivroDao;
import org.javaee.sandbox.model.Livro;

@Model
public class LivroDetalheBean {

	@Inject
	private LivroDao livroDao;

	private Livro livro;

	private Integer id;

	public void carregarDetalhe() {
		this.livro = livroDao.buscarPorId(getId());
	}

	public Livro getLivro() {
		return livro;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
