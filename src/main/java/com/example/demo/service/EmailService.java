package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import com.example.demo.entity.Email;

import com.example.demo.persistace.EmailRepository;

@Service
public class EmailService {
	private  JavaMailSender mailSender;
    private  EmailRepository emailRepository;
    
    @Autowired
    public EmailService(JavaMailSender mailSender, EmailRepository emailRepository) {
        this.mailSender = mailSender;
        this.emailRepository = emailRepository;
    }

    public void sendBookingDetails(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);

        // Save the sent email details
        Email email = new Email(to, subject, text);
        emailRepository.save(email);
    }

}
