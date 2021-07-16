package com.demo.inventario.interfaces;

import java.util.List;
import java.util.Optional;

import com.demo.inventario.models.Usuario;



public interface IUsuarioService {
	Optional<Usuario> findById(int id);
	List<Usuario> findAll();
	Usuario save(Usuario usuario);
	boolean update(Usuario usuario);
	boolean delete(int id);
}
