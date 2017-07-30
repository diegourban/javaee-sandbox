package org.javaee.sandbox.service;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.javaee.sandbox.dao.CompraDao;
import org.javaee.sandbox.infra.MailSender;
import org.javaee.sandbox.model.Compra;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/topics/CarrinhoComprasTopico") })
public class EnviaEmailCompra implements MessageListener {

	@Inject
	private CompraDao compraDao;

	@Inject
	private MailSender mailSender;

	@Override
	public void onMessage(Message message) {
		try {
			TextMessage textMessage = (TextMessage) message;
			Compra compra = compraDao.buscaPorUuid(textMessage.getText());

			String messageBody = "Sua compra foi realizada com sucesso com número de pedido " + compra.getUuid();
			mailSender.send("compras@casadocodigo.com.br", compra.getUsuario().getEmail(), "Nova Compra", messageBody);
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}

}
