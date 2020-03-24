package org.javacream.content.web;

import java.util.List;

import org.javacream.content.api.Content;
import org.javacream.content.api.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping(path = "api/content")
public class ContentWebService {

	@Autowired
	private ContentService contentService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "{id}")
	public Content findById(@PathVariable("id") String id) {
		try {
			return contentService.findById(id);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "tags/{tag}")
	public List<String> findByTag(@PathVariable("tag") String tag) {
		return contentService.findByTag(tag);
	}

	@PostMapping(path = "{id}", consumes = MediaType.TEXT_PLAIN_VALUE)
	public void saveContent(@PathVariable("id") String id, @RequestBody String data, @RequestHeader HttpHeaders headers) {
		List<String> tags = headers.get("tags");
		Content content = new Content(id, tags, data);
		contentService.createContent(content);
	}

}
