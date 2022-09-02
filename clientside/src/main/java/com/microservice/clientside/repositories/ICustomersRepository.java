package com.microservice.clientside.repositories;

import com.microservice.clientside.model.Customers;
import com.microservice.clientside.model.dto.CustomersDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICustomersRepository extends JpaRepository<Customers,Integer> {

    @Query(value = "select new com.microservice.clientside.model.dto.CustomersDTO(c.name,c.provider)" +
            "from Customers c " +
            "where c.name = :name " +
            "order by id")
    CustomersDTO findByName(@Param("name") String name);

    @Query(value = "select new com.microservice.clientside.model.dto.CustomersDTO(c.name,c.provider)" +
            "from Customers c " +
            "where c.provider = :provider " +
            "order by id")
    List<CustomersDTO> findByProviderOrderById(@Param("provider") String provider);
}
