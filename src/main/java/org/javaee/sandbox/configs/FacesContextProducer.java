package org.javaee.sandbox.configs;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

/**
 * JSF e CDI não estão integrados com a injeção do FacesContext. Essa classe é
 * responsável por produzir um FacesContext por request para que seja possível
 * utilizar o @Inject
 * 
 */
public class FacesContextProducer {

	@Produces
	@RequestScoped
	public FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

}
