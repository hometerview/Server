package com.ftw.hometerview.review.service;

import com.ftw.hometerview.review.controller.dto.ReviewDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface ReviewService {

    List<ReviewDto.Meta> getHomeReviewList(String buildingId, String cityId, Pageable pageable);

    List<ReviewDto.Detail> getBuildingReviewList(String buildingId, Pageable pageable);

    List<ReviewDto.Detail> getMyReviewList(Pageable pageable);

    void registerReview(ReviewDto.Create req);

    void modifyReview(ReviewDto.Modify req);

    void deleteReview(String reviewId);

}
