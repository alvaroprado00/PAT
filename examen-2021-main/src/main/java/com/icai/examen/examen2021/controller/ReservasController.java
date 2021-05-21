package com.icai.examen.examen2021.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icai.examen.examen2021.model.Booking;
import com.icai.examen.examen2021.repository.BookingsRepository;


//Indico que se trata de un restController y que atenderá todas las peticiones a /api/bookings
@RestController
@RequestMapping("/api/bookings")
public class ReservasController {

	//Inyecto directamente el repository
	@Autowired
	BookingsRepository bookingsRepo;
	
	@GetMapping
	public ResponseEntity<List<Booking>> getAllBookings(){
		
		//Utilizo el metodo por defecto de Crud Repository el cual me devuelve un iterable que paso a List
		
		Iterable<Booking> bookings= bookingsRepo.findAll();
		
		Iterator<Booking> it= bookings.iterator();
		
		ArrayList<Booking> bookingsList= new ArrayList<>();
		
		while(it.hasNext()) {
			bookingsList.add(it.next());
		}
		
		if (bookingsList.size()==0) {
			
			return new ResponseEntity<List<Booking>>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<List<Booking>>(bookingsList, HttpStatus.OK);
		}
		
	}
	
	//Se indica el id de la reserva mediante una parte variable del path
	@GetMapping("/{id}")
	public ResponseEntity<Booking> getBookingById(@PathVariable("id") long id){
		
		//El metodo por defecto te devuelve un objeto Optional 
		Optional<Booking> bookingFound= bookingsRepo.findById(id);
		
		if(bookingFound.isEmpty()) {
			//Si no me devuelve nada el repository lo indico con codigo http
			return new ResponseEntity<Booking>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<Booking>(bookingFound.get(), HttpStatus.OK);
		}
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Booking> deleteBookingById(@PathVariable("id") long id){
		
		//borro el booking que se me indica 
		bookingsRepo.deleteById(id);
		
		//luego lo busco para ver si realmente se ha borrado
		boolean exists=bookingsRepo.existsById(id);
		
		if(exists) {
			return new ResponseEntity<Booking>(HttpStatus.BAD_REQUEST);

		}else {
			return new ResponseEntity<Booking>(HttpStatus.OK);
		}
	}

}
