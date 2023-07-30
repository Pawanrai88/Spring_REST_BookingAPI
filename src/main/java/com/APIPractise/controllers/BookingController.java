package com.APIPractise.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.APIPractise.bean.Booking;
import com.APIPractise.services.BookingService;

@RestController
public class BookingController {

	
	  @Autowired
	  BookingService bookingservice;
	  
	  @GetMapping("/booking")
	  public List<Booking> getAllBooking() {
		  
		  return bookingservice.getAllBookings();
	  }
	  
	  @GetMapping("/booking/{id}")
	  public ResponseEntity<Booking> getBookingById(@PathVariable(value="id")int id) {
		  try {
			  Booking booking = bookingservice.getBookingById(id);
			  return new ResponseEntity<Booking>(booking,HttpStatus.OK);
		  }
		  catch(Exception e)
			{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
	  }
	  
	  @PostMapping("/booking")
	  public Booking createNewBooking(@RequestBody Booking booking) {
		  
		  return bookingservice.createBooking(booking); 
		  
	  }
	  
	  @PutMapping("/booking/{id}")
	  public ResponseEntity<Booking> updateBooking(@PathVariable(value="id")int id,@RequestBody Booking booking) {
		 try {
		  Booking updateBookingId= bookingservice.getBookingById(id);
		  updateBookingId.setOrigin(booking.getOrigin());
		  updateBookingId.setOrigin(booking.getDestination());
		  Booking updatedBooking = bookingservice.updateBooking(booking);
		  return new ResponseEntity<Booking>(updatedBooking,HttpStatus.OK);
		 }
		 catch(Exception e) {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
	  }
	  
		@DeleteMapping("/booking/{id}")
		public AddResponse deleteBooking(@PathVariable(value="id") int id) {
			
			
			return bookingservice.deleteBooking(id);
		}
}
