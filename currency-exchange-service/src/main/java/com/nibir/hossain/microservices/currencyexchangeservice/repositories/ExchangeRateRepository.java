package com.nibir.hossain.microservices.currencyexchangeservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nibir.hossain.microservices.currencyexchangeservice.domain.ExchangeRate;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

	ExchangeRate findByFromAndTo(String from, String to);

}
