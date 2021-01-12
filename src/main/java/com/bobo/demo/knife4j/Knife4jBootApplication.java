package com.bobo.demo.knife4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
public class Knife4jBootApplication {

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(Knife4jBootApplication.class, args);
        
        Environment env = application.getEnvironment();
        String port = env.getProperty("server.port");
        String hostAddress = InetAddress.getLocalHost().getHostAddress();
//        System.out.println(env.getProperty("spring.application.name"));
        System.out.println("http://"+hostAddress+":"+port);
        System.out.println("http://"+hostAddress+":"+port + "/doc.html");
    }

}
