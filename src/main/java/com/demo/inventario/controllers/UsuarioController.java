package com.demo.inventario.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.inventario.models.Usuario;
import com.demo.inventario.services.UsuarioService;

@RestController
@CrossOrigin(origins="*")
public class UsuarioController {
	@Autowired
	UsuarioService usuarioService;
	
	@RequestMapping(value = "/usuario/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Usuario>> listAllUsuarios() {
		List<Usuario> usuarios = usuarioService.findAll();
		
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
	}

	@RequestMapping(value = "/usuario/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Optional<Usuario>> getUsuario(@PathVariable("id") int id) {
		Optional<Usuario> usuario = usuarioService.findById(id);
		if (usuario == null) {
			return new ResponseEntity<Optional<Usuario>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Optional<Usuario>>(usuario, HttpStatus.OK);
	}

	@RequestMapping(value = "/usuario/", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
		
		Optional<Usuario> usuarioOld = usuarioService.findById(usuario.getId());
		if (!usuarioOld.isEmpty()) {
			System.out.println("A Companie with Nombre " + usuario.getId() + " already exist");
			return new ResponseEntity<Usuario>(usuario,HttpStatus.CONFLICT);
		}
		usuarioService.save(usuario);
		return new ResponseEntity<Usuario>(usuario,HttpStatus.CREATED);
	}

	@RequestMapping(value = "/usuario/{id}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Usuario> updateUsuario(@PathVariable("id") int id, @RequestBody Usuario usuario) {
		Usuario currentUsuario = usuarioService.findById(id).get();
		if (currentUsuario == null) {
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}
		currentUsuario.setNombre(usuario.getNombre());
		currentUsuario.setEdad(usuario.getEdad());
		currentUsuario.setCargo(usuario.getCargo());
		currentUsuario.setFechaingreso(usuario.getFechaingreso());
		usuarioService.update(currentUsuario);
		return new ResponseEntity<Usuario>(currentUsuario, HttpStatus.OK);
	}

	@RequestMapping(value = "/usuario/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Usuario> deleteUsuario(@PathVariable("id") int id) {
		Usuario usuario = usuarioService.findById(id).get();
		if (usuario == null) {
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}
		usuarioService.delete(id);
		return new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);
	}
}
