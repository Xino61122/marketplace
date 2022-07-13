package edu.es.eoi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.es.eoi.entity.Usuario;

@Repository
public interface UsuarioReposity extends JpaRepository<Usuario, Integer>{

	Usuario findByNombreEquals(String nombre);
	
	
}
