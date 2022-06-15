package edu.es.eoi.service;

import java.util.List;
import java.util.Optional;

import edu.es.eoi.dto.UsuarioDTO;
import edu.es.eoi.entity.Usuario;



public interface UsuarioService {

	List<UsuarioDTO> allUsuario();

	UsuarioDTO findUsuarioId(int id);

	Usuario saveUsuario(Usuario user);

	Optional<Usuario> updateUsuario(Usuario updateUser);

	Usuario loginUsuario(String nombre);

}