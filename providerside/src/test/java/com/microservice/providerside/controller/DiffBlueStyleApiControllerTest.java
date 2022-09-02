package com.microservice.providerside.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.providerside.interfaces.IServicesInterface;
import com.microservice.providerside.model.Customers;
import com.microservice.providerside.model.Providers;
import com.microservice.providerside.model.dto.CustomersDTO;
import com.microservice.providerside.model.dto.ProvidersDTO;
import com.microservice.providerside.model.dto.ResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ApiController.class, ProvidersDTO.class})
class DiffBlueStyleApiControllerTest {

    @Autowired
    @InjectMocks
    private ApiController apiController;

    @MockBean
    IServicesInterface iServicesInterface;

    private ResponseDTO expectedResult;

    ObjectMapper mapper = new ObjectMapper();

    MockMvc mockMvc1;

    @BeforeEach
    public void setUp(){
        mockMvc1 = MockMvcBuilders.standaloneSetup(apiController).build();
        expectedResult = new ResponseDTO<>();
    }

    @Test
    @DisplayName("AddProvider - Proper Param")
    void testAddProvider() throws Exception {
        Providers providers = new Providers();
        providers.setId(1);
        providers.setName("Name");

        ProvidersDTO providersDTO1 = new ProvidersDTO();
        providersDTO1.setName("Name");

        ResponseDTO expectedResult = ResponseDTO.builder()
                .data(providers)
                .message("Success")
                .build();

        String content = mapper.writeValueAsString(providersDTO1);
        String jsonResult = mapper.writeValueAsString(expectedResult);

        when(iServicesInterface.addProvider((ProvidersDTO) any())).thenReturn(providers);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/providerSide/api/addProvider")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc1.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(jsonResult));

    }

    @Test
    void testGetAllCustomersViaProvider_No_Data_Matching_For_Param_Passed() throws Exception {

        expectedResult = ResponseDTO.builder()
                .message("Database (maybe) empty. As values returned")
                .build();

        String jsonResult = mapper.writeValueAsString(expectedResult);

        when(iServicesInterface.getAllCustomersViaProvider("foo")).thenReturn(new ArrayList<>());

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/providerSide/api/getCustomerList")
                .param("provider", "foo");

        mockMvc1.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(jsonResult));
    }

    @Test
    void testGetAllCustomersViaProvider_Returns_CustomerList_For_Param_Passed() throws Exception {
        CustomersDTO customers = new CustomersDTO();
        customers.setName("Mickey Mouse");
        customers.setProvider("Walt Disney");

        ArrayList<CustomersDTO> customersList = new ArrayList<>();
        customersList.add(customers);

        expectedResult = ResponseDTO.builder()
                .data(customersList)
                .message("Success")
                .build();

        String jsonResult = mapper.writeValueAsString(expectedResult);

        when(iServicesInterface.getAllCustomersViaProvider("Walt Disney")).thenReturn(customersList);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/providerSide/api/getCustomerList")
                .param("provider", "Walt Disney");

        mockMvc1.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(jsonResult));
    }

    @Test
    void testGetAllProviders() throws Exception {
        when(iServicesInterface.getAllProviders()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/providerSide/api/getAllProviders");
        MockMvcBuilders.standaloneSetup(apiController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"data\":null,\"exception\":null,\"message\":\"Database (maybe) empty. As values returned\"}"));
    }

    @Test
    void testGetAllProviders2() throws Exception {
        Providers providers = new Providers();
        providers.setId(1);
        providers.setName("?");

        ArrayList<Providers> providersList = new ArrayList<>();
        providersList.add(providers);
        when(iServicesInterface.getAllProviders()).thenReturn(providersList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/providerSide/api/getAllProviders");
        MockMvcBuilders.standaloneSetup(apiController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"data\":[{\"id\":1,\"name\":\"?\"}],\"exception\":null,\"message\":null}"));
    }

}