package com.agustin.munoz.inditex.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.agustin.munoz.inditex.domain.PrecioDomain;
import com.agustin.munoz.inditex.entity.Precios;

/**
 * 
 * @author agust
 *
 */

public interface PreciosRepostitory extends CrudRepository<Precios, Long> {

	/**
	 * 
	 * agust
	 * 
	 * @param productId
	 * @param brandId
	 * @param fecha
	 * @return PrecioDomain
	 */
	@Query("""
			SELECT  new com.agustin.munoz.inditex.domain.PrecioDomain(pre.idProducto, pre.idBrand, pre.idListaPrecio, pre.precios, pro.descripcion, of.fechaStart, of.fechaEnd, bra.descripcionBrand, lis.descripcionListaPrecio, of.idOferta, of.descripcionOferta, pre.curr, of.priority)
			FROM com.agustin.munoz.inditex.entity.Precios pre
			JOIN com.agustin.munoz.inditex.entity.Productos pro on pre.idProducto=pro.idProducto
			JOIN com.agustin.munoz.inditex.entity.Ofertas of on pre.idOferta=of.idOferta
			JOIN com.agustin.munoz.inditex.entity.Brands bra on pre.idBrand=bra.idBrand
			JOIN com.agustin.munoz.inditex.entity.ListaPrecios lis on pre.idListaPrecio=lis.idListaPrecio
			WHERE pre.idProducto= :productId
			AND bra.idBrand =:brandId
			AND of.fechaStart<:fecha
			AND of.fechaEnd>:fecha
			ORDER BY of.priority desc
			LIMIT 1
			""")
	PrecioDomain findPrecios(Integer productId, Integer brandId, LocalDateTime fecha);

}
