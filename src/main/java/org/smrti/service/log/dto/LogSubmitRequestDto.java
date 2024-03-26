package org.smrti.service.log.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Map;

@Data
public class LogSubmitRequestDto {

    Map<String, String> tags;

    String log;
}
