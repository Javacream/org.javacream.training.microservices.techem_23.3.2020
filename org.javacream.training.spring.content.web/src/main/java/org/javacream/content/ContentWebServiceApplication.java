package org.javacream.content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javacream.content.api.Content;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class ContentWebServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContentWebServiceApplication.class, args);

	}
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();
	}
	@Bean
	@Qualifier("contentStore")
	Map<String, Content> contentStore() {
		HashMap<String, Content> testData = new HashMap<>();
		List<String> tags1 = new ArrayList<String>();
		tags1.add("IT");
		tags1.add("Java");
		tags1.add("Spring");
		Content content = new Content("SpringInAction", tags1, "A very good book");
		testData.put(content.getId(), content);

		List<String> tags2 = new ArrayList<String>();
		tags2.add("IT");
		Content content2 = new Content("DockerInAction", tags2, "Another very good book");
		testData.put(content2.getId(), content2);

		return testData;
	}

}
