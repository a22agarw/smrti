package org.smrti.service.log.service.implementation;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.smrti.service.log.dto.LogSubmitRequestDto;
import org.smrti.service.log.model.Tag;
import org.smrti.service.log.repository.TagRepository;
import org.smrti.service.log.service.LogService;
import org.smrti.service.log.service.TagService;
import org.smrti.service.log.utils.Constants;
import org.smrti.service.log.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

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

    @Override
    public ResponseEntity<Object> getByContextId(String contextId) {
        List<String> logIds = tagRepository.findLogIdByTagKey(Constants.contextTagKey, contextId);

        if (logIds.isEmpty()) {
            return new ResponseEntity<>(new Gson().fromJson(ResponseUtils.error("Context id does not exist."), Object.class),
                    HttpStatus.NOT_FOUND);
        }

        JsonArray result = new JsonArray();

        // Convert arraylist to set to remove any duplicate log ID
        Set<String> setLogIds = new LinkedHashSet<>(logIds);
        for (String logId: setLogIds) {
            JsonObject log = logService.getLogById(logId);
            result.add(log);
        }

        return new ResponseEntity<>(new Gson().fromJson(ResponseUtils.success(result), Object.class),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getByTags(Map<String, String> tags) {

        int totalTags = tags.keySet().size();
        int processedTags = 0;

        List<String> selectedLogIds = new ArrayList<>();
        List<String> finalLogIds = new ArrayList<>();

        for (String tagKey: tags.keySet()) {

            String tagValue = tags.get(tagKey);
            processedTags++;

            // If the last tag is being processed, then set the final result in finalLogIds
            // else keep updating selectedLogIds to restrict data
            if (processedTags == totalTags) {
                if (selectedLogIds.isEmpty()) {
                    // This condition is executed when there is exactly one tag present in the input
                    finalLogIds = tagRepository.findLogIdByTagKey(tagKey, tagValue);
                } else {
                    // Get the final result for the last tag being processed
                    // This will be the final list of log_id after processing all tags
                    finalLogIds = tagRepository.findByLogIdAndTagKey(selectedLogIds, tagKey, tagValue);
                }
            } else {
                if (processedTags == 1) {
                    // In every first run of the loop, get the log_id list
                    selectedLogIds = tagRepository.findLogIdByTagKey(tagKey, tagValue);
                } else {
                    // In every subsequent run, keep restricting on the basis of context_id and tag_key
                    // This keeps restricting data set for every tag query being executed
                    List<String> copiedSelectedLogIds = new ArrayList<>(selectedLogIds);
                    selectedLogIds = tagRepository.findByLogIdAndTagKey(copiedSelectedLogIds, tagKey, tagValue);
                }
            }
        }

        JsonArray result = new JsonArray();

        // Convert arraylist to a set to remove any duplicate context ID
        Set<String> setLogIds = new LinkedHashSet<>(finalLogIds);
        for (String logId: setLogIds) {
            JsonObject log = logService.getLogById(logId);
            result.add(log);
        }

        return new ResponseEntity<>(new Gson().fromJson(ResponseUtils.success(result), Object.class),
                HttpStatus.OK);
    }
}
