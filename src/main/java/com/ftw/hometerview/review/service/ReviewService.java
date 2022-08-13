package com.ftw.hometerview.review.service;

import com.ftw.hometerview.review.controller.dto.ReviewDto;

public interface ReviewService {

    void registerReview(ReviewDto.Create request);

}
