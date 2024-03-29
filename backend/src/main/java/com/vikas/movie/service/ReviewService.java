package com.vikas.movie.service;


import com.vikas.movie.model.Movie;
import com.vikas.movie.model.Reviews;
import com.vikas.movie.reposotory.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReviewService {


    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MongoTemplate mongoTemplate;
    public Reviews createReview(String reviewBody, String imdbId) {
        Reviews review = reviewRepository.insert(new Reviews(reviewBody, LocalDateTime.now(), LocalDateTime.now()));

        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviews").value(review))
                .first();


        return review;
    }
}
