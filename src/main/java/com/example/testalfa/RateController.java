package com.example.testalfa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateController {
    @Autowired
    private IOpenexchangeClient openExchangeClient;

    @GetMapping(value = "/ratechange/{currencyID}")
    public String getRateChange(@PathVariable String currencyID) { 
        return openExchangeClient.getCurrentRates();
    }
}