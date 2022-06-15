package edu.es.eoi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.es.eoi.dto.ArticuloDTO;
import edu.es.eoi.entity.Articulo;
import edu.es.eoi.service.ArticuloService;

@RestController
@RequestMapping(value = "/marketplace/articulos")
public class ArticuloController {

	@Autowired
	private ArticuloService service;

	@RequestMapping(value = "busqueda/{nombreparcial}", method = RequestMethod.GET)
	public ResponseEntity<?> allArticulos(@PathVariable String nombreparcial) {
		List<ArticuloDTO> listaArticulosDTO = service.allArticulos(nombreparcial);
		if (listaArticulosDTO.size() != 0) {
			return new ResponseEntity<>(listaArticulosDTO, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findArticulo(@PathVariable String id) {

		try {
			int pk = Integer.parseInt(id);
			Articulo articulo = service.findArticuloId(pk);
			if (articulo == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				ArticuloDTO articuloDTO = service.convertArticuloArticuloDTO(articulo);
				return new ResponseEntity<>(articuloDTO, HttpStatus.FOUND);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createArticulo(@RequestBody Articulo articulo) {

		if (articulo.getId() != 0) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		} else {
			try {
				service.saveArticulo(articulo);
				return new ResponseEntity<>(HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateArticulo(@PathVariable String id, @RequestBody Articulo articulo) {

		try {
			int pk = Integer.parseInt(id);
			
			try {
				service.updateArticulo(articulo, pk);
				return new ResponseEntity<>(HttpStatus.ACCEPTED);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
