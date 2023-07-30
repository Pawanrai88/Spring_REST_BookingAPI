package com.APIPractise.services;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.APIPractise.bean.Booking;
import com.APIPractise.controllers.AddResponse;
import com.APIPractise.respository.BookingRepository;

@Component
@Service
public class BookingService {
   
	
	
	@Autowired
	BookingRepository bookingrepo;
	
	public List<Booking> getAllBookings() {
		
		return bookingrepo.findAll();
	}
	

	public Booking getBookingById(int id) {
		
		List<Booking>bookings = bookingrepo.findAll();
		Booking booking= null;
		for(Booking book:bookings) {
			if(book.getBookingId()==id) {
				booking=book;
			}
		}
		return booking;
	}
	public int createBookingId() {
		
		Random rand = new Random();
		int uniqueID =rand.nextInt(10000);
		return uniqueID;
		
	}
	public Booking createBooking(Booking booking) {
		booking.setBookingId(createBookingId());
		bookingrepo.save(booking);
		return booking;
		
	}
	
	public Booking updateBooking(Booking booking) {
		bookingrepo.save(booking);
		return booking;
	}
	
	public AddResponse deleteBooking(int id) {
		bookingrepo.deleteById(id);
		
		AddResponse res=new AddResponse();
		res.setMsg("Booking Deleted!!");
		res.setId(id);
		return res;
		
	}
}
