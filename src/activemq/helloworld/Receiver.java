package activemq.helloworld;


import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Receiver {

	public static void main(String[] args) throws Exception{
		//��һ��������ConnectionFactory����������Ҫ�����û��������롢�Լ�Ҫ���ӵĵ�ַ����ʹ��Ĭ�ϼ��ɣ�Ĭ�϶˿�"tcp://localhost:61616"
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				"zx",
				"123456",
				"tcp://localhost:61616");
//				�ڶ�����ͨ��ConnectionFactory�����������Ǵ���һ��Connection���ӣ�������Connection��start�����������ӣ�ConnectionĬ���ǹرյġ�
		Connection connection = connectionFactory.createConnection();
		connection.start();
//				��������ͨ��Connection���󴴽�Session�Ự�����ڽ�����Ϣ������һΪ�Ƿ��������񣬲�����Ϊǩ��ģʽ��һ������Ϊ�Զ�ǩ�ա�
		Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
//				���Ĳ���ͨ��Session����Destination����ָ����һ���ͻ�������ָ��������ϢĿ���������Ϣ��Դ��Ŀ�꣬��PTPģʽ�У�Destination������Queue�����У���Pub/Subģʽ�У�Destination����ΪTopic�����⡣�ڳ����п���ʹ�ö��Queue��Topic��
		Destination desiDestination = session.createQueue("queue1");
//				���岽������ͨ��Session���󴴽����ͺͽ��ն���(�����ߺ�������)MessageProducer/MessageConsumer
		MessageConsumer messageConsumer = session.createConsumer(desiDestination);
		
		while(true){
			TextMessage msg = (TextMessage)messageConsumer.receive();
			if(msg==null)break;
			System.out.println("�յ������ݣ�"+msg.getText());
		}
		
		if(connection!=null){
			connection.close();
		}
	}
	
}
