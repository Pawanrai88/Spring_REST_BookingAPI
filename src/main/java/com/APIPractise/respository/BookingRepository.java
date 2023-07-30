package com.APIPractise.respository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.APIPractise.bean.Booking;

public interface BookingRepository extends JpaRepository<Booking,Integer>{

}
