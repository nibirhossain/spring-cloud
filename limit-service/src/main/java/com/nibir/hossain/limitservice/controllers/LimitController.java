package com.nibir.hossain.limitservice.controllers;

/*
 * Created by Nibir Hossain on 21.12.20
 */

import com.nibir.hossain.limitservice.config.Configuration;
import com.nibir.hossain.limitservice.model.LimitsConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/")
public class LimitController {

    @Autowired
    private Configuration configuration;

    @GetMapping(path = "limits")
    public LimitsConfiguration retrieveLimitsFromConfiguration() {
        return new LimitsConfiguration(configuration.getMinimum(), configuration.getMaximum());
    }
}
