package org.smrti.service.log.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface TagService {

    ResponseEntity<Object> getByContextId(String contextId);

    ResponseEntity<Object> getByTags(Map<String, String> tags);
}
