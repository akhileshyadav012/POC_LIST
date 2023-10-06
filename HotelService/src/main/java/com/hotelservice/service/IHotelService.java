package com.hotelservice.service;

import com.hotelservice.entity.Hotel;

import java.util.List;

public interface IHotelService {
    Hotel createHotel(Hotel hotel);
    List<Hotel> getAllHotel();
    Hotel getHotelById(String id);
    void deleteHotelById(String id);
    Hotel updateHotelById(String id, Hotel hotel);
}
