package org.daijie.jenny;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServerEurekaStartUp {

    public static void main(String[] args) {
		SpringApplication.run(ServerEurekaStartUp.class, args);
    }

}