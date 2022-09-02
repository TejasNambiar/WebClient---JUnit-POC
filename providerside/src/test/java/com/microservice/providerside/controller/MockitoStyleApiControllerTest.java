package com.microservice.providerside.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.providerside.interfaces.IServicesInterface;
import com.microservice.providerside.model.Customers;
import com.microservice.providerside.model.Providers;
import com.microservice.providerside.model.dto.CustomersDTO;
import com.microservice.providerside.model.dto.ProvidersDTO;
import com.microservice.providerside.model.dto.ResponseDTO;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ApiController.class})
class MockitoStyleApiControllerTest {

    private static ObjectMapper mapper;

    private static ResponseDTO expectedResult;

    private MockMvc mockMvc;

    private MockHttpServletRequestBuilder requestBuilder;

    static String jsonResult, content;

    @MockBean
    IServicesInterface iServicesInterface;

    @Autowired
    @InjectMocks
    private ApiController apiController;

    @Before
    public void setup() throws Exception{
        MockitoAnnotations.initMocks(this);
    }

    @BeforeEach
    public void beforeEach(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(apiController).build();
        mapper = new ObjectMapper();
        expectedResult = new ResponseDTO<>();
    }

    @Test
    @DisplayName("Test - Add Provider")
    void addProvider() throws Exception {
        Providers providers = new Providers();
        providers.setId(1);
        providers.setName("Name");

        ProvidersDTO providersDTO1 = new ProvidersDTO();
        providersDTO1.setName("Name");

        expectedResult = ResponseDTO.builder()
                .data(providers)
                .message("Success")
                .build();

        when(iServicesInterface.addProvider((ProvidersDTO) any())).thenReturn(providers);

        content = mapper.writeValueAsString(providersDTO1);
        jsonResult = mapper.writeValueAsString(expectedResult);

        requestBuilder = MockMvcRequestBuilders.post("/providerSide/api/addProvider")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content);

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(jsonResult));

    }

    @Test
    @DisplayName("Test - Get ProviderList")
    void getAllProviders() throws Exception {
        List<Providers> providersList = new ArrayList<>();
        Providers providers = Providers.builder()
                .id(1)
                .name("Richard Francis")
                .build();
        providersList.add(providers);

        expectedResult = ResponseDTO.builder()
                .data(providersList)
                .build();
        jsonResult = mapper.writeValueAsString(expectedResult);

        when(iServicesInterface.getAllProviders()).thenReturn(providersList);

        requestBuilder = MockMvcRequestBuilders.get("/providerSide/api/getAllProviders");

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(jsonResult));
    }

    @Test
    @DisplayName("Test - CustomerList via Provider")
    void getAllCustomersViaProvider() throws Exception {
        List<CustomersDTO> customersList = new ArrayList<>();
        CustomersDTO customer = CustomersDTO.builder()
                .name("Mickey Mouse")
                .provider("Walt Disney Studios")
                .build();
        customersList.add(customer);

        expectedResult = ResponseDTO.builder()
                .data(customersList)
                .message("Success")
                .build();
        jsonResult = mapper.writeValueAsString(expectedResult);

        when(iServicesInterface.getAllCustomersViaProvider("Walt Disney Studios")).thenReturn(customersList);

        requestBuilder = MockMvcRequestBuilders.get("/providerSide/api/getCustomerList")
                .param("provider","Walt Disney Studios");

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(jsonResult));
    }
}