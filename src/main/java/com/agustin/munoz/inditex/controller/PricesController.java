package com.agustin.munoz.inditex.controller;

import java.time.OffsetDateTime;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.agustin.munoz.inditex.error.InditexErrorCodes;
import com.agustin.munoz.inditex.error.InditexErrorMessages;
import com.agustin.munoz.inditex.exception.InditexException;
import com.agustin.munoz.inditex.openapi.PricesApiDelegate;
import com.agustin.munoz.inditex.openapi.model.PrecioResource;
import com.agustin.munoz.inditex.service.PriceService;

import lombok.AllArgsConstructor;

/**
 * 
 * @author agust
 *
 */

@CrossOrigin()
@RestController
@AllArgsConstructor
public class PricesController implements PricesApiDelegate {

	private final PriceService priceService;

	/**
	 * 
	 * agust
	 * 
	 * @param productId
	 * @param brandId
	 * @param fecha
	 * @return
	 */
	@Override
	public ResponseEntity<PrecioResource> getPrices(Integer productId, Integer brandId, OffsetDateTime fecha) {

		return Optional.ofNullable(priceService.getPrice(productId, brandId, fecha))
				.map(price -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(price))
				.orElseThrow(() -> new InditexException(InditexErrorMessages.NOT_FOUND.getDescription(),
						InditexErrorMessages.NOT_FOUND.getCode(),
						HttpStatus.valueOf(InditexErrorCodes.ERROR_404.getCode())));
	}

}
