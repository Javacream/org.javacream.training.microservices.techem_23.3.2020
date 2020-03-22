package org.javacream.util;

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
public class SequenceGeneratorTest {
	@Autowired private SequenceGenerator sequenceGenerator;
	@Test public void createsKey() {
		int key = sequenceGenerator.nextKey();
		Assert.assertNotNull(key);
	}
	@Test public void createdKeysAreSequential() {
		int key1 = sequenceGenerator.nextKey();
		int key2 = sequenceGenerator.nextKey();
		Assert.assertTrue(key2 == (key1 + 1));
	}

}
