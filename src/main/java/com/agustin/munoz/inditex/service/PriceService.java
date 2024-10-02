package com.agustin.munoz.inditex.service;

import java.time.OffsetDateTime;

import com.agustin.munoz.inditex.openapi.model.PrecioResource;

/**
 * 
 * @author agust
 *
 */

public interface PriceService {

	/**
	 * 
	 * agust
	 * @param productId
	 * @param brandId
	 * @param fecha
	 * @return
	 * PrecioResource
	 */
	PrecioResource getPrice(Integer productId, Integer brandId, OffsetDateTime fecha);

}
