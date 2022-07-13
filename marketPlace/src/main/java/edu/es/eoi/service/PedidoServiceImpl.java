package edu.es.eoi.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.es.eoi.dto.PedidoCreateDTO;
import edu.es.eoi.dto.PedidoDTO;
import edu.es.eoi.dto.RelacionArticuloPedidoDTO;
import edu.es.eoi.entity.Pedido;
import edu.es.eoi.entity.RelacionArticuloPedido;
import edu.es.eoi.repository.PedidoRepository;
import edu.es.eoi.repository.RelacionArticuloPedidoRepository;
import edu.es.eoi.repository.UsuarioReposity;

@Service
public class PedidoServiceImpl implements PedidoService {

	@Autowired
	PedidoRepository repoPedido;
	
	@Autowired
	UsuarioReposity repoUsuario;
	
	@Autowired
	ArticuloService repoArticulo;
	
	@Autowired
	RelacionArticuloPedidoRepository repoRelacion;

	@Override
	public List<PedidoDTO> allPedido() {
		
		List<PedidoDTO> pedidosDTO = new ArrayList<>();
		for(Pedido pedido:repoPedido.findAll()) {
			pedidosDTO.add(convertPedidoPedidoDTO(pedido));
		}
		
		return pedidosDTO;
	}
	
	@Override
	public List<PedidoDTO> allPedidoBusqueda(String nombre) {
		
		List<PedidoDTO> pedidosDTO = new ArrayList<>();
		for(Pedido pedido:repoPedido.findPedidoByNombreContainingOrderById(nombre)) {
			pedidosDTO.add(convertPedidoPedidoDTO(pedido));
		}
		
		return pedidosDTO;
	}

	@Override
	public PedidoDTO findPedidoId(int id) {
		
		return convertPedidoPedidoDTO(repoPedido.findPedidoById(id));
	}
	
	@Override
	public void deletePedidoId(int id) {
		
		repoPedido.deleteById(id);
	}
	
	@Override
	public void updatePedidoId(Pedido updatePedido, int id) {
		
		Pedido pedido = repoPedido.findPedidoById(id);
		
		if (updatePedido.getFecha() != null) {
			pedido.setFecha(updatePedido.getFecha());
		}
		if (updatePedido.getNombre() != null) {
			pedido.setNombre(updatePedido.getNombre());
		}
		if (updatePedido.getUsuario() != null) {
			pedido.setUsuario(updatePedido.getUsuario());
		}
		if (updatePedido.getRelArticulos() != null) {
			pedido.setRelArticulos(updatePedido.getRelArticulos());
		}
		
		repoPedido.save(pedido);
	}
	
	@Override
	public void savePedido(Pedido pedido) {
		
		if(pedido.getFecha() == null) {
			pedido.setFecha(Calendar.getInstance());
		}
		repoPedido.save(pedido);		
	}
	
	
	@Override
	public PedidoDTO convertPedidoPedidoDTO(Pedido pedido) {

		PedidoDTO pedidoDTO = new PedidoDTO();

		pedidoDTO.setId(pedido.getId());
		pedidoDTO.setNombre(pedido.getNombre());
		SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
		fmt.setCalendar(pedido.getFecha());
		pedidoDTO.setFecha(fmt.format(pedido.getFecha().getTime()));
		pedidoDTO.setArticulos(convertRelacionRelacionDTO(pedido.getRelArticulos()));

		return pedidoDTO;
	}
	
	@Override
	public Pedido createPedido(PedidoCreateDTO pedidoDto) {
		
		Calendar fecha = Calendar.getInstance();
		String fechaList[] =  (pedidoDto.getFecha()).split("-");
		try {
			fecha.set(Integer.parseInt(fechaList[0]), Integer.parseInt(fechaList[1]), Integer.parseInt(fechaList[2]));
		}catch (Exception e) {
			return null;
		}
		Pedido pedido=new Pedido();
		pedido.setNombre(pedidoDto.getNombre());
		pedido.setUsuario(repoUsuario.findById(pedidoDto.getIdUsuario()).get());
		pedido.setFecha(fecha);
		
		Pedido nuevoPedidoSave = repoPedido.save(pedido);
		
		List<RelacionArticuloPedidoDTO> listArt= pedidoDto.getArticulos();
		
		if(listArt!=null) {
			for (RelacionArticuloPedidoDTO rel : listArt) {
				RelacionArticuloPedido relacion = new RelacionArticuloPedido();
				relacion.setCantidad(rel.getCantidad());
				relacion.setArticuloId(repoArticulo.findArticuloId(rel.getId()));
				relacion.setPedidoId(nuevoPedidoSave);
				repoRelacion.save(relacion);			}
		}
		
		
		return pedido;
	}

	// TODO Sacar fuera si se hace un servicio RelacionArticuloPedidoDTO
	
	@Override
	public List<RelacionArticuloPedidoDTO> convertRelacionRelacionDTO(List<RelacionArticuloPedido> relacion) {

		List<RelacionArticuloPedidoDTO> relacionDTO = new ArrayList<>();

		for (RelacionArticuloPedido rel : relacion) {
			RelacionArticuloPedidoDTO relDTO = new RelacionArticuloPedidoDTO();
			relDTO.setId(rel.getId());
			relDTO.setCantidad(rel.getCantidad());
			relacionDTO.add(relDTO);
		}
		return relacionDTO;

	}


}
