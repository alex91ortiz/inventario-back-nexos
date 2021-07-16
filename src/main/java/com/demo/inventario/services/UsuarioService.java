package com.demo.inventario.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.inventario.interfaces.IUsuarioService;
import com.demo.inventario.models.Usuario;
import com.demo.inventario.repositories.UsuarioRepository;
@Transactional
@Service("usuarioService")
public class UsuarioService implements IUsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	@Override
	public Optional<Usuario> findById(int id) {
		// TODO Auto-generated method stub
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		return usuario;
	}
	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		List<Usuario> usuarios = (List<Usuario>) usuarioRepository.findAll();
		return usuarios;
	}
	@Override
	public Usuario save(Usuario usuario) {
		// TODO Auto-generated method stub
		return usuarioRepository.save(usuario);
	}
	@Override
	public boolean update(Usuario usuario) {
		// TODO Auto-generated method stub
		if((usuarioRepository.save(usuario)) != null ) {
			return true;
		}
		return false;
	}
	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		boolean isDeleted = false;
		try {
			usuarioRepository.deleteById(id);
			isDeleted = true;
		}catch(IllegalArgumentException e) {}
		return isDeleted;
	}

}
