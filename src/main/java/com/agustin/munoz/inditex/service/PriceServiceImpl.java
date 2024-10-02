package com.agustin.munoz.inditex.service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.agustin.munoz.inditex.domain.PrecioDomain;
import com.agustin.munoz.inditex.error.InditexErrorCodes;
import com.agustin.munoz.inditex.error.InditexErrorMessages;
import com.agustin.munoz.inditex.exception.InditexException;
import com.agustin.munoz.inditex.mapper.PrecioDomainMapper;
import com.agustin.munoz.inditex.openapi.model.PrecioResource;
import com.agustin.munoz.inditex.repository.PreciosRepostitory;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author agust
 *
 */
@Slf4j
@Service
@AllArgsConstructor
public class PriceServiceImpl implements PriceService {

	private final PreciosRepostitory preciosRepository;

	private final PrecioDomainMapper precioMapper;

	
	/**
	 * 
	 * agust
	 * @param productId
	 * @param brandId
	 * @param fecha
	 * @return
	 */
	@Override
	public PrecioResource getPrice(Integer productId, Integer brandId, OffsetDateTime fecha) {
		log.info("Conviertiendo formato fechas....");

		LocalDateTime dateTime = LocalDateTime.ofInstant(fecha.toInstant(), ZoneOffset.UTC);

		PrecioDomain precioDomain = null;
		try {
			log.info("Consulta BBDD....");
			precioDomain = preciosRepository.findPrecios(productId, brandId, dateTime);
			return precioMapper.toResource(precioDomain);
		} catch (DataAccessException e) {
			log.info("Error acceso repositorio....");
			throw new InditexException(InditexErrorMessages.DATA_EXCEPTION.getDescription(),
					InditexErrorMessages.DATA_EXCEPTION.getCode(),
					HttpStatus.valueOf(InditexErrorCodes.ERROR_500.getCode()));
		}

	}

}
