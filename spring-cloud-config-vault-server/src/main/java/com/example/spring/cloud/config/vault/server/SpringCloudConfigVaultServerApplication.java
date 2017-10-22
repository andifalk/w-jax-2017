package com.example.spring.cloud.config.vault.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class SpringCloudConfigVaultServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudConfigVaultServerApplication.class, args);
	}
}
