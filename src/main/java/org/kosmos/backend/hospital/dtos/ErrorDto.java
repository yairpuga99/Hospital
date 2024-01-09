package org.kosmos.backend.hospital.dtos;

import lombok.Data;

@Data
public class ErrorDto {
    private String errorCode;
    private String errorMessage;
}
