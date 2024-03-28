package org.smrti.service.log.controller;

import org.smrti.service.log.dto.LogPullTagsRequestDto;
import org.smrti.service.log.service.TagService;
import org.smrti.service.log.utils.ValidationUtils;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/log")
public class LogController {

    @Autowired
    TagService tagService;

    @Operation(summary = "Returns logs based on tags")
    @PostMapping(path = "/pull/tags")
    public ResponseEntity<Object> pullLogs(@Valid @RequestBody LogPullTagsRequestDto logPullTagsRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ValidationUtils.getValidationError(bindingResult);
        }
        return tagService.getByTags(logPullTagsRequestDto.getTags());
    }

    @Operation(summary = "Returns logs based on context ID")
    @GetMapping(path = "/pull/id")
    public ResponseEntity<Object> pullLogs(@RequestParam String contextId) {
        return tagService.getByContextId(contextId);
    }
}
