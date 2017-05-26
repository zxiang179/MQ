package activemq.p2p;

import java.awt.font.TextMeasurer;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;


public class Producer {
	
	//����ģʽ
	
	//1 ���ӹ���
	private ConnectionFactory connectionFactory;
	//2 ���Ӷ���
	private Connection connection;
	//3 session����
	private Session session;
	//4 ������
	private MessageProducer messageProducer;
	
	public Producer(){
		try{
			this.connectionFactory = new ActiveMQConnectionFactory(
					"zx",
					"123456",
					"tcp://localhost:61616");
			this.connection=this.connectionFactory.createConnection();
			this.connection.start();
			this.session= this.connection.createSession(Boolean.FALSE,Session.AUTO_ACKNOWLEDGE);
			this.messageProducer=this.session.createProducer(null);
		}catch(JMSException e){
			e.printStackTrace();
		}
	}
	
	public Session getSession(){
		return this.session;
	}
	
	public void send1(){
		try {
			
			Destination destination = this.session.createQueue("first");
			MapMessage msg1 = this.session.createMapMessage();
			msg1.setString("name", "����");
			msg1.setString("age", "23");
			msg1.setStringProperty("color", "blue");
			msg1.setIntProperty("sal", 2200);
			MapMessage msg2 = this.session.createMapMessage();
			msg2.setString("name", "����");
			msg2.setString("age", "26");
			msg2.setStringProperty("color", "red");
			msg2.setIntProperty("sal", 1200);
			MapMessage msg3 = this.session.createMapMessage();
			msg3.setString("name", "����");
			msg3.setString("age", "29");
			msg3.setStringProperty("color", "green");
			msg3.setIntProperty("sal", 4200);
			MapMessage msg4 = this.session.createMapMessage();
			msg4.setString("name", "����");
			msg4.setString("age", "30");
			msg4.setStringProperty("color", "blue");
			msg4.setIntProperty("sal", 3230);
			/*Destination destination = this.session.createQueue("first");
			MapMessage msg1 = this.session.createMapMessage();
			msg1.setString("name", "����");
			msg1.setString("age", "23");
			msg1.setStringProperty("color", "blue");
			msg1.setIntProperty("sal", 2200);
			int id =1;
			msg1.setInt("id", id);
			String receiver = id%2 ==0 ? "A":"B";
			msg1.setStringProperty("receiver", receiver);
			
			MapMessage msg2 = this.session.createMapMessage();
			msg2.setString("name", "����");
			msg2.setString("age", "26");
			msg2.setStringProperty("color", "red");
			msg2.setIntProperty("sal", 1200);
			id =2;
			msg2.setInt("id", id);
			receiver = id%2 ==0 ? "A":"B";
			msg2.setStringProperty("receiver", receiver);
			
			MapMessage msg3 = this.session.createMapMessage();
			msg3.setString("name", "����");
			msg3.setString("age", "29");
			msg3.setStringProperty("color", "green");
			msg3.setIntProperty("sal", 4200);
			id =3;
			msg3.setInt("id", id);
			receiver = id%2 ==0 ? "A":"B";
			msg3.setStringProperty("receiver", receiver);
			
			MapMessage msg4 = this.session.createMapMessage();
			msg4.setString("name", "����");
			msg4.setString("age", "30");
			msg4.setStringProperty("color", "blue");
			msg4.setIntProperty("sal", 3230);
			id =4;
			msg4.setInt("id", id);
			receiver = id%2 ==0 ? "A":"B";
			msg4.setStringProperty("receiver", receiver);*/
			
			this.messageProducer.send(destination,msg1,DeliveryMode.NON_PERSISTENT,2,1000*60*10L);
			this.messageProducer.send(destination,msg2,DeliveryMode.NON_PERSISTENT,3,1000*60*10L);
			this.messageProducer.send(destination,msg3,DeliveryMode.NON_PERSISTENT,6,1000*60*10L);
			this.messageProducer.send(destination,msg4,DeliveryMode.NON_PERSISTENT,9,1000*60*10L);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	public void send2(){
		try {
			Destination destination = this.session.createQueue("first");
			TextMessage msg = this.session.createTextMessage("����һ���ַ�������");
			this.messageProducer.send(destination,msg,DeliveryMode.NON_PERSISTENT,9,1000*60*10L);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Producer producer = new Producer();
		producer.send1();
//		producer.send2();
	}

}
