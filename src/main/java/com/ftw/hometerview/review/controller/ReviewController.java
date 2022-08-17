package com.ftw.hometerview.review.controller;

import static com.ftw.hometerview.core.util.Constants.DEFAULT_KEYWORD;
import static com.ftw.hometerview.core.util.Constants.DEFAULT_PAGE_SIZE;

import com.ftw.hometerview.core.annotation.NonAuthorized;
import com.ftw.hometerview.core.domain.ResponseEntity;
import com.ftw.hometerview.review.controller.dto.ReviewDto;
import com.ftw.hometerview.review.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/review")
@RestController
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "홈 지역별 리뷰 리스트 조회")
    @NonAuthorized
    @GetMapping
    public ResponseEntity<List<ReviewDto.Meta>> getMainReviews(
        @RequestParam(required = false, defaultValue = DEFAULT_KEYWORD) String buildingId,
        @RequestParam(required = false, defaultValue = DEFAULT_KEYWORD) String cityId,
        @PageableDefault(size = DEFAULT_PAGE_SIZE) Pageable pageable) {
        var reviewList = this.reviewService.getHomeReviewList(buildingId, cityId, pageable);
        return ResponseEntity.successResponse(reviewList);
    }

    @Operation(summary = "건물 리뷰 리스트 조회")
    @GetMapping("/detail")
    public ResponseEntity<List<ReviewDto.Detail>> getBuildingReviews(
        @RequestParam String buildingId,
        @PageableDefault(size = DEFAULT_PAGE_SIZE) Pageable pageable) {
        var reviewList = this.reviewService.getBuildingReviewList(buildingId, pageable);
        return ResponseEntity.successResponse(reviewList);
    }

    @Operation(summary = "내가 쓴 리뷰 리스트 조회")
    @GetMapping("/my")
    public ResponseEntity<List<ReviewDto.Detail>> getMyReviews(
        @PageableDefault(size = DEFAULT_PAGE_SIZE) Pageable pageable) {
        var reviewList = this.reviewService.getMyReviewList(pageable);
        return ResponseEntity.successResponse(reviewList);
    }

    @Operation(summary = "리뷰 등록")
    @PostMapping
    public ResponseEntity<Void> registerReview(@Validated @RequestBody ReviewDto.Create req) {
        this.reviewService.registerReview(req);
        return ResponseEntity.successResponse();
    }

    @Operation(summary = "리뷰 수정")
    @PatchMapping
    public ResponseEntity<Void> modifyReview(@Validated @RequestBody ReviewDto.Modify req) {
        this.reviewService.modifyReviewList(req);
        return ResponseEntity.successResponse();
    }

    @Operation(summary = "리뷰 삭제")
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable String reviewId) {
        this.reviewService.deleteReview(reviewId);
        return ResponseEntity.successResponse();
    }

}
