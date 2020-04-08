package org.daijie.jenny;

import org.daijie.shiro.annotation.EnableShiro;
import org.daijie.swagger.EnableMySwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients
@RefreshScope
@EnableShiro
@EnableMySwagger
@SpringBootApplication
public class ApiSysStartUp {

	public static void main(String[] args) {
		try {
			SpringApplication.run(ApiSysStartUp.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
