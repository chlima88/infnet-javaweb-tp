package br.edu.infnet.tpapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class TpappApplication {

	public static void main(String[] args) {
		SpringApplication.run(TpappApplication.class, args);
	}

}
