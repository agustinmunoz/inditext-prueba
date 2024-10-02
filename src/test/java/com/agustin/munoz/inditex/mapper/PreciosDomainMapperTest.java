package com.agustin.munoz.inditex.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.agustin.munoz.inditex.domain.PrecioDomain;
import com.agustin.munoz.inditex.entity.Brands;
import com.agustin.munoz.inditex.openapi.model.PrecioResource;

/**
 * 
 * @author agust
 *
 */

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class PreciosDomainMapperTest {

	@Autowired
	PrecioDomainMapperImpl mapper;

	@Test
	void toDomain() {

		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

		PrecioResource precioResource = new PrecioResource();
		precioResource.setProductId(35455L);
		precioResource.setProductName("Camisa Azul");
		precioResource.setOfertaId(1L);
		precioResource.setOfertaName("Primavera");
		precioResource.setOfertaPrioridad(1);
		precioResource.setBrandId(1);
		precioResource.setBrandName("ZARA");
		precioResource.setTarifaId(1);
		precioResource.setTarifaName("Listado Clientes Preferentes");
		precioResource.setStartDate("2020-06-14T00:00:00");
		precioResource.setEndDate("2020-12-31T23:59:59");
		precioResource.setPrice(35.5);
		precioResource.setCurr("EUR");

		PrecioDomain priceResult = mapper.toDomain(precioResource);

		PrecioDomain precioDomain = new PrecioDomain();

		precioDomain.setIdProducto(35455L);
		precioDomain.setDescripcionProducto("Camisa Azul");
		precioDomain.setIdOferta(1L);
		precioDomain.setDescripcionOferta("Primavera");
		precioDomain.setPriority(1);
		precioDomain.setIdBrand(1L);
		precioDomain.setDescripcionBrand("ZARA");
		precioDomain.setIdListaPrecio(1L);
		precioDomain.setDescripcionListaPrecios("Listado Clientes Preferentes");
		precioDomain.setFechaStart(LocalDateTime.parse("2020-06-14T00:00:00", formatter));
		precioDomain.setFechaEnd(LocalDateTime.parse("2020-12-31T23:59:59", formatter));
		precioDomain.setPrecio(35.5);
		precioDomain.setCurr("EUR");

		assertThat(priceResult).isEqualTo(precioDomain);
		assertThat(priceResult.hashCode()).isNotNull();
		assertThat(priceResult.toString()).isNotNull();

	}

	@Test
	void toResource() {

		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

		PrecioDomain precioDomain = PrecioDomain.builder().idProducto(35455L).idBrand(1L).idListaPrecio(1L)
				.descripcionListaPrecios("Listado Clientes Preferentes").precio(35.5).descripcionProducto("Camisa Azul")
				.fechaStart(LocalDateTime.parse("2020-06-14T00:00:00", formatter))
				.fechaEnd(LocalDateTime.parse("2020-12-31T23:59:59", formatter)).descripcionBrand("ZARA").idOferta(1L)
				.descripcionOferta("Primavera").curr("EUR").priority(1).build();

		PrecioResource precioResourceExpected = new PrecioResource();
		precioResourceExpected.setProductId(35455L);
		precioResourceExpected.setProductName("Camisa Azul");
		precioResourceExpected.setOfertaId(1L);
		precioResourceExpected.setOfertaName("Primavera");
		precioResourceExpected.setOfertaPrioridad(1);
		precioResourceExpected.setBrandId(1);
		precioResourceExpected.setBrandName("ZARA");
		precioResourceExpected.setTarifaId(1);
		precioResourceExpected.setTarifaName("Listado Clientes Preferentes");
		precioResourceExpected.setStartDate("2020-06-14T00:00:00");
		precioResourceExpected.setEndDate("2020-12-31T23:59:59");

		precioResourceExpected.setPrice(35.5);
		precioResourceExpected.setCurr("EUR");

		PrecioResource precioResource = mapper.toResource(precioDomain);

		assertThat(precioResourceExpected).isEqualTo(precioResource);
		assertThat(precioResource.hashCode()).isNotNull();
		assertThat(precioResource.toString()).isNotNull();

	}

	@Test
	void brandEntity() {

		Brands brand = new Brands();
		brand.setIdBrand(1L);
		brand.setDescripcionBrand("ZARA");

		Brands brand2 = new Brands(1L, "ZARA");

		assertThat(brand.getIdBrand()).isEqualTo(1L);
		assertThat(brand.getDescripcionBrand()).isEqualTo("ZARA");
		assertThat(brand.hashCode()).isNotNull();
		assertThat(brand.toString()).isNotNull();
		assertThat(brand2.getIdBrand()).isEqualTo(1L);
		assertThat(brand2.getDescripcionBrand()).isEqualTo("ZARA");

	}

}
