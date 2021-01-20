package com.example.testalfa;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="OpenexchangeClient", url = "${openexchange.base_url}")
public interface IOpenexchangeClient {
    @GetMapping("/api/latest.json?app_id=${openexchange.app_id}")
    String getCurrentRates();
}
