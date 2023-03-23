package com.mediscreen.serviceui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.mediscreen")
@EnableDiscoveryClient
public class ServiceUiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceUiApplication.class, args);
	}

}
