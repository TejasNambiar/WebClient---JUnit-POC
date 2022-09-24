package com.microservice.providerside.services;

import com.microservice.providerside.interfaces.IServicesInterface;
import com.microservice.providerside.interfaces.IWebClientInterface;
import com.microservice.providerside.model.Customers;
import com.microservice.providerside.model.Providers;
import com.microservice.providerside.model.dto.CustomersDTO;
import com.microservice.providerside.model.dto.ProvidersDTO;
import com.microservice.providerside.repositories.CustomersRepository;
import com.microservice.providerside.repositories.ProvidersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderServices implements IServicesInterface {

    @Autowired
    ProvidersRepository providersRepository;

    @Autowired
    IWebClientInterface webClientInterface;

    @Override
    public Providers addProvider(ProvidersDTO providersDTO) {
        Providers providers = Providers.builder()
                .name(providersDTO.getName())
                .build();

        return providersRepository.save(providers);
    }

    @Override
    public List<Providers> getAllProviders() {
        return providersRepository.findAll();
    }

    @Override
    public List<CustomersDTO> getAllCustomersViaProvider(String provider) {
        List<CustomersDTO> customersList = webClientInterface.getCustomerListViaProvider(provider);
        return customersList;
    }
}
