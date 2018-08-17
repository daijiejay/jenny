package org.daijie.jenny;

import org.daijie.core.controller.EnableExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@EnableDiscoveryClient
@EnableExceptionHandler
@RefreshScope
@SpringBootApplication
public class ApiDocStartUp {

	public static void main(String[] args) {
		try {
			SpringApplication.run(ApiDocStartUp.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
