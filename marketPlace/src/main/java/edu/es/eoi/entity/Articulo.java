package edu.es.eoi.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "articulo")
@Setter
@Getter
public class Articulo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String nombre;

	@Column
	private Double precio;

	@Column
	private Integer stock;

	@OneToMany(mappedBy = "articuloId")
	private List<RelacionArticuloPedido> relPedidos;
}
