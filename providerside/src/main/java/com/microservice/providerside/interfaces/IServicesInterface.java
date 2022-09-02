package com.microservice.providerside.interfaces;

import com.microservice.providerside.model.Customers;
import com.microservice.providerside.model.Providers;
import com.microservice.providerside.model.dto.CustomersDTO;
import com.microservice.providerside.model.dto.ProvidersDTO;

import java.util.List;

public interface IServicesInterface {
    Providers addProvider(ProvidersDTO providers);

    List<Providers> getAllProviders();

    List<CustomersDTO> getAllCustomersViaProvider(String provider);
}

