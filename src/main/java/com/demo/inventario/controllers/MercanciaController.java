package com.demo.inventario.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

import com.demo.inventario.models.Mercancia;
import com.demo.inventario.services.MercanciaService;

@RestController
@CrossOrigin(origins="*")
public class MercanciaController {
	@Autowired
	MercanciaService mercanciaService;
	
	@RequestMapping(value = "/mercancia/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Mercancia>> listAllMercancias() {
		List<Mercancia> mercancias = mercanciaService.findAll();
		
		return new ResponseEntity<List<Mercancia>>(mercancias, HttpStatus.OK);
	}

	@RequestMapping(value = "/mercancia/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Optional<Mercancia>> getMercancia(@PathVariable("id") int id) {
		Optional<Mercancia> mercancia = mercanciaService.findById(id);
		if (mercancia == null) {
			return new ResponseEntity<Optional<Mercancia>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Optional<Mercancia>>(mercancia, HttpStatus.OK);
	}

	@RequestMapping(value = "/mercancia/", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Mercancia> createMercancia(@RequestBody Mercancia mercancia) {
		
		Optional<Mercancia> mercanciaOld = mercanciaService.findById(mercancia.getId());
		if (!mercanciaOld.isEmpty()) {
			System.out.println("A Companie with Nombre " + mercancia.getId() + " already exist");
			return new ResponseEntity<Mercancia>(mercancia,HttpStatus.CONFLICT);
		}
		try{
			Date date = Calendar.getInstance().getTime();
			mercancia.setFechaactualizacion(date);
			mercanciaService.save(mercancia);
			return new ResponseEntity<Mercancia>(mercancia,HttpStatus.CREATED);
		}catch(Exception ex) {
			return new ResponseEntity<Mercancia>(HttpStatus.NOT_FOUND);
		}
		
	}

	@RequestMapping(value = "/mercancia/{id}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Mercancia> updateMercancia(@PathVariable("id") int id, @RequestBody Mercancia mercancia) {
		Mercancia currentMercancia = mercanciaService.findById(id).get();
		if (currentMercancia == null) {
			return new ResponseEntity<Mercancia>(HttpStatus.NOT_FOUND);
		}
		currentMercancia.setNombreproducto(mercancia.getNombreproducto());
		
		Date date = Calendar.getInstance().getTime();
		currentMercancia.setFechaactualizacion(date);
		currentMercancia.setCantidad(mercancia.getCantidad());
		currentMercancia.setFechaingreso(mercancia.getFechaingreso());

		currentMercancia.setUsuarioactual(mercancia.getUsuarioactual());
		mercanciaService.update(currentMercancia);
		return new ResponseEntity<Mercancia>(currentMercancia, HttpStatus.OK);
	}

	@RequestMapping(value = "/mercancia/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Mercancia> deleteMercancia(@PathVariable("id") int id) {
		Mercancia mercancia = mercanciaService.findById(id).get();
		if (mercancia == null) {
			return new ResponseEntity<Mercancia>(HttpStatus.NOT_FOUND);
		}
		mercanciaService.delete(id);
		return new ResponseEntity<Mercancia>(HttpStatus.NO_CONTENT);
	}
}
