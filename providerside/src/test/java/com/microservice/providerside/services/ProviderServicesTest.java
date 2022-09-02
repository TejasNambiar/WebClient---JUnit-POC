package com.microservice.providerside.services;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.microservice.providerside.interfaces.IWebClientInterface;
import com.microservice.providerside.model.Customers;
import com.microservice.providerside.model.Providers;
import com.microservice.providerside.model.dto.CustomersDTO;
import com.microservice.providerside.model.dto.ProvidersDTO;
import com.microservice.providerside.repositories.CustomersRepository;
import com.microservice.providerside.repositories.ProvidersRepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("test")
@ContextConfiguration(classes = {ProviderServices.class, ProvidersDTO.class})
@ExtendWith(SpringExtension.class)
class ProviderServicesTest {
    @MockBean
    private CustomersRepository customersRepository;

    @MockBean
    private IWebClientInterface iWebClientInterface;

    @Autowired
    private ProviderServices providerServices;

    @Autowired
    private ProvidersDTO providersDTO;

    @MockBean
    private ProvidersRepository providersRepository;

    @Test
    @DisplayName("Test - Add Provider")
    void testAddProvider() {
        Providers providers = new Providers();
        providers.setId(1);
        providers.setName("Name");


        ProvidersDTO providersDTO = ProvidersDTO.builder()
                .name("Richard")
                .build();

        when(providersRepository.save((Providers) any())).thenReturn(providers);
        // checks if referencing same Object
        assertSame(providers, providerServices.addProvider(providersDTO));
        // verifying if method (.save) is being called (on providersRepository)
        verify(providersRepository).save((Providers) any());
    }

    @Test
    @DisplayName("Test - Get All Provider")
    void testGetAllProviders() {
        ArrayList<Providers> providersList = new ArrayList<>();
        when(providersRepository.findAll()).thenReturn(providersList);
        List<Providers> actualAllProviders = providerServices.getAllProviders();
        assertSame(providersList, actualAllProviders);
        assertTrue(actualAllProviders.isEmpty());
        verify(providersRepository).findAll();
    }

    @Test
    @DisplayName("Test - Get Customers via Provider")
    void testGetAllCustomersViaProvider() {
        ArrayList<Customers> customersList = new ArrayList<>();
        when(customersRepository.findAll()).thenReturn(customersList);
        when(iWebClientInterface.getCustomerListViaProvider((String) any())).thenReturn(new ArrayList<>());
        List<CustomersDTO> actualAllCustomersViaProvider = providerServices.getAllCustomersViaProvider("Provider");
        assertSame(customersList, actualAllCustomersViaProvider);
        assertTrue(actualAllCustomersViaProvider.isEmpty());
        verify(customersRepository).findAll();
        verify(iWebClientInterface).getCustomerListViaProvider((String) any());
    }
}

