package com.hotelservice.service.impl;

import com.hotelservice.entity.Hotel;
import com.hotelservice.entity.Rating;
import com.hotelservice.entity.User;
import com.hotelservice.exceptions.NotFoundException;
import com.hotelservice.feignclient.IRatingService;
import com.hotelservice.feignclient.IUserService;
import com.hotelservice.repository.HotelRepository;
import com.hotelservice.service.IHotelService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements IHotelService {
    private static final Logger logger = LogManager.getLogger(HotelServiceImpl.class);
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private IRatingService ratingService;
    @Autowired
    private IUserService userService;

    @Override
    public Hotel createHotel(Hotel hotel) {
        logger.info("HotelServiceImpl - Inside createHotel method ");
        String randomUUID = UUID.randomUUID().toString();
        hotel.setHotelId(randomUUID);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotel() {
        logger.info("HotelServiceImpl - Inside getAllHotel method ");
        List<Hotel> hotelList = hotelRepository.findAll();
        List<String> hotelIdList = hotelList.stream().map(hotel -> hotel.getHotelId()).collect(Collectors.toList());
        for (String hotelId : hotelIdList){
            Optional<Hotel> optionalHotel = hotelRepository.findById(hotelId);
            if (optionalHotel.isEmpty()){
                throw new NotFoundException("Hotel Not Found");
            }
            Hotel hotel = optionalHotel.get();
            List<Rating> hotelRating = ratingService.getRatings(hotelId);
            List<Rating> ratings = hotelRating.stream().map(rating -> {
                User user = userService.getUser(rating.getUserId());
                rating.setUser(user);
                return rating;
            }).collect(Collectors.toList());
            hotel.setRatings(ratings);
        }
        return hotelList;
    }

    @Override
    public Hotel getHotelById(String id) {
        logger.info("HotelServiceImpl - Inside getHotelById method ");
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (optionalHotel.isEmpty()) {
            throw new NotFoundException("Hotel Not Found");
        }
        Hotel hotel = optionalHotel.get();
        List<Rating> hotelRating = ratingService.getRatings(hotel.getHotelId());
        List<Rating> ratings = hotelRating.stream().map(rating -> {
            User user = userService.getUser(rating.getUserId());
            rating.setUser(user);
            return rating;
        }).collect(Collectors.toList());

        hotel.setRatings(ratings);
        return hotel;
    }

    @Override
    public void deleteHotelById(String id) {
        logger.info("HotelServiceImpl - Inside deleteHotelById method ");
        hotelRepository.deleteById(id);
    }

    @Override
    public Hotel updateHotelById(String id, Hotel hotel) {
        logger.info("HotelServiceImpl - Inside updateHotelById method ");
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (optionalHotel.isEmpty()) {
            throw new NotFoundException("Hotel Not Found");
        }
        Hotel updateHotel = optionalHotel.get();
        updateHotel.setName(hotel.getName());
        updateHotel.setLocation(hotel.getLocation());
        updateHotel.setAbout(hotel.getAbout());
        return hotelRepository.save(updateHotel);
    }
}
