package org.smrti.service.log.service;

import com.google.gson.JsonObject;
import org.smrti.service.log.dto.LogSubmitRequestDto;

public interface LogService {

    String add(LogSubmitRequestDto logSubmitRequestDto);

    JsonObject getLogById(String contextId);
}
