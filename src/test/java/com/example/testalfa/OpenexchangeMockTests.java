package com.example.testalfa;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;
import com.example.testalfa.dto.OpenexchangeResponse;
import com.example.testalfa.dto.Rates;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class OpenexchangeMockTests {

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private IOpenexchangeClient client;

    @Test
	void whenWrongResponsetShouldReturnInternalServerError() throws Exception {
		when(client.getCurrentRates()).thenReturn(new OpenexchangeResponse("disclaimer", "license", 3, "USD", null));
		this.mockMvc.perform(get("/ratechange/RUB")).andDo(print()).andExpect(status().isInternalServerError());
    }
    
    @Test
    void whenCorrectResponseShouldReturnImg() throws Exception {
        Rates rates = new Rates();
        rates.setUSD(1d);
        rates.setRUB(73d);
        OpenexchangeResponse response = new OpenexchangeResponse("disclaimer", "license", 3, "USD", rates);
        when(client.getCurrentRates()).thenReturn(response);
        when(client.getHistoricalRates(IOpenexchangeClient.getDateChanged(-1))).thenReturn(response);
        this.mockMvc.perform(get("/ratechange/USD")).andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString("<img src=\"")));
    }
    
}
