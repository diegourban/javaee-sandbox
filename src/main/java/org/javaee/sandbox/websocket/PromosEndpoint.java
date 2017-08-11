package org.javaee.sandbox.websocket;

import java.io.IOException;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/canal/promos")
public class PromosEndpoint {

	@OnOpen
	public void onMessage(final Session session) {
		if(session.isOpen()) {
			try {
				session.getBasicRemote().sendText("{\"titulo\":\"Livro JavaOO com 40% de desconto\", \"livroId\":2}");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
