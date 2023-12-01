package com.message.service;

import com.message.request.EmailRequest;

public interface IEmailService {
    String sendEmail(EmailRequest emailRequest);
}
