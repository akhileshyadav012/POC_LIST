package com.hotelservice.controller;

import com.hotelservice.entity.Hotel;
import com.hotelservice.service.IHotelService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/hotels")
public class HotelController {
    private static final Logger logger = LogManager.getLogger(HotelController.class);
    @Autowired
    private IHotelService hotelService;

    @PostMapping
    public Hotel createHotel(@RequestBody Hotel hotel){
        logger.info("HotelController - Inside createHotel method");
        return hotelService.createHotel(hotel);
    }

    @GetMapping
    public List<Hotel> getAllUser(){
        logger.info("HotelController - Inside getAllUser method");
        return hotelService.getAllHotel();

    }

    @GetMapping("/{id}")
    public Hotel getUserById(@PathVariable("id") String id){
        logger.info("HotelController - Inside getUserById method");
        return hotelService.getHotelById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable("id") String id){
        logger.info("HotelController - Inside deleteUserById method");
        hotelService.deleteHotelById(id);
    }

    @PutMapping("/{id}")
    public Hotel updateUserById(@PathVariable("id") String id, @RequestBody Hotel user){
        logger.info("HotelController - Inside updateUserById method");
        return hotelService.updateHotelById(id, user);
    }
}
