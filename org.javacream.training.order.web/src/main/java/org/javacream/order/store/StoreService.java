package org.javacream.order.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class StoreService {

	@Autowired
	@Qualifier("store")
	private RestTemplate restTemplate;

	public int getStock(String category, String id) {
		return Integer.parseInt(
				restTemplate.getForObject("http://localhost:9091/api/store/" + category + "/" + id, String.class));

	}

	public void setStock(String category, String id, int stock) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("stock", Integer.toString(stock));
		HttpEntity<String> entity = new HttpEntity<String>("", headers);
		restTemplate.postForLocation("http://localhost:9091/api/store/" + category + "/" + id, entity);
	}

}