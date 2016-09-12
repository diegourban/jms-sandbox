package br.com.urban.sandbox.jms_sandbox;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.InitialContext;

public class TesteProdutorLogQueue {

	public static void main(String[] args) throws Exception {
		InitialContext context = new InitialContext();
		ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
		
		Connection connection = factory.createConnection();
		connection.start(); 
		
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination fila = (Destination) context.lookup("LOG");
		MessageProducer producer = session.createProducer(fila);
		
		Message message = session.createTextMessage("INFO ....");
		producer.send(message, DeliveryMode.NON_PERSISTENT, 0, 10000);
		
		// MessageConsumer consumer = session.createConsumer(fila, "JMSPriority > 6" );
		
		session.close();
		connection.close();
		context.close();
	}

}
