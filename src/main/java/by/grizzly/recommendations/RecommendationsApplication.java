package by.grizzly.recommendations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class RecommendationsApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		ApiContextInitializer.init();
		return application.sources(RecommendationsApplication.class);
	}

	public static void main(String[] args) {
		ApiContextInitializer.init();
		SpringApplication.run(RecommendationsApplication.class, args);
	}

}
