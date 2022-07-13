package edu.es.eoi.service;

import java.util.List;

import edu.es.eoi.dto.PedidoCreateDTO;
import edu.es.eoi.dto.PedidoDTO;
import edu.es.eoi.dto.RelacionArticuloPedidoDTO;
import edu.es.eoi.entity.Pedido;
import edu.es.eoi.entity.RelacionArticuloPedido;

public interface PedidoService {

	List<PedidoDTO> allPedidoBusqueda(String nombre);
	
	List<PedidoDTO> allPedido();

	PedidoDTO findPedidoId(int id);

	void deletePedidoId(int id);

	void updatePedidoId(Pedido updatePedido, int id);
	
	void savePedido(Pedido pedido);
	
	Pedido createPedido(PedidoCreateDTO pedidoDto);

	PedidoDTO convertPedidoPedidoDTO(Pedido pedido);

	List<RelacionArticuloPedidoDTO> convertRelacionRelacionDTO(List<RelacionArticuloPedido> relacion);

}