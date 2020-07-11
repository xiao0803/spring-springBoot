package com.example.actuator.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TestHealth implements HealthIndicator {
	@Override
	public Health health() {
		try {
			RestTemplate RestTemplate = new RestTemplate();
			RestTemplate.getForObject("http://test-server1104.com/", String.class);
			return Health.up().build();
		} catch (Exception e) {
			// TODO: handle exception
			return Health.down().withDetail("请求test-server1104服务异常：", e.getMessage()).build();
		}
	}
}
