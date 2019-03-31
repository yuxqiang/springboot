package org.ten_gathering;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.ten_common.util.IdWorker;

@SpringBootApplication
@EnableCaching
public class GatheringApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatheringApplication.class);
	}

	@Bean
	public IdWorker idWorker() {
		return new IdWorker(1, 1);
	}
}
