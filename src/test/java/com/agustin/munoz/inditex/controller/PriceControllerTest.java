package com.agustin.munoz.inditex.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.agustin.munoz.inditex.error.InditexErrorCodes;
import com.agustin.munoz.inditex.error.InditexErrorMessages;
import com.agustin.munoz.inditex.exception.InditexException;
import com.agustin.munoz.inditex.openapi.model.PrecioResource;
import com.agustin.munoz.inditex.service.PriceService;

/**
 * 
 * @author agust
 *
 */
@ExtendWith(MockitoExtension.class)
public class PriceControllerTest {

	@Mock
	PriceService priceService;

	@InjectMocks
	PricesController priceController;

	@DisplayName("getPrices_OK")
	@Test
	void getPrices_OK() throws InditexException {

		OffsetDateTime fecha = OffsetDateTime.of(2020, 6, 14, 12, 0, 0, 0, ZoneOffset.ofHours(0));

		PrecioResource priceMaximaPrioridad = new PrecioResource();
		priceMaximaPrioridad.setProductId(Long.valueOf("35455"));
		priceMaximaPrioridad.setProductName("Camisa Verde");
		priceMaximaPrioridad.setBrandId(1);
		priceMaximaPrioridad.setBrandName("ZARA");
		priceMaximaPrioridad.setTarifaId(1);
		priceMaximaPrioridad.setTarifaName("Cliente Preferenta Tarifa 1");
		priceMaximaPrioridad.setStartDate("2020-06-14T00:00:00");
		priceMaximaPrioridad.setEndDate("2020-12-31T23:59:59");
		priceMaximaPrioridad.setPrice(35.40);
		priceMaximaPrioridad.setCurr("EUR");

		when(priceService.getPrice(35455, 1, fecha)).thenReturn(priceMaximaPrioridad);

		ResponseEntity<PrecioResource> response = priceController.getPrices(35455, 1, fecha);
		priceMaximaPrioridad.equals(response.getBody());

	}

	@DisplayName("getPrices_Empty_OK")
	@Test
	void getPrices_Empty_KO()  {

		
		when(priceService.getPrice(any(Integer.class), any(Integer.class), any(OffsetDateTime.class))).thenReturn(null);		
		OffsetDateTime fecha = OffsetDateTime.of(2020, 6, 14, 12, 0, 0, 0,
				 ZoneOffset.ofHours(0));	
				
		try {		
		priceController.getPrices(35455, 1, fecha);
		} catch (InditexException e) {
			assertThat(e.getMessage()).isEqualTo(InditexErrorMessages.NOT_FOUND.getDescription());
			assertThat(e.getInditexCode()).isEqualTo(InditexErrorMessages.NOT_FOUND.getCode());
			assertThat(e.getStatus()).isEqualTo(HttpStatus.valueOf(InditexErrorCodes.ERROR_404.getCode()));
			
		}
		
	
	
}

}
