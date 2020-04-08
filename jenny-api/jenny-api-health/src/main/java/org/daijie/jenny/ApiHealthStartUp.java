package org.daijie.jenny;

import org.daijie.shiro.annotation.EnableShiro;
import org.daijie.swagger.EnableMySwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableMySwagger
@EnableDiscoveryClient
@EnableFeignClients
@RefreshScope
@EnableShiro
@SpringBootApplication
public class ApiHealthStartUp {

	public static void main(String[] args) {
		try {
			SpringApplication.run(ApiHealthStartUp.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
