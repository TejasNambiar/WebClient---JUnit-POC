package com.microservice.clientside.services;

import com.microservice.clientside.exceptions.NoDataFoundException;
import com.microservice.clientside.interfaces.IServicesInterface;
import com.microservice.clientside.model.Customers;
import com.microservice.clientside.model.dto.CustomersDTO;
import com.microservice.clientside.repositories.ICustomersRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServices implements IServicesInterface {

    @Autowired
    ICustomersRepository iCustomersRepository;

    @Override
    public List<Customers> getAllCustomers() {
        return iCustomersRepository.findAll();
    }

    @Override
    public CustomersDTO getCustomerViaName(String name) {
        CustomersDTO customersDTO = iCustomersRepository.findByName(name);
        Optional<CustomersDTO> optionalCustomersDTO = Optional.ofNullable(customersDTO);
        if(optionalCustomersDTO.isPresent())
            return optionalCustomersDTO.get();
        throw new NoDataFoundException("No Customer found for given name: "+name+". Please check and try again");
    }

    @Override
    public Customers addCustomer(CustomersDTO customersDTO) {
        Customers customers = Customers.builder()
                .name(customersDTO.getName())
                .provider(customersDTO.getProvider())
                .build();
        return iCustomersRepository.save(customers);
    }

    @Override
    public List<CustomersDTO> getCustomerListViaProvider(String provider) {
        return iCustomersRepository.findByProviderOrderById(provider);
    }
}
