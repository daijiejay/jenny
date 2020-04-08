package org.daijie.jenny;

import org.daijie.swagger.EnableMySwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableMySwagger
@EnableDiscoveryClient
@SpringBootApplication
public class CloudSysStartUp {

	public static void main(String[] args) {
		try {
			SpringApplication.run(CloudSysStartUp.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
