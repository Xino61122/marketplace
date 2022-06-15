package edu.es.eoi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/marketplace/reports")
public class ReportsController {

	
	@RequestMapping(value = "busqueda/{nombreparcial}", method = RequestMethod.GET)
	public ResponseEntity<?> masVendidos() {

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
}
