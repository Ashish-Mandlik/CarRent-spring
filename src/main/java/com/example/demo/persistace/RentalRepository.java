package com.example.demo.persistace;



import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Car;
import com.example.demo.entity.Rental;
import com.example.demo.entity.User;


@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findByUser(User user);
    List<Rental> findByCar(Car car);
	List<Rental> findByStartDateLessThanEqualAndEndDateGreaterThanEqual(LocalDate startDate,
			LocalDate endDate);
}


