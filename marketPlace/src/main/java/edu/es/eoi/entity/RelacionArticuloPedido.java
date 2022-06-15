package edu.es.eoi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pertenece_a")
@Setter
@Getter
public class RelacionArticuloPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private int cantidad;
	
	@ManyToOne
	@JoinColumn(name = "id_articulo", referencedColumnName = "id")
	private Articulo articuloId;
	
	@ManyToOne
	@JoinColumn(name = "id_pedido", referencedColumnName = "id")
	private Pedido pedidoId;
	
}
