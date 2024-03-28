package org.smrti.service.log.service;

import com.google.gson.JsonObject;

public interface LogService {

    JsonObject getLogById(String contextId);
}
