package com.hzit.dept;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@MapperScan("com.hzit.dept.mapper")
@EnableEurekaClient
@EnableDiscoveryClient
public class SpringCloudProviderDeptApp {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudProviderDeptApp.class, args);
	}
}
