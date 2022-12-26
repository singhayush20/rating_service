package com.ayushsingh.rating_service.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayushsingh.rating_service.dto.RatingDto;
import com.ayushsingh.rating_service.entities.Rating;
import com.ayushsingh.rating_service.exceptions.ResourceNotFoundException;
import com.ayushsingh.rating_service.repository.RatingRepostory;
import com.ayushsingh.rating_service.service.RatingService;
@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    RatingRepostory ratingRepostory;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public RatingDto saveRating(RatingDto ratingDto) {
        ratingDto.setRatingId(UUID.randomUUID().toString());
        Rating rating = this.ratingRepostory.save(this.ratingDtoToRating(ratingDto));
        return this.ratingToDto(rating);
    }

    @Override
    public RatingDto updateRating(RatingDto ratingDto) {
        Rating rating = this.ratingRepostory.findById(ratingDto.getRatingId()).get();
        rating.setRating(ratingDto.getRating());
        rating.setFeedback(ratingDto.getFeedback());
        rating = this.ratingRepostory.save(rating);
        return this.ratingToDto(rating);
    }

    @Override
    public void deleteRating(String ratingId) {
        Rating rating = this.ratingRepostory.findById(ratingId).get();
        if (rating != null) {
            this.ratingRepostory.delete(rating);
        } else {
            throw new ResourceNotFoundException("Rating", "Rating id", ratingId);
        }
    }

    @Override
    public List<RatingDto> getAllRatings() {
        List<Rating> ratings = this.ratingRepostory.findAll();
        List<RatingDto> ratingDtos = new ArrayList<>();
        for (Rating rating : ratings) {
            ratingDtos.add(this.ratingToDto(rating));
        }
        return ratingDtos;
    }

    @Override
    public RatingDto getRatingById(String ratingId) {
        Rating rating = this.ratingRepostory.findById(ratingId).get();
        if (rating != null) {
            return this.ratingToDto(rating);
        } else {
            throw new ResourceNotFoundException("Rating", "Rating id", ratingId);
        }
    }

    @Override
    public List<RatingDto> findAllByUserId(String userId) {
        List<Rating> ratings=this.ratingRepostory.findByUserId(userId);
        List<RatingDto> ratingDtos = new ArrayList<>();
        for (Rating rating : ratings) {
            ratingDtos.add(this.ratingToDto(rating));
        }
        return ratingDtos;
    }

    @Override
    public List<RatingDto> findAllByHotelId(String hotelId) {
        List<Rating> ratings=this.ratingRepostory.findByHotelId(hotelId);
        List<RatingDto> ratingDtos = new ArrayList<>();
        for (Rating rating : ratings) {
            ratingDtos.add(this.ratingToDto(rating));
        }
        return ratingDtos;
    }

    private Rating ratingDtoToRating(RatingDto ratingDto) {
        return this.modelMapper.map(ratingDto, Rating.class);
    }

    private RatingDto ratingToDto(Rating rating) {
        return this.modelMapper.map(rating, RatingDto.class);
    }
}
