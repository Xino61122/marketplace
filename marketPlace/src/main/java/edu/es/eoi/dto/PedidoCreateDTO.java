package edu.es.eoi.dto;

import java.util.List;

import edu.es.eoi.entity.RelacionArticuloPedido;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoCreateDTO {
	
	private String nombre;
	private int idUsuario;
	private List<RelacionArticuloPedido> relArticulos;
}
