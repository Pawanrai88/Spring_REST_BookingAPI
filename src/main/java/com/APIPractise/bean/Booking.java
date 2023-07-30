package com.APIPractise.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Booking")
public class Booking {

	
	@Id
	@Column(name="booking_id")
	int bookingId;
	
	@Column(name="first_name")
	String firstName;
	
	@Column(name="last_name")
	String lastName;
	
	@Column(name="origin_country")
	String origin;
	
	@Column(name="destination_country")
	String destination;
	
	public Booking(int bookingId, String firstName, String lastName, String origin, String destination) {
		this.bookingId=bookingId;
		this.firstName=firstName;
		this.lastName=lastName;
		this.origin=origin;
		this.destination=destination;
	}
	
	public Booking() {
		
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
	

}
