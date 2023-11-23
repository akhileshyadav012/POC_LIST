package com.message.service.impl;

import com.message.service.IMessageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements IMessageService {
    private static final Logger logger = LogManager.getLogger(MessageServiceImpl.class);
}
