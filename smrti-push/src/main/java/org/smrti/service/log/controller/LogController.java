package org.smrti.service.log.controller;

import org.smrti.service.log.dto.LogSubmitRequestDto;
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

    @Operation(summary = "Submits logs")
    @PostMapping(path = "/push")
    public ResponseEntity<Object> submitLog(@Valid @RequestBody LogSubmitRequestDto logSubmitRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ValidationUtils.getValidationError(bindingResult);
        }
        return tagService.submit(logSubmitRequestDto);
    }
}
