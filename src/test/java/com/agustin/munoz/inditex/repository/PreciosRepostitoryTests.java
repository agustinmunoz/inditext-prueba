package com.agustin.munoz.inditex.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.agustin.munoz.inditex.domain.PrecioDomain;

/**
 * 
 * @author agust
 *
 */

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PreciosRepostitoryTests {

	@Autowired
	PreciosRepostitory priceRepository;

	@Test
	void getFechas1() {

		String fecha = "2020-06-14T10:00:00";

		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		LocalDateTime dateTime = LocalDateTime.parse(fecha, formatter);

		PrecioDomain expectedPrecio = PrecioDomain.builder().idProducto(35455L).idBrand(1L).idListaPrecio(1L)
				.descripcionListaPrecios("Listado Clientes Preferentes").precio(35.5).descripcionProducto("Camisa Azul")
				.fechaStart(LocalDateTime.parse("2020-06-14T00:00", formatter))
				.fechaEnd(LocalDateTime.parse("2020-12-31T23:59:59", formatter)).descripcionBrand("ZARA").idOferta(1L)
				.descripcionOferta("Primavera").curr("EUR").priority(1).build();

		PrecioDomain precioDomain = priceRepository.findPrecios(35455, 1, dateTime);

		assertThat(precioDomain).isEqualTo(expectedPrecio);

	}

	@Test
	void getFechas2() {

		String fecha = "2020-06-14T16:00:00";

		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		LocalDateTime dateTime = LocalDateTime.parse(fecha, formatter);

		PrecioDomain expectedPrecio = PrecioDomain.builder().idProducto(35455L).idBrand(1L).idListaPrecio(1L)
				.descripcionListaPrecios("Listado Clientes Preferentes").precio(25.45)
				.descripcionProducto("Camisa Azul").fechaStart(LocalDateTime.parse("2020-06-14T15:00", formatter))
				.fechaEnd(LocalDateTime.parse("2020-06-14T18:30", formatter)).descripcionBrand("ZARA").idOferta(2L)
				.descripcionOferta("Verano").curr("EUR").priority(2).build();

		PrecioDomain precioDomain = priceRepository.findPrecios(35455, 1, dateTime);

		assertThat(precioDomain).isEqualTo(expectedPrecio);

	}

	@Test
	void getFechas3() {

		String fecha = "2020-06-14T21:00:00";

		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		LocalDateTime dateTime = LocalDateTime.parse(fecha, formatter);

		PrecioDomain expectedPrecio = PrecioDomain.builder().idProducto(35455L).idBrand(1L).idListaPrecio(1L)
				.descripcionListaPrecios("Listado Clientes Preferentes").precio(35.5).descripcionProducto("Camisa Azul")
				.fechaStart(LocalDateTime.parse("2020-06-14T00:00", formatter))
				.fechaEnd(LocalDateTime.parse("2020-12-31T23:59:59", formatter)).descripcionBrand("ZARA").idOferta(1L)
				.descripcionOferta("Primavera").curr("EUR").priority(1).build();

		PrecioDomain precioDomain = priceRepository.findPrecios(35455, 1, dateTime);

		assertThat(precioDomain).isEqualTo(expectedPrecio);

	}

	@Test
	void getFechas4() {

		String fecha = "2020-06-15T10:00:00";

		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		LocalDateTime dateTime = LocalDateTime.parse(fecha, formatter);

		PrecioDomain expectedPrecio = PrecioDomain.builder().idProducto(35455L).idBrand(1L).idListaPrecio(1L)
				.descripcionListaPrecios("Listado Clientes Preferentes").precio(30.5).descripcionProducto("Camisa Azul")
				.fechaStart(LocalDateTime.parse("2020-06-15T00:00", formatter))
				.fechaEnd(LocalDateTime.parse("2020-06-15T11:00", formatter)).descripcionBrand("ZARA").idOferta(3L)
				.descripcionOferta("Otono").curr("EUR").priority(3).build();

		PrecioDomain precioDomain = priceRepository.findPrecios(35455, 1, dateTime);

		assertThat(precioDomain).isEqualTo(expectedPrecio);

	}

	@Test
	void getFechas5() {

		String fecha = "2020-06-16T21:00:00";

		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		LocalDateTime dateTime = LocalDateTime.parse(fecha, formatter);

		PrecioDomain expectedPrecio = PrecioDomain.builder().idProducto(35455L).idBrand(1L).idListaPrecio(1L)
				.descripcionListaPrecios("Listado Clientes Preferentes").precio(38.95)
				.descripcionProducto("Camisa Azul").fechaStart(LocalDateTime.parse("2020-06-15T16:00", formatter))
				.fechaEnd(LocalDateTime.parse("2020-12-31T23:59:59", formatter)).descripcionBrand("ZARA").idOferta(4L)
				.descripcionOferta("Invierno").curr("EUR").priority(4).build();

		PrecioDomain precioDomain = priceRepository.findPrecios(35455, 1, dateTime);

		assertThat(precioDomain).isEqualTo(expectedPrecio);

	}

	@Test
	void getFechas_Null() {

		String fecha = "2019-06-16T21:00:00";
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		LocalDateTime dateTime = LocalDateTime.parse(fecha, formatter);
		PrecioDomain precioDomain = priceRepository.findPrecios(35455, 1, dateTime);
		assertThat(precioDomain).isNull();

	}
	
	
	


}
