package org.javacream.content;

import java.util.Arrays;
import java.util.List;

import org.javacream.content.api.Content;
import org.javacream.content.api.ContentService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ContentServiceTest {

	private static final String VALID_ID = "SpringinAction";
	private static final String INVALID_ID = "5&6%%";
	private static final String VALID_TAG = "Java";
	private static final String INVALID_TAG = ".NET";
	private static final String VALID_DATA = "TES%";
	private static final String INVALID_DATA = "NOT_TEST";
	
	
	
	@Autowired
	private ContentService contentService;

	@Test
	public void contextLoads() {

	}

	@Test
	public void validResourceIdIsFound() {
		Assert.assertNotNull(contentService.findById(VALID_ID));
		System.out.println(contentService.findById(VALID_ID));

	}
	@Test
	public void validTagIsFound() {
		Assert.assertTrue(contentService.findByTag(VALID_TAG).size() > 0);

	}
	@Test
	public void invalidTagIsNotFound() {
		Assert.assertTrue(contentService.findByTag(INVALID_TAG).size() == 0);

	}
	@Test (expected = IllegalArgumentException.class)
	public void nullTagThrowsIllegalArgumentException() {
		contentService.findByTag(null);

	}
	@Test (expected = IllegalArgumentException.class)
	public void invalidResourceIdThrowsIllegalArgumentException() {
		contentService.findById(INVALID_ID);

	}
	@Test (expected = IllegalArgumentException.class)
	public void nullThrowsIllegalArgumentException() {
		contentService.findById(null);

	}

	@Test
	public void createContent() {
		List<String> tags = Arrays.asList(new String[] {"this", "that"});
		contentService.createContent(new Content(Double.toString(Math.random()), tags, "TEST"));

	}

	@Test
	public void deleteContent() {
		List<String> tags = Arrays.asList(new String[] {"this", "that"});
		final String TEST_ID="TEST_ID_";
		contentService.createContent(new Content(TEST_ID, tags, "TEST"));
		Assert.assertNotNull(contentService.findById(TEST_ID));
		contentService.deleteById(TEST_ID);

	}
	
	@Test
	public void validDataIsFound() {
		createContent();
		Assert.assertTrue(contentService.findByData(VALID_DATA).size() > 0);

	}
	@Test
	public void invalidDataIsNotFound() {
		Assert.assertTrue(contentService.findByData(INVALID_DATA).size()== 0);

	}
	
}
