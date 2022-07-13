package edu.es.eoi.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoCreateDTO {
	
	private String nombre;
	private String fecha;
	private int idUsuario;
	private List<RelacionArticuloPedidoDTO> articulos;
}
