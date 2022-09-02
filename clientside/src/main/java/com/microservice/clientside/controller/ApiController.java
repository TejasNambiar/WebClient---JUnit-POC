package com.microservice.clientside.controller;

import com.microservice.clientside.interfaces.IServicesInterface;
import com.microservice.clientside.model.Customers;
import com.microservice.clientside.model.dto.CustomersDTO;
import com.microservice.clientside.model.dto.ResponseDTO;
import com.microservice.clientside.utils.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Predicate;

@RestController
@RequestMapping("clientSide/api")
public class ApiController {
    @Autowired
    IServicesInterface iServicesInterface;

    @GetMapping("/getAllCustomers")
    public ResponseEntity<ResponseDTO<List<Customers>>> getAllCustomers(){
        ResponseDTO<List<Customers>> responseDTO = new ResponseDTO<>();
        if(!CollectionUtils.isEmpty(iServicesInterface.getAllCustomers())) {
            responseDTO.setData(iServicesInterface.getAllCustomers());
            responseDTO.setMessage("Success");
        }
        else{
            responseDTO.setData(iServicesInterface.getAllCustomers());
            responseDTO.setException(new Exception("Empty List retrieved. Please check Database"));
            responseDTO.setMessage("Failed to retrieve data, as empty result retrieved. DB (maybe) empty");
        }
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/getCustomerViaName")
    public ResponseEntity<ResponseDTO<CustomersDTO>> getCustomerViaName(@RequestParam String name){
        ResponseDTO<CustomersDTO> responseDTO = new ResponseDTO<>();
        Predicate<String> nullCheck = StringUtils::isNotBlank;
        if(nullCheck.test(name)){
            CustomersDTO customersDTO = iServicesInterface.getCustomerViaName(name);
            if(customersDTO!=null) {
                responseDTO.setData(customersDTO);
                responseDTO.setMessage(Constants.SUCCESS_MESSAGE);
            }
            else {
                responseDTO.setData(new CustomersDTO());
                responseDTO.setMessage("No Customer found for given name: "+name+". Please check and try again");
            }
        }else{
            responseDTO.setException(new Exception("Null check failed, please check params passed"));
            responseDTO.setMessage("Name not provided. Please check params before executing api");
        }
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/getCustomersViaProvider")
    public ResponseEntity<ResponseDTO<List<CustomersDTO>>> getCustomerListViaProvider(@RequestParam String provider){
        ResponseDTO<List<CustomersDTO>> responseDTO = new ResponseDTO<>();
        Predicate<String> nullCheck = StringUtils::isNotBlank;
        if(nullCheck.test(provider)){
            responseDTO.setData(iServicesInterface.getCustomerListViaProvider(provider));
            responseDTO.setMessage(Constants.SUCCESS_MESSAGE);
        }
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/addCustomer")
    public ResponseEntity<ResponseDTO<Customers>> addCustomer(@RequestBody CustomersDTO customersDTO){
        ResponseDTO<Customers> responseDTO = new ResponseDTO<>();
        Predicate<CustomersDTO> nullCheck = dtoObject ->StringUtils.isNotBlank(customersDTO.getName())
                && StringUtils.isNotBlank(customersDTO.getProvider());
        if(nullCheck.test(customersDTO)){
            responseDTO.setData(iServicesInterface.addCustomer(customersDTO));
            responseDTO.setMessage(Constants.SUCCESS_MESSAGE);
        }else{
            responseDTO.setException(new Exception("Null check failed, please check params passed"));
            responseDTO.setMessage("Provider or Name or both not provided. Please check params before passing object");
        }
        return ResponseEntity.ok(responseDTO);
    }
}
