package org.smrti.service.log.service.implementation;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.smrti.service.log.dto.LogSubmitRequestDto;
import org.smrti.service.log.model.Log;
import org.smrti.service.log.repository.LogRepository;
import org.smrti.service.log.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LogServiceImplementation implements LogService {

    @Autowired
    LogRepository logRepository;

    @Override
    public String add(LogSubmitRequestDto logSubmitRequestDto) {

        // Create a complete log object with tags and log
        JsonObject log = new JsonObject();
        String tagsJsonString = new Gson().toJson(logSubmitRequestDto.getTags());
        log.add("tags", new Gson().fromJson(tagsJsonString, JsonObject.class));
        log.addProperty("value", logSubmitRequestDto.getLog());

        // Save the new appended log
        Log newLog = new Log(log);
        Log savedLog = logRepository.save(newLog);

        return savedLog.getId();
    }
}
