package com.demo.inventario.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.inventario.interfaces.ICargoService;
import com.demo.inventario.models.Cargo;
import com.demo.inventario.repositories.CargoRepository;

@Transactional
@Service("cargoService")
public class CargoService implements ICargoService {
	
	@Autowired
	CargoRepository cargoRepository;
	@Override
	public Optional<Cargo> findById(int id) {
		// TODO Auto-generated method stub
		Optional<Cargo> cargo = cargoRepository.findById(id);
		return cargo;
	}
	@Override
	public List<Cargo> findAll() {
		// TODO Auto-generated method stub
		List<Cargo> cargos = (List<Cargo>) cargoRepository.findAll();
		return cargos;
	}
	@Override
	public Cargo save(Cargo cargo) {
		// TODO Auto-generated method stub
		return cargoRepository.save(cargo);
	}
	@Override
	public boolean update(Cargo cargo) {
		// TODO Auto-generated method stub
		if((cargoRepository.save(cargo)) != null ) {
			return true;
		}
		return false;
	}
	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		boolean isDeleted = false;
		try {
			cargoRepository.deleteById(id);
			isDeleted = true;
		}catch(IllegalArgumentException e) {}
		return isDeleted;
	}

}
