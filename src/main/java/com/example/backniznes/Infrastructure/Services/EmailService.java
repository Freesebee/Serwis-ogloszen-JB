package com.example.backniznes.Infrastructure.Services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Async
@AllArgsConstructor
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(String toEmail) {
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();

        simpleMailMessage.setTo(toEmail);
        simpleMailMessage.setSubject("subject");
        simpleMailMessage.setText("Twoje ogłoszenie zostało zatwierdzone");
        simpleMailMessage.setFrom("AskorMailer@interia.pl");
        javaMailSender.send(simpleMailMessage);
    }
}