package com.agustin.munoz.inditex.controller.server;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.agustin.munoz.inditex.controller.PricesController;
import com.agustin.munoz.inditex.repository.PreciosRepostitory;

/**
 * 
 * @author agust
 *
 */

@SpringBootTest
@AutoConfigureMockMvc
public class ITTest {

	@Autowired
	private MockMvc mockMvc;



	@Test
	public void fecha1() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/prices").contentType(MediaType.APPLICATION_JSON)
				.param("productId", "35455").param("brandId", "1").param("fecha", "2020-06-14T10:00:00Z"))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.productName", is("Camisa Azul"))).andExpect(jsonPath("$.price", is(35.5)))
				.andExpect(jsonPath("$.startDate", is("2020-06-14T00:00:00")))
				.andExpect(jsonPath("$.endDate", is("2020-12-31T23:59:59"))).andExpect(jsonPath("$.ofertaId", is(1)))
				.andExpect(jsonPath("$.ofertaName", is("Primavera"))).andExpect(jsonPath("$.ofertaPrioridad", is(1)));

	}

	@Test
	public void fecha2() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/prices").contentType(MediaType.APPLICATION_JSON)
				.param("productId", "35455").param("brandId", "1").param("fecha", "2020-06-14T16:00:00Z"))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.productName", is("Camisa Azul"))).andExpect(jsonPath("$.price", is(25.45)))
				.andExpect(jsonPath("$.startDate", is("2020-06-14T15:00:00")))
				.andExpect(jsonPath("$.endDate", is("2020-06-14T18:30:00"))).andExpect(jsonPath("$.ofertaId", is(2)))
				.andExpect(jsonPath("$.ofertaName", is("Verano"))).andExpect(jsonPath("$.ofertaPrioridad", is(2)));

	}

	@Test
	public void fecha_KO_Date_parser() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/prices").contentType(MediaType.APPLICATION_JSON)
				.param("productId", "35455").param("brandId", "1").param("fecha", "2020-06-1"))
				.andExpect(status().is(400));

	}

	@Test
	public void fecha_KO_MissingParameter() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/prices").contentType(MediaType.APPLICATION_JSON)
				.param("productId", "35455")
				// .param("brandId", "1")
				.param("fecha", "2020-06-14T16:00:00")).andExpect(status().is(400));

	}

}
