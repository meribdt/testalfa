package com.example.testalfa;

import com.example.testalfa.dto.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class RateController {


    // POLES


    @Autowired
    private IOpenexchangeClient openExchangeClient;

    @Autowired
    private IGiphyClient giphyClient;

    @Value("${openexchange.base_currency}")
    private String baseCurrency;

    @Value("${giphy.growth_phrase}")
    private String growthPhrase;

    @Value("${giphy.fall_phrase}")
    private String fallPhrase;

    private Integer offset;

    private Boolean isTest = false;

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public void setIsTest(Boolean isTest) {
        this.isTest = isTest;
    }


    // PUBLIC REGION


    @GetMapping(value = "/ratechange/{currencyID}")
    public @ResponseBody ResponseEntity<String> getRateChange(@PathVariable("currencyID") String currencyID) {
        OpenexchangeResponse currentRates = openExchangeClient.getCurrentRates();
        if (currentRates == null || currentRates.getRates() == null) {
            return getErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "WRONG_RESPONSE_FROM_API");
        }
        Double actualRate = getRate(currentRates, currencyID);
        OpenexchangeResponse historicalRates = openExchangeClient.getHistoricalRates(IOpenexchangeClient.getDateChanged(-1)) ;
        if (historicalRates == null || historicalRates.getRates() == null) {
            return getErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "WRONG_RESPONSE_FROM_API");
        }
        Double lastRate = getRate(historicalRates, currencyID);
        if (actualRate == null || lastRate == null) {
            return getErrorPage(HttpStatus.BAD_REQUEST, "UNKNOWN_CURRENCY");
        }
        String query = actualRate / lastRate > 1 ? growthPhrase : fallPhrase;
        if (isTest != null && !isTest) offset = IGiphyClient.getRandomOffset(1000);
        String gifUrl = getGifUrl(giphyClient.searchGif(query, offset));
        if (gifUrl == null) {
            return getErrorPage(HttpStatus.NOT_FOUND, "NOT FOUND SUITABLE GIF, but your result is " + query);
        }
        return getGifPageContent(gifUrl);
    }



    // PRIVATE REGION
    

    /*
     * Searches for suitable property in object of type Rates
     * @param  rates   object of class Rates
     * @param  code    String ISO code of currency
     * @return value for requested current code if present, null otherwice
     */
    private Double getRate(OpenexchangeResponse currentRates, String code) {
        try {
            var ratesMap = currentRates.getRates().toMap();
            return (Double)ratesMap.get(baseCurrency) / (Double)ratesMap.get(code);
        } 
        catch (Exception e) {    
            return null;
        } 
    }

    /*
     * Searches for gif url
     * @param  response   GiphyResponce object
     * @return url of original gif if present, null otherwise
     */
    private String getGifUrl(GiphyResponse response) {
        try {
            return response.getData().get(0).getImages().getOriginal().getUrl();
        }
        catch (Exception e) {
            return null;
        }
    }

    /*
     * Generates error page
     * @param  msg      error message
     * @param  status   http status
     * @return ResponseEntity
     */
    private ResponseEntity<String> getErrorPage(HttpStatus status, String msg) {
        return ResponseEntity.status(status).body(msg);
    }

    /*
     * Generates gif page content
     * @param  src  gif url
     * @return ResponseEntity
     */
    private ResponseEntity<String> getGifPageContent(String src) {
        return ResponseEntity.ok().body("<img src=\"" + src + "\"/><br>Powered by Giphy");
    }
}