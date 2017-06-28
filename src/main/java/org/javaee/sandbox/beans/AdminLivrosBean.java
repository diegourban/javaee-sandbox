package org.javaee.sandbox.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.javaee.sandbox.daos.AutorDao;
import org.javaee.sandbox.daos.LivroDao;
import org.javaee.sandbox.models.Autor;
import org.javaee.sandbox.models.Livro;

@Named // CDI
@RequestScoped // CDI
public class AdminLivrosBean {

	@Inject // CDI
	private LivroDao livroDao;

	@Inject
	private AutorDao autorDao;

	@Inject // provido por FacesContextProducer
	private FacesContext context;

	private Livro livro = new Livro();

	private List<Integer> autoresId = new ArrayList<>();

	@Transactional // JTA
	public String salvar() {
		for (final Integer autorId : autoresId) {
			livro.getAutores().add(new Autor(autorId));
		}
		livroDao.salvar(livro);

		context.getExternalContext().getFlash().setKeepMessages(true);

		context.addMessage(null, new FacesMessage("Livro cadastrado com sucesso!"));

		// sem o faces-redirect, acontece um forward no servidor
		// com o faces-redirect, o servidor envia um http status code 302 para
		// client para redirectionar
		// always redirect after a post
		return "/livros/lista?faces-redirect=true";
	}

	public List<Autor> getAutores() {
		return autorDao.listar();
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public List<Integer> getAutoresId() {
		return autoresId;
	}

	public void setAutoresId(List<Integer> autoresId) {
		this.autoresId = autoresId;
	}

}
