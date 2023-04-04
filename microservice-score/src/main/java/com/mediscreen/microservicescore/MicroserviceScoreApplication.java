package com.mediscreen.microservicescore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.mediscreen")
@EnableDiscoveryClient
public class MicroserviceScoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceScoreApplication.class, args);
	}

}
