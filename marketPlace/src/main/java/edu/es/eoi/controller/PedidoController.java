package edu.es.eoi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping(value = "/marketplace/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService service;

	@RequestMapping( method = RequestMethod.GET)
	public ResponseEntity<?> allProductos() {

		if (service.allPedido().size() != 0) {
			return new ResponseEntity<>(service.allPedido(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@RequestMapping(value = "busqueda/{nombreparcial}", method = RequestMethod.GET)
	public ResponseEntity<?> allProductosBusqueda(@PathVariable String nombreparcial) {

		if (service.allPedidoBusqueda(nombreparcial).size() != 0) {
			return new ResponseEntity<>(service.allPedidoBusqueda(nombreparcial), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findPedidoId(@PathVariable String id) {

		try {
			int pk = Integer.parseInt(id);
			try {
				PedidoDTO pedido = service.findPedidoId(pk);
				return new ResponseEntity<>(pedido, HttpStatus.OK);
			} catch (Exception e) {

			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletePedido(@PathVariable String id) {

		try {
			int pk = Integer.parseInt(id);
			try {
				service.deletePedidoId(pk);
				return new ResponseEntity<>(HttpStatus.OK);
			} catch (Exception e) {

			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createPedido(@RequestBody PedidoCreateDTO pedidoDto) {
		System.out.println("ENTRO");
		Pedido pedido = service.createPedido(pedidoDto);
		if (pedido.getId() != 0) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			try {
				service.savePedido(pedido);
				return new ResponseEntity<>(HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateArticulo(@PathVariable String id, @RequestBody Pedido pedidoUpdate) {

		try {
			int pk = Integer.parseInt(id);
			
			try {
				service.updatePedidoId(pedidoUpdate, pk);
				return new ResponseEntity<>(HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}

}
