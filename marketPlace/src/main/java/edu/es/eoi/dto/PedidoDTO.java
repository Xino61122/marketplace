package edu.es.eoi.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoDTO {

	private int id;
	
	private String nombre;
	
	private String fecha;
	
	private List<RelacionArticuloPedidoDTO> articulos;
	
}
