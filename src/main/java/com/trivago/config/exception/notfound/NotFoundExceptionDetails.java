package com.trivago.config.exception.notfound;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
public class NotFoundExceptionDetails {
    private String title;
    private int status;
    private String details;
    private LocalDateTime time;
}
