package com.nibir.hossain.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {
	
	@Autowired
	private CurrencyExchangeServiceProxy proxy;
	
	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrency(@PathVariable("from") String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
		
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		final String currencyExchangeUri = "http://localhost:8000/currency-exchange/from/{from}/to/{to}";
		
		ResponseEntity<CurrencyConversionBean> reponseEntity = new RestTemplate().getForEntity(currencyExchangeUri, CurrencyConversionBean.class, uriVariables);
		CurrencyConversionBean res = reponseEntity.getBody();
		
		System.out.println("CurrencyConversionBean " + res);
		
		return new CurrencyConversionBean(res.getId(), from, to, res.getConversionMultiple(), 
				quantity, quantity.multiply(res.getConversionMultiple()), res.getPort());
	}
	
	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrencyFeign(@PathVariable("from") String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
		
		CurrencyConversionBean res = proxy.retrieveExchangeRate(from, to);
		
		System.out.println("CurrencyConversionBean (using Feign) " + res);
		
		return new CurrencyConversionBean(res.getId(), from, to, res.getConversionMultiple(), 
				quantity, quantity.multiply(res.getConversionMultiple()), res.getPort());
	}
}
