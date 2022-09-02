package com.microservice.clientside.model.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Builder
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO<T> {
    private T data;
    private Exception exception;
    private String message;
}
