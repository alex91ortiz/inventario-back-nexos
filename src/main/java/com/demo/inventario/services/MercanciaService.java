package com.demo.inventario.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.inventario.interfaces.IMercanciaService;
import com.demo.inventario.models.Mercancia;
import com.demo.inventario.repositories.MercanciaRepository;

@Transactional
@Service("mercanciaService")
public class MercanciaService implements IMercanciaService {
	
	@Autowired
	MercanciaRepository mercanciaRepository;

	@Override
	public Optional<Mercancia> findById(int id) {
		// TODO Auto-generated method stub
		Optional<Mercancia> mercancia = mercanciaRepository.findById(id);
		return mercancia;
	}
	@Override
	public List<Mercancia> findAll() {
		// TODO Auto-generated method stub
		List<Mercancia> mercancias = (List<Mercancia>) mercanciaRepository.findAll();
		return mercancias;
	}
	@Override
	public Mercancia save(Mercancia mercancia) {
		// TODO Auto-generated method stub
		return mercanciaRepository.save(mercancia);
	}
	@Override
	public boolean update(Mercancia mercancia) {
		// TODO Auto-generated method stub
		if((mercanciaRepository.save(mercancia)) != null ) {
			return true;
		}
		return false;
	}
	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		boolean isDeleted = false;
		try {
			mercanciaRepository.deleteById(id);
			isDeleted = true;
		}catch(IllegalArgumentException e) {}
		return isDeleted;
	}

}
