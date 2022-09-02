package com.microservice.providerside.model.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProvidersDTO {
    private String name;
}
