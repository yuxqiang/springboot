package org.ten_recruit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.ten_common.util.IdWorker;

@SpringBootApplication
public class RecruitApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecruitApplication.class, args);
	}

	@Bean
	public IdWorker idWorkker() {
		return new IdWorker(1, 1);
	}

}
