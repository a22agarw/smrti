package org.smrti.service.log.dto;

import lombok.Data;

import java.util.HashMap;

/**
 * DTO class for general response
 */
@Data
public class GeneralResponseDto {

    boolean status;
    Object data;
    String error;

    public GeneralResponseDto(Object data) {
        this.status = true;
        this.data = data;
        this.error = "";
    }

    public GeneralResponseDto(String error) {
        this.status = false;
        this.data = new HashMap<>();
        this.error = error;
    }
}
