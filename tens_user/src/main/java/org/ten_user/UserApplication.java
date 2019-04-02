package org.ten_user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.ten_common.util.IdWorker;

@SpringBootApplication
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class);
	}

	@Bean
	public IdWorker idWorker() {
		return new IdWorker(1, 1);
	}
}
