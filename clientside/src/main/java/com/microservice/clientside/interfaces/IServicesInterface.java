package com.microservice.clientside.interfaces;

import com.microservice.clientside.model.Customers;
import com.microservice.clientside.model.dto.CustomersDTO;

import java.util.List;

public interface IServicesInterface {
    public List<Customers> getAllCustomers();
    public CustomersDTO getCustomerViaName(String name);
    public Customers addCustomer(CustomersDTO customersDTO);
    public List<CustomersDTO> getCustomerListViaProvider(String provider);
}
