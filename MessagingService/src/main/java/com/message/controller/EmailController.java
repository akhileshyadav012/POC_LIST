package com.message.controller;

import com.message.request.EmailRequest;
import com.message.service.IEmailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/v1/email")
public class EmailController {
    private static final Logger logger = LogManager.getLogger(EmailController.class);

    @Autowired
    private IEmailService emailService;
    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest emailRequest){
        logger.info("EmailController - Inside sendEmail method");
        String sendEmail = emailService.sendEmail(emailRequest);
        return ResponseEntity.of(Optional.ofNullable(sendEmail));
    }


}
