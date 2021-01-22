package com.example.testalfa;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.example.testalfa.dto.OpenexchangeResponse;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="OpenexchangeClient", url = "${openexchange.base_url}")
public interface IOpenexchangeClient {
    @GetMapping(value = "/api/latest.json?app_id=${openexchange.app_id}")
    OpenexchangeResponse getCurrentRates();

    @GetMapping(value = "/api/historical/{date}.json?app_id=${openexchange.app_id}")
    OpenexchangeResponse getHistoricalRates(@PathVariable("date") String date);

    /*
     * Returns date with the given difference to actual date
     * @param  difference   number of days to substract or extract from the actual date
     * @return changed date String formatted
     */
    public static String getDateChanged(Integer difference){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, difference);
        return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
    }
}
