package edu.es.eoi.service;

import java.util.List;

import edu.es.eoi.dto.ArticuloDTO;
import edu.es.eoi.entity.Articulo;

public interface ArticuloService {

	List<ArticuloDTO> allArticulos(String nombre);

	Articulo findArticuloId(int id);

	void saveArticulo(Articulo articulo);

	void updateArticulo(Articulo updateArticulo, int id);

	ArticuloDTO convertArticuloArticuloDTO(Articulo articulo);

}