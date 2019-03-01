package com.hzit.hystrisdashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrix
@EnableHystrixDashboard
public class SpringCloudHystrisDashboardAPP9001 {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringCloudHystrisDashboardAPP9001.class, args);
	}

}
