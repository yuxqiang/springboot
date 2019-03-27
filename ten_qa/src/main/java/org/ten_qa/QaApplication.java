package org.ten_qa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.ten_common.util.IdWorker;

@SpringBootApplication
public class QaApplication {

	public static void main(String[] args) {
		SpringApplication.run(QaApplication.class);
	}

	@Bean
	public IdWorker idWorker() {
		return new IdWorker(1, 1);
	}
}