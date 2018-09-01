package org.daijie.jenny;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import de.codecentric.boot.admin.server.config.EnableAdminServer;

@EnableAutoConfiguration
@SpringBootApplication
@EnableAdminServer
public class ServerAdminStartUp {

    public static void main(String[] args) {
		try {
			SpringApplication.run(ServerAdminStartUp.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}