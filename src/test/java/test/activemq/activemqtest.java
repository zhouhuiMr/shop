package test.activemq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.Application;
import com.activemq.order.JMSofOrderCreate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
public class activemqtest {
	@Autowired
	JMSofOrderCreate producer;
	
	@Test
	public void sendMessage() {
		producer.MsgProducerByQueue("test", "hello");
	}
}
