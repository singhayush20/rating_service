package com.ayushsingh.rating_service.dto;

import lombok.Data;

@Data
public class RatingDto {
    private String ratingId;
    private String userId;
    private String hotelId;
    private int rating;
    private String feedback;

}
