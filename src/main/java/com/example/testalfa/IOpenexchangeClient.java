package com.example.testalfa;

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
}
