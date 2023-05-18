package com.example.demo.controller;

import java.time.LocalDate;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.Rental;
import com.example.demo.service.RentalService;


@RestController
public class RentalController {
    @Autowired
    private RentalService rentalService;


    //    localhost:8080/car/book?id=2&&carLicenseNumber=ABC123&&startDate=2023-06-01&&endDate=2023-06-06
    
    @PostMapping("/car/book")
    public void bookCar(@RequestParam Long id,
                          @RequestParam String carLicenseNumber,
                          @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                          @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
         rentalService.bookCar(id, carLicenseNumber, startDate, endDate);
    }

//   localhost:8080/car/bookings?carLicenseNumber=ABC123
    @GetMapping("/car/bookings")
    public List<Rental> getCarBookings(@RequestParam String carLicenseNumber) {
        return rentalService.getCarBookings(carLicenseNumber);
    }
}