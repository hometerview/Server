package com.ftw.hometerview.review.service;

import com.ftw.hometerview.place.service.BuildingService;
import com.ftw.hometerview.review.controller.dto.ReviewDto.Create;
import com.ftw.hometerview.review.controller.dto.ReviewDto.Detail;
import com.ftw.hometerview.review.controller.dto.ReviewDto.Meta;
import com.ftw.hometerview.review.controller.dto.ReviewDto.Modify;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReviewServiceFacade {

    private final ReviewService reviewService;
    private final BuildingService buildingService;

    public List<Meta> getHomeReviewList(String companyId, String cityId, Pageable pageable) {
        List<String> buildingIds = this.buildingService.getBuildingsByCity(cityId);
        return this.reviewService.getHomeReviewList(companyId, pageable, buildingIds);
    }

    public List<Detail> getBuildingReviewList(String buildingId, Pageable pageable) {
        return this.reviewService.getBuildingReviewList(buildingId, pageable);
    }

    public List<Detail> getMyReviewList(Pageable pageable) {
        return this.reviewService.getMyReviewList(pageable);
    }

    public void registerReview(Create req) {
        this.reviewService.registerReview(req);
    }

    public void modifyReview(Modify req) {
        this.reviewService.modifyReview(req);
    }

    public void deleteReview(String reviewId) {
        this.reviewService.deleteReview(reviewId);
    }
}
