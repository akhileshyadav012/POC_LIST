package com.deliveryboy.config;

import com.deliveryboy.constants.AppConstant;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    public NewTopic topic(){
        return TopicBuilder
                .name(AppConstant.LOCATION_TOPIC_NAME)
                .build();
    }

    public NewTopic newTopic(){
        return TopicBuilder.name(AppConstant.NAME)
                .build();
    }

}
