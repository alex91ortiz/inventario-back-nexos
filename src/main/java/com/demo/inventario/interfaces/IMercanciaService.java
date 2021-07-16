package com.demo.inventario.interfaces;

import java.util.List;
import java.util.Optional;

import com.demo.inventario.models.Mercancia;

public interface IMercanciaService {
	Optional<Mercancia> findById(int id);
	List<Mercancia> findAll();
	Mercancia save(Mercancia mercancia);
	boolean update(Mercancia mercancia);
	boolean delete(int id);
}
