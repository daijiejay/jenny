package org.daijie.jenny;

import org.daijie.shiro.security.annotation.EnableShiroSecurityServer;
import org.daijie.swagger.EnableMySwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableMySwagger
@EnableShiroSecurityServer
@SpringBootApplication
public class ServerShiroStartUp {

	public static void main(String[] args) {
		try {
			SpringApplication.run(ServerShiroStartUp.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
