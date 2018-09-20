package org.daijie.jenny.eureka.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.discovery.shared.Application;
import com.netflix.eureka.EurekaServerContext;
import com.netflix.eureka.EurekaServerContextHolder;
import com.netflix.eureka.registry.PeerAwareInstanceRegistry;

@RestController
@RequestMapping(value = "/applications", produces = "application/json;charset=UTF-8")
public class ApplicationController {

	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Application> getServerMonitorings(HttpServletResponse response) {
		return getRegistry().getSortedApplications();
	}

	private PeerAwareInstanceRegistry getRegistry() {
		return getServerContext().getRegistry();
	}

	private EurekaServerContext getServerContext() {
		return EurekaServerContextHolder.getInstance().getServerContext();
	}
}
