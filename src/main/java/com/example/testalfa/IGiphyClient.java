package com.example.testalfa;

import com.example.testalfa.dto.GiphyResponse;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "GiphyClient", url = "${giphy.base_url}")
public interface IGiphyClient {
    @GetMapping(value = "/v1/gifs/search?api_key=${giphy.api_key}&q={query}&limit=1&offset={offset}")
    public GiphyResponse searchGif(
        @PathVariable("query") String query,
        @PathVariable("offset") Integer offset
    );

    /*
     * Returns random offset for searcing query
     * @param  rates   object of class Rates
     * @param  code    String ISO code of currency
     * @return value for requested current code if present, null otherwice
     */
    public static Integer getRandomOffset(Integer max) {
        Double offset = Math.random() * max;
        return offset.intValue();
    }
}
