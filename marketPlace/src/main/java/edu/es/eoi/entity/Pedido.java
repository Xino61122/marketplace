package edu.es.eoi.entity;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pedido")
@Setter
@Getter
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Calendar fecha;

	@Column
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario", referencedColumnName = "id")
	private Usuario usuario;

	@OneToMany(mappedBy = "pedidoId")
	private List<RelacionArticuloPedido> relArticulos;
}
