package com.deliveryboy.service;

public interface IKafkaService {
    boolean updateLocation(String location);
    void updateName(String name);
}
