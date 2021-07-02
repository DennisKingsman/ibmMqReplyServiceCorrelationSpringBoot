package com.ibm.mq.reply.service.demo.jms;

import com.ibm.mq.jms.MQQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.util.Random;

@Slf4j
@Service
public class PaymentService {

    private JmsTemplate jmsTemplate;

    @Autowired
    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @JmsListener(destination = "DEV.QUEUE.1")
    public void receive(Message message) throws JMSException {
        TextMessage textMessage = (TextMessage) message;
        final String textMessageBody = textMessage.getText();
        log.info("### 2 ### Payment Service received message: {} with correlationId: {}",
                textMessageBody,
                textMessage.getJMSCorrelationID());
        // some random logic to complete the order (80% of times it returns true)
        Random random = new Random(); //message to convertAndSend
        String orderCompleted = (random.nextInt(101) >= 20) ? "payment_ok" : "payment_failed";
        // send response
        log.info("### 3 ### Payment Service sending response");
        MQQueue mqQueue = new MQQueue("DEV.QUEUE.1");
        jmsTemplate.convertAndSend(mqQueue, orderCompleted, responseMessage -> {
            responseMessage.setJMSCorrelationID(textMessage.getJMSCorrelationID());
            return responseMessage;
        });
    }

}
