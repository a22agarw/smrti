package org.smrti.service.log.dto;

import lombok.Data;

import java.util.Map;

@Data
public class LogPullTagsRequestDto {

    Map<String, String> tags;

    String timestamp_start;

    String timestamp_end;
}
