package com.microservice.providerside.services;

import com.microservice.providerside.interfaces.IWebClientInterface;
import com.microservice.providerside.model.dto.CustomersDTO;
import com.microservice.providerside.model.dto.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Service
public class WebClientServices implements IWebClientInterface {

    WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:1002/clientSide/api")
            .build();

    @Override
    public List<CustomersDTO> getCustomerListViaProvider(String provider) {
        Mono<ResponseDTO<List<CustomersDTO>>> response = webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/getCustomersViaProvider")
                        .queryParam("provider",provider)
                        .build())
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> {
                    if(clientResponse.statusCode().equals(HttpStatus.NOT_FOUND)){
                        return Mono.error(new HttpClientErrorException(HttpStatus.NOT_FOUND,
                                "Entity Not Found"));
                    }else{
                        return Mono.error(new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR));
                    }
                })
                .bodyToMono(new ParameterizedTypeReference<ResponseDTO<List<CustomersDTO>>>() {});

        List<CustomersDTO> customersDTOList = (List<CustomersDTO>) response.block().getData();
        log.info("\nGetting CustomerDTOList:\n"+customersDTOList);
        return customersDTOList;
    }
}
