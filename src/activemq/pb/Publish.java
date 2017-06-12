package activemq.pb;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Publish {
	
	private ConnectionFactory factory;
	private Connection connection;
	private Session session;
	private MessageProducer producer;
	
	public Publish(){
		try {
			factory = new ActiveMQConnectionFactory(
					"zx", 
					"123456", 
					"tcp://localhost:61616");
			connection = factory.createConnection();
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			producer = session.createProducer(null);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessage() throws Exception{
		Destination destination = session.createTopic("topic1");
		TextMessage textMessage = session.createTextMessage("Œ“ «ƒ⁄»›");
		producer.send(destination,textMessage);
	}
	
	public static void main(String[] args) throws Exception{
		Publish p = new Publish();
		p.sendMessage();
	}
	

}
