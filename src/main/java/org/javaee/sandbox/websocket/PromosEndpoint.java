package org.javaee.sandbox.websocket;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.javaee.sandbox.model.Promo;

@ServerEndpoint("/canal/promos")
public class PromosEndpoint {

	@Inject
	private UsuariosSession usuarios;

	@OnOpen
	public void onMessage(final Session session) {
		usuarios.add(session);
	}
	
	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		usuarios.remove(session);
		System.out.println(closeReason.getCloseCode());
	}

	public void send(final Promo promo) {
		final List<Session> sessions = usuarios.getSessions();
		for (final Session session : sessions) {
			if (session.isOpen()) {
				try {
					session.getBasicRemote().sendText(promo.toJson());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
}
