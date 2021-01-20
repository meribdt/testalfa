package com.example.testalfa;

import com.example.testalfa.dto.GiphyResponse;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "GiphyClient", url = "${giphy.base_url}")
public interface IGiphyClient {
    @GetMapping(value = "/v1/gifs/search?api_key=${giphy.api_key}&q={query}&limit={limit}&offset={offset}")
    public GiphyResponse searchGif(
        @PathVariable("query") String query,
        @PathVariable("limit") Integer limit,
        @PathVariable("offset") Integer offset
    );
}
