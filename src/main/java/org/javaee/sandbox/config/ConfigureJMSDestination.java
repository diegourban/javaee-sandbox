package org.javaee.sandbox.config;

import javax.jms.JMSDestinationDefinition;

@JMSDestinationDefinition(name = "java:/jms/topics/CarrinhoComprasTopico", interfaceName = "javax.jms.Topic")
public class ConfigureJMSDestination {

}