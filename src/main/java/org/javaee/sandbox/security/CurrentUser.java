package org.javaee.sandbox.security;

import java.security.Principal;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.javaee.sandbox.dao.SecurityDao;
import org.javaee.sandbox.model.SystemUser;

@Model
public class CurrentUser {

	@Inject
	private HttpServletRequest request;

	@Inject
	private SecurityDao securityDao;

	private SystemUser systemUser;

	@PostConstruct
	public void loadPrincipal() {
		Principal principal = request.getUserPrincipal();
		if (principal != null) {
			String email = request.getUserPrincipal().getName();
			systemUser = securityDao.buscarPorEmail(email);
		}
	}

	public SystemUser get() {
		return systemUser;
	}

	public boolean hasRole(String role) {
		return request.isUserInRole(role);
	}
	
	public String logout() {
		request.getSession().invalidate();
		return "/admin/livros/lista.xhtml?faces-redirect=true";
	}
}
