package com.ayushsingh.rating_service.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ayushsingh.rating_service.entities.Rating;

public interface RatingRepostory extends MongoRepository<Rating,String>{
    
    List<Rating> findByUserId(String userId);
    List<Rating> findByHotelId(String hotelId);
}
