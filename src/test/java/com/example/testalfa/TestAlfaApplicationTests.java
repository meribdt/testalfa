package com.example.testalfa;

import static org.assertj.core.api.Assertions.assertThat;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class TestAlfaApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired 
	private RateController rateController;

	@Test
	void contextLoads() throws Exception {
		assertThat(rateController).isNotNull();
	}

	@Test
	void getRateChangeForUsdShouldReturnImg() throws Exception {
		this.mockMvc.perform(get("/ratechange/USD")).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString("<img src=\"")));
	}

	@Test
	void getRateChangeForUSAShouldReturnServerError() throws Exception {
		this.mockMvc.perform(get("/ratechange/USA")).andDo(print()).andExpect(status().isBadRequest());
	}

}
