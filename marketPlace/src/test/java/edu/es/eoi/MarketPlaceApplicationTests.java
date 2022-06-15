package edu.es.eoi;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.es.eoi.dto.ArticuloDTO;
import edu.es.eoi.dto.UsuarioDTO;
import edu.es.eoi.entity.Articulo;
import edu.es.eoi.entity.Pedido;
import edu.es.eoi.entity.Usuario;
import edu.es.eoi.repository.ArticuloRepository;
import edu.es.eoi.repository.PedidoRepository;
import edu.es.eoi.repository.UsuarioReposity;
import edu.es.eoi.service.ArticuloService;

@SpringBootTest
class MarketPlaceApplicationTests {

	@Autowired
	ArticuloRepository repoArticulo;

	@Autowired
	PedidoRepository repoPedido;

	@Autowired
	UsuarioReposity repoUsuario;

//	******************************** REPOSITORY TEST ********************************

	@Test
	void ArticuloRepositoryTestFindCreateAndDelete() {
		Articulo articulo = new Articulo();
		articulo.setNombre("TestBorrar");
		articulo.setPrecio(1000.0);
		articulo.setStock(3);
		repoArticulo.save(articulo);

		Assertions.assertEquals("TestBorrar", repoArticulo.findById(articulo.getId()).get().getNombre());

		repoArticulo.delete(articulo);

	}

	@Test
	void UsuarioRepositoryTestFindCreateAndDelete() {
		Usuario usuario = new Usuario();
		usuario.setNombre("pepe");
		usuario.setPassword("Algo");
		repoUsuario.save(usuario);

		for (int i = 0; i < repoUsuario.findAll().size(); i++) {
			System.out.println(repoUsuario.findAll().get(i).getNombre());
		}

		Assertions.assertEquals("pepe", repoUsuario.findById(usuario.getId()).get().getNombre());
		Assertions.assertEquals("Algo", repoUsuario.findById(usuario.getId()).get().getPassword());
		repoUsuario.delete(usuario);
	}

	@Test
	void PedidoRepositoryTestFindCreateAndDelete() {
		Usuario usuario = new Usuario();
		usuario.setNombre("pepe");
		usuario.setPassword("Algo");
		repoUsuario.save(usuario);
		Pedido pedido = new Pedido();
		Calendar fecha = Calendar.getInstance();
		fecha.set(2016, 6, 3);
		pedido.setFecha(fecha);
		pedido.setNombre("testeoBorrar");
		pedido.setUsuario(usuario);
		repoPedido.save(pedido);

		System.out.println(repoPedido.findPedidoByNombreContainingOrderById("test"));

		Assertions.assertEquals("testeoBorrar", repoPedido.findById(pedido.getId()).get().getNombre());

		repoUsuario.delete(usuario);
		repoPedido.delete(pedido);

	}

//	******************************** SERVICE TEST ********************************
//  -----USUARIO-----

	@Test
	void allUsuariosTest() {

		Usuario usuario1 = new Usuario();
		usuario1.setNombre("pepe");
		usuario1.setPassword("Algo");
		repoUsuario.save(usuario1);

		List<UsuarioDTO> usuariosDTO = new ArrayList<>();

		for (Usuario user : repoUsuario.findAll()) {
			UsuarioDTO usuario = new UsuarioDTO();
			usuario.setId(user.getId());
			usuario.setNombre(user.getNombre());
			usuario.setPassword(user.getPassword());
			usuariosDTO.add(usuario);
		}

		Assertions.assertEquals("pepe", repoUsuario.findById(usuario1.getId()).get().getNombre());
		Assertions.assertEquals("Algo", repoUsuario.findById(usuario1.getId()).get().getPassword());
		repoUsuario.delete(usuario1);
	}

	@Test
	void findUsuarioIdTest() {

		Usuario usuario1 = new Usuario();
		usuario1.setNombre("pepe");
		usuario1.setPassword("Algo");
		repoUsuario.save(usuario1);

		UsuarioDTO usuario = new UsuarioDTO();
		Optional<Usuario> user = repoUsuario.findById(usuario1.getId());

		if (user.isEmpty()) {
			Assertions.assertNotNull(user);
		} else {
			usuario.setId(user.get().getId());
			usuario.setNombre(user.get().getNombre());
			usuario.setPassword(user.get().getPassword());
			Assertions.assertEquals("pepe", repoUsuario.findById(usuario.getId()).get().getNombre());
			repoUsuario.deleteById(usuario1.getId());
		}

	}

	@Test
	void updateUsuarioTest() {
		// Crear usuario para probar (acordarse de borralo luego

//		Usuario updateUser = new Usuario();
//		updateUser.setNombre("pepe");
//		updateUser.setPassword("Algo");
//		repoUsuario.save(updateUser);

		Optional<Usuario> testUser = repoUsuario.findById(12);

		Usuario updateUser = testUser.get();
		updateUser.setNombre("Test");
		updateUser.setPassword(null);

		Optional<Usuario> user = repoUsuario.findById(updateUser.getId());
		if (user.isEmpty()) {
			Assertions.assertNotNull(user);
		} else {
			if (updateUser.getNombre() != null) {
				user.get().setNombre(updateUser.getNombre());
			}
			if (updateUser.getPassword() != null) {
				user.get().setPassword(updateUser.getPassword());
			}
			repoUsuario.save(user.get());
			Assertions.assertEquals("Test", user.get().getNombre());
		}
//		repoUsuario.delete(updateUser);
	}

//  -----ARTICULO-----
	
	@Autowired
	private ArticuloService service;
	
	@Test
	void allArticulosTest() {		

		System.out.println(service.allArticulos("ñ"));

	}

	@Test 
	void findArticuloIdTest() {
		Articulo articulo = repoArticulo.findArticuloById(2);
		ArticuloDTO articuloDTO = new ArticuloDTO();
		
		articuloDTO.setId(articulo.getId());
		articuloDTO.setNombre(articulo.getNombre());
		articuloDTO.setPrecio(articulo.getPrecio());
		articuloDTO.setStock(articulo.getStock());
		Assertions.assertEquals(null, articulo);
	}
	
//  -----PEDIDO-----
//	@Autowired
//	private PedidoService servicePedido;
//	
//	@Test
//	void findAllPedidos() {
//		
//		servicePedido.allPedido();
//		
//	}
//	
}




























