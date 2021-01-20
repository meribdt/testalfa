package com.example.testalfa;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import com.example.testalfa.dto.OpenexchangeResponse;
import com.example.testalfa.dto.Rates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateController {
    @Autowired
    private IOpenexchangeClient openExchangeClient;

    @Value("${openexchange.base_currency}")
    private String baseCurrency;

    @GetMapping(value = "/ratechange/{currencyID}")
    public String getRateChange(@PathVariable("currencyID") String currencyID) {
        OpenexchangeResponse currentRates = openExchangeClient.getCurrentRates();
        Double actualRate = getRate(currentRates.getRates(), currencyID);
        OpenexchangeResponse historicalRates = openExchangeClient.getHistoricalRates(getDateChanged(-1));
        Double lastRate = getRate(historicalRates.getRates(), currencyID);
        try {
            var ratio = compareRatio(lastRate, actualRate);       
            return ratio ? "rich" : "broke"; 
        }
        catch (Exception e) {
            return "UNKNOWN_CURRENCY";
        }
        
    }

    public String getDateChanged(Integer value){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, value);
        return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
    }

    private Double getRate(Rates rates, String code) {
        try {
            var ratesMap = rates.toMap();
            return (Double)ratesMap.get(baseCurrency) / (Double)ratesMap.get(code);
        } 
        catch (Exception e) {            
            e.printStackTrace();
            return null;
        } 
    }

    private Boolean compareRatio(Double last, Double actual) {
        return actual / last > 1;
    }
}