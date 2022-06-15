package edu.es.eoi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.es.eoi.dto.PedidoCreateDTO;
import edu.es.eoi.dto.PedidoDTO;
import edu.es.eoi.entity.Pedido;
import edu.es.eoi.service.PedidoService;

@RestController
@RequestMapping(value = "/marketplace/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService service;

	@RequestMapping(value = "busqueda/{nombreparcial}", method = RequestMethod.GET)
	public ResponseEntity<?> allProductos(@PathVariable String nombreparcial) {

		if (service.allPedido(nombreparcial).size() != 0) {
			return new ResponseEntity<>(service.allPedido(nombreparcial), HttpStatus.FOUND);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findPedidoId(@PathVariable String id) {

		try {
			int pk = Integer.parseInt(id);
			try {
				PedidoDTO pedido = service.findPedidoId(pk);
				return new ResponseEntity<>(pedido, HttpStatus.FOUND);
			} catch (Exception e) {

			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletePedido(@PathVariable String id) {

		try {
			int pk = Integer.parseInt(id);
			try {
				service.deletePedidoId(pk);
				return new ResponseEntity<>(HttpStatus.ACCEPTED);
			} catch (Exception e) {

			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createPedido(@RequestBody PedidoCreateDTO pedidoDTO) {

		Pedido pedido = service.createPedido(pedidoDTO);
		if (pedido.getId() != 0) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		} else {
			try {
				service.savePedido(pedido);
				return new ResponseEntity<>(HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateArticulo(@PathVariable String id, @RequestBody Pedido pedidoUpdate) {

		try {
			int pk = Integer.parseInt(id);
			
			try {
				service.updatePedidoId(pedidoUpdate, pk);
				return new ResponseEntity<>(HttpStatus.ACCEPTED);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

}
