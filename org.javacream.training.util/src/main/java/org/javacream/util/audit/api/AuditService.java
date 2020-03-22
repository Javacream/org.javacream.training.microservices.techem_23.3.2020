package org.javacream.util.audit.api;

import java.util.List;

public interface AuditService {

	public void audit(String message);
	public List<String> logAll();
}
