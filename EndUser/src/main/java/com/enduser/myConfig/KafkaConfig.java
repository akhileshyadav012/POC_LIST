package com.enduser.myConfig;

import com.enduser.constant.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class KafkaConfig {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConfig.class);

    @KafkaListener(topics = AppConstants.LOCATION_UPDATE_TOPIC, groupId = AppConstants.GROUP_ID)
    public void updateLocation(String value){
        logger.info("Location Value = " + value);
    }

    @KafkaListener(topics = AppConstants.NAME, groupId = AppConstants.GROUP_ID)
    public void updateName(String name){
        logger.info("Name Value = " + name);
    }
}
