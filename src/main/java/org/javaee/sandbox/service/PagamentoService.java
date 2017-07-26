package org.javaee.sandbox.service;

import java.net.URI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.servlet.ServletContext;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
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
	
	@Inject
	private JMSContext jmsContext;
	
	@Resource(name = "java:/jms/topics/CarrinhoComprasTopico")
	private Destination destination;

	private static ExecutorService executor = Executors.newFixedThreadPool(50);

	@POST
	public void pagar(@Suspended final AsyncResponse ar, @QueryParam("uuid") String uuid) {
		Compra compra = compraDao.buscaPorUuid(uuid);

		final String contextPath = servletContext.getContextPath();

		final JMSProducer producer = jmsContext.createProducer();
		
		executor.submit(() -> {
			try {
				String resposta = pagamentoGateway.pagar(compra.getTotal());
				System.out.println(resposta);

				producer.send(destination, compra.getUuid());
				
				URI responseUri = UriBuilder.fromPath("http://localhost:8080" + contextPath + "/index.xhtml")
						.queryParam("msg", "Compra realizada com sucesso").build();

				Response response = Response.seeOther(responseUri).build();
				ar.resume(response);
			} catch (Exception e) {
				ar.resume(new WebApplicationException());
			}
		});
	}
}
