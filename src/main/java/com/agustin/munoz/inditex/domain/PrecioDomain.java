package com.agustin.munoz.inditex.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author agust
 *
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class PrecioDomain {

	private Long idProducto;

	private Long idBrand;

	private Long idListaPrecio;

	private Double precio;

	private String descripcionProducto;

	private LocalDateTime fechaStart;

	private LocalDateTime fechaEnd;

	private String descripcionBrand;

	private String descripcionListaPrecios;

	private Long idOferta;

	private String descripcionOferta;

	private String curr;

	private Integer priority;

}
