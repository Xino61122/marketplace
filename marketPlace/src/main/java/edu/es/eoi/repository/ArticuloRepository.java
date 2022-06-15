package edu.es.eoi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.es.eoi.entity.Articulo;

@Repository
public interface ArticuloRepository extends JpaRepository<Articulo, Integer> {

	//optional
	Articulo findArticuloById(Integer id);
	
	Articulo findArticuloByNombreContaining(String nombre);
	
	List<Articulo> findArticuloByNombreContainingOrderById(String nombre);
	
}
