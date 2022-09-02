package com.microservice.providerside.controller;

import com.microservice.providerside.interfaces.IServicesInterface;
import com.microservice.providerside.model.Customers;
import com.microservice.providerside.model.Providers;
import com.microservice.providerside.model.dto.CustomersDTO;
import com.microservice.providerside.model.dto.ProvidersDTO;
import com.microservice.providerside.model.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "providerSide/api/")
public class ApiController {

    @Autowired
    IServicesInterface servicesInterface;

    @PostMapping(value = "/addProvider")
    public ResponseEntity<ResponseDTO<Providers>> addProvider(@RequestBody ProvidersDTO providers){
        Optional<ProvidersDTO> object = Optional.ofNullable(providers);
        ResponseDTO<Providers> responseDTO = new ResponseDTO<>();
        if(object.isPresent()){
            Providers provider = servicesInterface.addProvider(object.get());
            responseDTO.setData(provider);
            responseDTO.setMessage("Success");
        }
        else {
            responseDTO.setException(new NullPointerException("Null Object"));
            responseDTO.setMessage("Object provided :: Empty. Provide proper one");
        }
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(value = "/getAllProviders")
    public ResponseEntity<ResponseDTO<List<Providers>>> getAllProviders(){
        List<Providers> providersList = servicesInterface.getAllProviders();
        ResponseDTO<List<Providers>> responseDTO = new ResponseDTO<>();
        if(!CollectionUtils.isEmpty(providersList)){
            responseDTO.setData(providersList);
        }
        else {
            responseDTO.setMessage("Database (maybe) empty. As values returned");
        }
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(value = "/getCustomerList")
    public ResponseEntity<ResponseDTO<List<CustomersDTO>>> getAllCustomersViaProvider(@RequestParam String provider){
        List<CustomersDTO> customerList = servicesInterface.getAllCustomersViaProvider(provider);
        ResponseDTO<List<CustomersDTO>> responseDTO = new ResponseDTO<>();
        if(!CollectionUtils.isEmpty(customerList)){
            responseDTO.setData(customerList);
            responseDTO.setMessage("Success");
        }
        else {
            responseDTO.setMessage("Database (maybe) empty. As values returned");
        }

        return ResponseEntity.ok(responseDTO);
    }
}
