package com.demo.inventario.interfaces;

import java.util.List;
import java.util.Optional;

import com.demo.inventario.models.Cargo;

public interface ICargoService {
	Optional<Cargo> findById(int id);
	List<Cargo> findAll();
	Cargo save(Cargo cargo);
	boolean update(Cargo cargo);
	boolean delete(int id);
}
