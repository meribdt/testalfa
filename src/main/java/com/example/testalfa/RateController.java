package com.example.testalfa;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.example.testalfa.dto.GiphyResponse;
import com.example.testalfa.dto.OpenexchangeResponse;
import com.example.testalfa.dto.Rates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
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


    // PUBLIC REGION


    @GetMapping(value = "/ratechange/{currencyID}")
    public @ResponseBody ResponseEntity<String> getRateChange(@PathVariable("currencyID") String currencyID) {
        OpenexchangeResponse currentRates = openExchangeClient.getCurrentRates();
        Double actualRate = getRate(currentRates.getRates(), currencyID);
        OpenexchangeResponse historicalRates = openExchangeClient.getHistoricalRates(getDateChanged(-1)) ;
        Double lastRate = getRate(historicalRates.getRates(), currencyID);
        if (actualRate == null || lastRate == null) {
            return getErrorPage(HttpStatus.BAD_REQUEST, "UNKNOWN_CURRENCY");
        }
        String query = actualRate / lastRate > 1 ? growthPhrase : fallPhrase;        
        Double offset = Math.random() * 1000;
        String gifUrl = getGifUrl(giphyClient.searchGif(query, 1, offset.intValue()));
        if (gifUrl == null) {
            return getErrorPage(HttpStatus.NOT_FOUND, "NOT FOUND SUITABLE GIF, but your result is " + query);
        }
        return getGifPageContent(gifUrl);
    }


    // PRIVATE REGION
    

    /*
     * Returns date with the given difference to actual date
     * @param  difference   number of days to substract or extract from the actual date
     * @return changed date String formatted
     */
    private String getDateChanged(Integer difference){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, difference);
        return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
    }

    /*
     * Searches for suitable property in object of type Rates
     * @param  rates   object of class Rates
     * @param  code    String ISO code of currency
     * @return value for requested current code if present, null otherwice
     */
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