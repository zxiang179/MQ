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
		//第一步：建立ConnectionFactory工厂对象，需要填入用户名、密码、以及要连接的地址，均使用默认即可，默认端口"tcp://localhost:61616"
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				"zx",
				"123456",
				"tcp://localhost:61616");
//		第二步：通过ConnectionFactory工厂对象我们创建一个Connection连接，并调用Connection的start方法开启连接，Connection默认是关闭的。
		Connection connection = connectionFactory.createConnection();
		connection.start();
//		第三步：通过Connection对象创建Session会话，用于接受消息，参数一为是否启用事务，参数二为签收模式，一般设置为自动签收。
//		Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
		Session session = connection.createSession(Boolean.TRUE, Session.CLIENT_ACKNOWLEDGE);
//		第四部：通过Session创建Destination对象，指的是一个客户端用来指定生产消息目标和消费消息来源的目标，在PTP模式中，Destination被称作Queue即队列；在Pub/Sub模式中，Destination被称为Topic即主题。在程序中可以使用多个Queue和Topic。
		Destination desiDestination = session.createQueue("queue1");
//		第五步：我们通过Session对象创建发送和接收对象(生产者和消费者)MessageProducer/MessageConsumer
		MessageProducer messageProducer = session.createProducer(null);
//		第六步：我们可以使用MessageProducer的setDeliveryMode方法为其设置持久性特性和非持久性特性。
//		messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
//		第七步：最后我们使用JMS规范的TextMessage形式创建数据(通过Session对象)，并用MessageProducer的send方法发送数据。同理，客户端使用receive方法进行接收数据。最后不要忘记关闭Connection连接
		/*for(int i=0;i<5;i++){
			TextMessage textMessage = session.createTextMessage();
			textMessage.setText("我是消息内容,id为："+i);
			//arg1:目的地
			//arg2:消息
			//arg3:是否持久化
			//arg4:优先级
			//arg5:消息在MQ上的存放有效期
			messageProducer.send(desiDestination,textMessage,DeliveryMode.NON_PERSISTENT,i,1000*60*1);	
			System.out.println("生产者生产消息---"+i);
		}*/
		
		TextMessage textMessage1 = session.createTextMessage();
		textMessage1.setText("我是消息内容1");
		messageProducer.send(desiDestination,textMessage1,DeliveryMode.PERSISTENT,1,1000*60*20);
		
		TextMessage textMessage2 = session.createTextMessage();
		textMessage2.setText("我是消息内容8");
		messageProducer.send(desiDestination,textMessage2,DeliveryMode.PERSISTENT,8,1000*60*20);
		
		TextMessage textMessage3 = session.createTextMessage();
		textMessage3.setText("我是消息内容4");
		messageProducer.send(desiDestination,textMessage3,DeliveryMode.PERSISTENT,4,1000*60*20);
		
		session.commit();
		
		if(connection!=null){
			connection.close();
		}
	}
	
}
