package com.nibir.hossain.microservices.currencyexchangeservice.controllers;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nibir.hossain.microservices.currencyexchangeservice.domain.ExchangeRate;
import com.nibir.hossain.microservices.currencyexchangeservice.repositories.ExchangeRateRepository;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	private Environment env;
	
	private final ExchangeRateRepository exchangeRateRepository;
	
	public CurrencyExchangeController(ExchangeRateRepository exchangeRateRepository) {
		this.exchangeRateRepository = exchangeRateRepository;
	}
	
	@GetMapping(path = "/currency-exchange/from/{from}/to/{to}")
	public ExchangeRate retrieveExchangeRate(@PathVariable String from, @PathVariable String to) {
		ExchangeRate exchangeRate = exchangeRateRepository.findByFromAndTo(from, to);
		exchangeRate.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		
		return exchangeRate;
	}
}
