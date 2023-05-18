package com.example.demo.service;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Rental;
import com.example.demo.entity.User;
import com.example.demo.persistace.RentalRepository;
import com.example.demo.persistace.UserRepository;

//UserService class

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RentalRepository rentalRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    
    public User addUser(User user) {
        return userRepository.save(user);
    }
    
    public User updateUser(Long id, User user) {
        Optional<User> userData = userRepository.findById(id);
        if (userData.isPresent()) {
            User existingUser = userData.get();
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPhoneNumber(user.getPhoneNumber());
            existingUser.setAddress(user.getAddress());
            existingUser.setDateOfBirth(user.getDateOfBirth());
            existingUser.setLicenseNumber(user.getLicenseNumber());
            existingUser.setPaymentInformation(user.getPaymentInformation());
            return userRepository.save(existingUser);
        } else {
            return null;
        }
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<Rental> getUserBookings(Long id) {
        User user = userRepository.findByid(id);
        return rentalRepository.findByUser(user);
    }
}

