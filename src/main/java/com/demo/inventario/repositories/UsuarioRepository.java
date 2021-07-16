package com.demo.inventario.repositories;

import org.springframework.data.repository.CrudRepository;

import com.demo.inventario.models.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer>{

}
