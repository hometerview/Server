package com.ftw.hometerview.review.service;

import static com.ftw.hometerview.core.interceptor.AuthUtil.getCurrentMemberId;

import com.ftw.hometerview.core.domain.ResponseType;
import com.ftw.hometerview.core.exception.BadRequestException;
import com.ftw.hometerview.core.exception.NotFoundException;
import com.ftw.hometerview.review.controller.dto.ReviewDto;
import com.ftw.hometerview.review.controller.dto.ReviewDto.Create;
import com.ftw.hometerview.review.controller.dto.ReviewDto.Detail;
import com.ftw.hometerview.review.controller.dto.ReviewDto.Meta;
import com.ftw.hometerview.review.controller.dto.ReviewDto.Modify;
import com.ftw.hometerview.review.domain.Review;
import com.ftw.hometerview.review.repository.ReviewRepository;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReviewService {


    private final ReviewRepository reviewRepository;

    public List<Meta> getHomeReviewList(String companyId, Pageable pageable, List<String> buildingIds) {
        List<Review> reviews = this.reviewRepository.getReviewByCompanyId(companyId, pageable);
        return reviews.stream()
            .filter(review -> buildingIds.contains(review.getBuildingId()))
            .map(Review::toMeta)
            .toList();
    }

    public List<Detail> getBuildingReviewList(String buildingId, Pageable pageable) {
        List<Review> reviews = this.reviewRepository.getReviewByBuildingId(buildingId, pageable);
        List<ReviewDto.Detail> response = reviews.stream().map(Review::toDetail).toList();
        return response;
    }

    public List<Detail> getMyReviewList(Pageable pageable) {
        List<Review> reviews = this.reviewRepository.getReviewByMemberId(getCurrentMemberId(),
            pageable);
        List<ReviewDto.Detail> response = reviews.stream().map(Review::toDetail).toList();
        return response;
    }

    public void registerReview(Create req) {
        Review review = req.toReview();
        setCertification(review);
        this.reviewRepository.save(review);
    }

    public void modifyReview(Modify req) {
        Review review = getReviewWithAuthCheck(req.getReviewId());
        review.modify(req);
        setCertification(review);
        this.reviewRepository.save(review);
    }

    private void setCertification(Review review) {
        // TODO:: member service, feign... param: getCurrentMemberId, response: String
        List<String> cert = Collections.emptyList();
        review.setCertification(cert);
    }

    public void deleteReview(String reviewId) {
        Review review = getReviewWithAuthCheck(reviewId);
        this.reviewRepository.delete(review);
    }

    private Review getReviewWithAuthCheck(String reviewId) {
        Review review = this.reviewRepository.findById(reviewId)
            .orElseThrow(() -> new NotFoundException(
                ResponseType.REVIEW_NOT_EXIST));
        if (!review.getMemberId().equals(getCurrentMemberId())) {
            throw new BadRequestException(ResponseType.REVIEW_NOT_AUTHOR);
        }
        return review;
    }

}
