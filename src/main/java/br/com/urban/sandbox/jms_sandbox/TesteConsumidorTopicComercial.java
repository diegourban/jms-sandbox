package br.com.urban.sandbox.jms_sandbox;

import java.io.Serializable;
import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.InitialContext;

import br.com.urban.sandbox.jms_sandbox.model.Pedido;

public class TesteConsumidorTopicComercial {

	public static void main(String[] args) throws Exception {
		InitialContext context = new InitialContext();
		ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
		
		Connection connection = factory.createConnection();
		connection.setClientID("comercial");
		connection.start(); 
		
		//Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
		Session session = connection.createSession(true, Session.SESSION_TRANSACTED);
		Topic topico = (Topic) context.lookup("loja");
		//MessageConsumer consumer = session.createConsumer(topico);
		
		MessageConsumer consumer = session.createDurableSubscriber(topico, "assinatura");
		
		consumer.setMessageListener(new MessageListener() {
			
			@Override
			public void onMessage(Message message) {
				
				//TextMessage textMessage = (TextMessage) message;
				ObjectMessage objectMessage = (ObjectMessage) message;
				
				try {
					//System.out.println("Receiving message: " + textMessage.getText());
					Pedido pedido = (Pedido) objectMessage.getObject();
					System.out.println("Receiving message: " + pedido);
					//message.acknowledge();
					session.commit();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
		
		new Scanner(System.in).nextLine();
		
		session.close();
		connection.close();
		context.close();
	}

}
