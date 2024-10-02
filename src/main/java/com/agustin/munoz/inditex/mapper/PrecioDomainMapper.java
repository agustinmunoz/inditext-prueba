package com.agustin.munoz.inditex.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.agustin.munoz.inditex.domain.PrecioDomain;
import com.agustin.munoz.inditex.openapi.model.PrecioResource;

/**
 * 
 * @author agust
 *
 */

@Mapper(componentModel = "spring")
public interface PrecioDomainMapper {

	@Mapping(source = "idProducto", target = "productId")
	@Mapping(source = "idBrand", target = "brandId")
	@Mapping(source = "idListaPrecio", target = "tarifaId")
	@Mapping(source = "precio", target = "price")
	@Mapping(source = "descripcionProducto", target = "productName")
	@Mapping(source = "fechaStart", target = "startDate")
	@Mapping(source = "fechaEnd", target = "endDate")
	@Mapping(source = "descripcionBrand", target = "brandName")
	@Mapping(source = "descripcionListaPrecios", target = "tarifaName")
	@Mapping(source = "idOferta", target = "ofertaId")
	@Mapping(source = "descripcionOferta", target = "ofertaName")
	@Mapping(source = "curr", target = "curr")
	@Mapping(source = "priority", target = "ofertaPrioridad")
	PrecioResource toResource(PrecioDomain precioDomain);

	@InheritInverseConfiguration
	PrecioDomain toDomain(PrecioResource precioResource);

}
