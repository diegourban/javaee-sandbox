package org.javaee.sandbox.websocket;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.Session;

@ApplicationScoped
public class UsuariosSession {

	private List<Session> sessions = new ArrayList<>();

	public void add(Session session) {
		sessions.add(session);
	}

	public List<Session> getSessions() {
		return this.sessions;
	}

	public void remove(Session session) {
		this.sessions.remove(session);
	}

}
