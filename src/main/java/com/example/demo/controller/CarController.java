package com.example.demo.controller;

import java.time.LocalDate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Car;
import com.example.demo.service.CarService;


//Controller Layer
@RestController
public class CarController {
  @Autowired
  private CarService carService;
  
  public CarController(CarService carService) {
      this.carService = carService;
  }
  
  @GetMapping("/cars")
  public List<Car> getAllCars() {
      return carService.getAllCars();
  }

  @GetMapping("/cars/{id}")
  public ResponseEntity<Car> getCarById(@PathVariable Long id) {
      Car car = carService.getCarById(id);
      if (car != null) {
          return new ResponseEntity<>(car, HttpStatus.OK);
      } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
  }

  @PostMapping("/cars")
  public Car addCar(@RequestBody Car car) {
      return carService.addCar(car);
  }
  
  @PutMapping("/cars/{id}")
  public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car car) {
      Car existingCar = carService.getCarById(id);
      if (existingCar != null) {
          existingCar.setCarLicenseNumber(car.getCarLicenseNumber());
          existingCar.setManufacturer(car.getManufacturer());
          existingCar.setModel(car.getModel());
          existingCar.setBasePrice(car.getBasePrice());
          existingCar.setPph(car.getPph());
          existingCar.setSecurityDeposit(car.getSecurityDeposit());

          carService.addCar(existingCar);

          return new ResponseEntity<>(existingCar, HttpStatus.OK);
      } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
  }
  
  @DeleteMapping("/cars/{id}")
  public ResponseEntity<HttpStatus> deleteCarById(@PathVariable Long id) {
      carService.deleteCarById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
  
  
  //  localhost:8080/search-cars?startDate=2023-05-10&&endDate=2023-05-15
  	//based on date available cars
  @GetMapping("/search-cars")
  public List<Car> searchCars(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
      return carService.searchCars(startDate, endDate);
  }
  
//  localhost:8080/calculate-price?startDate=2023-05-10&&endDate=2023-05-15&&carLicenseNumber=ABC123

  @GetMapping("/calculate-price")
  public double calculatePrice(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                               @RequestParam String carLicenseNumber) {
      return carService.calculatePrice(startDate, endDate, carLicenseNumber);
  }
}