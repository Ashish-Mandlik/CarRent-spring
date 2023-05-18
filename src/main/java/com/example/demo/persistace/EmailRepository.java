package com.example.demo.persistace;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Email;

public interface EmailRepository extends JpaRepository<Email, Long> {

}
