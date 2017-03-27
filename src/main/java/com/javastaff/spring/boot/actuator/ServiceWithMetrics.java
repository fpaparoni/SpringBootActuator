package com.javastaff.spring.boot.actuator;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.stereotype.Service;

@Service
public class ServiceWithMetrics {
	@Autowired
	private CounterService counterService;
	
	@Autowired
	private GaugeService gaugeService;
	
	@PostConstruct
	public void faccioCose() {
		counterService.increment("faccio.cose");
		gaugeService.submit("faccio.grandi.cose", 123);
	}
}
