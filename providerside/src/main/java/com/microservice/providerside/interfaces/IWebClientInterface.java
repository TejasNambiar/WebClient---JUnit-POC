package com.microservice.providerside.interfaces;

import com.microservice.providerside.model.Customers;
import com.microservice.providerside.model.dto.CustomersDTO;

import java.util.List;

public interface IWebClientInterface {
    List<CustomersDTO> getCustomerListViaProvider(String provider);
}
