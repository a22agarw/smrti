package org.smrti.service.log.service;

import org.smrti.service.log.dto.LogSubmitRequestDto;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface TagService {

    ResponseEntity<Object> submit(LogSubmitRequestDto logSubmitRequestDto);

    ResponseEntity<Object> getByContextId(String contextId);

    ResponseEntity<Object> getByTags(Map<String, String> tags);
}
