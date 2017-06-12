package activemq.pb;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Consumer1 {

	private ConnectionFactory factory;
	private Connection connection;
	private Session session;
	private MessageConsumer consumer;
	
	public Consumer1(){
		try {
			factory = new ActiveMQConnectionFactory(
					"zx", 
					"123456", 
					"tcp://localhost:61616");
			connection = factory.createConnection();
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	public void receiver() throws Exception{
		Destination destionation = session.createTopic("topic1");
		consumer = session.createConsumer(destionation);
		consumer.setMessageListener(new Listener());
	}
	
	class Listener implements MessageListener{
		@Override
		public void onMessage(Message message) {
			if(message instanceof TextMessage){
				System.out.println("c1收到消息：==========");
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		Consumer1 c1 = new Consumer1();
		c1.receiver();
	}
}
