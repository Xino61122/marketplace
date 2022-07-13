package edu.es.eoi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.es.eoi.dto.ArticuloDTO;
import edu.es.eoi.entity.Articulo;
import edu.es.eoi.repository.ArticuloRepository;

@Service
public class ArticuloServiceImpl implements ArticuloService {

	@Autowired
	ArticuloRepository repoArticulo;

	@Override
	public List<ArticuloDTO> allArticulosBusqueda(String nombre) {

		List<Articulo> articulos= new ArrayList<>();
		for (Articulo articulofind : repoArticulo.findArticuloByNombreContainingOrderById(nombre)) {			
			articulos.add(articulofind);
		}
		
		List<ArticuloDTO> articulosDTO= new ArrayList<>();
		for (Articulo articulofind:articulos) {
			articulosDTO.add(convertArticuloArticuloDTO(articulofind));
		}

		return articulosDTO;
	}

	@Override
	public List<ArticuloDTO> allArticulos() {

		List<Articulo> articulos= new ArrayList<>();
		for (Articulo articulofind : repoArticulo.findAll()) {			
			articulos.add(articulofind);
		}
		
		List<ArticuloDTO> articulosDTO= new ArrayList<>();
		for (Articulo articulofind:articulos) {
			articulosDTO.add(convertArticuloArticuloDTO(articulofind));
		}

		return articulosDTO;
	}
	
	@Override
	public Articulo findArticuloId(int id) {

		return repoArticulo.findArticuloById(id);
	}

	@Override
	public void saveArticulo(Articulo articulo) {

		repoArticulo.save(articulo);
	}

	@Override
	public void updateArticulo(Articulo updateArticulo, int id) {
		Articulo articulo = repoArticulo.findById(id).get();

		if (updateArticulo.getNombre() != null) {
			articulo.setNombre(updateArticulo.getNombre());
		}
		if (updateArticulo.getPrecio() != null) {
			articulo.setPrecio(updateArticulo.getPrecio());
		}
		if (updateArticulo.getStock() != null) {
			articulo.setStock(updateArticulo.getStock());
		}
		
		repoArticulo.save(articulo);
	}

	@Override
	public ArticuloDTO convertArticuloArticuloDTO(Articulo articulo) {
		ArticuloDTO articuloDTO = new ArticuloDTO();

		articuloDTO.setId(articulo.getId());
		articuloDTO.setNombre(articulo.getNombre());
		articuloDTO.setPrecio(articulo.getPrecio());
		articuloDTO.setStock(articulo.getStock());
		return articuloDTO;
	}
	
	public void deleteArticulo(Articulo articulo) {
		repoArticulo.delete(articulo);
	}

}
