package com.ayushsingh.rating_service.service;

import java.util.List;

import com.ayushsingh.rating_service.dto.RatingDto;

public interface RatingService {
    public RatingDto saveRating(RatingDto ratingDto);
    public RatingDto updateRating(RatingDto ratingDto);
    public void deleteRating(String ratingId);
    public List<RatingDto> getAllRatings();
    public RatingDto getRatingById(String ratingId);
    public List<RatingDto> findAllByUserId(String userId);
    public List<RatingDto> findAllByHotelId(String hotelId);
}
