package com.agustin.munoz.inditex.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "ofertas")
public class Ofertas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_oferta")
	private Long idOferta;

	@Column(name = "descripcion_oferta")
	private String descripcionOferta;

	@Column(name = "fecha_start")
	private LocalDateTime fechaStart;

	@Column(name = "fecha_end")
	private LocalDateTime fechaEnd;

	@Column(name = "priority")
	private Integer priority;

}
