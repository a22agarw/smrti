package org.smrti.service.log.service.implementation;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.smrti.service.log.model.Log;
import org.smrti.service.log.repository.LogRepository;
import org.smrti.service.log.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.smrti.service.log.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class LogServiceImplementation implements LogService {

    @Autowired
    LogRepository logRepository;

    @Override
    public JsonObject getLogById(String logId) {
        Optional<Log> log = logRepository.findById(logId);
        if (log.isEmpty()) {
            return null;
        }

        return new Gson().fromJson(Constants.gson.toJson(log), JsonObject.class);
    }
}
