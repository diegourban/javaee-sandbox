package org.javaee.sandbox.resource;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.javaee.sandbox.dao.LivroDao;
import org.javaee.sandbox.model.Livro;
import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;

@Path("livros")
public class LivroResource {

	@Inject
	private LivroDao livroDao;
	
	@GET
	@Path("ultimosLancamentos")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Wrapped(element = "livros")
	public List<Livro> ultimosLancamentos() {
		return livroDao.ultimosLancamentos();
	}

}
