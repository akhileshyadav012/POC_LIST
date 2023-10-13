package com.deliveryboy.service.impl;

import com.deliveryboy.constants.AppConstant;
import com.deliveryboy.service.IKafkaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaServiceImpl implements IKafkaService {
    private static final Logger logger = LoggerFactory.getLogger(KafkaServiceImpl.class);
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    public boolean updateLocation(String location){
        this.kafkaTemplate.send(AppConstant.LOCATION_TOPIC_NAME, location);
        logger.info("Location Produced");
        return true;
    }

    public void updateName(String name){
        this.kafkaTemplate.send(AppConstant.NAME, name);
        logger.info("Name Produced");
    }
}
