package activemq.helloworld;


import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Sender {

	public static void main(String[] args) throws Exception{
		//��һ��������ConnectionFactory����������Ҫ�����û��������롢�Լ�Ҫ���ӵĵ�ַ����ʹ��Ĭ�ϼ��ɣ�Ĭ�϶˿�"tcp://localhost:61616"
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				"zx",
				"123456",
				"tcp://localhost:61616");
//		�ڶ�����ͨ��ConnectionFactory�����������Ǵ���һ��Connection���ӣ�������Connection��start�����������ӣ�ConnectionĬ���ǹرյġ�
		Connection connection = connectionFactory.createConnection();
		connection.start();
//		��������ͨ��Connection���󴴽�Session�Ự�����ڽ�����Ϣ������һΪ�Ƿ��������񣬲�����Ϊǩ��ģʽ��һ������Ϊ�Զ�ǩ�ա�
		Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
//		���Ĳ���ͨ��Session����Destination����ָ����һ���ͻ�������ָ��������ϢĿ���������Ϣ��Դ��Ŀ�꣬��PTPģʽ�У�Destination������Queue�����У���Pub/Subģʽ�У�Destination����ΪTopic�����⡣�ڳ����п���ʹ�ö��Queue��Topic��
		Destination desiDestination = session.createQueue("queue1");
//		���岽������ͨ��Session���󴴽����ͺͽ��ն���(�����ߺ�������)MessageProducer/MessageConsumer
		MessageProducer messageProducer = session.createProducer(desiDestination);
//		�����������ǿ���ʹ��MessageProducer��setDeliveryMode����Ϊ�����ó־������Ժͷǳ־������ԡ�
		messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
//		���߲����������ʹ��JMS�淶��TextMessage��ʽ��������(ͨ��Session����)������MessageProducer��send�����������ݡ�ͬ���ͻ���ʹ��receive�������н������ݡ����Ҫ���ǹر�Connection����
		for(int i=0;i<5;i++){
			TextMessage textMessage = session.createTextMessage();
			textMessage.setText("������Ϣ����,idΪ��"+i);
			messageProducer.send(textMessage);	
			System.out.println("������������Ϣ---"+i);
		}
		
		if(connection!=null){
			connection.close();
		}
	}
	
}
