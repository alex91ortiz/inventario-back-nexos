package com.demo.inventario.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.inventario.models.Cargo;



@Repository
public interface CargoRepository extends CrudRepository<Cargo, Integer>{

}
