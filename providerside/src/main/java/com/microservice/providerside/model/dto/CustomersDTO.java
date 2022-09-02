package com.microservice.providerside.model.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Builder
@Component
@AllArgsConstructor
@NoArgsConstructor
public class CustomersDTO {
    private String name;
    private String provider;
}
