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

import com.demo.inventario.models.Cargo;
import com.demo.inventario.services.CargoService;

@RestController
@CrossOrigin(origins="*")
public class CargoController {
	@Autowired
	CargoService cargoService;
	
	@RequestMapping(value = "/cargo/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Cargo>> listAllCargos() {
		List<Cargo> cargos = cargoService.findAll();
		
		return new ResponseEntity<List<Cargo>>(cargos, HttpStatus.OK);
	}

	@RequestMapping(value = "/cargo/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Optional<Cargo>> getCargo(@PathVariable("id") int id) {
		Optional<Cargo> cargo = cargoService.findById(id);
		if (cargo == null) {
			return new ResponseEntity<Optional<Cargo>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Optional<Cargo>>(cargo, HttpStatus.OK);
	}

	@RequestMapping(value = "/cargo/", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Cargo> createCargo(@RequestBody Cargo cargo) {
		
		Optional<Cargo> cargoOld = cargoService.findById(cargo.getId());
		if (!cargoOld.isEmpty()) {
			return new ResponseEntity<Cargo>(cargo,HttpStatus.CONFLICT);
		}
		cargoService.save(cargo);
		return new ResponseEntity<Cargo>(cargo,HttpStatus.CREATED);
	}

	@RequestMapping(value = "/cargo/{id}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Cargo> updateCargo(@PathVariable("id") int id, @RequestBody Cargo cargo) {
		Cargo currentCargo = cargoService.findById(id).get();
		if (currentCargo == null) {
			return new ResponseEntity<Cargo>(HttpStatus.NOT_FOUND);
		}
		currentCargo.setNombre(cargo.getNombre());
		currentCargo.setDescripcion(cargo.getDescripcion());
		
		cargoService.update(currentCargo);
		return new ResponseEntity<Cargo>(currentCargo, HttpStatus.OK);
	}

	@RequestMapping(value = "/cargo/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Cargo> deleteCargo(@PathVariable("id") int id) {
		Cargo cargo = cargoService.findById(id).get();
		if (cargo == null) {
			return new ResponseEntity<Cargo>(HttpStatus.NOT_FOUND);
		}
		cargoService.delete(id);
		return new ResponseEntity<Cargo>(HttpStatus.NO_CONTENT);
	}
}
