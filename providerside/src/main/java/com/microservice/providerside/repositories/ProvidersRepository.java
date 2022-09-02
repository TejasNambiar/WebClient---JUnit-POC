package com.microservice.providerside.repositories;

import com.microservice.providerside.model.Providers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvidersRepository extends JpaRepository<Providers,Integer> {
}
