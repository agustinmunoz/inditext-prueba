package com.agustin.munoz.inditex.controller.server;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.validateMockitoUsage;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.agustin.munoz.inditex.exception.InditexException;
import com.agustin.munoz.inditex.openapi.model.InditexErrorResponse;
import com.agustin.munoz.inditex.repository.PreciosRepostitory;

import wiremock.com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author agust
 *
 */

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class ITTest2 {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PreciosRepostitory preciosRepostitory;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

	}

	@AfterEach
	public void tearDown() {
		validateMockitoUsage();
	}

	@Test
	public void apiServerPrices_KO_InditexException() throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();

		when(preciosRepostitory.findPrecios(Mockito.any(), Mockito.any(), Mockito.any()))
				.thenThrow(Mockito.mock(DataAccessException.class));

		ResultActions response = mockMvc.perform(get("/prices").param("productId", "35455").param("brandId", "1")
				.param("fecha", "2020-06-14T00:00:00Z"));

		MvcResult mvcr = response.andReturn();

		Exception errorException = mvcr.getResolvedException();

		String responseString = mvcr.getResponse().getContentAsString();

		InditexErrorResponse responsePrice = objectMapper.readValue(responseString, InditexErrorResponse.class);

		InditexErrorResponse inditexErrorExpected = new InditexErrorResponse();
		inditexErrorExpected.message("Error data exception");
		// inditexErrorExpected.timestamp("2020-01-01T00:00:00");
		inditexErrorExpected.error("INDITEX 05");

		assertThat(errorException.getClass()).isEqualTo(InditexException.class);
		assertThat(responsePrice.getMessage()).isEqualTo(inditexErrorExpected.getMessage());
		assertThat(responsePrice.getError()).isEqualTo(inditexErrorExpected.getError());
		assertThat(responsePrice.getTimestamp()).isNotBlank();
		assertThat(responsePrice.getTimestamp()).isNotNull();

	}

	@Test
	public void apiServerPrices_KO_RuntimeException() throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();

		when(preciosRepostitory.findPrecios(Mockito.any(), Mockito.any(), Mockito.any()))
				.thenThrow(Mockito.mock(RuntimeException.class));

		ResultActions response = mockMvc.perform(get("/prices").param("productId", "35455").param("brandId", "1")
				.param("fecha", "2020-06-14T00:00:00Z"));

		MvcResult mvcr = response.andReturn();

		String responseString = mvcr.getResponse().getContentAsString();

		InditexErrorResponse responsePrice = objectMapper.readValue(responseString, InditexErrorResponse.class);

		InditexErrorResponse inditexErrorExpected = new InditexErrorResponse();
		inditexErrorExpected.message("Error Interno");
		// inditexErrorExpected.timestamp("2020-01-01T00:00:00");
		inditexErrorExpected.error("INDITEX 04");

		assertThat(responsePrice.getMessage()).isEqualTo(inditexErrorExpected.getMessage());
		assertThat(responsePrice.getError()).isEqualTo(inditexErrorExpected.getError());
		assertThat(responsePrice.getTimestamp()).isNotBlank();
		assertThat(responsePrice.getTimestamp()).isNotNull();

	}

}
