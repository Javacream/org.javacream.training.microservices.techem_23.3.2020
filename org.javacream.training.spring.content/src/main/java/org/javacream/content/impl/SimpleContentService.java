package org.javacream.content.impl;



import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.javacream.content.api.Content;
import org.javacream.content.api.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SimpleContentService implements ContentService {

	@Autowired @Qualifier("contentStore")
	private Map<String, Content> contentStore;
	@Override
	public Content findById(String id) {
		if (id == null) {
			throw new IllegalArgumentException("provided id was null");
		}
		Content content = contentStore.get(id);
		if (content == null) {
			throw new IllegalArgumentException("no content for id=" + id);
		}
		return content;
	}

	@Override
	public List<String> findByTag(String tag) {
		if (tag == null || tag.trim().length() == 0) {
			throw new IllegalArgumentException("provided tag was null or empty");
		}
		return contentStore.values().stream().filter(content -> content.getTags().contains(tag)).map(content -> content.getId()).collect(Collectors.toList()); 
	}

}