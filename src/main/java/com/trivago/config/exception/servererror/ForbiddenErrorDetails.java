package com.trivago.config.exception.servererror;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ForbiddenErrorDetails {

    private String title;
    private int status;
    private String details;
    private LocalDateTime time;

}
