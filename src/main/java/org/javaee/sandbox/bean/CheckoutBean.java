package org.javaee.sandbox.bean;

import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.javaee.sandbox.model.CarrinhoCompras;
import org.javaee.sandbox.model.Compra;
import org.javaee.sandbox.model.Usuario;

@Model
public class CheckoutBean {

	private Usuario usuario = new Usuario();

	@Inject
	private CarrinhoCompras carrinho;

	@Inject
	private FacesContext facesContext;

	@Transactional
	public void finalizar() {
		Compra compra = new Compra();
		compra.setUsuario(this.usuario);
		carrinho.finalizar(compra);

		String contextName = facesContext.getExternalContext().getContextName();
		HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
		response.setStatus(307); // redirect temporário
		response.setHeader("Location", "/" + contextName + "/service/pagamento?uuid=" + compra.getUuid());
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
