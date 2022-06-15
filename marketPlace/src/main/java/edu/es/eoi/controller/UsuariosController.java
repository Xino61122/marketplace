package edu.es.eoi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.es.eoi.dto.UsuarioDTO;
import edu.es.eoi.entity.Usuario;
import edu.es.eoi.service.UsuarioService;

@RestController
@RequestMapping(value = "/marketplace/usuarios")
public class UsuariosController {

	@Autowired
	private UsuarioService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> findAll() {
		if (service.allUsuario().size() != 0) {
			return new ResponseEntity<>(service.allUsuario(), HttpStatus.FOUND);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findUsuario(@PathVariable String id) {

		try {
			int pk = Integer.parseInt(id);
			UsuarioDTO usuario = service.findUsuarioId(pk);
			if (usuario == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(usuario, HttpStatus.FOUND);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createUsuario(@RequestBody Usuario usuario) {

		if (usuario.getId() != 0) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		} else {
			// Usuario.nombre es unique, por lo que hacemos una comprobación de si ya existe
			// dicho Usuario
			try {
				service.saveUsuario(usuario);
				return new ResponseEntity<>(HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.IM_USED);
			}

		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUsuario(@PathVariable String id, @RequestBody Usuario usuario) {

		if (usuario.getId() != Integer.valueOf(id)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			try {
				service.updateUsuario(usuario);
				return new ResponseEntity<>(HttpStatus.ACCEPTED);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.IM_USED);
			}
		}
	}
	
	
	@RequestMapping(value = "/login" ,method = RequestMethod.POST)
	public ResponseEntity<?> loginUsuario(@RequestBody Usuario usuario){
		
		Usuario findUsuario = service.loginUsuario(usuario.getNombre());
		if(findUsuario.getPassword().equals(usuario.getPassword())) {
			return new ResponseEntity<>(service.findUsuarioId(findUsuario.getId()),HttpStatus.FOUND);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		
	}
	
}
