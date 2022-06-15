package edu.es.eoi.entity;

import java.util.List;

import javax.persistence.CascadeType;
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
@Table(name = "usuario")
@Setter
@Getter
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String nombre;
	
	@Column
	private String password;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
	private List<Pedido> pedidoUser; 
	
}
