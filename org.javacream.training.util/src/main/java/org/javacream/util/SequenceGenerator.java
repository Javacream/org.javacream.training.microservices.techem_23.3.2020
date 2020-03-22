package org.javacream.util;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class SequenceGenerator {

	@Autowired
	private EntityManager entityManager;

	public int nextKey() {
		Integer key = (Integer) entityManager.createNativeQuery("select col_key from keys").getSingleResult();
		key++;
		Query query = entityManager.createNativeQuery("update keys set col_key= :key");
		query.setParameter("key", key);
		query.executeUpdate();
		return key;
	}
}
