package org.smrti.service.log.service.implementation;

import com.google.gson.Gson;
import org.smrti.service.log.dto.LogSubmitRequestDto;
import org.smrti.service.log.model.Tag;
import org.smrti.service.log.repository.TagRepository;
import org.smrti.service.log.service.LogService;
import org.smrti.service.log.service.TagService;
import org.smrti.service.log.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Service
@Slf4j
public class TagServiceImplementation implements TagService {

    @Autowired
    TagRepository tagRepository;

    @Autowired
    LogService logService;

    @Override
    public ResponseEntity<Object> submit(LogSubmitRequestDto logSubmitRequestDto) {

        if (!logSubmitRequestDto.getTags().containsKey("context_id")) {
            return new ResponseEntity<>(new Gson().fromJson(ResponseUtils.error("Key context_id is missing."), Object.class),
                    HttpStatus.BAD_REQUEST);
        }

        // Get the current timestamp
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss").format(new java.util.Date());

        // Insert log in mongoDB
        String logId = logService.add(logSubmitRequestDto);

        // For each tag, insert tags in mySQL table
        for (String tagKey: logSubmitRequestDto.getTags().keySet()) {
            String tagValue = logSubmitRequestDto.getTags().get(tagKey);
            Tag tag = new Tag(logId, tagKey, tagValue, timeStamp);
            tagRepository.saveAndFlush(tag);
        }

        return new ResponseEntity<>(new Gson().fromJson(ResponseUtils.success(), Object.class),
                HttpStatus.CREATED);
    }
}
