package org.ten_article;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.ten_common.util.IdWorker;

@SpringBootApplication
public class ArticleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArticleApplication.class);
	}

	@Bean
	public IdWorker idWorker() {
		return new IdWorker(1, 1);
	}
}
