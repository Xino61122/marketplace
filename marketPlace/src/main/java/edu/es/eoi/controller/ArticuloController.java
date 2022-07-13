package edu.es.eoi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.es.eoi.dto.ArticuloDTO;
import edu.es.eoi.entity.Articulo;
import edu.es.eoi.service.ArticuloService;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping(value = "/marketplace/articulos")
public class ArticuloController {

	@Autowired
	private ArticuloService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> allArticulosDto() {
		List<ArticuloDTO> listaArticulosDTO = service.allArticulos();
		if (listaArticulosDTO.size() != 0) {
			return new ResponseEntity<>(listaArticulosDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}

	
	@RequestMapping(value = "busqueda/{nombreparcial}", method = RequestMethod.GET)
	public ResponseEntity<?> allArticulosBusqueda(@PathVariable String nombreparcial) {
		List<ArticuloDTO> listaArticulosDTO = service.allArticulosBusqueda(nombreparcial);
		if (listaArticulosDTO.size() != 0) {
			return new ResponseEntity<>(listaArticulosDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findArticulo(@PathVariable String id) {

		try {
			int pk = Integer.parseInt(id);
			Articulo articulo = service.findArticuloId(pk);
			if (articulo == null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				ArticuloDTO articuloDTO = service.convertArticuloArticuloDTO(articulo);
				return new ResponseEntity<>(articuloDTO, HttpStatus.OK);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createArticulo(@RequestBody Articulo articulo) {
		if (articulo.getId() != 0) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			try {
				service.saveArticulo(articulo);
				return new ResponseEntity<>(HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		}

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateArticulo(@PathVariable String id, @RequestBody Articulo articulo) {

		try {
			int pk = Integer.parseInt(id);
			
			try {
				service.updateArticulo(articulo, pk);
				return new ResponseEntity<>(HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@RequestMapping(value = "/delete/{id}" ,method = RequestMethod.POST)
	public ResponseEntity<?> deleteUsuario(@PathVariable String id,@RequestBody Articulo articulo){
		try {
			int pk = Integer.parseInt(id);
			Articulo art = service.findArticuloId(pk);
			if (art == null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				service.deleteArticulo(articulo);
				return new ResponseEntity<>(HttpStatus.OK);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@RequestMapping(value = "/delete/{id}" ,method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteArticulo(@PathVariable String id){
		try {
			int pk = Integer.parseInt(id);
			Articulo articulo = service.findArticuloId(pk);
			if (articulo == null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				service.deleteArticulo(articulo);
				return new ResponseEntity<>(HttpStatus.OK);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

}
