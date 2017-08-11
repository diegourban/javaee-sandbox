package org.javaee.sandbox.bean;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import org.javaee.sandbox.model.Promo;
import org.javaee.sandbox.websocket.PromosEndpoint;

@Model
public class AdminPromosBean {

	private Promo promo = new Promo();

	@Inject
	private PromosEndpoint promos;

	public void enviar() {
		promos.send(promo);
	}

	public Promo getPromo() {
		return promo;
	}

	public void setPromo(Promo promo) {
		this.promo = promo;
	}

}
