package com.agustin.munoz.inditex.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author agust
 *
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "precios")
public class Precios {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "id_producto")
	private Long idProducto;

	@Column(name = "id_oferta")
	private Long idOferta;

	@Column(name = "id_brand")
	private Long idBrand;

	@Column(name = "id_lista_precio")
	private Long idListaPrecio;

	@Column(name = "precios")
	private Double precios;

	@Column(name = "curr")
	private String curr;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productos_id_producto")
	private Productos productos;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ofertas_id_oferta")
	private Ofertas ofertas;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "brands_id_brand")
	private Brands brands;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "listaPrecios_id_lista_precio")
	private ListaPrecios listaPrecios;

}
