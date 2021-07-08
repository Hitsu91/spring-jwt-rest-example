package dev.chaofang.springsecurityjwt.advice_cotroller;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorMessage {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
}
