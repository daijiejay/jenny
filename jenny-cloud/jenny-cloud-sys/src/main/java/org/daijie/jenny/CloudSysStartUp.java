package org.daijie.jenny;

import org.daijie.core.annotation.EnableParametersFilter;
import org.daijie.core.controller.EnableExceptionHandler;
import org.daijie.core.swagger.EnableMySwagger;
import org.daijie.jdbc.annotation.EnableMybatis;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableMySwagger
@EnableMybatis(basePackages = {"org.daijie.jenny.common.mapper.sys"})
@EnableParametersFilter
@EnableDiscoveryClient
@EnableExceptionHandler
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
