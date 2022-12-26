package com.ayushsingh.rating_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ayushsingh.rating_service.constants.AppConstants;
import com.ayushsingh.rating_service.dto.RatingDto;
import com.ayushsingh.rating_service.exceptions.ApiResponse;
import com.ayushsingh.rating_service.exceptions.SuccessResponse;
import com.ayushsingh.rating_service.service.RatingService;

@RestController
@RequestMapping("/microservices/rating")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @GetMapping("/get-all-ratings")
    public ResponseEntity<?> getAllRatings() {
        List<RatingDto> ratings = this.ratingService.getAllRatings();
        SuccessResponse<List<RatingDto>> successResponse = new SuccessResponse<>(AppConstants.SUCCESS_CODE,
                AppConstants.SUCCESS_MESSAGE, ratings);
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    @GetMapping("/get-all-ratings-by-user")
    public ResponseEntity<?> getAllRatingsByUser(@RequestParam(name = "userId") String userId) {
        List<RatingDto> ratings = this.ratingService.findAllByUserId(userId);
        SuccessResponse<List<RatingDto>> successResponse = new SuccessResponse<>(AppConstants.SUCCESS_CODE,
                AppConstants.SUCCESS_MESSAGE, ratings);
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    @GetMapping("/get-all-ratings-by-hotel")
    public ResponseEntity<?> getAllRatings(@RequestParam(name = "hotelId") String hotelId) {
        List<RatingDto> ratings = this.ratingService.findAllByHotelId(hotelId);
        SuccessResponse<List<RatingDto>> successResponse = new SuccessResponse<>(AppConstants.SUCCESS_CODE,
                AppConstants.SUCCESS_MESSAGE, ratings);
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    @PostMapping("/save-rating")
    public ResponseEntity<?> saveRating(@RequestBody RatingDto ratingDto) {
        RatingDto rating = this.ratingService.saveRating(ratingDto);
        SuccessResponse<RatingDto> successResponse = new SuccessResponse<>(AppConstants.SUCCESS_CODE,
                AppConstants.SUCCESS_MESSAGE, rating);
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete-rating")
    public ResponseEntity<?> deleteRating(@RequestParam(name = "ratingId") String ratingId) {
        this.ratingService.deleteRating(ratingId);
        ApiResponse apiResponse=new ApiResponse(AppConstants.SUCCESS_CODE,"Rating deleted successfully",AppConstants.SUCCESS_MESSAGE);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("update-rating")
    public ResponseEntity<?> updateRating(@RequestBody RatingDto ratingDto) {
        RatingDto rating = this.ratingService.updateRating(ratingDto);
        SuccessResponse<RatingDto> successResponse = new SuccessResponse<>(AppConstants.SUCCESS_CODE,
                AppConstants.SUCCESS_MESSAGE, rating);
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }
}
