package edu.es.eoi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.es.eoi.dto.UsuarioDTO;
import edu.es.eoi.entity.Usuario;
import edu.es.eoi.repository.UsuarioReposity;

@Service
public class UsuariosServiceImpl implements UsuarioService {

	@Autowired
	UsuarioReposity repoUsuario;

	public List<UsuarioDTO> allUsuario() {

		List<UsuarioDTO> usuariosDTO = new ArrayList<>();

		for (Usuario user : repoUsuario.findAll()) {
			UsuarioDTO usuario = new UsuarioDTO();
			usuario.setId(user.getId());
			usuario.setNombre(user.getNombre());
			usuario.setPassword(user.getPassword());
			usuariosDTO.add(usuario);
		}

		return usuariosDTO;
	}

	public UsuarioDTO findUsuarioId(int id) {

		UsuarioDTO usuario = new UsuarioDTO();
		Optional<Usuario> user = repoUsuario.findById(id);
		if (user.isEmpty()) {
			return usuario = null;
		} else {
			usuario.setId(user.get().getId());
			usuario.setNombre(user.get().getNombre());
			usuario.setPassword(user.get().getPassword());
			return usuario;
		}
	}

	public Usuario saveUsuario(Usuario user) {

		return repoUsuario.save(user);
	}

	public Optional<Usuario> updateUsuario(Usuario updateUser) {

		Optional<Usuario> user = repoUsuario.findById(updateUser.getId());

		if (updateUser.getNombre() != null || !updateUser.getNombre().isEmpty()) {
			user.get().setNombre(updateUser.getNombre());
		}
		if (updateUser.getPassword() != null || !updateUser.getPassword().isEmpty()) {
			user.get().setPassword(updateUser.getPassword());
		}
		repoUsuario.save(updateUser);
		return user;

	}

	public Usuario loginUsuario(String nombre) {
		
		 return repoUsuario.findByNombreEquals(nombre);		
	}
	
	public void deleteUsuario(Usuario usuario) {
		repoUsuario.delete(usuario);
	}

	public Usuario findUsuarioIdUser(int id) {
		
		
		
		return repoUsuario.findById(id).get();
	}
	
}
