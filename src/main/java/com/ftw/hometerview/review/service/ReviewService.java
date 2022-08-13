package com.ftw.hometerview.review.service;

import com.ftw.hometerview.review.controller.dto.ReviewDto;
import java.util.List;

public interface ReviewService {

    List<ReviewDto.Meta> getHomeReviewList(String buildingId, String cityId);

    List<ReviewDto.Detail> getBuildingReviewList(String buildingId);

    List<ReviewDto.Detail> getMyReviewList();

    void registerReview(ReviewDto.Create request);

    void modifyReviewList(ReviewDto.Modify req);

    void deleteReview(String reviewId);

}
