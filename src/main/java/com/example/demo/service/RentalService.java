package com.example.demo.service;



import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Car;
import com.example.demo.entity.Rental;
import com.example.demo.entity.User;
import com.example.demo.persistace.CarRepository;
import com.example.demo.persistace.RentalRepository;
import com.example.demo.persistace.UserRepository;

import jakarta.persistence.EntityNotFoundException;


@Service
public class RentalService {
    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CarService carService;
    
    @Autowired
    private EmailService emailService  ;

//    public Rental bookCar(Long id, String carLicenseNumber, LocalDate startDate, LocalDate endDate) {
//        User user = userRepository.findByid(id);
//        Car car = carRepository.findByCarLicenseNumber(carLicenseNumber);
//        double totalPrice = carService.calculatePrice(startDate, endDate, carLicenseNumber);
//        Rental rental = new Rental();
//        rental.setUser(user);
//        rental.setCar(car);
//        rental.setStartDate(startDate);
//        rental.setEndDate(endDate);
//        rental.setPrice(totalPrice);
//        return rentalRepository.save(rental);
//    }

    public Rental bookCar(Long id, String carLicenseNumber, LocalDate startDate, LocalDate endDate) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        Car car = carRepository.findByCarLicenseNumber(carLicenseNumber);
        double totalPrice = carService.calculatePrice(startDate, endDate, carLicenseNumber);
        Rental rental = new Rental();
        rental.setUser(user);
        rental.setCar(car);
        rental.setStartDate(startDate);
        rental.setEndDate(endDate);
        rental.setPrice(totalPrice);
        Rental savedRental = rentalRepository.save(rental);

        // Create the email content
        String emailSubject = "Car Rental Booking Details";
        String emailText = "Thank you for booking a car with us!\n\n" +
                "Booking ID: " + savedRental.getId() + "\n" +
                "Car License Number: " + car.getCarLicenseNumber() + "\n" +
                "Start Date: " + startDate + "\n" +
                "End Date: " + endDate + "\n" +
                "Total Price: " + totalPrice + "\n\n" +
                "If you have any questions, please feel free to contact us.";

        // Send the email
        emailService.sendBookingDetails(user.getEmail(), emailSubject, emailText);

        return savedRental;
    }
    
    public List<Rental> getCarBookings(String carLicenseNumber) {
        Car car = carRepository.findByCarLicenseNumber(carLicenseNumber);
        return rentalRepository.findByCar(car);
    }
}
