package org.javacream.content.impl;

import java.util.List;

import org.javacream.content.api.Content;
import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.repository.CrudRepository;

@N1qlPrimaryIndexed
public interface ContentRepository extends CrudRepository<Content, String> {
	
	@Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter} AND $1 in tags")
	List<Content> findByTag(String tag);
}
