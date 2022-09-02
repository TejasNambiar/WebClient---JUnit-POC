package com.microservice.providerside.repositories;

import com.microservice.providerside.model.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomersRepository extends JpaRepository<Customers,Integer> {
}
