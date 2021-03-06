package org.javaee.sandbox.bean;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import javax.transaction.Transactional;

import org.javaee.sandbox.dao.AutorDao;
import org.javaee.sandbox.dao.LivroDao;
import org.javaee.sandbox.infra.FileSaver;
import org.javaee.sandbox.model.Autor;
import org.javaee.sandbox.model.Livro;

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
	
	private Part capaLivro;

	@Transactional // JTA
	public String salvar() {
		livroDao.salvar(livro);
		
		final FileSaver fileSaver = new FileSaver();
		final String relativePath = fileSaver.write(capaLivro, "livros"); 
		livro.setCapaPath(relativePath);

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

	public Part getCapaLivro() {
		return capaLivro;
	}

	public void setCapaLivro(Part capaLivro) {
		this.capaLivro = capaLivro;
	}

}
