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
//		Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
		Session session = connection.createSession(Boolean.TRUE, Session.CLIENT_ACKNOWLEDGE);
//		���Ĳ���ͨ��Session����Destination����ָ����һ���ͻ�������ָ��������ϢĿ���������Ϣ��Դ��Ŀ�꣬��PTPģʽ�У�Destination������Queue�����У���Pub/Subģʽ�У�Destination����ΪTopic�����⡣�ڳ����п���ʹ�ö��Queue��Topic��
		Destination desiDestination = session.createQueue("queue1");
//		���岽������ͨ��Session���󴴽����ͺͽ��ն���(�����ߺ�������)MessageProducer/MessageConsumer
		MessageProducer messageProducer = session.createProducer(null);
//		�����������ǿ���ʹ��MessageProducer��setDeliveryMode����Ϊ�����ó־������Ժͷǳ־������ԡ�
//		messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
//		���߲����������ʹ��JMS�淶��TextMessage��ʽ��������(ͨ��Session����)������MessageProducer��send�����������ݡ�ͬ���ͻ���ʹ��receive�������н������ݡ����Ҫ���ǹر�Connection����
		/*for(int i=0;i<5;i++){
			TextMessage textMessage = session.createTextMessage();
			textMessage.setText("������Ϣ����,idΪ��"+i);
			//arg1:Ŀ�ĵ�
			//arg2:��Ϣ
			//arg3:�Ƿ�־û�
			//arg4:���ȼ�
			//arg5:��Ϣ��MQ�ϵĴ����Ч��
			messageProducer.send(desiDestination,textMessage,DeliveryMode.NON_PERSISTENT,i,1000*60*1);	
			System.out.println("������������Ϣ---"+i);
		}*/
		
		TextMessage textMessage1 = session.createTextMessage();
		textMessage1.setText("������Ϣ����1");
		messageProducer.send(desiDestination,textMessage1,DeliveryMode.PERSISTENT,1,1000*60*20);
		
		TextMessage textMessage2 = session.createTextMessage();
		textMessage2.setText("������Ϣ����8");
		messageProducer.send(desiDestination,textMessage2,DeliveryMode.PERSISTENT,8,1000*60*20);
		
		TextMessage textMessage3 = session.createTextMessage();
		textMessage3.setText("������Ϣ����4");
		messageProducer.send(desiDestination,textMessage3,DeliveryMode.PERSISTENT,4,1000*60*20);
		
		session.commit();
		
		if(connection!=null){
			connection.close();
		}
	}
	
}
