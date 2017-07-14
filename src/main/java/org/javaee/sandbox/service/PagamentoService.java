package org.javaee.sandbox.service;

import java.net.URI;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.javaee.sandbox.dao.CompraDao;
import org.javaee.sandbox.model.Compra;

@Path("/pagamento")
public class PagamentoService {

	@Context
	private ServletContext servletContext;

	@Inject
	private CompraDao compraDao;

	@Inject
	private PagamentoGateway pagamentoGateway;

	@POST
	public Response pagar(@QueryParam("uuid") String uuid) {
		Compra compra = compraDao.buscaPorUuid(uuid);
		pagamentoGateway.pagar(compra.getTotal());

		URI responseUri = UriBuilder
				.fromPath("http://localhost:8080" + servletContext.getContextPath() + "/index.xhtml")
				.queryParam("msg", "Compra realizada com sucesso").build();

		Response response = Response.seeOther(responseUri).build();

		return response;
	}
}
