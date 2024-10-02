package com.agustin.munoz.inditex.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;

import com.agustin.munoz.inditex.domain.PrecioDomain;
import com.agustin.munoz.inditex.error.InditexErrorCodes;
import com.agustin.munoz.inditex.error.InditexErrorMessages;
import com.agustin.munoz.inditex.exception.InditexException;
import com.agustin.munoz.inditex.mapper.PrecioDomainMapper;
import com.agustin.munoz.inditex.openapi.model.PrecioResource;
import com.agustin.munoz.inditex.repository.PreciosRepostitory;

/**
 * 
 * @author agust
 *
 */

@ExtendWith(MockitoExtension.class)
public class PriceServiceTests {

	@Mock
	PreciosRepostitory preciosRepository;

	@Mock
	PrecioDomainMapper precioDomainMapper;

	@InjectMocks
	PriceServiceImpl priceService;

	@Test
	void getPrices_OK_1() {

		String fecha = "2020-06-14T10:00:00";

		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		LocalDateTime dateTime = LocalDateTime.parse(fecha, formatter);

		OffsetDateTime offSetDateTime = OffsetDateTime.of(dateTime, ZoneOffset.UTC);

		PrecioDomain expectedPrecio = PrecioDomain.builder().idProducto(35455L).idBrand(1L).idListaPrecio(1L)
				.descripcionListaPrecios("Listado Clientes Preferentes").precio(35.5).descripcionProducto("Camisa Azul")
				.fechaStart(LocalDateTime.parse("2020-06-14T00:00", formatter))
				.fechaEnd(LocalDateTime.parse("2020-12-31T23:59:59", formatter)).descripcionBrand("ZARA").idOferta(1L)
				.descripcionOferta("Primavera").curr("EUR").priority(1).build();

		when(preciosRepository.findPrecios(any(Integer.class), any(Integer.class), any(LocalDateTime.class)))
				.thenReturn(expectedPrecio);

		PrecioResource precioResource = new PrecioResource();
		precioResource.setProductId(35455L);
		precioResource.setBrandId(1);
		precioResource.setTarifaId(1);
		precioResource.setTarifaName("Listado Clientes Preferentes");
		precioResource.setPrice(35.5);
		precioResource.setProductName("Camisa Azul");
		precioResource.setStartDate("2020-06-14T00:00");
		precioResource.setEndDate("2020-12-31T23:59:59");
		precioResource.setBrandName("ZARA");
		precioResource.setOfertaId(1L);
		precioResource.setOfertaName("Primavera");
		precioResource.setCurr("EUR");
		precioResource.setOfertaPrioridad(1);

		when(precioDomainMapper.toResource(expectedPrecio)).thenReturn(precioResource);

		PrecioResource response = priceService.getPrice(35455, 1, offSetDateTime);
		assertThat(response).isEqualTo(precioResource);

	}

	@Test
	void getPrices_KO() {

		String fecha = "2020-06-14T10:00:00";

		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		LocalDateTime dateTime = LocalDateTime.parse(fecha, formatter);

		OffsetDateTime offSetDateTime = OffsetDateTime.of(dateTime, ZoneOffset.UTC);

		when(preciosRepository.findPrecios(any(Integer.class), any(Integer.class), any(LocalDateTime.class)))
				.thenThrow(Mockito.mock(DataAccessException.class));

		try {
			priceService.getPrice(35455, 1, offSetDateTime);

		} catch (InditexException e) {
			assertThat(e.getMessage()).isEqualTo(InditexErrorMessages.DATA_EXCEPTION.getDescription());
			assertThat(e.getInditexCode()).isEqualTo(InditexErrorMessages.DATA_EXCEPTION.getCode());
			assertThat(e.getStatus()).isEqualTo(HttpStatus.valueOf(InditexErrorCodes.ERROR_500.getCode()));
		}

	}

	@Test
	void getPrices_KO2() {

		String fecha = "2020-06-14T10:00:00";

		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		LocalDateTime dateTime = LocalDateTime.parse(fecha, formatter);

		OffsetDateTime offSetDateTime = OffsetDateTime.of(dateTime, ZoneOffset.UTC);

		when(preciosRepository.findPrecios(any(Integer.class), any(Integer.class), any(LocalDateTime.class)))
				.thenThrow(Mockito.mock(InditexException.class));

		try {
			priceService.getPrice(35455, 1, offSetDateTime);

		} catch (InditexException e) {
			assertThat(e.getMessage()).isNull();

		}

	}

}
