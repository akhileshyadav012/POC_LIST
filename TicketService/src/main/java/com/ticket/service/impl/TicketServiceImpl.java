package com.ticket.service.impl;

import com.ticket.external.impl.UserServiceFeignClient;
import com.ticket.external.response.UserResponse;
import com.ticket.service.ITicketService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements ITicketService {
    private static final Logger logger = LogManager.getLogger(TicketServiceImpl.class);

    @Autowired
    private UserServiceFeignClient serviceFeignClient;

    public String demoMethod(){
        logger.info("TicketServiceImpl - Inside demoMethod method");
        String number = String.valueOf(5 + 3);

        String demo = serviceFeignClient.demo();
        System.out.println("demo number = " + demo);

        UserResponse userResponse = serviceFeignClient.getUserById("4d62f9d4-f1d4-4424-9f69-c66afde9d99d");
        System.out.println("USERrESPONSE = " + userResponse);
        return number;
    }
}
