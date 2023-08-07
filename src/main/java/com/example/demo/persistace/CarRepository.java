package com.example.demo.persistace;


import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {
	
	
    List<Car> findByCarLicenseNumberNotIn(List<String> notIn);

	Car findByCarLicenseNumber(String carLicenseNumber);

	Optional<Car> findById(Long id);

	void deleteById(Long id);


}




