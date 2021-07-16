package com.ibm.mq.reply.service.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class JmsClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(JmsClientApplication.class, args);
    }

}
