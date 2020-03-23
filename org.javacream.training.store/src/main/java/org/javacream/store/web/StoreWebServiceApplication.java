package org.javacream.store.web;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@RestController
@RequestMapping(path = "api")
@ManagedResource(description = "Store Management", objectName = "javacream:service=store")
public class StoreWebServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(StoreWebServiceApplication.class, args);
	}
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();
	}
	@PersistenceContext private EntityManager entityManager;

	@GetMapping(path = "store/{category}/{item}", produces = MediaType.TEXT_PLAIN_VALUE)
	public String getStock(@PathVariable("category") String category, @PathVariable("item") String item) {
		return Integer.toString(readStock(category, item));
	}

	@PostMapping(path = "store/{category}/{item}", produces = MediaType.TEXT_PLAIN_VALUE)
	@Transactional
	public void setStock(@PathVariable("category") String category, @PathVariable("item") String item,
			@RequestHeader("stock") int stock) {
		saveStock(category, item, stock);
	}
	
	private void saveStock(String category, String id, int stock) {
		if (category == null || id == null) {
			throw new IllegalArgumentException("category and id must not be null");
		}
		StoreId storeId = new StoreId(category, id);
		
		StoreEntry storeEntry = entityManager.find(StoreEntry.class, storeId);
		if (storeEntry == null) {
			entityManager.persist(new StoreEntry(category, id, stock));
		}else {
			entityManager.merge(new StoreEntry(category, id, stock));
		}
	}	
	private int readStock(String category, String id) {
		counter++;
		try {
			Query query = entityManager.createQuery("select stock from StoreEntry where category = :category and itemId = :id");
			query.setParameter("category", category);
			query.setParameter("id", id);
			Integer result = (Integer) query.getSingleResult();
			return result;
		}
		catch(Exception e) {
			return 0;
		}
	}
	@ManagedOperation(description = "dump store to console")
	public void dumpStock() {
		System.out.println(entityManager.createQuery("select stock from StoreEntry as stock", StoreEntry.class).getResultList());
		
	}

	@ManagedOperation(description = "reset counter")
	public void resetCounter() {
		counter = 0;
	}

	private int counter = 0;

	@ManagedAttribute(description =  "the actual invocation counter")
	public int getCounter() {
		return counter;
	}
	
	
}
