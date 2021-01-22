package com.example.testalfa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.beans.factory.annotation.Value;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.example.testalfa.dto.Datum;
import com.example.testalfa.dto.GiphyResponse;
import com.example.testalfa.dto.Images;
import com.example.testalfa.dto.Original;

import static org.hamcrest.Matchers.containsString;

@SpringBootTest
@AutoConfigureMockMvc
class GiphyMockTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired 
    private RateController rateController;
    
    @Value("${giphy.growth_phrase}")
    private String growthPhrase;

    @Value("${giphy.fall_phrase")
    private String fallPhrase;

    @MockBean
    private IGiphyClient client;

    @Test
    void whenCorrectResponseShouldReturnImg() throws Exception {
        rateController.setOffset(1000);
        Original original = new Original();
        original.setUrl("https://testurl.com");
        Images images = new Images();
        images.setOriginal(original);
        Datum datum = new Datum();
        datum.setImages(images);
        List<Datum> data = new ArrayList<Datum>();
        data.add(datum);
        GiphyResponse response = new GiphyResponse();    
        response.setData(data);    
        when(client.searchGif(growthPhrase, 1000)).thenReturn(response);        
        when(client.searchGif(fallPhrase, 1000)).thenReturn(response);
        this.mockMvc.perform(get("/ratechange/USD")).andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString("<img src=")));
    }

    @Test
    void whenWrongResponseShouldReturnNotFound() throws Exception {
        rateController.setOffset(1000);        
        when(client.searchGif(growthPhrase, 1000)).thenReturn(null);
        when(client.searchGif(fallPhrase, 1000)).thenReturn(null);
        this.mockMvc.perform(get("/ratechange/USD")).andDo(print()).andExpect(status().isNotFound());
    }    
}
